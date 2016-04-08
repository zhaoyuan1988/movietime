package com.atguigu.movietime.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Qzhang on 2016/3/24 0024.Email:1123888210.com
 */
public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;//屏蔽触摸事件不做处理
    }

    //事件冲突:事件优先交给外层处理,但外层最后一个时无法处理
    //false 则将事件传给孩子处理,否则会由自己onTouchEvent处理

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
