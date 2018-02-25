package org.gdpu.ols.util;

import java.io.File;
import java.util.UUID;

public class FileUtil {


    public static String makeDirPath(String teacherId,String fileName,String path){
        //通过文件名来算出一级目录和二级目录
        int fileHashCode=fileName.hashCode();
        int dir1=fileHashCode & 0xf;
        int dir2=(fileHashCode & 0xf0) >> 4;

        String dir=path+"\\"+teacherId+"\\"+dir1+"\\"+dir2;

        //如果该目录不存在，就创建目录
        File file=new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }

        return dir;
    }

    public static String makeFileName(String fileName){
        return UUID.randomUUID().toString()+"_"+fileName;
    }
}
