package com.wenld.flea.common;


import com.wenld.baselib.http.callback.EngineCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wenld
 *         created at 2016/8/17 13:46
 */
public class ESApi {

    public static void logon(String name, String pwd, BaseApiCallback baseApiCallback) {
        Map<String, String> paramsMap = new HashMap<>();
//        paramsMap.put("controller", "user");
        paramsMap.put("method", "logon");
        paramsMap.put("user_id", name);
        paramsMap.put("user_password", pwd);
        ApiRequest.postAPI(AppConfig.URL, paramsMap, baseApiCallback);
    }

    /**
     * 获取商品列表
     *
     * @param name
     * @param baseApiCallback
     */
    public static void commodityList(String name, String flag, BaseApiCallback baseApiCallback) {
        Map<String, String> paramsMap = new HashMap<>();
//        paramsMap.put("controller", "user");
        paramsMap.put("method", "requestList");
        paramsMap.put("type", name);
        ApiRequest.postAPI(AppConfig.URL, paramsMap, baseApiCallback);
    }

    /**
     * 商品上传接口
     *
     * @param name
     * @param desc
     * @param link
     * @param pirce
     * @param type
     * @param baseApiCallback
     */
    public static void upload(String name, String desc, String link, String pirce, String type, BaseApiCallback baseApiCallback) {
        Map<String, String> paramsMap = new HashMap<>();
//        paramsMap.put("controller", "user");
        paramsMap.put("method", "requestUpload");
        paramsMap.put("user_id", name);
        paramsMap.put("desc", desc);
        paramsMap.put("link", link);
        paramsMap.put("pirce", pirce);
        paramsMap.put("type", type);
        ApiRequest.postAPI(AppConfig.URL, paramsMap, baseApiCallback);
    }

    /**
     * 注册
     *
     * @param user_id
     * @param user_password
     * @param baseApiCallback
     */
    public static void account(String user_id, String user_password, String un, String tel, EngineCallBack baseApiCallback) {
        Map<String, String> paramsMap = new HashMap<>();
//        paramsMap.put("controller", "user");
        paramsMap.put("method", "add");
        paramsMap.put("ua", user_id);
        paramsMap.put("up", user_password);
        paramsMap.put("un", un);
        paramsMap.put("tel", tel);
        ApiRequest.postAPI("http://wanghong.magic-future.com/loginaction.php", paramsMap, baseApiCallback);
    }
}
