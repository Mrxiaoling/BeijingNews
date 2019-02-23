package com.example.beijingnews.acitity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.beijingnews.R;
import com.example.beijingnews.SplashActivity;
import com.example.beijingnews.utils.CahcheUtils;
import com.example.beijingnews.utils.DensityUtil;

import java.util.ArrayList;

import static com.example.beijingnews.SplashActivity.START_MAIN;

public class GuideActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private Button btn_start_main;
    private LinearLayout ll_point_group;
    private ImageView iv_red_point;
    private ArrayList<ImageView> imageViews;
    //两点之间的间距
    private int leftmax;

    private int widthdpi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_activy);
        viewpager = findViewById(R.id.viewpager);
        btn_start_main = findViewById(R.id.btn_start_main);
        ll_point_group = findViewById(R.id.ll_point_group);
        iv_red_point = findViewById(R.id.iv_red_point);


        //准备数据
        int [] ids = new int[]{
                R.drawable.guide_1,
                R.drawable.guide_2,
                R.drawable.guide_3
        };
        widthdpi = DensityUtil.dipTopx(this, 10);
        imageViews = new ArrayList<>();
        for (int i = 0 ; i < ids.length;i++){
            ImageView imageView = new ImageView(this);
            //设置背景
            imageView.setBackgroundResource(ids[i]);
            //添加到集合中
            imageViews.add(imageView);

            //创建点,添加到线性布局里面
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);
            /**
             * 单位是像素，需要做适配
             * 把单位当成dp转成对应的像素
             */
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthdpi,widthdpi);
            if (i != 0){
                //不包括第0个点，所有的点距离左边10个像素
                params.leftMargin = widthdpi;
            }
            point.setLayoutParams(params);
            ll_point_group.addView(point);

        }

        //设置Viewpager的适配器
        viewpager.setAdapter(new MyPagerAdapter());

        //根据View的生命周期，当视图执行到onLayout或者onDraw的时候，视图的宽和高，边距都有了
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());
        //得到屏幕滑动的百分比
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());

        btn_start_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1、保存曾经进入过主页面
                CahcheUtils.putBoolean(GuideActivity.this, SplashActivity.START_MAIN,true);
                //2、跳转到主页面
                Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                //3、关闭引导页面
                finish();
            }
        });
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{
        /**
         * 当页面滚动会回调该方法
         * @param positon 当前滑动页面的位置
         * @param positionOffset 页面滑动的百分比
         * @param positionOffsetPixels 滑动的像素
         */
        @Override
        public void onPageScrolled(int positon, float positionOffset, int positionOffsetPixels) {
//            两点间移动的距离 = 屏幕滑动百分比 * 间距
            int leftMargin = (int) (positionOffset*leftmax);
//            两点间滑动距离对应的坐标 = 原来的起始位置 +  两点间移动的距离
            leftMargin = positon*leftmax + (int) (positionOffset*leftmax);
//
//            params.leftMargin = 两点间滑动距离对应的坐标
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_point.getLayoutParams();
            params.leftMargin = leftMargin;
            iv_red_point.setLayoutParams(params);

        }

        /**
         * 当页面被选中的时候回调该方法
         * @param position 被选中页面的位置
         */
        @Override
        public void onPageSelected(int position) {

            if (position == imageViews.size()-1){
                //最后一个页面
                btn_start_main.setVisibility(View.VISIBLE);

            }else {
                //其他页面
                btn_start_main.setVisibility(View.GONE);
            }

        }

        /**
         * 当viewpager滑动状态发生变化的时候回调该方法
         * @param state
         */
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener{

        @Override
        public void onGlobalLayout() {
            //执行不止一次
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                iv_red_point.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                // 间距  = 第1个点距离左边的距离 - 第0个点距离左边的距离
                leftmax = ll_point_group.getChildAt(1).getLeft()-ll_point_group.getChildAt(0).getLeft();


            }
        }
    }

    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return imageViews.size();
        }

        /**
         *
         * @param view 当前创建的视图
         * @param o instantiateItem返回的结果值
         * @return
         */
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
//            return view == imageViews.get(Integer.parseInt((String) o));
            return view == o;
        }

        /**
         * 作用：getView
         * @param container  Viewpager
         * @param position 要创建的页面的位置
         * @return
         */
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            //添加到容器中
            container.addView(imageView);

//            return position;
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
