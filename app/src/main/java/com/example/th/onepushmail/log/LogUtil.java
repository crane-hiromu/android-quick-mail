package com.example.th.onepushmail.log;

import android.content.Context;
import android.util.Log;

/**
 * ログを出す
 *
 * Created by T.H on 2016/12/25.
 */

public class LogUtil {
    LogUtil self = this;

    public static void log(Context name, String method, String log) {
        Log.d("["+name+"]","[---------log---------]");
        Log.d("["+method+"]","["+log+"]");
    }
}
