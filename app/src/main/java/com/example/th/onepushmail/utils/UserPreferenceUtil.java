package com.example.th.onepushmail.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.th.onepushmail.definition.UtilDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Gmailアカウントの取得・保存・変更
 * <p>
 * Created by T.H on 2017/01/04.
 */

public class UserPreferenceUtil {
    private final UserPreferenceUtil self = this;

    /**
     * 取得 (空じゃなければ取得)
     */
    public static Map<String, String> getAccount(@NonNull Context context) {
        SharedPreferences gmailInfo = PreferenceManager.getDefaultSharedPreferences(context);
        Map<String, String> data = new HashMap<String, String>();
        if (!TextUtils.isEmpty(gmailInfo.getString(UtilDefinition.NAME, ""))) {
            data.put(UtilDefinition.NAME, gmailInfo.getString(UtilDefinition.NAME, ""));
        }
        if (!TextUtils.isEmpty(gmailInfo.getString(UtilDefinition.EMAIL, ""))) {
            data.put(UtilDefinition.EMAIL, gmailInfo.getString(UtilDefinition.EMAIL, ""));
        }
        if (!TextUtils.isEmpty(gmailInfo.getString(UtilDefinition.PASSWORD, ""))) {
            data.put(UtilDefinition.PASSWORD, gmailInfo.getString(UtilDefinition.PASSWORD, ""));
        }
        return data;
    }

    /**
     * 保存
     */
    public static void save(@NonNull Context context, @Nullable String name, @NonNull String fromEmail, @NonNull String password) {
        SharedPreferences gmailInfo = PreferenceManager.getDefaultSharedPreferences(context);
        gmailInfo.edit().putString(UtilDefinition.NAME, name).commit();
        gmailInfo.edit().putString(UtilDefinition.EMAIL, fromEmail).commit();
        gmailInfo.edit().putString(UtilDefinition.PASSWORD, password).commit();
    }

    /**
     * 変更 (空じゃなければ更新する)
     */
    public static void edit(@NonNull Context context, @Nullable String name, @Nullable String fromEmail, @Nullable String password) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        if (!TextUtils.isEmpty(name)) {
            editor.putString(UtilDefinition.NAME, name);
        }
        if (!TextUtils.isEmpty(fromEmail)) {
            editor.putString(UtilDefinition.EMAIL, fromEmail);
        }
        if (!TextUtils.isEmpty(password)) {
            editor.putString(UtilDefinition.PASSWORD, password);
        }
        editor.commit();
    }


}
