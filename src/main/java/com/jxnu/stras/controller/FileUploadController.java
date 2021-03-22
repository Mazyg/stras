package com.jxnu.stras.controller;

import com.jxnu.stras.utils.OSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class FileUploadController {
    @Autowired
    OSSUtil ossUtil;


    @RequestMapping(value="/upload/img")
    @ResponseBody
    public Map<String,Object> uploadImg(HttpServletRequest request,  @RequestParam("img") MultipartFile file,@RequestParam("type") String type) throws Exception{
        int id=0;
        if(type != null){
            if("热点时事".equals(type) ){
                id = 1;
            }else if("榜样力量".equals(type)){
                id = 2;
            }else if("身边榜样".equals(type)){
                id = 3;
            }else if("新时代楷模".equals(type)){
                id = 4;
            }else if("全球战疫".equals(type)){
                id = 5;
            }else if("最美中国人".equals(type)){
                id = 6;
            }else if("最美中国景".equals(type)){
                id = 7;
            }else if("最美中国事".equals(type)){
                id = 8;
            }else  if("视频".equals(type)){
                id = 12;
            }else if("人物".equals(type)){
                id = 13;
            }else{
                id = 14;
            }
        }else {
            id = 15;
        }
        /*String imgDirectory = "D:\\img"+File.separator+id;*/
        String imgDirectory="/var/upload/file"+File.separator+id;
        if(!file.isEmpty()){
//            获取文件名
            String originalFilename =file.getOriginalFilename();
            String fileName=originalFilename.substring(originalFilename.lastIndexOf("\\")+1);
            int biginIndex=fileName.lastIndexOf(".");
            String extname="";
            if(biginIndex>0){
                extname=fileName.substring(fileName.lastIndexOf("."));
            }
            String destFilename="picture-"+ UUID.randomUUID().toString()+extname;
            String destFile=imgDirectory+File.separator+destFilename;
            File upload_directory=new File(imgDirectory+File.separator);
            if(!upload_directory.exists()){
                upload_directory.mkdirs();
            }
            File dest=new File(destFile);
            file.transferTo(dest);

            ossUtil.uploadFile("img/"+destFilename,dest,destFilename);
            Map<String,Object> ret=new HashMap<>();
            ret.put("location","https://mazyg.oss-cn-shanghai.aliyuncs.com/img/"+destFilename);
            return ret;

        }
        else {
            Map<String,Object> ret=new HashMap<>();
            ret.put("location",null);
            return ret;
        }
    }
    @RequestMapping(value = "/upload/movie")
    @ResponseBody
    public Map<String,Object> uploadMovie(HttpServletRequest request, @RequestParam("movie") MultipartFile file) throws Exception{
      /* String movieDirectory = "D:\\movies"+File.separator;*/
        String movieDirectory = "/var/upload/file"+File.separator;
        if(!file.isEmpty()){
//            获取文件名
            String originalFilename =file.getOriginalFilename();
            String fileName=originalFilename.substring(originalFilename.lastIndexOf("\\")+1);
            int biginIndex=fileName.lastIndexOf(".");
            String extname="";
            if(biginIndex>0){
                extname=fileName.substring(fileName.lastIndexOf("."));
            }
            String destFilename="movie-"+ UUID.randomUUID().toString()+extname;
            String destFile=movieDirectory+File.separator+destFilename;
            File upload_directory=new File(movieDirectory);
            if(!upload_directory.exists()){
                upload_directory.mkdirs();
            }
            File dest=new File(destFile);
            file.transferTo(dest);
            ossUtil.uploadFile("movies/"+destFilename,dest,destFilename);
            Map<String,Object> ret=new HashMap<>();
            ret.put("location","https://mazyg.oss-cn-shanghai.aliyuncs.com/movies/"+destFilename);
            return ret;

        }
        else {
            Map<String,Object> ret=new HashMap<>();
            ret.put("location",null);
            return ret;
        }

    }

    @RequestMapping(value = "/upload/file")
    @ResponseBody
    public Map<String,Object> uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws Exception{
       /* String movieDirectory = "D:\\file"+File.separator;*/
        String movieDirectory = "/var/upload/file"+File.separator;
        if(!file.isEmpty()){
//            获取文件名
            String originalFilename =file.getOriginalFilename();
            String fileName=originalFilename.substring(originalFilename.lastIndexOf("\\")+1);
            int biginIndex=fileName.lastIndexOf(".");
            String extname="";
            if(biginIndex>0){
                extname=fileName.substring(fileName.lastIndexOf("."));
            }
            String destFilename="file-"+ UUID.randomUUID().toString()+extname;
            String destFile=movieDirectory+File.separator+destFilename;
            File upload_directory=new File(movieDirectory);
            if(!upload_directory.exists()){
                upload_directory.mkdirs();
            }
            File dest=new File(destFile);
            file.transferTo(dest);
            ossUtil.uploadFile("file/"+destFilename,dest,destFilename);
            Map<String,Object> ret=new HashMap<>();
            ret.put("location","https://mazyg.oss-cn-shanghai.aliyuncs.com/file/"+destFilename);
            return ret;

        }
        else {
            Map<String,Object> ret=new HashMap<>();
            ret.put("location",null);
            return ret;
        }

    }




}
