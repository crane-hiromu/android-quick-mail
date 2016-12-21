package com.example.th.onepushmail.activity.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.th.onepushmail.R;
import com.example.th.onepushmail.activity.main.mail.MainSendReportMailActivity;
import com.example.th.onepushmail.activity.main.mail.MainSendWorkMailActivity;


/**
 * デフォルト画面(ログイン済みの場合)
 *
 * Created by T.H on 2016/12/20.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final MainActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.main_button_top).setOnClickListener(this);
        findViewById(R.id.main_button_bottom).setOnClickListener(this);
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
