package com.lin.baselib.util;

import android.util.Log;

import com.lin.baselib.config.DebugConfig;


/**
 * @author: linjunqin
 * @function: 增加日志的调试开关 DEBUG
 * @date: 18/9/2018
 */
public class LogUtils {



    public static void v(String tag, String msg) {
        if (DebugConfig.IS_DEBUG) {
            Log.v(tag, checkMsg(msg));
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (DebugConfig.IS_DEBUG) {
            Log.v(tag, checkMsg(msg), tr);
        }
    }

    public static void d(String tag, String msg) {
        if (DebugConfig.IS_DEBUG) {
            Log.d(tag, checkMsg(msg));
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (DebugConfig.IS_DEBUG) {
            Log.d(tag, checkMsg(msg), tr);
        }
    }

    public static void i(String tag, String msg) {
        if (DebugConfig.IS_DEBUG) {
            Log.i(tag, checkMsg(msg));
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (DebugConfig.IS_DEBUG) {
            Log.i(tag, checkMsg(msg), tr);
        }
    }

    public static void w(String tag, String msg) {
        if (DebugConfig.IS_DEBUG) {
            Log.w(tag, checkMsg(msg));
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (DebugConfig.IS_DEBUG) {
            Log.w(tag, checkMsg(msg), tr);
        }
    }

    public static void w(String tag, Throwable tr) {
        if (DebugConfig.IS_DEBUG) {
            Log.w(tag, tr);
        }
    }

    public static void e(String tag, String msg) {
        if (DebugConfig.IS_DEBUG) {
            Log.e(tag, checkMsg(msg));
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (DebugConfig.IS_DEBUG) {
            Log.e(tag, checkMsg(msg), tr);
        }
    }

    private static String checkMsg(String msg) {
        return msg == null ? "null" : msg;
    }
}
