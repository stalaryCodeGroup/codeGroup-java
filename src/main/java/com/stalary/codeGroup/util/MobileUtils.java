package com.stalary.codeGroup.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by BianXunye on 2017/5/3.
 */
public class MobileUtils {

    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
}
