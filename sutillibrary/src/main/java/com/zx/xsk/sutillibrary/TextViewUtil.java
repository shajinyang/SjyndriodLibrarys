package com.zx.xsk.sutillibrary;

import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sjy on 2017/7/6.
 */

public class TextViewUtil {

    /**
     * 是否有空值
     * @param textView
     * @return
     */
    public static boolean isEmpty(TextView... textView){
        for (TextView tv:textView
             ) {
            if(tv.getText().toString().trim().isEmpty()){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为中文
     * @param value
     * @return
     */
    public static boolean isChinese(String value){
        String reg = "[\\u4e00-\\u9fa5]+";
        return value.matches(reg);
    }

    /**
     * 判断是否是手机号
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles){
        Pattern pa = Pattern.compile("^1[0-9]{10}$");
        Matcher ms = pa.matcher(mobiles);
         return ms.matches();
    }

    /**
     * textview赋值
     * @param textView
     * @param value
     */
    public static void setText(TextView textView,Object value){
        if(value==null||value.toString().equals("null")){
            textView.setText("");
        }else {
            textView.setText(value.toString()+"");
        }
    }




    /**
     * 隐藏电话号码赋值
     * @param textView
     * @param value
     */
    public static void setTelHide(TextView textView,Object value){
        if(value==null||value.toString().equals("null")){
            textView.setText("");
        }else {
            if(value.toString().length()>10){
                textView.setText(value.toString().substring(0,3)+"****"+value.toString().substring(value.toString().length()-4,value.toString().length()));
            }else {
                textView.setText("***********");
            }

        }
    }


}
