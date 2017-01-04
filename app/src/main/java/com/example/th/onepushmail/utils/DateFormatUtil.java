package com.example.th.onepushmail.utils;

import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日付取得
 * <p>
 * Created by T.H on 2017/01/04.
 */

public class DateFormatUtil {
    public static String getDate(){
        //日付の型を定義
        final String format = "yyyy/MM/dd";

        //日付取得
        final Date date = new Date(System.currentTimeMillis());

        //指定した形式で日付を取得
        final DateFormat dateFormat = new SimpleDateFormat(format);

        return dateFormat.format(date);
    }
}
