package com.example.th.onepushmail;

import android.content.Context;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日付取得のテスト
 *
 * Created by T.H on 2017/01/04.
 */

public class DateTest {
    final DateTest self = this;
    static Context context;

    @Test
    public void getNowDate() throws Exception {
        //日付の型を定義
        final String format = "yyyy/MM/dd";

        //日付取得
        final Date date = new Date(System.currentTimeMillis());
        System.out.println("日付：" + date);

        //指定した形式で日付を取得
        final DateFormat dateFormat = new SimpleDateFormat(format);
        System.out.println(dateFormat.format(date));
//        return dateFormat.format(date);
    }

//        assertEquals(4, 2 + 2);
}

