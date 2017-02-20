package com.wulinpeng.daiylreader.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午9:09
 * @description:
 */
public class TimeUtil {

    /**
     * 返回距离现在的时间间隔
     * 时间格式:yyyy-MM-ddTHH:mm:ss.SSSZ
     * @param formatTime
     * @return
     */
    public static String getTimeInterval(String formatTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String time = formatTime.replace("T", " ").replace("Z", "");
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long seconds = (System.currentTimeMillis() - date.getTime()) / 1000;
        if (seconds < 60) {
            return "刚刚";
        }
        seconds /= 60;
        if (seconds < 60) {
            return seconds + "分钟前";
        }
        seconds /= 60;
        if (seconds < 24) {
            return seconds + "小时前";
        }
        seconds /= 24;
        if (seconds < 30) {
            return seconds + "小时前";
        }
        seconds /= 30;
        if (seconds < 12) {
            return seconds + "月前";
        }
        seconds /= 12;
        return seconds + "年前";
    }
}
