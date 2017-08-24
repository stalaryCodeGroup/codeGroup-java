package com.stalary.codeGroup.util;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.File;
import java.io.InputStream;

/**
 * Created by jinghongyu on 4/15/17.
 */
public class QiNiuUtil {
    private static String ACCESS_KEY = "yNjhOp7gnH4St9yP72OlwuQ6JUNQf49pxAuGWYFt";

    private static String SECRET_KEY = "-aQoyzV-9FUr7w5Cgtr5y9ZiVg0SFMSZBYgxdDyq";

    private static String Bucket_Name = "yangshang";

    private static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    public static String getUpToken() {
        return auth.uploadToken(Bucket_Name);
    }

    /**
     * 获取私有资源的url
     * @param url
     * @return
     */
    public static String getDownloadUrl(String url) {
        return auth.privateDownloadUrl(url,1800);
    }

//    public static String getUpToken() {
//        return getUpToken(null);
//    }

    /**
     * 覆盖上传
     *
     * @param key  文件名
     * @param file 文件
     */
    public static void upload(String key, File file) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);

        try {
            //调用put方法上传
            Response res = uploadManager.put(file, key, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }

    public static void upload(String key, InputStream inputStream) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);

        try {
            //调用put方法上传
            Response res = uploadManager.put(inputStream, key, getUpToken(),null,null);
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }
}
