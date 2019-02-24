package com.example.beijingnews;

import android.app.Application;

import org.xutils.x;

public class BeijngNewsApplication extends Application {
    /**
     * 在所有组件被创建之前执行
     */
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
