package com.example.beijingnews.menudetailpager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.beijingnews.base.MenuDetailBasePager;
import com.example.beijingnews.utils.LogUtil;

public class InteracMenuDetailPager extends MenuDetailBasePager {

    public TextView textView;

    public InteracMenuDetailPager(Context context) {

        super(context);
    }

    @Override
    public View initView() {
        textView = new TextView(context);

        return textView;
    }

    @Override
    public void initData() {

        super.initData();
        LogUtil.e("互动详情页面数据被初始化了..");
        textView.setText("图组详情页面内容");
    }
}
