package com.sdkj.heaterbluetooth.app;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;

public class App extends Application {
    private static App instance;

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        super.onTerminate();
    }
}
