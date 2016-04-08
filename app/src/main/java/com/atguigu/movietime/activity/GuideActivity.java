package com.atguigu.movietime.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.atguigu.movietime.R;
import com.atguigu.movietime.utils.DensityUtil;
import com.atguigu.movietime.utils.SpUtils;


public class GuideActivity extends Activity {
    private ViewPager viewpage_guide;
    private Button btn_start_main;
    private LinearLayout ll_point_gourp;
    private ImageView iv_red_point;
    private int screenPoint;
    private int margLeft;
    private int[] ids = {R.drawable.lead_bg1, R.drawable.lead_bg2, R.drawable.lead_bg3,R.drawable.lead_bg4};
    private int[] ivs = {R.drawable.lead_bg1_iv, R.drawable.lead_bg2_iv, R.drawable.lead_bg3_iv,R.drawable.lead_bg4_iv};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();

        addPoints();

        //使用ViewPager
        //1.在布局中定义ViewPager
        //2.在代码中实例化
        //3.准备数据-网络的或者本地的-封装成集合
        //4.设置适配器，PagerAdapter
        viewpage_guide.setAdapter(new MyPagerAdapter());
        //监听viewPager页面的改变来移动红点
        viewpage_guide.addOnPageChangeListener(new MyOnPageChangeListener());

        //跳转主页面
        btn_start_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //记录是否进入过主页面
                SpUtils.setBooleanData(GuideActivity.this, SplashActivity.START_MAIN, true);

                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        /**
         *
         * @param position 当前页面的位置
         * @param positionOffset 在屏幕移动百分比
         * @param positionOffsetPixels 屏幕上移动的像数
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

//            Log.e("TAG", "position==" + position + ",positionOffset==" + positionOffset + ",positionOffsetPixels==" + positionOffsetPixels);

            float leftMarg = margLeft * position + margLeft * positionOffset;

            RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(screenPoint,screenPoint);
            param.leftMargin = (int) leftMarg;
            iv_red_point.setLayoutParams(param);

        }

        @Override//被选中
        public void onPageSelected(int position) {
            //最后一页时显示按钮
            if (ids.length - 1 == position) {
                btn_start_main.setVisibility(View.VISIBLE);
            }else {
                btn_start_main.setVisibility(View.GONE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }



    class MyPagerAdapter extends PagerAdapter {

        @Override//实例化对应页面
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(GuideActivity.this);
            imageView.setBackgroundResource(ids[position]);
            container.addView(imageView);
            return imageView;
    }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return ids.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }

    private void initView() {
        viewpage_guide = (ViewPager)findViewById(R.id.viewpage_guide);
        btn_start_main = (Button)findViewById(R.id.btn_start_main);
        ll_point_gourp = (LinearLayout)findViewById(R.id.ll_point_gourp);
        iv_red_point = (ImageView)findViewById(R.id.iv_red_point);

        //得到红点间距        //View 从创建到显示过程中主要的方法，构造方法，onMeasure()-->onLayout()->onDraw()
        //onLayout()在这个阶段肯定还有距离
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());
    }

    class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener{

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onGlobalLayout() {
            //要反注册
            iv_red_point.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            //红点间距
            margLeft = ll_point_gourp.getChildAt(1).getLeft() - ll_point_gourp.getChildAt(0).getLeft();
        }
    }
    private void addPoints() {

        //把10当成dp，在不同的手机上转换成不同值
        screenPoint = DensityUtil.dip2px(this, 10);
        for(int i = 0; i < ids.length; i++) {
            ImageView point_gray = new ImageView(this);
            point_gray.setBackgroundResource(R.drawable.point_gray);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(screenPoint,screenPoint);
            if(i!=0) {
                param.leftMargin = screenPoint;
                point_gray.setLayoutParams(param);
            }
            ll_point_gourp.addView(point_gray);
        }
    }
}
