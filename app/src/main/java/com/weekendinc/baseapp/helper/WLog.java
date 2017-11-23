package com.weekendinc.baseapp.helper;

import android.util.Log;

import com.weekendinc.baseapp.BuildConfig;

/**
 * Created by Fadhlan on 8/16/17.
 */

public final class WLog {

    public static int w(String tag, String message) {
        return w(tag, message, null);
    }

    public static int w(String tag, String message, Throwable e) {
        if (BuildConfig.DEBUG)
            return Log.w(tag, message, e);
        return -1;
    }

    public static int e(String tag, String message) {
        if (BuildConfig.DEBUG)
            return e(tag, message, null);
        return -1;
    }

    public static int e(String tag, String message, Throwable throwable) {
        if (BuildConfig.DEBUG)
            return Log.e(tag, message, throwable);
        return -1;
    }

    public static int wtf(String tag, String message, Throwable throwable) {
        if (BuildConfig.DEBUG)
            return Log.wtf(tag, message, throwable);
        return -1;
    }

    public static int v(String tag, String message) {
        if (BuildConfig.DEBUG)
            return Log.v(tag, message);
        return -1;
    }

    public static int d(String tag, String message) {
        if (BuildConfig.DEBUG)
            return Log.d(tag, message);
        return -1;
    }
}
