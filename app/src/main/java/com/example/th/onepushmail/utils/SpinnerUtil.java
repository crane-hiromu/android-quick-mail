package com.example.th.onepushmail.utils;

import android.app.Activity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Spinner 定義
 *
 * Created by T.H on 2017/01/15.
 */

public class SpinnerUtil {
    public static void set(Activity activity, Spinner spinner, String[] spinnerItems){
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (activity, android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) activity);
    }
}