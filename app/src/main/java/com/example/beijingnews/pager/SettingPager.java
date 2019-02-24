package com.example.beijingnews.pager;

import android.content.Context;
import android.widget.TextView;

import com.example.beijingnews.base.BasePager;

public class SettingPager extends BasePager {

    public SettingPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        tv_title.setText("设置");
        //联网请求数据，创建视图
        TextView textView = new TextView(context);
        textView.setText("设置");
        //把子视图添加到BasePager的FrameLayout中
        fl_content.addView(textView);
        //绑定数据
    }
}