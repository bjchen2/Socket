package com.FileTrans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created By Cx On 2018/6/2 23:53
 */
public class Utils {

    public static boolean downLoad(File file, String url){
        //将指定文件下载到指定位置
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            System.out.println("下载中......");
            fis = new FileInputStream(file);
            fos = new FileOutputStream(url);
            byte[] buf = new byte[1024];
            while(fis.read(buf) != -1){
                //循环读入，直到文件末尾结束
                fos.write(buf);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("文件下载失败！！！");
            return false;
        }finally {
            try {
                if (fis != null)  fis.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("下载完成！");
        return true;
    }
}
