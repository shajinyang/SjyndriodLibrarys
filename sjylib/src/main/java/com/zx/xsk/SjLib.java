package com.zx.xsk;

import android.app.Application;

import com.zx.xsk.managers.SPer;
import com.zx.xsk.managers.Toaster;

/**
 * 初始化基础类
 * Created by sjy on 2017/9/27.
 */

public class SjLib {

    public static void init(Application mApplication){
        Toaster.setApplication(mApplication);
        SPer.init(mApplication.getApplicationContext(),mApplication.getPackageName());
    }
}
