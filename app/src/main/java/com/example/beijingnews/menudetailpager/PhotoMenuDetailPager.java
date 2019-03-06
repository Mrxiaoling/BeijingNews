package com.example.beijingnews.menudetailpager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.beijingnews.base.MenuDetailBasePager;
import com.example.beijingnews.utils.LogUtil;

public class PhotoMenuDetailPager extends MenuDetailBasePager {

    public TextView textView;

    public PhotoMenuDetailPager(Context context) {
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
        LogUtil.e(",,,,,");
        textView.setText("...........");
    }
}
