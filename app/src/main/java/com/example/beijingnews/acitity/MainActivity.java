package com.example.beijingnews.acitity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.beijingnews.R;
import com.example.beijingnews.utils.DensityUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

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
    }
}
