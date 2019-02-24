package com.example.beijingnews.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.example.beijingnews.R;

public class ContentFragment extends BaseFragment {

    private ViewPager viewPager;
    private RadioGroup rg_main;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.content_fragment, null);
        viewPager = view.findViewById(R.id.viewpager);
        rg_main = view.findViewById(R.id.rg_main);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //设置默认选中首页
        rg_main.check(R.id.rb_home);
    }
}
