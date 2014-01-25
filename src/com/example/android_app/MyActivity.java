package com.example.android_app;

import org.apache.cordova.DroidGap;
import android.os.Bundle;

public class MyActivity extends DroidGap {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/index.html");
    }
}