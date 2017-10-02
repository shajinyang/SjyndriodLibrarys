package com.zx.xsk.managers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences操作类
 * Created by sjy on 2016/11/11.
 */

public class SPer {
    private static SharedPreferences preferences;

    public static void init(Context context, String name) {
        preferences = context.getSharedPreferences(name, Activity.MODE_PRIVATE);
    }

    public static void setString(String key, String value) {
        preferences.edit().putString(key, value).commit();
    }

    public static String getString(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    public static void setInt(String key, int value) {
        preferences.edit().putInt(key, value).commit();
    }

    public static Integer getInt(String key, int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    public static void setBoolean(String key, boolean value) {
        preferences.edit().putBoolean(key, value).commit();
    }

    public static Boolean getBoolean(String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }
}
