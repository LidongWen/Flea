package com.wenld.flea.common;


import com.wenld.baselib.http.callback.EngineCallBack;
import com.wenld.flea.App;

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
        ApiRequest.postAPI("http://wanghong.magic-future.com/loginaction.php", paramsMap, baseApiCallback);
    }

    /**
     * 获取商品列表
     *
     * @param type
     * @param baseApiCallback
     */
    public static void commodityList(String type, BaseApiCallback baseApiCallback) {
        Map<String, String> paramsMap = new HashMap<>();
//        paramsMap.put("controller", "user");
        paramsMap.put("method", "list");
        paramsMap.put("page", "1");
        paramsMap.put("num", "100");
        paramsMap.put("order", "time");
        paramsMap.put("by", "up");
        paramsMap.put("type", type);
        ApiRequest.postAPI("http://wanghong.magic-future.com/getgoods.php", paramsMap, baseApiCallback);
    }

    /**
     * 商品上传接口
     * <p>
     * uid		用户id
     * title		标题
     * price	价格
     * describe	描述
     * classify	分类id
     * contact 	内容
     * img 		图片
     *
     * @param baseApiCallback
     */
    public static void upload(String title, String price, String describe, String classify, String contact, BaseApiCallback baseApiCallback) {
        Map<String, String> paramsMap = new HashMap<>();
//        paramsMap.put("controller", "user");
        paramsMap.put("uid", App.getInstance().user.getId() + "");
        paramsMap.put("title", title);
        paramsMap.put("price", price);
        paramsMap.put("describe", describe);
        paramsMap.put("classify", classify);
        paramsMap.put("contact", contact);
        ApiRequest.postAPI("http://wanghong.magic-future.com/addgoods.php", paramsMap, baseApiCallback);
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

    /**
     * 获取我的商品
     *
     * @param baseApiCallback
     */
    public static void getMyGoods(BaseApiCallback baseApiCallback) {
        Map<String, String> paramsMap = new HashMap<>();
//        paramsMap.put("controller", "user");
        paramsMap.put("method", "gbg");
        paramsMap.put("uid", App.getInstance().user.getId() + "");
        ApiRequest.postAPI("http://wanghong.magic-future.com/getgoods.php", paramsMap, baseApiCallback);
    }

    /**
     * 获取收藏商品
     *
     * @param baseApiCallback
     */
    public static void getCollectGoods(BaseApiCallback baseApiCallback) {
        Map<String, String> paramsMap = new HashMap<>();
//        paramsMap.put("controller", "user");
        paramsMap.put("method", "list");
        paramsMap.put("uid", App.getInstance().user.getId() + "");
        ApiRequest.postAPI("http://wanghong.magic-future.com/sc.php", paramsMap, baseApiCallback);
    }

    /**
     * 添加收藏商品
     *
     * @param baseApiCallback
     */
    public static void addCollect(String gid, BaseApiCallback baseApiCallback) {
        Map<String, String> paramsMap = new HashMap<>();
//        paramsMap.put("controller", "user");
        paramsMap.put("method", "add");
        paramsMap.put("gid", gid);
        paramsMap.put("uid", App.getInstance().user.getId() + "");
        ApiRequest.postAPI("http://wanghong.magic-future.com/sc.php", paramsMap, baseApiCallback);
    }

    /**
     * 取消收藏商品
     *
     * @param baseApiCallback
     */
    public static void cancelCollect(String gid, BaseApiCallback baseApiCallback) {
        Map<String, String> paramsMap = new HashMap<>();
//        paramsMap.put("controller", "user");
        paramsMap.put("method", "del");
        paramsMap.put("gid", gid);
        paramsMap.put("uid", App.getInstance().user.getId() + "");
        ApiRequest.postAPI("http://wanghong.magic-future.com/sc.php", paramsMap, baseApiCallback);
    }
}
