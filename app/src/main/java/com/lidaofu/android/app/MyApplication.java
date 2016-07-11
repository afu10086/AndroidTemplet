package com.lidaofu.android.app;

import android.app.Application;

/**
 * Created by lidaofu on 2016/3/8.
 */
public class MyApplication extends Application {

    private static MyApplication single;

    @Override
    public void onCreate() {
        super.onCreate();
        single = this;
        initialize();
    }

    public static MyApplication getSingle() {
        return single;
    }


    private void initialize() {
        MyCrashHandler.getInstance().register(this);//异常处理

    }



}
