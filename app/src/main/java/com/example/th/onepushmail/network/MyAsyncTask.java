package com.example.th.onepushmail.network;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentManager;

import com.example.th.onepushmail.definition.ButtonDefinition;
import com.example.th.onepushmail.fragment.ProgressDialogFragment;

/**
 * Created by T.H on 2017/01/04.
 */

public abstract class MyAsyncTask extends AsyncTask<Void,Void,Void> {

    ProgressDialogFragment progressDialogFragment = null;

    private String title;
    private String message;
    private FragmentManager manager;

    /**
     * 基本形
     */
    public MyAsyncTask() {
        super();
    }

    /**
     * Dialog fragment
     */
    public MyAsyncTask(String title, String message, FragmentManager manager) {
        super();
        this.title = title;
        this.message = message;
    }

    /**
     * バッググラウンド処理の前処理（準備）
     * UI Thread処理
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();



        progressDialogFragment = ProgressDialogFragment.newInstance(title, message);
        ProgressDialogFragment.show(manager, null);
    }



    /**
     * バックグラウンド処理
     */
//    @Override
//    protected Void doInBackground(Void... params) {
//        return null;
//    }
//
//    protected abstract void onPostExecute(Void... params);
}