package com.example.th.onepushmail.fragment;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;

import com.example.th.onepushmail.definition.BundleDefinition;
import com.example.th.onepushmail.utils.LogUtil;

/**
 * プログレスダイアログ
 * <p>
 * ・実装方法
 * progressDialogFragment = ProgressDialogFragment.newInstance(引数、引数、、、);
 * progressDialogFragment.show(getSupportFragmentManager(), null);
 * <p>
 * Created by T.H on 2016/12/29.
 */

public class ProgressDialogFragment extends DialogFragment {
    private final ProgressDialogFragment self = this;
    private static ProgressDialog progressDialog = null;

    /**
     * タイトル
     * <p>
     * 例 ProgressDialogFragment.newInstance("title");
     */
    public static ProgressDialogFragment newInstance(@NonNull String title) {
        Bundle args = new Bundle();
        args.putString(BundleDefinition.BUNDLE_TITLE, title);
        args.putInt(BundleDefinition.BUNDLE_STYLE, 0);

        ProgressDialogFragment fragment = new ProgressDialogFragment();
        fragment.setArguments(args);

        return fragment;
    }

    /**
     * タイトル+メッセージ
     * <p>
     * 例 ProgressDialogFragment.newInstance("title","message");
     */
    public static ProgressDialogFragment newInstance(@NonNull String title, @NonNull String message) {
        Bundle args = new Bundle();
        args.putString(BundleDefinition.BUNDLE_TITLE, title);
        args.putString(BundleDefinition.BUNDLE_MESSAGE, message);
        args.putInt(BundleDefinition.BUNDLE_STYLE, 0);

        ProgressDialogFragment fragment = new ProgressDialogFragment();
        fragment.setArguments(args);

        return fragment;
    }

    /**
     * タイトル+メッセージ+スタイル
     * <p>
     * 例 ProgressDialogFragment.newInstance("title","message",ProgressDialog.STYLE_HORIZONTAL);
     */
    public static ProgressDialogFragment newInstance(@NonNull String title, @NonNull String message, @NonNull int style) {
        Bundle args = new Bundle();
        args.putString(BundleDefinition.BUNDLE_TITLE, title);
        args.putString(BundleDefinition.BUNDLE_MESSAGE, message);
        args.putInt(BundleDefinition.BUNDLE_STYLE, style);

        ProgressDialogFragment fragment = new ProgressDialogFragment();
        fragment.setArguments(args);

        return fragment;
    }

    /**
     * ダイアログ生成
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // パラメータを取得
        String title = getArguments().getString(BundleDefinition.BUNDLE_TITLE);
        String message = getArguments().getString(BundleDefinition.BUNDLE_MESSAGE);
        Integer style = getArguments().getInt(BundleDefinition.BUNDLE_STYLE);

        progressDialog = new ProgressDialog(getActivity());

        //タイトル
        if (!TextUtils.isEmpty(title)) {
            progressDialog.setTitle(title);
        }

        //メッセージ
        if (!TextUtils.isEmpty(title)) {
            progressDialog.setMessage(message);
        }

        //プログレスダイアログ 0:STYLE_SPINNER/1:STYLE_HORIZONTAL
        progressDialog.setProgressStyle(style);

        //バックボタンの無効
        self.setCancelable(false);
        return progressDialog;
    }

    /**
     * progressDialog取得
     */
    @Override
    public Dialog getDialog() {
        LogUtil.log(getContext(), "getDialog", "--------getDialog--------");
        return progressDialog;
    }

    /**
     * ProgressDialog破棄
     *
     * 例 ProgressDialogFragment.dismiss();
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.log(getContext(), "onDestroy", "--------onDestroy Dialog--------");
        progressDialog = null;
    }
}
