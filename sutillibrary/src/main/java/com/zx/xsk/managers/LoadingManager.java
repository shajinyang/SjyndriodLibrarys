package com.zx.xsk.managers;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Window;

import com.zx.xsk.sutillibrary.R;

/**
 * 加载提交弹框，屏蔽用户点击返回事件
 * Created by sjy on 2017/9/22.
 */

public class LoadingManager {
    public static AlertDialog myloadingview;


    public static void showLoading(Context mContext){
        if(myloadingview==null){
            AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
            myloadingview=builder.setView(LayoutInflater.from(mContext).inflate(R.layout.progress_view,null)).create();
            myloadingview.setCanceledOnTouchOutside(false);
            Window dialogWindow = myloadingview.getWindow();
            dialogWindow.setDimAmount(0);//设置昏暗度为0
        }
        myloadingview.show();
    }


    public static void closeLoading(){
        if(myloadingview!=null){
            myloadingview.dismiss();
        }
    }

    public static void destoryLoading(){
        if(myloadingview!=null){
            myloadingview=null;
        }
    }

}
