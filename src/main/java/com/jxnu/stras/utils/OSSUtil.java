package com.jxnu.stras.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhiYi Li
 * @version 1.0
 * @date 2020/12/8 13:44
 */
@Component
public class OSSUtil {

    private volatile static OSS ossClient;

    @Value("${endpoint}")
    private String endpoint;
    @Value("${accessKeyId}")
    private String accessKeyId;
    @Value("${accessKeySecret}")
    private String accessKeySecret;
    @Value("${bucketName}")
    private String bucketName;

    /**
     * 获得OSSClient实例
     *
     * @return OSS
     */
    public OSS getOssClient() {

        if (ossClient == null) {
            synchronized (OSSUtil.class) {
                if (ossClient == null) {
                    ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
                }
            }
        }
        return ossClient;
    }

    /**
     * 关闭OSS实例
     */
    public void closeOSSClient() {
        if (ossClient != null) {
            ossClient.shutdown();
        }
    }

    /**
     * 上传大文件,使用分片上传，实现分片上传以及断点续传的功能
     * 注：objectName需要指定包含文件后缀在内的完整路径，例如abc/abc/moyu.jpg
     * 最多支持10000块分片,通过设置分片大小来控制上传总大小限制，目前不超过200000M
     * 客户端最大上传48.8TB
     * 每片大小最小100k
     * objectName中已经包含了文件格式
     *
     * @param objectName 你希望放在云上的路径
     * @param file       要上传的文件
     * @param fileName 下载时的文件名
     * @return 文件的在线url
     */
    public void uploadFile(String objectName, File file,String fileName) throws IOException {
        OSS ossClient = getOssClient();
        if (ossClient == null) {
            throw new NullPointerException("oss客户端创建失败");
        }

        InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, objectName);

        // 如果需要在初始化分片时设置文件存储类型，请参考以下示例代码。
         ObjectMetadata metadata = new ObjectMetadata();
        //在这里设置下载时的文件名称
        //获取文件格式
        String fileType = objectName.split("\\.")[1];
        fileName = fileName + "." +fileType;
        System.out.println(fileName);
        metadata.setContentDisposition("attachment; filename=\""+ URLEncoder.encode(fileName,"UTF-8") +"\"");
        request.setObjectMetadata(metadata);
        //初始化分片
        InitiateMultipartUploadResult result = ossClient.initiateMultipartUpload(request);
        // 返回uploadId，它是分片上传事件的唯一标识，您可以根据这个uploadId发起相关的操作，如取消分片上传、查询分片上传等。
        String uploadId = result.getUploadId();

        // partETags是PartETag的集合。PartETag由分片的ETag和分片号组成。
        List<PartETag> partETags = new ArrayList<PartETag>();

        //设置每个分片的大小
        final long partSize = 20 * 1024 * 1024L;   // 10MB
        //计算有多少分片
        long fileLength = file.length();
        int partCount = (int) (fileLength / partSize);
        if (fileLength % partSize != 0) {
            partCount++;
        }
        //遍历分片上传
        for (int i = 0; i < partCount; i++) {
            long startPos = i * partSize;
            long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
            InputStream instream = new FileInputStream(file);
            //跳过已经上传的分片
            instream.skip(startPos);

            UploadPartRequest uploadPartRequest = new UploadPartRequest();
            uploadPartRequest.setBucketName(bucketName);
            uploadPartRequest.setKey(objectName);
            uploadPartRequest.setUploadId(uploadId);
            uploadPartRequest.setInputStream(instream);

            // 设置分片大小。除了最后一个分片没有大小限制，其他的分片最小为100 KB。
            uploadPartRequest.setPartSize(curPartSize);
            // 设置分片号。每一个上传的分片都有一个分片号，取值范围是1~10000，如果超出这个范围，OSS将返回InvalidArgument的错误码。
            uploadPartRequest.setPartNumber(i + 1);
            // 每个分片不需要按顺序上传，甚至可以在不同客户端上传，OSS会按照分片号排序组成完整的文件。
            UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
            // 每次上传分片之后，OSS的返回结果包含PartETag。PartETag将被保存在partETags中。
            partETags.add(uploadPartResult.getPartETag());
        }

        // 创建CompleteMultipartUploadRequest对象。
        // 在执行完成分片上传操作时，需要提供所有有效的partETags。OSS收到提交的partETags后，会逐一验证每个分片的有效性。当所有的数据分片验证通过后，OSS将把这些分片组合成一个完整的文件。
        CompleteMultipartUploadRequest completeMultipartUploadRequest =
                new CompleteMultipartUploadRequest(bucketName, objectName, uploadId, partETags);

        // 如果需要在完成文件上传的同时设置文件访问权限，请参考以下示例代码。
        // completeMultipartUploadRequest.setObjectACL(CannedAccessControlList.PublicRead);

        // 完成上传。
        CompleteMultipartUploadResult completeMultipartUploadResult = ossClient.completeMultipartUpload(completeMultipartUploadRequest);
        //上传后删除临时文件
        FileUtils.deleteFile(file.getAbsolutePath());
    }

    /**
     * 判断文件是否存在
     *
     * @param objectName objectName
     * @return boolean
     */
    public boolean isExist(String objectName) {
        OSS ossClient = getOssClient();
        if (ossClient == null) {
            throw new NullPointerException("oss客户端创建失败");
        }
        return ossClient.doesObjectExist(bucketName, objectName);
    }

    /**
     * 删除单个文件
     *
     * @param objectName objectName
     * @return boolean
     */
    public boolean deleteFile(String objectName) {
        OSS ossClient = getOssClient();
        if (ossClient == null) {
            throw new NullPointerException("oss客户端创建失败");
        }
        ossClient.deleteObject(bucketName, objectName);
        if (isExist(objectName)) {
            System.err.println("文件删除失败！:" + objectName);
            return false;
        } else {
            return true;
        }
    }
}
