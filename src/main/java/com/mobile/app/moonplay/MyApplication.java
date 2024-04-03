package com.mobile.app.moonplay;

import android.app.Application;

import in.myinnos.customfontlibrary.TypefaceUtil;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/gta.ttf");

    }

}
