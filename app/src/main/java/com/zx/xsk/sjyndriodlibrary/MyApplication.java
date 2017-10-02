package com.zx.xsk.sjyndriodlibrary;

import android.app.Application;

import com.zx.xsk.SjLib;

/**
 * Created by sjy on 2017/9/29.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SjLib.init(this);
    }
}
