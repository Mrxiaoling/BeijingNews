package com.example.beijingnews.fragment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.beijingnews.R;
import com.example.beijingnews.base.BasePager;
import com.example.beijingnews.pager.GovaffairPager;
import com.example.beijingnews.pager.HomePager;
import com.example.beijingnews.pager.NewsCenterPager;
import com.example.beijingnews.pager.SettingPager;
import com.example.beijingnews.pager.SmartServicePager;

import java.util.ArrayList;


public class ContentFragment extends BaseFragment {
    //五个页面的集合
    private ArrayList<BasePager> basePagers;
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
        //初始化五个页面，放入集合中
        basePagers = new ArrayList<>();
        basePagers.add(new HomePager(context));
        basePagers.add(new NewsCenterPager(context));
        basePagers.add(new GovaffairPager(context));
        basePagers.add(new SmartServicePager(context));
        basePagers.add(new SettingPager(context));
        //设置默认选中首页
        rg_main.check(R.id.rb_home);

        //设置viewpager的适配器
        viewPager.setAdapter(new ContentFragmentAdapter());
    }
    class ContentFragmentAdapter extends PagerAdapter{

        public ContentFragmentAdapter() {
            super();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            BasePager basePager = basePagers.get(position);//各个页面的实例
            View rootView = basePager.rootView;//各个子页面
            //调用各个页面的initData（）
            basePager.initData();//初始化数据
            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return basePagers.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view==o;
        }
    }

}
