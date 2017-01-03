package com.example.th.onepushmail.activity.main.mail;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.th.onepushmail.R;
import com.example.th.onepushmail.databinding.ActivityMainSendReportMailBinding;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;

import java.util.Collections;


public class MainSendReportMailActivity extends AppCompatActivity implements
        View.OnClickListener {
    private final MainSendReportMailActivity self = this;
    private ActivityMainSendReportMailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(self, R.layout.activity_main_send_report_mail);
    }

    @Override
    public void onClick(View v) {

    }


}