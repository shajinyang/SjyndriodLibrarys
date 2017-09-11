package com.zx.xsk.sutillibrary;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by sjy on 2017/5/31.
 */

public class ViewUtil {

    /**
     * 将布局文件转为bitmap文件
     * @param view
     * @param activity
     * @return
     */
    public static Bitmap xmlToBitmap(View view, Activity activity){
        //启用绘图缓存
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        DisplayMetrics metrics =new DisplayMetrics();
        int width = 0;
        int height = 0;
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.JELLY_BEAN_MR1){
            width=600;
            height=800;
        }else {
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
            width = metrics.widthPixels;
            height = metrics.heightPixels;
        }
        //调用下面这个方法非常重要，如果没有调用这个方法，得到的bitmap为null
        view.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));
        //这个方法也非常重要，设置布局的尺寸和位置
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        //获得绘图缓存中的Bitmap
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    /**
     * 设置状态栏样式
     * @param activity
     * @param ishide true 隐藏状态栏   false 透明状态栏
     */
    public static void setStatusBar(Activity activity,boolean ishide){
        if(ishide){
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }else {
            if (Build.VERSION.SDK_INT<Build.VERSION_CODES.LOLLIPOP) {

            }else {
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        }
    }
}
