package com.example.th.onepushmail.activity.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.th.onepushmail.activity.main.mail.MainSendReportMailActivity;
import com.example.th.onepushmail.activity.main.mail.MainSendWorkMailActivity;
import com.example.th.onepushmail.databinding.ActivityMainBinding;
import com.example.th.onepushmail.R;
import com.example.th.onepushmail.definition.UtilDefinition;
import com.example.th.onepushmail.utils.CommonUtil;
import com.example.th.onepushmail.utils.ToastUtil;
import com.example.th.onepushmail.utils.UserPreferenceUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;


/**
 * デフォルト画面(ログイン済みの場合)
 * <p>
 * Created by T.H on 2016/12/20.
 */

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        Toolbar.OnMenuItemClickListener {
    private final MainActivity self = this;

    private ActivityMainBinding binding;
    private View toolbar;

    private static class Greeting {
        private static final String MO = "Good Morning!";
        private static final String AF = "Good Afternoon!";
        private static final String EV = "Good Evening!";
        private static final String NI = "Good Night!";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(self, R.layout.activity_main);
        binding.activityMainGreeting.setText(judgeHour());
        binding.activityMainDateText.setText(String.valueOf(CommonUtil.getDate(0)));
        binding.activityMainUserName.setText(UserPreferenceUtil.getAccount(self).get(UtilDefinition.NAME));

        binding.activityMainFloatButton.setOnClickListener(self);
        binding.activityMainSendWorkButton.setOnClickListener(self);
        binding.activityMainSendReportButton.setOnClickListener(self);

        //ToolBar
        toolbar = binding.activityMainCommonToolBar;
        ((TextView) toolbar.findViewById(R.id.common_tool_bar_title)).setText(getString(R.string.activity_main_tool_bar_title));
//        ((Toolbar)toolbar.findViewById(R.id.common_tool_bar_wrapper)).setNavigationOnClickListener(self);
//        ((Toolbar)toolbar.findViewById(R.id.common_tool_bar_wrapper)).setOnMenuItemClickListener(self);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //action button
            case R.id.activity_main_float_button:
                checkVisibility(binding.activityMainSendWorkButton);
                checkVisibility(binding.activityMainSendReportButton);
                break;

            //出社連絡
            case R.id.activity_main_send_work_button:
                sendMail(MainSendWorkMailActivity.class);
                break;

            //日報
            case R.id.activity_main_send_report_button:
                sendMail(MainSendReportMailActivity.class);
                break;

            //ToolBar
            case R.id.common_tool_bar_wrapper:
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }


    /**
     * 現在時刻を判定
     */
    @NonNull
    private String judgeHour() {
        Integer num = Integer.valueOf(CommonUtil.getDate(2));
        if (num >= 19 || num <= 5) {
            return Greeting.NI;
        } else if (num >= 16) {
            return Greeting.EV;
        } else if (num >= 12) {
            return Greeting.AF;
        }
        return Greeting.MO;
    }

    /**
     * visibility の判定
     */
    private void checkVisibility(View v) {
        switch (v.getVisibility()) {
            case View.VISIBLE:
                v.setVisibility(View.INVISIBLE);
//                binding.activityMainFloatButton.setBackgroundResource(R.drawable.float_button_new);
                break;

            case View.INVISIBLE:
                v.setVisibility(View.VISIBLE);
//                binding.activityMainFloatButton.setBackgroundResource(R.drawable.float_button_cancel);
                break;

            default:
                break;
        }
    }

    /**
     * ActionButton 子要素の共通メソッドをラップ
     */
    private void sendMail(Class<?> cl) {
        Intent intent = new Intent(self, cl);
        startActivity(intent);
    }
}
