package com.example.th.onepushmail.activity.main.mail;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.th.onepushmail.R;
import com.example.th.onepushmail.databinding.ActivityMainSendWorkMailBinding;
import com.example.th.onepushmail.definition.MailDefinition;
import com.example.th.onepushmail.network.GmailManager;
import com.example.th.onepushmail.utils.CommonUtil;
import com.example.th.onepushmail.utils.SpinnerUtil;
import com.example.th.onepushmail.utils.ToastUtil;
import com.example.th.onepushmail.views.animation.ViewAnimations;
import com.github.florent37.viewanimator.AnimationListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

public class MainSendWorkMailActivity extends AppCompatActivity implements
        View.OnClickListener,
        AdapterView.OnItemSelectedListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    private final MainSendWorkMailActivity self = this;

    //Spinner
    private String spinnerItems[] = {"業務名１","業務名２"};
    private String mailText;

    //Binding
    private ActivityMainSendWorkMailBinding binding;
    private Button sendButton;

    private TextView titleBind;
    private Spinner spinnerBind;

    private GoogleApiClient locationClient;
    private final int REQUEST_PERMISSION = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(self, R.layout.activity_main_send_work_mail);
        sendButton = binding.activityMainSendWorkButtonMail;
        sendButton.setOnClickListener(self);

        titleBind = binding.activityMainSendTitle;
        spinnerBind = binding.activityMainSendSpinner;

        // Spinner
        // todo 将来的にはspinnerItemsをアプリ側から追加・消去できるようにしたい
        SpinnerUtil.set(self, binding.activityMainSendSpinner, spinnerItems);


//        //Location の許可
//        if (Build.VERSION.SDK_INT >= 23) {
//            checkPermission();
//        } else {
//            startLocation();
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        locationClient.disconnect();
    }


    @Override
    public void onClick(View v) {
        //画面幅全体
        int size = CommonUtil.getScreenSize(self).x;
        switch (v.getId()) {
            //送信
            case R.id.activity_main_send_work_button_mail:
                //連打対策
                sendButton.setEnabled(false);
                titleBind.setVisibility(View.INVISIBLE);
                spinnerBind.setVisibility(View.INVISIBLE);
                ViewAnimations.invisibleSize(sendButton, 200, size, 1000, stopListener);

                //メール送信
                sendMail();
                break;

            //存在しない
            default:
                break;
        }
    }

    /**
     * Spinner の選択
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mailText = String.valueOf(parent.getSelectedItem());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


    ////////////////////////////////////////////////google////////////////////////////////////////////////

    /**
     * 接続時
     */
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        ToastUtil.show(self, "onConnected", 1);
        addGeofence();
    }

    /**
     * 切断時
     */
    @Override
    public void onConnectionSuspended(int i) {
        ToastUtil.show(self, "onConnectionSuspended", 1);
    }

    /**
     * 接続失敗
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        ToastUtil.show(self, "onConnectionFailed", 1);
    }

    /**
     * location
     */
    @Override
    public void onLocationChanged(Location location) {
        ToastUtil.show(self, "update", 1);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 権限の受け取り結果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startLocation();
        } else {
            ToastUtil.show(self, "許可されないとメールが送信できません", 1);
        }
    }

    /**
     * 権限の確認
     */
    private void checkPermission() {
        // 既に許可している
        if (ActivityCompat.checkSelfPermission(self, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            startLocation();
        }
        // 拒否していた場合
        else {
            requestLocationPermission();
        }
    }

    /**
     * Location の権限を要求
     */
    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(self, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(MainSendWorkMailActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION);
        } else {
            ToastUtil.show(self, "許可されないとメールが送信できません", 1);

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, REQUEST_PERMISSION);
        }
    }

    /**
     * location に接続
     */
    private void startLocation() {
        locationClient = new GoogleApiClient.Builder(self)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(self)
                .addOnConnectionFailedListener(self)
                .build();
        locationClient.connect();
    }

    /**
     * Geofence
     */
    private void addGeofence() {
        double latitude = 35.658775;
        double longitude = 139.705223;
        float radius = 50;

        Geofence.Builder builder = new Geofence.Builder();
        builder.setRequestId("ID");
        builder.setCircularRegion(latitude, longitude, radius);
        builder.setExpirationDuration(Geofence.NEVER_EXPIRE);
        builder.setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT);

        ArrayList<Geofence> geofences = new ArrayList<Geofence>();
        geofences.add(builder.build());

        // PendingIntent の生成
        Intent intent = new Intent(self, MainSendWorkMailActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(self, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        // Geofences の登録
        LocationServices.GeofencingApi.addGeofences(locationClient, geofences, pendingIntent);
    }

    /**
     * Gmail 送信
     */
    private void sendMail() {
        try {
//            new GmailManager(self, MailDefinition.ME, mailText).sendMail(sendButton);
            new GmailManager(self, MailDefinition.ML_ALL, mailText).sendMail(sendButton);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ViewAnimations コールバック
     */
    AnimationListener.Stop stopListener = new AnimationListener.Stop() {
        @Override
        public void onStop() {
            ViewAnimations.visibleSize(sendButton, 0, 200, 1000);
            sendButton.setEnabled(true);
            titleBind.setVisibility(View.VISIBLE);
            spinnerBind.setVisibility(View.VISIBLE);
        }
    };
}
