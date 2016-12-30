package com.example.th.onepushmail.activity.main.mail;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.th.onepushmail.R;
import com.example.th.onepushmail.databinding.ActivityMainSendWorkMailBinding;
import com.example.th.onepushmail.fragment.AlertDialogFragment;
import com.example.th.onepushmail.fragment.ProgressDialogFragment;
import com.example.th.onepushmail.interfaces.DialogListener;
import com.example.th.onepushmail.log.LogUtil;

public class MainSendWorkMailActivity extends AppCompatActivity implements
        View.OnClickListener,
        DialogListener {
    private final MainSendWorkMailActivity self = this;
    private ActivityMainSendWorkMailBinding binding;
    private AlertDialogFragment alertDialogFragment;
    private ProgressDialogFragment progressDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(self, R.layout.activity_main_send_work_mail);
        binding.mainSendWorkButtonMain.setOnClickListener(self);
    }

    @Override
    public void onClick(View v) {
        View sendButton = binding.mainSendWorkButtonMain;

        switch (v.getId()) {
            //送信
            case R.id.main_send_work_button_main:
                alertDialogFragment = AlertDialogFragment.newInstance("本当に送信しますか？", "送信", "キャンセル", false);
                alertDialogFragment.setDialogListener(self);
                alertDialogFragment.show(getSupportFragmentManager(), null);
                break;

            //存在しない
            default:
                break;
        }
    }

    /**
     * ダイアログのボタン処理
     */
    @Override
    public void positiveClick() {
        progressDialogFragment = ProgressDialogFragment.newInstance("送信中","しばらくお待ちください");
        progressDialogFragment.show(getSupportFragmentManager(), null);
    }

    @Override
    public void negativeClick() {
//        alertDialogFragment.removeDialogListener();
    }
}
