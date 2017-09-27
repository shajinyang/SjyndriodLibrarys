package com.zx.xsk;

import android.app.Application;

import com.zx.xsk.managers.SharedPreferenceManager;
import com.zx.xsk.managers.ToastManager;

/**
 * 初始化基础类
 * Created by sjy on 2017/9/27.
 */

public class Sjutil {

    public static void init(Application mApplication){
        ToastManager.setApplication(mApplication);
        SharedPreferenceManager.init(mApplication.getApplicationContext(),mApplication.getPackageName());
    }
}
