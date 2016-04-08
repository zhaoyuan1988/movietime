package com.atguigu.movietime.utils;

/**
 * Created by Qzhang on 2016/3/25 0025.Email:1123888210.com
 */
public class Url {
    /**
     * 服务器地址
     */
    //public static  final String BASE_URL = "http://192.168.10.70:8080/zhbj";
    public static  final String BASE_URL = "http://192.168.1.102:8080/zhbj";

    //当前的服务器，就在本地，就可以用
//    public static  final String BASE_URL = "http://10.0.2.2:8080/zhbj";

    /**http://localhost:8080/zhbj/categories.json
     * 新闻中心的地址
     */
    public static String NEWSCENTER_URL = BASE_URL+"/categories.json";
}
