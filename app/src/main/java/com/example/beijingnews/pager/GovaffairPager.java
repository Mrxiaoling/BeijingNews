package com.example.beijingnews.pager;

import android.content.Context;
import android.widget.TextView;

import com.example.beijingnews.base.BasePager;
import com.example.beijingnews.utils.LogUtil;

public class GovaffairPager extends BasePager {

    public GovaffairPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("政要被初始化了");
        tv_title.setText("政要");
        //联网请求数据，创建视图
        TextView textView = new TextView(context);
        textView.setText("政要");
        //把子视图添加到BasePager的FrameLayout中
        fl_content.addView(textView);
        //绑定数据
    }
}
