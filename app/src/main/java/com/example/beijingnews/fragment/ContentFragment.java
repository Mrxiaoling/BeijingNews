package com.example.beijingnews.fragment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.beijingnews.R;
import com.example.beijingnews.acitity.MainActivity;
import com.example.beijingnews.base.BasePager;
import com.example.beijingnews.pager.GovaffairPager;
import com.example.beijingnews.pager.HomePager;
import com.example.beijingnews.pager.NewsCenterPager;
import com.example.beijingnews.pager.SettingPager;
import com.example.beijingnews.pager.SmartServicePager;
import com.example.beijingnews.view.NoScrollViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;


public class ContentFragment extends BaseFragment {
    //五个页面的集合
    private ArrayList<BasePager> basePagers;
    private NoScrollViewPager viewPager;
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


        //设置viewpager的适配器
        viewPager.setAdapter(new ContentFragmentAdapter());

        //设置RadioGroup的选中状态的监听
        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());


        //监听某个页面被选中，初始化对应的页面的数据
        viewPager.addOnPageChangeListener(new MyOnPagerChangeListener());
        //设置默认选中首页
        rg_main.check(R.id.rb_home);
        basePagers.get(0).initData();
    }

    /**
     * 得到新闻中心
     * @return
     */
    public NewsCenterPager getNewsCenterPager() {

        return (NewsCenterPager) basePagers.get(1);
    }

    class MyOnPagerChangeListener implements ViewPager.OnPageChangeListener{
        public MyOnPagerChangeListener() {
            super();
        }

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

            basePagers.get(i).initData();
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_home:
                    viewPager.setCurrentItem(0);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_newscenter:
                    viewPager.setCurrentItem(1);

                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_FULLSCREEN);
                    break;
                case R.id.rb_smartservice:
                    viewPager.setCurrentItem(2);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_govaffair:
                    viewPager.setCurrentItem(3);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_setting:
                    viewPager.setCurrentItem(4);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
            }
        }
    }

    private void isEnableSlidingMenu(int i) {
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.getSlidingMenu().setTouchModeAbove(i);
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
