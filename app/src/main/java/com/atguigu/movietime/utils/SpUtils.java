package com.atguigu.movietime.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils {
    private static final String SP_NAME = "beijingnew";
    private static final String ISLOGIN = "islogin";

    /**
     * 保存boolean类型的数据
     * @param context
     * @param key
     * @param value
     */
    public static void setBooleanData(Context context,String key,boolean value){
        SharedPreferences sp=context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

    /**
     * 取保存的boolean类型的数据
     * @param context
     * @param key
     * @return
     */
    public static boolean getBooleanData(Context context,String key){
        SharedPreferences sp=context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key,false);
    }
    /**
     * 保存int类型的数据
     * @param context
     * @param key
     * @param value
     */
    public static void setIntData(Context context,String key,Integer value){
        SharedPreferences sp=context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).commit();
    }

    /**
     * 取保存的int类型的数据
     * @param context
     * @param key
     * @return
     */
    public static int getIntData(Context context,String key){
        SharedPreferences sp=context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, 0);
    }


    /**
     * 获取String类型的数据
     * @param context
     * @param key
     * @return
     */

    public static String getString(Context context,String key) {
        SharedPreferences sp=context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    /**
     * 保存String类型的数据
     * @param context
     * @param key
     * @param value
     */
    public static void putString(Context context,String key, String value) {
        SharedPreferences sp=context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

}
