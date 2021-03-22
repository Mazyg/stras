package com.jxnu.stras.utils;

import java.io.File;

/**
 * @author ZhiYi Li
 * @version 1.0
 * @date 2020/12/9 10:04
 */
public class FileUtils {
    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     */
    public static void deleteFile(String sPath) {
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
        }
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     */
    public static void deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return;
        }
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        assert files != null;
        if (files.length > 0) {
            for (File file : files) {
                //删除子文件
                if (file.isFile()) {
                    deleteFile(file.getAbsolutePath());
                } else {
                    deleteDirectory(file.getAbsolutePath());
                }
            }
        }

        //删除当前目录
        dirFile.delete();
    }
}
