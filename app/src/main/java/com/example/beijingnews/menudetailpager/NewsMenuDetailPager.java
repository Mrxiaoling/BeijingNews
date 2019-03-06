package com.example.beijingnews.menudetailpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beijingnews.R;
import com.example.beijingnews.base.MenuDetailBasePager;
import com.example.beijingnews.domain.NewsCenterPagerBeam;
import com.example.beijingnews.menudetailpager.tabdetailpager.TabDetailPager;
import com.example.beijingnews.utils.LogUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class NewsMenuDetailPager extends MenuDetailBasePager {

//    public TextView textView;
    @ViewInject(R.id.news_viewpager)
    private ViewPager viewpager;

    /**
     * 页签页面数据的集合
     */
    private List<NewsCenterPagerBeam.DataBean.ChildrenBean> childrenData;
    /**
     * 页签页面的集合
     */
    private ArrayList<TabDetailPager> tabDetailPagers;

//    public NewsMenuDetailPager(Context context) {
//        super(context);
//    }

    public NewsMenuDetailPager(Context context, NewsCenterPagerBeam.DataBean dataBean) {
        super(context);
        childrenData=dataBean.getChildren();

    }

    @Override
    public View initView() {
//        textView = new TextView(context);
//        return textView;

        View view = View.inflate(context, R.layout.newsmenu_detail_pager  ,null );
        x.view().inject(NewsMenuDetailPager.this,view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
//        LogUtil.e("数据初始化成功");
//        textView.setText(".......");
        //准备新闻详情页面的数据
        tabDetailPagers = new ArrayList<>();
        for (int i=0;i<childrenData.size();i++){
            tabDetailPagers.add(new TabDetailPager(context,childrenData.get(i)));

        }

        viewpager.setAdapter(new MyNewsMenuDetailPagerAdapter());

    }

    class MyNewsMenuDetailPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return tabDetailPagers.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view==o;
        }

//        public MyNewsMenuDetailPagerAdapter() {
//            super();
//        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            TabDetailPager tabDetailPager = tabDetailPagers.get(position);
            View rootView = tabDetailPager.rootView;
            tabDetailPager.initData();//初始化数据
            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
