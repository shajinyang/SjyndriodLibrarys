package com.zx.xsk.sutillibrary;

import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by sjy on 2017/5/28.
 */

public class NumberUtil {
    public static String touzi_ed_values22 = "";

    /**
     * 在数字型字符串千分位加逗号
     * @param str
     * @param edtext
     * @return sb.toString()
     */
    public static String addComma(String str,TextView edtext){

        touzi_ed_values22 = edtext.getText().toString().trim().replaceAll(",","");

        boolean neg = false;
        if (str.startsWith("-")){  //处理负数
            str = str.substring(1);
            neg = true;
        }
        String tail = null;
        if (str.indexOf('.') != -1){ //处理小数点
            tail = str.substring(str.indexOf('.'));
            str = str.substring(0, str.indexOf('.'));
        }
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        for (int i = 3; i < sb.length(); i += 4){
            sb.insert(i, ',');
        }
        sb.reverse();
        if (neg){
            sb.insert(0, '-');
        }
        if (tail != null){
            sb.append(tail);
        }
        return sb.toString();
    }

    /**
     * 转换为两位小数格式的  金额
     * @param money
     * @return
     */
    public static  String conertToMoney(String money){
        Float moneyf=Float.parseFloat(money);
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(moneyf);
    }

    /**
     *转化两位数 例  9转为09
     * @param value
     * @return
     */
    public static String convertToTow(int value){
        String disvalue = String.format("%02d", value);//格式化
        return disvalue;
    }

}
