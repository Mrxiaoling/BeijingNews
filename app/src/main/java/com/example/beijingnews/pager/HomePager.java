package com.example.beijingnews.pager;

import android.content.Context;
import android.widget.TextView;

import com.example.beijingnews.base.BasePager;
import com.example.beijingnews.utils.LogUtil;

public class HomePager extends BasePager {

    public HomePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("主页面被初始化了");
        //设置标题
        tv_title.setText("主页面");
        //联网请求数据，创建视图
        TextView textView = new TextView(context);
        fl_content.addView(textView);
        textView.setText("主页面");
        //把子视图添加到BasePager的FrameLayout中

        //绑定数据
    }
}
