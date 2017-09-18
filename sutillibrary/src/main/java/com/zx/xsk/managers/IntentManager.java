package com.zx.xsk.managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by sjy on 2017/6/2.
 */

public class IntentManager {

    /**
     * 普通跳转
     * @param context
     * @param activity
     */
    public static void skipToActivity(Context context, Class<?> activity){
        Intent intent=new Intent(context,activity);
        context.startActivity(intent);
    }
    /**
     * 普通跳转带参数
     * @param context
     * @param activity
     */
    public static void skipToActivity(Context context,Bundle bundle, Class<?> activity){
        Intent intent=new Intent(context,activity);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 普通跳转带参数,带跳转属性
     * @param context
     * @param activity
     */
    public static void skipToActivity(Context context, Bundle bundle, Class<?> activity,int... flags){
        Intent intent=new Intent(context,activity);
        for (int flag:
             flags) {
            intent.addFlags(flag);
        }
        intent.putExtras(bundle);
        context.startActivity(intent);


    }
    /**
     * 普通跳转,带跳转属性
     * @param context
     * @param activity
     */
    public static void skipToActivity(Context context,  Class<?> activity,int... flags){
        Intent intent=new Intent(context,activity);
        for (int flag:
                flags) {
            intent.addFlags(flag);
        }
        context.startActivity(intent);


    }

    /**
     * 传值跳转
     * @param myactivity
     * @param activity
     * @param bundle
     * @param requestCode
     */
    public static void skipToActivityForResult(Activity myactivity, Class<?> activity, Bundle bundle,int requestCode){
        Intent intent=new Intent(myactivity,activity);
        if(bundle!=null) {
            intent.putExtras(bundle);
        }
        myactivity.startActivityForResult(intent,requestCode);

    }
    /**
     * 传值跳转
     * @param myactivity
     * @param activity
     * @param requestCode
     */
    public static void skipToActivityForResult(Activity myactivity, Class<?> activity,int requestCode){
        Intent intent=new Intent(myactivity,activity);
        myactivity.startActivityForResult(intent,requestCode);
    }

    /**
     * 传值跳转
     * @param activity
     * @param requestCode
     */
    public static void skipToActivityForResult(Fragment fragment, Class<?> activity, int requestCode){
        Intent intent=new Intent(fragment.getContext(),activity);
        fragment.startActivityForResult(intent,requestCode);
    }


    /**
     * 拨打电话
     * @param numbers
     */
    public static void skipToCall(Context context,String numbers){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+numbers));
        context.startActivity(intent);
    }

    /**
     * 发送短信
     * @param numbers
     */
    public static void skipToMSM(Context context,String numbers){
        Uri smsToUri = Uri.parse("smsto:"+numbers);
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        //短信内容
        intent.putExtra("sms_body", "你好");
        context.startActivity(intent);
    }

}
