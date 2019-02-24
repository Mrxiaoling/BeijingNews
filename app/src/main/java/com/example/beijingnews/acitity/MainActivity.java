package com.example.beijingnews.acitity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.beijingnews.R;
import com.example.beijingnews.fragment.ContentFragment;
import com.example.beijingnews.fragment.LeftMenuFragment;
import com.example.beijingnews.utils.DensityUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    public static final String MAIN_CONTENT_TAG = "main_content_tag";
    public static final String LEFTMENU_TAG = "leftmenu_tag";

    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置左侧菜单
        setBehindContentView(R.layout.activity_leftmenu);

        //设置模式
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setMode(SlidingMenu.LEFT);

        //设置滑动模式：滑动边缘
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

        //设置主页占据的比例
        slidingMenu.setBehindOffset(DensityUtil.dipTopx(MainActivity.this, 200));

        //初始化Fragment
        initFragment();
    }

    private void initFragment() {

        //1、得到FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        //2、开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //3、替换
        fragmentTransaction.replace(R.id.fl_leftmenu, new LeftMenuFragment(),LEFTMENU_TAG);//左侧菜单
        fragmentTransaction.replace(R.id.fl_main_content, new ContentFragment(),MAIN_CONTENT_TAG);//左侧菜单
        //4、提交
        fragmentTransaction.commit();

    }
}
