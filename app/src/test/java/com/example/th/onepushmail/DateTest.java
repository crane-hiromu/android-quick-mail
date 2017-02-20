package com.example.th.onepushmail;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Display;

import com.example.th.onepushmail.definition.UtilDefinition;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日付取得のテスト
 *
 * Created by T.H on 2017/01/04.
 */

public class DateTest {

    @Test
    public void getNowDate() throws Exception {
        //日付の型を定義
        final String format = "yyyy/MM/dd HH:mm:ss";

        //日付取得
        final Date date = new Date(System.currentTimeMillis());
        System.out.println("日付：" + date);

        //指定した形式で日付を取得
        final DateFormat dateFormat = new SimpleDateFormat(format);
        System.out.println(dateFormat.format(date));
//        return dateFormat.format(date);
    }
}

