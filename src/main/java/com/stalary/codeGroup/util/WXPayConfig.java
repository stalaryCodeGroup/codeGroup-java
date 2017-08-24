package com.stalary.codeGroup.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class WXPayConfig implements com.github.wxpay.sdk.WXPayConfig{

    private byte[] certData;
    public WXPayConfig() throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();
        /**
         getResource()方法会去classpath下找这个文件，获取到url resource, 得到这个资源后，调用url.getFile获取到 文件 的绝对路径
         */
        URL url = classLoader.getResource("apiclient_cert.p12");
        File file = new File(url.getFile());
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }


    /**
     * 获取 App ID
     *
     * @return App ID
     */
    public String getAppID(){
        return "wx0af20685ad131322";
    }


    /**
     * 获取 Mch ID
     *
     * @return Mch ID
     */
    public String getMchID(){
        return "1363620202";
    }


    /**
     * 获取 API 密钥
     *
     * @return API密钥
     */
    public String getKey(){
            return "Potato16GL2017paySettingEAFC3218";

    }


    /**
     * 获取商户证书内容
     *
     * @return 商户证书内容
     */
    public InputStream getCertStream(){
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    /**
     * HTTP(S) 连接超时时间，单位毫秒
     *
     * @return
     */
    public int getHttpConnectTimeoutMs() {
        return 6*1000;
    }

    /**
     * HTTP(S) 读数据超时时间，单位毫秒
     *
     * @return
     */
    public int getHttpReadTimeoutMs() {
        return 8*1000;
    }

//    /**
//     * 获取WXPayDomain, 用于多域名容灾自动切换
//     * @return
//     */
//    public IWXPayDomain getWXPayDomain(){
//
//        WXPayDomain wxPayDomain = new WXPayDomain();
//        wxPayDomain.getDomain(this);
//        return wxPayDomain;
//
//    }

    /**
     * 是否自动上报。
     * 若要关闭自动上报，子类中实现该函数返回 false 即可。
     *
     * @return
     */
    public boolean shouldAutoReport() {
        return true;
    }

    /**
     * 进行健康上报的线程的数量
     *
     * @return
     */
    public int getReportWorkerNum() {
        return 6;
    }


    /**
     * 健康上报缓存消息的最大数量。会有线程去独立上报
     * 粗略计算：加入一条消息200B，10000消息占用空间 2000 KB，约为2MB，可以接受
     *
     * @return
     */
    public int getReportQueueMaxSize() {
        return 10000;
    }

    /**
     * 批量上报，一次最多上报多个数据
     *
     * @return
     */
    public int getReportBatchSize() {
        return 10;
    }

}
