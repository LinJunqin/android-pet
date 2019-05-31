package com.lin.baselib.util.string;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by linjunqin on 2018/9/24.
 */

public class DateFormat {
    private static SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    public static String format(Date date) {
        if (date == null) {
            return null;
        }
        sFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
        return sFormat.format(date);
    }

    public static String format(String pattern, Date date) {
        if (date == null) {
            return null;
        }
        sFormat.applyPattern(pattern);
        return sFormat.format(date);
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        sFormat.applyPattern("yyyy-MM-dd");
        return sFormat.format(date);
    }

    public static String formatTime(Date date) {
        if (date == null) {
            return null;
        }
        sFormat.applyPattern("HH:mm:ss");
        return sFormat.format(date);
    }

    public static Date parse(String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }
        sFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
        try {
            return sFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date parse(String pattern, String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }
        sFormat.applyPattern(pattern);
        try {
            return sFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date parseDate(String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }
        sFormat.applyPattern("yyyy-MM-dd");
        try {
            return sFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date parseTime(String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }
        sFormat.applyPattern("HH:mm:ss");
        try {
            return sFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
