package com.atguigu.movietime.base;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Qzhang on 2016/3/24 0024.Email:1123888210.com
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志
    }
}
