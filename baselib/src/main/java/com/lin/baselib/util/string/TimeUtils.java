package com.lin.baselib.util.string;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by linjunqin on 2018/9/24.
 */

public class TimeUtils {

    private TimeUtils() {
        throw new UnsupportedOperationException("TimeUtils Error");
    }

    /**
     * 未加单引号的字母 'A' 到 'Z' 和 'a' 到 'z' 被解释为模式字母，用来表示日期或时间字符串元素。
     * 所有其他字符均不解释；只是在格式化时将它们简单复制到输出字符串，或者在分析时与输入字符串进行匹配。
     * 字母	日期或时间元素	表示	示例
     * G	    Era 标志符	Text	AD 公元
     * y	    年	Year	1996; 96
     * M	    年中的月份	Month	July; Jul; 07
     * w	    年中的周数	Number	27
     * W	    月份中的周数	Number	2
     * D	    年中的天数	Number	189
     * d	    月份中的天数	Number	10
     * F	    月份中的星期	Number	2
     * E	    星期中的天数	Text	Tuesday; Tue
     * a	    Am/pm 标记	Text	PM
     * H	    一天中的小时数（0-23）	Number	0
     * k	    一天中的小时数（1-24）	Number	24
     * K	    am/pm 中的小时数（0-11）	Number	0
     * h	    am/pm 中的小时数（1-12）	Number	12
     * m	    小时中的分钟数	Number	30
     * s	    分钟中的秒数	Number	55
     * S	    毫秒数	Number	978
     * z	    时区	General time zone	Pacific Standard Time; PST; GMT-08:00
     * Z	    时区	RFC 822 time zone	-0800
     * <p>
     * HH:mm    15:44
     * h:mm a    3:44 下午
     * HH:mm z    15:44 CST
     * HH:mm Z    15:44 +0800
     * HH:mm zzzz    15:44 中国标准时间
     * HH:mm:ss    15:44:40
     * yyyy-MM-dd    2016-08-12
     * yyyy-MM-dd HH:mm    2016-08-12 15:44
     * yyyy-MM-dd HH:mm:ss    2016-08-12 15:44:40
     * yyyy-MM-dd HH:mm:ss zzzz    2016-08-12 15:44:40 中国标准时间
     * EEEE yyyy-MM-dd HH:mm:ss zzzz    星期五 2016-08-12 15:44:40 中国标准时间
     * yyyy-MM-dd HH:mm:ss.SSSZ    2016-08-12 15:44:40.461+0800
     * yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
     * yyyy.MM.dd G 'at' HH:mm:ss z    2016.08.12 公元 at 15:44:40 CST
     * K:mm a    3:44 下午
     * EEE, MMM d, ''yy    星期五, 八月 12, '16
     * hh 'o''clock' a, zzzz    03 o'clock 下午, 中国标准时间
     * yyyyy.MMMMM.dd GGG hh:mm aaa    02016.八月.12 公元 03:44 下午
     * EEE, d MMM yyyy HH:mm:ss Z    星期五, 12 八月 2016 15:44:40 +0800
     * yyMMddHHmmssZ    160812154440+0800
     * yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
     * EEEE 'DATE('yyyy-MM-dd')' 'TIME('HH:mm:ss')' zzzz    星期五 DATE(2016-08-12) TIME(15:44:40) 中国标准时间
     */
//
    public static Long transfer(String time, String format) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        try {
            date = sdf.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date == null ? System.currentTimeMillis() : date.getTime();
    }

    public static String transfer(long time, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return dateFormat.format(new Date(time));
    }

    public static long getDayStartTime() {
        long day = 1000 * 60 * 60 * 24;
        return (System.currentTimeMillis() / day) * day - 1000 * 60 * 60 * 8;
    }

    public static final SimpleDateFormat DEFAULT_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    public static final SimpleDateFormat CHAT_SDF = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
    public static final SimpleDateFormat TIME_SDF = new SimpleDateFormat("HH:mm", Locale.getDefault());

    //yyyy-MM-dd HH:mm:ss
    public static String milliseconds2String(long milliseconds) {
        return milliseconds2String(milliseconds, DEFAULT_SDF);
    }

    public static String milliseconds2String(long milliseconds, SimpleDateFormat format) {
        return format.format(new Date(milliseconds));
    }

    /**
     * 将时间字符串转为时间戳
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return 毫秒时间戳
     */
    public static long string2Milliseconds(String time) {
        return string2Milliseconds(time, DEFAULT_SDF);
    }

    /**
     * 将时间字符串转为时间戳
     * <p>格式为用户自定义</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 毫秒时间戳
     */
    public static long string2Milliseconds(String time, SimpleDateFormat format) {
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 将时间字符串转为Date类型
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return Date类型
     */
    public static Date string2Date(String time) {
        return string2Date(time, DEFAULT_SDF);
    }

    /**
     * 将时间字符串转为Date类型
     * <p>格式为用户自定义</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return Date类型
     */
    public static Date string2Date(String time, SimpleDateFormat format) {
        return new Date(string2Milliseconds(time, format));
    }

    /**
     * 将Date类型转为时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time Date类型时间
     * @return 时间字符串
     */
    public static String date2String(Date time) {
        return date2String(time, DEFAULT_SDF);
    }

    /**
     * 将Date类型转为时间字符串
     * <p>格式为用户自定义</p>
     *
     * @param time   Date类型时间
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String date2String(Date time, SimpleDateFormat format) {
        return format.format(time);
    }

    /**
     * 将Date类型转为时间戳
     *
     * @param time Date类型时间
     * @return 毫秒时间戳
     */
    public static long date2Milliseconds(Date time) {
        return time.getTime();
    }

    /**
     * 将时间戳转为Date类型
     *
     * @param milliseconds 毫秒时间戳
     * @return Date类型时间
     */
    public static Date milliseconds2Date(long milliseconds) {
        return new Date(milliseconds);
    }

    /**
     * 毫秒与毫秒的倍数
     */
    public static final int MSEC = 1;
    /**
     * 秒与毫秒的倍数
     */
    public static final int SEC = 1000;
    /**
     * 分与毫秒的倍数
     */
    public static final int MIN = 60000;
    /**
     * 时与毫秒的倍数
     */
    public static final int HOUR = 3600000;
    /**
     * 天与毫秒的倍数
     */
    public static final int DAY = 86400000;

    /**
     * 毫秒时间戳单位转换（单位：unit）
     *
     * @param milliseconds 毫秒时间戳
     * @param unit         <ul>
     *                     <li>MSEC:毫秒</li>
     *                     <li>SEC :秒</li>
     *                     <li>MIN :分</li>
     *                     <li>HOUR:小时</li>
     *                     <li>DAY :天</li>
     *                     </ul>
     * @return unit时间戳
     */
    private static long milliseconds2Unit(long milliseconds, int unit) {
        switch (unit) {
            case MSEC:
            case SEC:
            case MIN:
            case HOUR:
            case DAY:
                return milliseconds / unit;
        }
        return -1;
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>time1和time2格式都为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time0 时间字符串1
     * @param time1 时间字符串2
     * @param unit  <ul>
     *              <li>MSEC:毫秒</li>
     *              <li>SEC :秒</li>
     *              <li>MIN :分</li>
     *              <li>HOUR:小时</li>
     *              <li>DAY :天</li>
     *              </ul>
     * @return unit时间戳
     */
    public static long getIntervalTime(String time0, String time1, int unit) {
        return getIntervalTime(time0, time1, unit, DEFAULT_SDF);
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>time1和time2格式都为format</p>
     *
     * @param time0  时间字符串1
     * @param time1  时间字符串2
     * @param unit   <ul>
     *               <li>MSEC:毫秒</li>
     *               <li>SEC :秒</li>
     *               <li>MIN :分</li>
     *               <li>HOUR:小时</li>
     *               <li>DAY :天</li>
     *               </ul>
     * @param format 时间格式
     * @return unit时间戳
     */
    public static long getIntervalTime(String time0, String time1, int unit, SimpleDateFormat format) {
        return Math.abs(milliseconds2Unit(string2Milliseconds(time0, format)
                - string2Milliseconds(time1, format), unit));
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>time1和time2都为Date类型</p>
     *
     * @param time0 Date类型时间1
     * @param time1 Date类型时间2
     * @param unit  <ul>
     *              <li>MSEC:毫秒</li>
     *              <li>SEC :秒</li>
     *              <li>MIN :分</li>
     *              <li>HOUR:小时</li>
     *              <li>DAY :天</li>
     *              </ul>
     * @return unit时间戳
     */
    public static long getIntervalTime(Date time0, Date time1, int unit) {
        return Math.abs(milliseconds2Unit(date2Milliseconds(time1)
                - date2Milliseconds(time0), unit));
    }

    /**
     * 获取当前时间
     *
     * @return 毫秒时间戳
     */
    public static long getCurTimeMills() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @return 时间字符串
     */
    public static String getCurTimeString() {
        return date2String(new Date());
    }

    /**
     * 获取当前时间
     * <p>格式为用户自定义</p>
     *
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String getCurTimeString(SimpleDateFormat format) {
        return date2String(new Date(), format);
    }

    /**
     * 获取当前时间
     * <p>Date类型</p>
     *
     * @return Date类型时间
     */
    public static Date getCurTimeDate() {
        return new Date();
    }

    /**
     * 获取与当前时间的差（单位：unit）
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @param unit <ul>
     *             <li>MSEC:毫秒</li>
     *             <li>SEC :秒</li>
     *             <li>MIN :分</li>
     *             <li>HOUR:小时</li>
     *             <li>DAY :天</li>
     *             </ul>
     * @return unit时间戳
     */
    public static long getIntervalByNow(String time, int unit) {
        return getIntervalByNow(time, unit, DEFAULT_SDF);
    }

    /**
     * 获取与当前时间的差（单位：unit）
     * <p>time格式为format</p>
     *
     * @param time   时间字符串
     * @param unit   <ul>
     *               <li>MSEC:毫秒</li>
     *               <li>SEC :秒</li>
     *               <li>MIN :分</li>
     *               <li>HOUR:小时</li>
     *               <li>DAY :天</li>
     *               </ul>
     * @param format 时间格式
     * @return unit时间戳
     */
    public static long getIntervalByNow(String time, int unit, SimpleDateFormat format) {
        return getIntervalTime(getCurTimeString(), time, unit, format);
    }

    /**
     * 获取与当前时间的差（单位：unit）
     * <p>time为Date类型</p>
     *
     * @param time Date类型时间
     * @param unit <ul>
     *             <li>MSEC:毫秒</li>
     *             <li>SEC :秒</li>
     *             <li>MIN :分</li>
     *             <li>HOUR:小时</li>
     *             <li>DAY :天</li>
     *             </ul>
     * @return unit时间戳
     */
    public static long getIntervalByNow(Date time, int unit) {
        return getIntervalTime(getCurTimeDate(), time, unit);
    }

    /**
     * 判断闰年
     *
     * @param year 年份
     * @return true: 闰年<br>false: 平年
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    //获取信息发送时间与现在时间的差值
    public static long getDeltaTime(long recentTime) {
        return new Date().getTime() - recentTime;
    }

    //获取发布资讯与现状的时间差
    // yyyy-MM-dd HH:mm:ss
    public static String getTimeDifferent(String timeStr){
        long time=System.currentTimeMillis()-transfer(timeStr,"yyyy-MM-dd HH:mm:ss");
        time=time/1000;
        if ((time<60)){
            return time+"秒前";
        }else if((time=time/60)<60){
            return time+"分钟前";
        }else if ((time=time/60)<24){
            return time+"小时前";
        }else if ((time=time/24)<30 ){
            return time+"天前";
        }else if ((time=time/30)<12 ){
            return time+"个月前";
        }else{
            return time/12+"年前";
        }
    }

    //毫秒 --> HH:mm:ss
    public static String toHoursStr(long mills){
        StringBuilder builder=new StringBuilder();
        mills=mills/1000;
        long t=mills/60/60;
        if (t<10)builder.append(0);
        builder.append(t);
        builder.append(":");
        t=mills/60%60;
        if (t<10)builder.append(0);
        builder.append(t);
        builder.append(":");
        t=mills%60;
        if (t<10)builder.append(0);
        builder.append(t);
        return builder.toString();
    }

    public static boolean isToday(long time){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int day=calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTimeInMillis(time);
        return year==calendar.get(Calendar.YEAR)&&day==calendar.get(Calendar.DAY_OF_YEAR);
    }

    public static boolean isYesterday(long time){
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,-1);
        int year=calendar.get(Calendar.YEAR);
        int day=calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTimeInMillis(time);
        return year==calendar.get(Calendar.YEAR)&&day==calendar.get(Calendar.DAY_OF_YEAR);
    }

    public static boolean isSameDay(long oneTime,long otherTime){
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(oneTime);
        int year=calendar.get(Calendar.YEAR);
        int day=calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTimeInMillis(otherTime);
        return year==calendar.get(Calendar.YEAR)&&day==calendar.get(Calendar.DAY_OF_YEAR);
    }

    public static boolean isSameMonth(long oneTime,long otherTime){
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(oneTime);
        int mon=calendar.get(Calendar.MONTH);
        calendar.setTimeInMillis(otherTime);
        return mon==calendar.get(Calendar.MONTH);
    }
}
