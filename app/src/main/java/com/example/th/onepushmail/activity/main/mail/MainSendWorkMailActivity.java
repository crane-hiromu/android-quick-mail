package com.example.th.onepushmail.activity.main.mail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.th.onepushmail.R;
import com.example.th.onepushmail.databinding.ActivityMainSendWorkMailBinding;
import com.example.th.onepushmail.definition.ButtonDefinition;
import com.example.th.onepushmail.fragment.AlertDialogFragment;
import com.example.th.onepushmail.fragment.ProgressDialogFragment;
import com.example.th.onepushmail.interfaces.DialogListener;
import com.example.th.onepushmail.network.GmailManager;
import com.example.th.onepushmail.views.animation.ViewAnimations;

public class MainSendWorkMailActivity extends AppCompatActivity implements
        View.OnClickListener,
        DialogListener {

    //Dialog
    public static class DialogRequestCode {
        public static final int SEND_CHECK_ALERT = 1;
        public static final int AFTER_PROGRESS = 2;
    }

    private final MainSendWorkMailActivity self = this;
    private ActivityMainSendWorkMailBinding binding;
    private AlertDialogFragment alertDialogFragment;
    private ProgressDialogFragment progressDialogFragment;
    private View sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(self, R.layout.activity_main_send_work_mail);
        sendButton = binding.mainSendWorkButtonMail;
        sendButton.setOnClickListener(self);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //送信
            case R.id.main_send_work_button_mail:
                alertDialogFragment = AlertDialogFragment.newInstance("本当に送信しますか？", ButtonDefinition.SEND, ButtonDefinition.CANCEL, false, DialogRequestCode.SEND_CHECK_ALERT);
                alertDialogFragment.setDialogListener(self);
                alertDialogFragment.show(getSupportFragmentManager(), null);

                //連打対策
                sendButton.setEnabled(false);
                ViewAnimations.invisible(sendButton, 1000);

                //リクエストをとばす(一旦３秒のプログレス)
//                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                           if (progressDialogFragment.getShowsDialog()){
//                              progressDialogFragment.dismiss();
//                          }
//
//                        alertDialogFragment = AlertDialogFragment.newInstance(ButtonDefinition.COMPLEATE, ButtonDefinition.OK, DialogRequestCode.AFTER_PROGRESS);
//                        alertDialogFragment.setDialogListener(self);
                        alertDialogFragment.show(getSupportFragmentManager(), null);
//                    }
//                }, 3000);
                try {
                    GmailManager gmailManager = new GmailManager(self, "h.tsuruta@leihauoli.com", "android");
                    gmailManager.sendMail(self);
                } catch (Exception e) {
                    e.printStackTrace();
                }

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
    public void positiveClick(int which) {
        switch (which) {
            case DialogRequestCode.SEND_CHECK_ALERT:
                progressDialogFragment = ProgressDialogFragment.newInstance(ButtonDefinition.SENDING, ButtonDefinition.WAITING);
                progressDialogFragment.show(getSupportFragmentManager(), null);
                break;

            case DialogRequestCode.AFTER_PROGRESS:
                alertDialogFragment.removeDialogListener();
                appearButton(sendButton);
                break;
        }
    }

    @Override
    public void negativeClick(int which) {
//        switch (which){
//            case DialogRequestCode.SEND_CHECK_ALERT:
//                alertDialogFragment.removeDialogListener();
//                break;
//
//            case DialogRequestCode.AFTER_PROGRESS:
//
//                break;
//
//            //ありえない
//            default:
//                break;
//        }
        alertDialogFragment.removeDialogListener();
        appearButton(sendButton);
    }

    /**
     * ボタンを再表示させる
     */
    private void appearButton(View button) {
        button.setEnabled(true);
        ViewAnimations.visible(button, 1000);
    }
}
