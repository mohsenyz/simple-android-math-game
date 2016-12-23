package com.phoenix.math;

import android.app.Application;

import com.phoenix.math.helper.FontsOverride;

/**
 * Created by dahlia on 12/24/16.
 */
public class AppController extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "MONOSPACE", "iran.ttf");
    }
}