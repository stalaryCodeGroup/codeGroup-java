package com.stalary.codeGroup.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jinghongyu on 4/17/17.
 */
public class StringUtil {

    public static Boolean isEmpty(String paramStr){
        if (null == paramStr || paramStr.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    public static String getUuid(){
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);

    }

    public static String captureName(String name) {
//        name = name.substring(0, 1).toUpperCase() + name.substring(1);
//        return  name;
        char[] cs=name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }

    public static String getErrorInfoFromException(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return "\r\n" + sw.toString() + "\r\n".substring(0,4000);
        } catch (Exception e2) {
            return "bad getErrorInfoFromException";
        }
    }

    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    public static String getAliPayForm(String formStr,String codeSubject,String beforeCodeSubject){
        String[] formItems = formStr.split("&quot;");    String result = "";    for (int i = 0; i < formItems.length; i++){
            if (formItems[i].equals(codeSubject)){
                formItems[i] = beforeCodeSubject;        }
        }
        return String.join("&quot;",formItems);
    }



}
