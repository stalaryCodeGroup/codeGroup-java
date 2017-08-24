package com.stalary.codeGroup.util;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

/**
 * @author Peter on 2017-03-19.
 */
public class NumUtils {

    /**
     * Round to certain number of decimals
     */
    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public static Double parseDouble(String str) {
        if (str == null || str.length() < 1) {
            //这里是返回null,还是0,自己修改
            return Double.valueOf(0);
        }
        return Double.valueOf(str);
    }

    /**
     * @param amount
     * @param defaultReturnStr 默认为空时对象返回的信息
     * @return
     */
    public static String doubleToStr(Double amount, String defaultReturnStr){
        if (null == amount){
            if (null == defaultReturnStr)
                return "0.00";
            else
                return defaultReturnStr;
        }else{
            return amount.toString();
        }

    }

    public static Integer parseInteger(String str) {
        if (str == null || str.length() < 1) {
            //这里是返回null,还是0,自己修改
            return Integer.valueOf(0);
        }
        return Integer.valueOf(str);
    }

    public static double sum(double[] value){
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < value.length; i++){
            sum = sum.add(new BigDecimal(value[i]));
        }
        return sum.doubleValue();
    }

    public static double sum(double v, double... values) {
        BigDecimal bigDecimal = new BigDecimal(v);
        for (double value : values) {
            bigDecimal = bigDecimal.add(new BigDecimal(value));
        }
        return bigDecimal.doubleValue();
    }

    public static int sum(int[] value){
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < value.length; i++){
            sum = sum.add(new BigDecimal(value[i]));
        }
        return sum.intValue();
    }

    public static double avg(double[] doubles){
        return new BigDecimal(NumUtils.sum(doubles)).divide(new BigDecimal(doubles.length), 10, ROUND_HALF_DOWN).doubleValue();
    }
}
