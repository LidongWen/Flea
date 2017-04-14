package com.wenld.flea.common;

import android.net.Uri;

import com.wenld.baselib.http.HttpUtils;
import com.wenld.baselib.http.callback.EngineCallBack;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.File;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Wenld on 2016/06/27.
 */
public class ApiRequest {
    private static final String mParameter = "parameter";
    private static final String mToken = "token";
    private static HttpUtils instance = HttpUtils.getInstance();

    public static void postAPI(String url, Map<String, String> params, EngineCallBack callback) {
        String content = Judgeurl(url);
        instance.post()
                .url(content)
                .params(params)
                .build()
                .execute(callback);
//        instance.post()
//                .url(Const.URL)
//                .addParams(ApiRequest.UrlBuide(params))
//                .build()
//                .execute(callback);
    }

    public static void getAPI(String url, Map<String, String> params, EngineCallBack callback) {
        String content = ApiRequest.UrlBuide(Judgeurl(url), params);
        instance.post()
                .url(content)
                .build()
                .execute(callback);
    }

    /**
     * 描述:普通get请求
     * Created by Vamoose on 2016/2/17.
     */
    public static void getAPI(String url, Callback callback) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(callback);
    }

    public static void postLargeAPI(Map<String, String> params, EngineCallBack callback) {
//        String content = ApiRequest.UrlBuide(Const.URL, params);
        instance.post()
                .url(AppConfig.URL)
                .params(params)
                .build()
                .execute(callback);
//        instance.post()
//                .url(Const.URL)
//                .addParams(ApiRequest.UrlBuide(params))
//                .build()
//                .execute(callback);
    }


    /**
     * 带  header post请求
     *
     * @param params
     * @param headers
     * @param callback
     */
    public static void postAPIandHeader(Map<String, String> params, Map<String, String> headers, EngineCallBack callback) {
        String content = ApiRequest.UrlBuide(AppConfig.URL, params);
        instance.post()
                .url(content)
                .headers(headers)
                .build()
                .execute(callback);
    }

    /**
     * 带header post请求
     *
     * @param url
     * @param params
     * @param headers
     * @param callback
     */
    public static void postAPIandHeader(String url, Map<String, String> params, Map<String, String> headers, EngineCallBack callback) {
        String content = ApiRequest.UrlBuide(url, params);
        instance.post()
                .url(content)
                .headers(headers)
                .build()
                .execute(callback);
    }


    /**
     * 描述:上传
     * Created by Vamoose on 2016/2/17.
     */
    public static void uploadAPI(String url, String uploadName, Map<String, String> headers, File file, EngineCallBack callback) {
        instance.post()
                .addFile(uploadName, file.getName(), file)
                .url(url)
                .headers(headers)
                .build()
                .execute(callback);
    }

    public static String UrlBuide(String mUrl, Map<String, String> mParamsMap) {
        Uri.Builder builder = Uri.parse(mUrl).buildUpon();
        Iterator<String> it = mParamsMap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            String value = mParamsMap.get(key);
            builder.appendQueryParameter(key, value);
        }
        return builder.toString();
    }

    public static String Judgeurl(String weburl) {

        if (weburl == null) {
            return "";
        }
        if (weburl.length() > 0) {
            if (weburl.toLowerCase(Locale.ENGLISH).trim().indexOf("http://") < 0 && weburl.toLowerCase(Locale.ENGLISH).trim().indexOf("https://") < 0) {
                weburl = AppConfig.URL + weburl;
            }
        }
        return Uri.decode(weburl);
    }
}
