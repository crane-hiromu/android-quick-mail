package com.example.th.onepushmail.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Toast 定義ファイル
 *
 * Created by T.H on 2017/01/05.
 */

public class ToastUtil {
    private final ToastUtil self = this;

    /**
     * 基本形: 0,1
     * Top: 2,3
     * Midlle: 4,5
     * Bottom: 6,7
     *
     * 例 ToastUtil.toast(self, "message", 1)
     */
    public static void show(@NonNull Activity activity, @NonNull String message, @NonNull  int num){
        Toast toast = null;
        switch (num){
            //基本形 表示２秒
            case 0:
                toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT);
                break;

            //基本形 表示４秒
            case 1:
                toast = Toast.makeText(activity, message, Toast.LENGTH_LONG);
                break;

//            //Top 表示２秒
//            case 2:
//                toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 100);
//                break;
//
//            //Top 表示４秒
//            case 3:
//                toast = Toast.makeText(activity, message, Toast.LENGTH_LONG);
//                toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 100);
//                break;
//
//            //Midlle 表示２秒
//            case 4:
//                toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
//                break;
//
//            //Midlle 表示４秒
//            case 5:
//                toast = Toast.makeText(activity, message, Toast.LENGTH_LONG);
//                toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
//                break;
//
//            //Bottom 表示２秒
//            case 6:
//                toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, -100);
//                break;
//
//            //Bottom 表示４秒
//            case 7:
//                toast = Toast.makeText(activity, message, Toast.LENGTH_LONG);
//                toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, -100);
//                break;
        }
        if(toast != null){
            toast.show();
            LogUtil.log("ToastUtil", "------show-----");
        } else {
            LogUtil.log("ToastUtil", "------error-----");
        }
    }

    /**
     * 任意のx,y座標に表示
     */
    public static void show(@NonNull Activity activity,@NonNull  String message,@NonNull  int x,@NonNull  int y){
        Toast toast = Toast.makeText(activity, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER|Gravity.CENTER, x, y);
        toast.show();
    }
}
