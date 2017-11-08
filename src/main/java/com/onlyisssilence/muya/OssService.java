package com.onlyisssilence.muya;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/*
* ------------------------------------------------------------------
* Copyright © 2017 Hangzhou DtDream Technology Co.,Lt d. All rights reserved.
* ------------------------------------------------------------------
* 
* Created with IntelliJ IDEA.
* User: onlyisssilence
* Date: 2017/11/8
* Time: 19:21
* Description: 
*/
public class OssService {

    private static final String OSS_ENDPOINT = "xxx";
    private static final String ACCESS_ID = "xxx";
    private static final String ACCESS_KEY = "xxx";
    private static final String OSS_BUCKET_NAME = "xxx";
    private static OSSClient ossClient;
    public static OssService ossService;

    private OssService() {
    }

    public static OssService newInstance() {
        if (ossService == null) {
            ossService = new OssService();
            // 使用默认的OSS服务器地址创建OSSClient对象。
            ossClient = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY);
        }
        return ossService;
    }

    // 上传文件
    public void uploadFile(String key, String filename) throws OSSException, ClientException,
            FileNotFoundException {
        File file = new File(filename);
        file.getName();

        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(file.length());

        InputStream input = new FileInputStream(file);
        PutObjectResult rs = ossClient.putObject(OSS_BUCKET_NAME, key, input,
                objectMeta);
        System.out.println("上传成功：" + rs.getETag());
    }

    // 下载文件
    public void downloadFile(String key, String filename) throws OSSException, ClientException {
        ossClient.getObject(new GetObjectRequest(OSS_BUCKET_NAME, key), new File(
                filename));
    }
    // 删除文件
    public void deleteFile(String ossFileName) throws OSSException, ClientException {
        ossClient.deleteObject(OSS_BUCKET_NAME, ossFileName);

    }
}
