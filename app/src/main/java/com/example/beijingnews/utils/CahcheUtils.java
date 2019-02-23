package com.example.beijingnews.utils;

import android.content.Context;
import android.content.SharedPreferences;

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
}
