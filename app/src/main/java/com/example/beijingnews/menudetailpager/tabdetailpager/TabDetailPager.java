package com.example.beijingnews.menudetailpager.tabdetailpager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.beijingnews.base.MenuDetailBasePager;
import com.example.beijingnews.domain.NewsCenterPagerBeam;
import com.example.beijingnews.utils.LogUtil;

public class TabDetailPager extends MenuDetailBasePager {


    private final NewsCenterPagerBeam.DataBean.ChildrenBean childrenData;
    public TextView textView;
//    public TabDetailPager(Context context) {
//        super(context);
//
//    }

    public TabDetailPager(Context context, NewsCenterPagerBeam.DataBean.ChildrenBean childrenData) {
        super(context);
        this.childrenData= childrenData;

    }

    @Override
    public View initView() {
        textView=new TextView(context);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText(childrenData.getTitle());
        LogUtil.e(childrenData.getTitle());
    }
}
