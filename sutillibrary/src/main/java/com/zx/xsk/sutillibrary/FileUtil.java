package com.zx.xsk.sutillibrary;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 文件工具类
 * Created by sjy on 2017/5/10.
 */

public class FileUtil {


    /**
     * content Uri转file绝对路径
     * @param selectedVideoUri
     * @param contentResolver
     * @return
     */

    public static String getFilePathFromContentUri(Uri selectedVideoUri,
                                                   ContentResolver contentResolver) {
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};

        Cursor cursor = contentResolver.query(selectedVideoUri, filePathColumn, null, null, null);
//      也可用下面的方法拿到cursor
//      Cursor cursor = this.context.managedQuery(selectedVideoUri, filePathColumn, null, null, null);

        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    /**
     * 将bitmap保存到本地sd卡  默认文件名为系统时间
     * @param b bitmap
     * @param floderpath 本地文件夹路径 例如： "/sdcard/images"
     * @return
     */
    public static String savePic(Bitmap b, String floderpath, boolean isInsertSys, Context context) {
        SimpleDateFormat sdf = null;
        File outfile = new File(floderpath);
        // 如果文件不存在，则创建一个新文件
        if (!outfile.isDirectory()) {
            try {
                outfile.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String fname = null;
        String filename=System.currentTimeMillis() + ".png";
        fname = outfile + "/" +filename ;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fname);
            if (null != fos) {
                b.compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.flush();
                fos.close();
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        if(isInsertSys==true){
            // 其次把文件插入到系统图库
            try {
                MediaStore.Images.Media.insertImage(context.getContentResolver(),
                        fname, filename, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 最后通知图库更新
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fname)));
        }

        return fname;
    }

    /**
     * 保存网络图片到本地
     * @param netpath
     * @param floderpath
     * @param isInsertSys
     * @param context
     * @return
     */
    public static String savePic(String netpath,String floderpath, boolean isInsertSys, Context context){
        return savePic(returnBitmap(netpath),floderpath,isInsertSys,context);
    }

    /**
     * 根据图片的url路径获得Bitmap对象
     * @param url
     * @return
     */
    public static Bitmap returnBitmap(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;

        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }

}
