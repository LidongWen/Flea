package com.wenld.flea.common;


import com.wenld.baselib.http.callback.EngineCallBack;
import com.wenld.flea.App;
import com.wenld.flea.aop.LogonPermission;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wenld
 *         created at 2016/8/17 13:46
 */
public class ESApi {

    public static void logon(String name, String pwd, CallBackBaseData callBackBaseData) {
        Map<String, String> paramsMap = new HashMap<>();
//        paramsMap.put("controller", "user");
        paramsMap.put("method", "logon");
        paramsMap.put("user_id", name);
        paramsMap.put("user_password", pwd);
        ApiRequest.postAPI("http://wanghong.magic-future.com/loginaction.php", paramsMap, callBackBaseData);
    }

    /**
     * 获取商品列表
     *
     * @param type
     * @param callBackBaseData
     */
    public static void commodityList(String type, CallBackBaseData callBackBaseData) {
        Map<String, String> paramsMap = new HashMap<>();
//        paramsMap.put("controller", "user");
        paramsMap.put("method", "list");
        paramsMap.put("page", "1");
        paramsMap.put("uid", App.getInstance().user==null?"": App.getInstance().user.getId() + "");
        paramsMap.put("num", "100");
        paramsMap.put("order", "time");
        paramsMap.put("by", "up");
        paramsMap.put("type", type);
        ApiRequest.postAPI("http://wanghong.magic-future.com/getgoods.php", paramsMap, callBackBaseData);
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
     * @param callBackBaseData
     */
    public static void upload(String title, String price, String describe, String classify, String contact, CallBackBaseData callBackBaseData) {
        Map<String, String> paramsMap = new HashMap<>();
//        paramsMap.put("controller", "user");
        paramsMap.put("uid", App.getInstance().user.getId() + "");
        paramsMap.put("title", title);
        paramsMap.put("price", price);
        paramsMap.put("describe", describe);
        paramsMap.put("classify", classify);
        paramsMap.put("contact", contact);
        ApiRequest.postAPI("http://wanghong.magic-future.com/addgoods.php", paramsMap, callBackBaseData);
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
     * @param callBackBaseData
     */
    public static void getMyGoods(CallBackBaseData callBackBaseData) {
        Map<String, String> paramsMap = new HashMap<>();
//        paramsMap.put("controller", "user");
        paramsMap.put("method", "gbu");
        paramsMap.put("uid", App.getInstance().user.getId() + "");
        ApiRequest.postAPI("http://wanghong.magic-future.com/getgoods.php", paramsMap, callBackBaseData);
    }

    /**
     * 获取收藏商品
     *
     * @param callBackBaseData
     */
    public static void getCollectGoods(CallBackBaseData callBackBaseData) {
        Map<String, String> paramsMap = new HashMap<>();
//        paramsMap.put("controller", "user");
        paramsMap.put("method", "list");
        paramsMap.put("uid", App.getInstance().user.getId() + "");
        ApiRequest.postAPI("http://wanghong.magic-future.com/sc.php", paramsMap, callBackBaseData);
    }

    /**
     * 添加收藏商品
     *
     * @param callBackBaseData
     */
    @LogonPermission(toLogon = false)
    public static void addCollect(String gid, CallBackBaseData callBackBaseData) {
        Map<String, String> paramsMap = new HashMap<>();
//        paramsMap.put("controller", "user");
        paramsMap.put("method", "add");
        paramsMap.put("gid", gid);
        paramsMap.put("uid", App.getInstance().user.getId() + "");
        ApiRequest.postAPI("http://wanghong.magic-future.com/sc.php", paramsMap, callBackBaseData);
    }

    /**
     * 取消收藏商品
     *
     * @param callBackBaseData
     */
    @LogonPermission(toLogon = false)
    public static void cancelCollect(String gid, CallBackBaseData callBackBaseData) {
        Map<String, String> paramsMap = new HashMap<>();
//        paramsMap.put("controller", "user");
        paramsMap.put("method", "del");
        paramsMap.put("gid", gid);
        paramsMap.put("uid", App.getInstance().user.getId() + "");
        ApiRequest.postAPI("http://wanghong.magic-future.com/sc.php", paramsMap, callBackBaseData);
    }

    /**
     * 修改发布状态
     * @param gid
     * @param status ，0为关闭，1为开启
     * @param callBackBaseData
     */
    @LogonPermission(toLogon = false)
    public  static void statusGood(String gid,String status ,CallBackBaseData callBackBaseData){
        Map<String, String> paramsMap = new HashMap<>();
//        paramsMap.put("controller", "user");
        paramsMap.put("method", "change");
        paramsMap.put("gid", gid);
        paramsMap.put("status", status);
        ApiRequest.postAPI("http://wanghong.magic-future.com/goodsaction.php", paramsMap, callBackBaseData);
    }
}
