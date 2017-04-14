package com.wenld.flea.common;

/**
 * App全局配置常量
 * 基本只放final的，主要用于多版本配置与默认配置
 */
public class AppConfig {

    public static final boolean isDev = true; // 开发模式   //开发模式标识
    public static final int DEBUG_LEVEL = 0; // 开发模式    //Log打印级别-0都打印: Log.VERBOSE~Log.ASSERT
    public static final int HTTP_CONNECT_TIMEOUT = 20;   //网络连接超时时间-单位秒
    public static final int HTTP_REQUEST_TIMEOUT = 20; //网络请求超时时间-单位秒
    public static final int HTTP_UPLOAD_TIMEOUT = 30;    //上传超时时间-单位秒
    public static final int scrolleTime = 5000;   //广告切换时间

    public static final String SUCCESS_CODE = "0";
    public static final String REALESE_URL = "https://app.qipai.com/qiyun/index.php";
    public static String URL = REALESE_URL;//"http://61.131.48.180:11380/";

}
