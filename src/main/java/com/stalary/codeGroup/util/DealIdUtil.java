package com.stalary.codeGroup.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by BianXunye on 2017/4/24.
 */
public class DealIdUtil {
    // type 交易编号前缀字母
    // length 交易编号除字母外的长度
    public static String getDealId(String type, int length){
        int randomLength = length - 8;
        if(randomLength <= 0)
            return null;

        Date date = new Date();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        String strDate = dateformat.format(date);

        int max = (int)Math.pow(10,randomLength) - 1,  min = (int)Math.pow(10,randomLength - 1);

        Random random = new Random();
        int randomNum = random.nextInt(max)%(9*min) + min;

        return type + strDate + randomNum;
    }
}
