package com.wenld.flea.common;


import java.util.HashMap;
import java.util.Map;

/**
 * @author Wenld
 *         created at 2016/8/17 13:46
 */
public class ESApi {

    public static void GetUserList( BaseApiCallback baseApiCallback) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("controller", "file");
        paramsMap.put("method", "requestGetUserList");
        ApiRequest.postAPI(AppConfig.URL, paramsMap, baseApiCallback);
    }

    public static void logon(String name, String pwd, BaseApiCallback baseApiCallback) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("controller", "user");
        paramsMap.put("method", "requestUserLogin");
        paramsMap.put("user_id", name);
        paramsMap.put("user_password", pwd);
        ApiRequest.postAPI(AppConfig.URL, paramsMap, baseApiCallback);
    }
}
