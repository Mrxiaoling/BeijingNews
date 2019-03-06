package com.example.beijingnews.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

import com.example.beijingnews.SplashActivity;
import com.example.beijingnews.acitity.GuideActivity;

/**
 * 缓存工具类，用来判断是否进入过主页面
 */
public class CahcheUtils {
    /**
     *
     * @param context 上下文
     *
     * @param key key值
     * @return
     */
    public static boolean getBoolean (Context context,String key){

        SharedPreferences sp = context.getSharedPreferences("ling", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }
    //保存软件参数
    public static void putBoolean(Context context, String key, boolean value) {

        SharedPreferences sp = context.getSharedPreferences("ling", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();

    }

    /**
     * 缓存文本数据
     * @param context
     * @param key
     * @param value
     */
    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences("ling", Context.MODE_PRIVATE);
        sp.edit().putString(key ,value ).commit();

    }

    public static String getString(Context context, String key) {

        SharedPreferences sp = context.getSharedPreferences("ling", Context.MODE_PRIVATE);

//        String text = null;//空指针，应采用下面的方式
//        text.equals('ddd');
        return sp.getString(key, "");
    }
}
