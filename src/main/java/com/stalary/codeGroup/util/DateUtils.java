package com.stalary.codeGroup.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Peter on 2017-03-14.
 */
public class DateUtils {

    static SimpleDateFormat defaultFormatter = new SimpleDateFormat("yyyy-MM-dd");

    static SimpleDateFormat generalFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static SimpleDateFormat monthFormatter = new SimpleDateFormat("yyyyMM");

    static SimpleDateFormat chineseFormatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

    static SimpleDateFormat chineseFormatterSimple = new SimpleDateFormat("yyyy年MM月dd日");

    static java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static Date stringToTime(String dateStr) {
        if (dateStr == null || dateStr.length() == 0) {
            return null;
        }
        Date date;
        try {
            date = generalFormatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return date;
    }

    public static Date stringToDate(String dateStr) {
        if (dateStr == null || dateStr.length() == 0) {
            return null;
        }
        return localDateToDate(LocalDate.parse(dateStr, formatter));
    }

    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


    public static String dateToString(Date date) {

        if (date == null) {
            return "";
        }

        return defaultFormatter.format(date);
    }

    public static String dateToChinese(Date date) {

        if (date == null) {
            return "";
        }

        return chineseFormatter.format(date);
    }

    public static String dateToChineseSimple(Date date) {

        if (date == null) {
            return "";
        }

        return chineseFormatterSimple.format(date);
    }

    public static String getThisMonth() {
        Date date = new Date();
        return monthFormatter.format(date);
    }


    public static int dayOfMonth(Date date) {

        return toLocalDate(date).getDayOfMonth();

    }

    public static LocalDate plusDays(LocalDate localDate) {
        return localDate.plusDays(1);
    }

    public static Date plusDays(Date date) {
        return toDate(toLocalDate(date).plusDays(1));
    }

    public static Date plusDays(Date date, int days) {
        return toDate(toLocalDate(date).plusDays(days));
    }

    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate toLocalDate(Date date) {

        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        }

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static String getSystemDateStamp() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timeStamp = sdf.format(date);
        return timeStamp;

    }

    public static String getSystemTimeSimple() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String timeStamp = sdf.format(date);
        return timeStamp;

    }


    public static Long getTimeDifference(String date1, String date2) throws ParseException {
        long beginTime = defaultFormatter.parse(date1).getTime();
        long endTime = defaultFormatter.parse(date2).getTime();
        return endTime - beginTime;
    }

    public static Long getTimeDifference(String date1) throws ParseException {
        long beginTime = defaultFormatter.parse(date1).getTime();
        long endTime = new Date().getTime();
        return endTime - beginTime;
    }

    public static String getDateFromTimeStampStr(String timeStampStr){
        if (StringUtil.isEmpty(timeStampStr))
            return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(timeStampStr);
        Date date = new Date(lt);
        return simpleDateFormat.format(date);
    }

    public static String getDateTimeFromTimeStampStr(String timeStampStr){
        if (StringUtil.isEmpty(timeStampStr))
            return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(timeStampStr);
        Date date = new Date(lt);
        return simpleDateFormat.format(date);

    }

    public static Date getDateByDateStr(String dataFormate, String dataStr) {

        if (StringUtil.isEmpty(dataFormate)) {
            dataFormate = "yyyy-MM-dd";
        }

        DateFormat sdf = new SimpleDateFormat(dataFormate);
        Date date = null;
        try {
            if (StringUtil.isNumeric(dataStr)) {
                long lt = new Long(dataStr);
                date = new Date(lt);
            } else {
                date = sdf.parse(dataStr);

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;

    }

    /**
     * 获得某个月最大天数
     *
     * @param year 年份
     * @param month 月份 (1-12)
     * @return 某个月最大天数
     */
    public static int getMaxDayByYearMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year - 1);
        calendar.set(Calendar.MONTH, month);
        return calendar.getActualMaximum(Calendar.DATE);
    }

    /**
     * 设置当前时区为中国，然后获取当前时间，转化为字符串
     */
    public static String getChinaTime() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Date date = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }
}
