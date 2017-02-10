package com.wmm1995.smartbutler.utils;

import android.util.Log;

/**
 * Created by Administrator on 2017/2/10.
 */

public class L {

    //开关
    public static final boolean DEBUG = true;
    //TAG
    public static final String TAG = "Smartbutler";

    //5个等级 D I W E
    public static void d(String text) {
        if (DEBUG) {
            Log.d(TAG, text);
        }
    }

    public static void i(String text) {
        if (DEBUG) {
            Log.i(TAG, text);
        }
    }

    public static void w(String text) {
        if (DEBUG) {
            Log.w(TAG, text);
        }
    }

    public static void e(String text) {
        if (DEBUG) {
            Log.e(TAG, text);
        }
    }
}
