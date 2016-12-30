package com.example.th.onepushmail.activity.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.th.onepushmail.activity.main.mail.MainSendReportMailActivity;
import com.example.th.onepushmail.activity.main.mail.MainSendWorkMailActivity;
import com.example.th.onepushmail.databinding.ActivityMainBinding;
import com.example.th.onepushmail.R;


/**
 * デフォルト画面(ログイン済みの場合)
 * <p>
 * Created by T.H on 2016/12/20.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final MainActivity self = this;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(self, R.layout.activity_main);
        binding.mainButtonTop.setOnClickListener(self);
        binding.mainButtonBottom.setOnClickListener(self);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            //出社連絡
            case R.id.main_button_top:
                intent = new Intent(self, MainSendWorkMailActivity.class);
                startActivity(intent);
                break;

            //日報
            case R.id.main_button_bottom:
                intent = new Intent(self, MainSendReportMailActivity.class);
                startActivity(intent);
                break;

        }
    }
}
