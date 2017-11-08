package com.onlyisssilence.muya;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;

import java.io.FileNotFoundException;

public class App {



    public static void main(String[] args) {

        //Object对象
        String ossFileName = null;
		/*说明：
		 * 1、要满足命名规范
		 * 2、可以制定到bucket的文件夹
		 * 3、例如A/B/C/女帝.jpg则是将文件上传到bucket的文件夹A下面的文件夹B下面的文件夹C中，命名为女帝.jpg
		 * 4、一般情况下上传文件到OSS命名仍然以原文件的名字命名
		 * */

        String uploadFilePath = "E:\\123.zip";
        String downloadFilePath = "E:\\down\\321.zip";


        OssService ossService = OssService.newInstance();
        ossService.deleteFile(ossFileName);


        try {
            System.out.println("正在上传...");

            long startTime = System.currentTimeMillis();

            ossService.uploadFile( "123.zip", uploadFilePath);
            long endTime = System.currentTimeMillis();
            System.out.println("上传花费时间约：" + (endTime - startTime) + " ms");

            System.out.println("正在下载...");
            long startTime_d = System.currentTimeMillis();
            ossService.downloadFile("123.zip", downloadFilePath);
            long endTime_d = System.currentTimeMillis();
            System.out.println("下载花费时间约：" + (endTime_d - startTime_d) + " ms");

        } catch (OSSException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("OK");
    }






}
