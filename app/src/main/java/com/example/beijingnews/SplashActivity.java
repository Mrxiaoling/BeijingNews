package com.example.beijingnews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.beijingnews.acitity.GuideActivity;
import com.example.beijingnews.acitity.MainActivity;
import com.example.beijingnews.utils.CahcheUtils;

public class SplashActivity extends Activity {

    public static final String START_MAIN = "start_main";
    private RelativeLayout rl_splahs_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rl_splahs_root = (RelativeLayout) findViewById(R.id.rl_splahs_root);

        //渐变动画，缩放动画，旋转动画

        AlphaAnimation aa = new AlphaAnimation(0,1);
        aa.setFillAfter(true);
        ScaleAnimation sa = new ScaleAnimation(0,1,0,1,ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f);
        sa.setFillAfter(true);
        RotateAnimation ra = new RotateAnimation(0, 360,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        ra.setFillAfter(true);
        AnimationSet set = new AnimationSet(false);
        //添加三个动画没有先后顺序
        set.addAnimation(ra);
        set.addAnimation(sa);
        set.addAnimation(aa);
        set.setDuration(2000);
        rl_splahs_root.startAnimation(set);
        set.setAnimationListener(new MyAnimationListener());
    }

    class MyAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            //判断是否进入过主页面
            //如果进入过，则直接进入主页面，否则进入引导界面
            boolean isStartMain = CahcheUtils.getBoolean(SplashActivity.this, START_MAIN);
            if (isStartMain){

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);

            }else {
                Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                startActivity(intent);
            }

            //关闭SplashActivity界面
            finish();
//            Toast.makeText(SplashActivity.this, "动画播放完成", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
