package com.softtech.nytime.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import timber.log.Timber;

/**
 * MaxiBilgi
 * Created by SoftTech Garage on 15.02.2019.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public void onPause() {
        Timber.i("onPause");
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timber.i("onCreat");
        super.onCreate(savedInstanceState);
    }
}
