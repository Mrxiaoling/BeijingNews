package com.example.beijingnews.menudetailpager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.beijingnews.base.MenuDetailBasePager;
import com.example.beijingnews.utils.LogUtil;

public class NewsMenuDetailPager extends MenuDetailBasePager {

    public TextView textView;

    public NewsMenuDetailPager(Context context) {
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
        LogUtil.e("数据初始化成功");
        textView.setText(".......");
    }
}
