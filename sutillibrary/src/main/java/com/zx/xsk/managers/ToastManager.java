package com.zx.xsk.managers;

import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by sjy on 2017/6/2.
 */

public class ToastManager {
    private static Toast toast=null;

    /**
     * toast弹框
     * @param msg
     * @param context
     */
    public static void showToast(String msg, Context context){
        if(toast==null){
            toast=Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        }else {
            toast.setText(msg);
        }
        toast.show();
//        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();

    }

    /**
     * 复制内容到剪切板，并提示
     * @param msg 复制的内容
     * @param context
     */
    public static void showToastWithCopy(String msg, Context context){
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setText(msg);
        Toast.makeText(context,"复制成功",Toast.LENGTH_SHORT).show();
    }
}
