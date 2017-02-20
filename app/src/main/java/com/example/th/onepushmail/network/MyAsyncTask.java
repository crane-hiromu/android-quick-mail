package com.example.th.onepushmail.network;

import android.os.AsyncTask;

/**
 * サブスレッド通信
 *
 * Created by T.H on 2017/01/04.
 */

public abstract class MyAsyncTask extends AsyncTask<Void,Void,Integer> {
    private final MyAsyncTask self = this;

    /**
     * 基本形
     */
    public MyAsyncTask() {
        super();
    }

    /**
     * バッググラウンド処理の前処理（準備）
     * UI Thread処理
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * バックグラウンド処理
     */
    @Override
    protected Integer doInBackground(Void... params) {
        return null;
    }

    /**
     * バックグラウンド処理が終わった後の処理（表示の更新）
     */
    protected abstract void onPostExecute(Integer num);
}