package com.example.beijingnews.pager;

import android.content.Context;
import android.widget.TextView;

import com.example.beijingnews.base.BasePager;
import com.example.beijingnews.utils.LogUtil;

public class SmartServicePager extends BasePager {

    public SmartServicePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("智慧服务被初始化了");
        tv_title.setText("智慧服务");
        //联网请求数据，创建视图
        TextView textView = new TextView(context);
        fl_content.addView(textView);
        textView.setText("智慧");
        //把子视图添加到BasePager的FrameLayout中

        //绑定数据
    }
}
