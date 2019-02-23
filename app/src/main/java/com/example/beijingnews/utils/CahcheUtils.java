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
}
