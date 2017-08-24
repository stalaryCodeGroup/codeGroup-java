package com.stalary.codeGroup.util;

import org.apache.shiro.util.ThreadContext;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Peter on 2017-03-02.
 */
public class WebUtils {

    public static Integer getLoginUserId() {
        return Integer.valueOf(ThreadContext.get(Constant.USERID).toString());
    }

    public static Boolean checkisNotFitUserType(Integer userType) {
        return !(Integer.valueOf(ThreadContext.get(Constant.USERTYPE).toString()) == userType);
    }

    public static String getOpenId() {
        return null == ThreadContext.get(Constant.OPEN_ID) ? "" : ThreadContext.get(Constant.OPEN_ID).toString();
    }

    public static String getWeChatWebAuthAccessToken() {
        return null == ThreadContext.get(Constant.WECHAT_WEBAUTH_ACCESSTOKEN) ? "" : ThreadContext.get(Constant.WECHAT_WEBAUTH_ACCESSTOKEN).toString();
    }

    public static long getWeChatWebAuthAccessTokenTimeStamp() {
        return null == ThreadContext.get(Constant.WECHAT_WEBAUTH_ACCESSTOKEN_TIMESTAMP) ? 0 : (long)ThreadContext.get(Constant.WECHAT_WEBAUTH_ACCESSTOKEN_TIMESTAMP);
    }

    public static String getWeChatWebAuthRefreshToken() {
        return null == ThreadContext.get(Constant.WECHAT_WEBAUTH_REFRESHTOKEN) ? "" : ThreadContext.get(Constant.WECHAT_WEBAUTH_REFRESHTOKEN).toString();
    }


    public static String getIpAddress(HttpServletRequest request){

        String ipAddress = request.getHeader("x-forwarded-for");

        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknow".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();

            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡获取本机配置的IP地址
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inetAddress.getHostAddress();
            }
        }

        //对于通过多个代理的情况，第一个IP为客户端真实的IP地址，多个IP按照','分割
        if(null != ipAddress && ipAddress.length() > 15){
            //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",") > 0){
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }

        return ipAddress;
    }
}
