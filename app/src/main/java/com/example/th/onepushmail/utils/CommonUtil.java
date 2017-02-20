package com.example.th.onepushmail.utils;

import android.app.Activity;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Display;

import com.example.th.onepushmail.definition.UtilDefinition;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日付取得
 * <p>
 * Created by T.H on 2017/01/04.
 */

public class CommonUtil {

    /**
     * 日付取得
     */
    public static String getDate(int kinds) {
        //日付の型を指定
        String format = null;
        switch (kinds){
            // 日付
            case 0:
                format = "dd";
                break;

            // 年/月/日
            case 1:
                format = "yyyy/MM/dd";
                break;

            // 時間
            case 2:
                format = "HH";
                break;

            //存在しない
            default:
                break;
        }

        //日付取得
        final Date date = new Date(System.currentTimeMillis());

        //指定した形式で日付を取得
        final DateFormat dateFormat = new SimpleDateFormat(format);

        return dateFormat.format(date);
    }

    /**
     * スクリーンサイズ取得
     */
    public static Point getScreenSize(@Nullable Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return point;
    }

////
//    /**
//     * キーボード閉じる(閉じた場合 true、既に閉じていた場合 false)
//     */
//    public static boolean hideKeyboard(@Nullable Activity activity) {
//        if (activity == null) {
//            return false;
//        }
//        InputMethodManager im = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
//        boolean result = im.hideSoftInputFromWindow(activity.getWindow().getDecorView().getRootView().getWindowToken(), 0);
//        return result;
//    }
//
//    /**
//     * キーボード閉じる(閉じた場合 true、既に閉じていた場合 false)
//     */
//    public static boolean hideKeyboard(@Nullable View view) {
//        if (view == null) {
//            return false;
//        }
//        Context context = view.getContext();
//        if (context == null) {
//            return false;
//        }
//        InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        boolean result = im.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        return result;
//    }
}
