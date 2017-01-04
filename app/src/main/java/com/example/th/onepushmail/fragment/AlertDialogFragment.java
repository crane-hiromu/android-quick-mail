package com.example.th.onepushmail.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;

import com.example.th.onepushmail.R;
import com.example.th.onepushmail.definition.BundleDefinition;
import com.example.th.onepushmail.interfaces.DialogListener;
import com.example.th.onepushmail.utils.LogUtil;

/**
 * アラートダイアログ
 * <p>
 * ・実装方法
 * AlertDialogFragment alertDialog = AlertDialogFragment.newInstance(引数、引数、、、、);
 * alertDialog.show(getSupportFragmentManager(),null);
 */
public class AlertDialogFragment extends DialogFragment {
    private final AlertDialogFragment self = this;
    private DialogListener listener = null;

    /**
     * メッセージ
     * <p>
     * 例 AlertDialogFragment.newInstance("メッセージ",0);
     * <p>
     * 備考 番号は処理分岐用に任意に設定可
     */
    public static AlertDialogFragment newInstance(@NonNull String message, @NonNull int which) {
        Bundle args = new Bundle();
        args.putString(BundleDefinition.BUNDLE_MESSAGE, message);
        args.putBoolean(BundleDefinition.BUNDLE_BOOL, true);
        args.putInt(BundleDefinition.BUNDLE_WHICH, which);

        AlertDialogFragment fragment = new AlertDialogFragment();
        fragment.setArguments(args);

        return fragment;
    }

    /**
     * メッセージ+positiveボタン
     * <p>
     * 例 AlertDialogFragment.newInstance("メッセージ","OK",0);
     */
    public static AlertDialogFragment newInstance(@NonNull String message, @NonNull String posBtnTitle,@NonNull int which) {
        Bundle args = new Bundle();
        args.putString(BundleDefinition.BUNDLE_MESSAGE, message);
        args.putString(BundleDefinition.BUNDLE_POSITIVE_BUTTON_TITLE, posBtnTitle);
        args.putBoolean(BundleDefinition.BUNDLE_BOOL, true);
        args.putInt(BundleDefinition.BUNDLE_WHICH, which);

        AlertDialogFragment fragment = new AlertDialogFragment();
        fragment.setArguments(args);

        return fragment;
    }

    /**
     * メッセージ+positiveボタン+バックボタン無効
     * <p>
     * 例 AlertDialogFragment.newInstance("メッセージ","OK",false,0);
     */
    public static AlertDialogFragment newInstance(@NonNull String message, @NonNull String posBtnTitle,@NonNull boolean cancelable,@NonNull int which) {
        Bundle args = new Bundle();
        args.putString(BundleDefinition.BUNDLE_MESSAGE, message);
        args.putString(BundleDefinition.BUNDLE_POSITIVE_BUTTON_TITLE, posBtnTitle);
        args.putBoolean(BundleDefinition.BUNDLE_BOOL, cancelable);
        args.putInt(BundleDefinition.BUNDLE_WHICH, which);

        AlertDialogFragment fragment = new AlertDialogFragment();
        fragment.setArguments(args);

        return fragment;
    }

    /**
     * メッセージ+positiveボタン+negativeボタン
     * <p>
     * 例 AlertDialogFragment.newInstance("メッセージ","OK","Cancel",0);
     */
    public static AlertDialogFragment newInstance(@NonNull String message, @NonNull String posBtnTitle,@NonNull String negBtnTitle,@NonNull int which) {
        Bundle args = new Bundle();
        args.putString(BundleDefinition.BUNDLE_MESSAGE, message);
        args.putString(BundleDefinition.BUNDLE_POSITIVE_BUTTON_TITLE, posBtnTitle);
        args.putString(BundleDefinition.BUNDLE_NEGATIVE_BUTTON_TITLE, negBtnTitle);
        args.putBoolean(BundleDefinition.BUNDLE_BOOL, true);
        args.putInt(BundleDefinition.BUNDLE_WHICH, which);

        AlertDialogFragment fragment = new AlertDialogFragment();
        fragment.setArguments(args);

        return fragment;
    }

    /**
     * メッセージ+positiveボタン+negativeボタン+バックボタン無効
     * <p>
     * 例 AlertDialogFragment.newInstance("メッセージ","OK","Cancel",false,0);
     */
    public static AlertDialogFragment newInstance(@NonNull String message, @NonNull String posBtnTitle, @NonNull String negBtnTitle,@NonNull boolean cancelable,@NonNull int which) {
        Bundle args = new Bundle();
        args.putString(BundleDefinition.BUNDLE_MESSAGE, message);
        args.putString(BundleDefinition.BUNDLE_POSITIVE_BUTTON_TITLE, posBtnTitle);
        args.putString(BundleDefinition.BUNDLE_NEGATIVE_BUTTON_TITLE, negBtnTitle);
        args.putBoolean(BundleDefinition.BUNDLE_BOOL, cancelable);
        args.putInt(BundleDefinition.BUNDLE_WHICH, which);

        AlertDialogFragment fragment = new AlertDialogFragment();
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
        String positiveButtonTitle = getArguments().getString(BundleDefinition.BUNDLE_POSITIVE_BUTTON_TITLE);
        String negativeButtonTitle = getArguments().getString(BundleDefinition.BUNDLE_NEGATIVE_BUTTON_TITLE);
        Boolean bool = getArguments().getBoolean(BundleDefinition.BUNDLE_BOOL);
        final int whichParam = getArguments().getInt(BundleDefinition.BUNDLE_WHICH);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CommonAlertDialog);

        //タイトル
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }

        //メッセージ
        builder.setMessage(message);

        //ポジティブボタン
        builder.setPositiveButton(positiveButtonTitle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LogUtil.log(getContext(), "positiveClick", "--------ok--------");
                listener.positiveClick(whichParam);
                dismiss();
            }
        });

        if (!TextUtils.isEmpty(negativeButtonTitle)) {
            // ネガティブボタン
            builder.setNegativeButton(negativeButtonTitle, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    LogUtil.log(getContext(), "negativeClick", "--------cancel--------");
                    listener.negativeClick(whichParam);
                    dismiss();
                }
            });
        }

        //バックボタンの許可
        self.setCancelable(bool);
        return builder.create();
    }

    /**
     * リスナーを追加
     */
    public void setDialogListener(DialogListener listener) {
        LogUtil.log(getContext(), "setDialogListener", "--------setDialog--------");
        this.listener = listener;
    }

    /**
     * リスナー削除
     */
    public void removeDialogListener() {
        LogUtil.log(getContext(), "removeDialogListener", "--------removeDialog--------");
        this.listener = null;
    }
}
