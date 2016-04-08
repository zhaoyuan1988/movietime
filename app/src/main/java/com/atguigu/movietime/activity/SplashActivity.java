package com.atguigu.movietime.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.atguigu.movietime.R;
import com.atguigu.movietime.utils.SpUtils;


public class SplashActivity extends Activity {
    public static final String START_MAIN = "isstart_main";
    private RelativeLayout rl_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rl_splash = (RelativeLayout)findViewById(R.id.rl_splash);
        //1.三个动画，缩放动画，渐变动画，旋转动画

        //缩放动画
        ScaleAnimation sa = new ScaleAnimation(0,1,0,1, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        sa.setDuration(2000);
        sa.setFillAfter(true);
        //渐变动画

        AlphaAnimation aa = new AlphaAnimation(0,1);
        aa.setDuration(2000);
        aa.setFillAfter(true);

        //旋转动画
        RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(2000);
        ra.setFillAfter(true);

        //2.把三个动画加入到AnimaitionSet,没有先后
        AnimationSet set = new AnimationSet(false);

        set.addAnimation(sa);
        set.addAnimation(aa);
        set.addAnimation(ra);
        //3.View.startAnimation(动画）；
        rl_splash.startAnimation(set);
        //监听动画
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //true:曾经进入过主页

                boolean isEnterMained = SpUtils.getBooleanData(SplashActivity.this, START_MAIN);
                if(isEnterMained){
                    //曾经进入过主页面
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    //引导页面
                    Intent intent = new Intent(SplashActivity.this,GuideActivity.class);
                    startActivity(intent);
                }
//            Toast.makeText(SplashActivity.this,"动画播放完成",Toast.LENGTH_SHORT).show();
                //动画播放完成后进入引导页面

                //关闭欢迎页面
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
