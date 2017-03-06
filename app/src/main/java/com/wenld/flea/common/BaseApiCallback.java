package com.wenld.flea.common;

import com.wenld.commontools.FastJsonUtil;
import com.wenld.commontools.LogUtil;
import com.wenld.commontools.StringUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by Wenld on 2016/06/27.
 */
public abstract class BaseApiCallback extends Callback<BaseDataModel> {
    @Override
    public BaseDataModel parseNetworkResponse(Response response, int id) {
        try {
            String aa = response.body().string();
            LogUtil.i("TAG-->", "" + aa);

            return FastJsonUtil.getObject(aa, BaseDataModel.class);
        } catch (Exception e) {
//            LogUtil.e("BaseApiCallback解析异常" + response.body());
            e.printStackTrace();
            return new BaseDataModel();
        }
    }

    @Override
    public void onError(Call request, Exception e, int id) { //客户端异常
//        onClientError(request + e.toString());
    }

    @Override
    public void onResponse(BaseDataModel response, int id) {
        if (response == null || response.getCode() == null) {
            onClientError("接口返回异常");
            return;
        }
        switch (response.getCode()) {
            case AppConfig.SUCCESS_CODE://0:成功
                onAPISuccess(StringUtils.processNullStr(response.getData()));
                break;
            default://其他类型的错误消息
                onAPIFailure(StringUtils.processNullStr(response.getMessage()));
                break;
        }
    }

    /**
     * 服务端返回正确回调
     *
     * @param data
     */
    protected abstract void onAPISuccess(String data);

    /**
     * 服务端返回正确错误
     *
     * @param msg
     */
    protected abstract void onAPIFailure(String msg);

    /**
     * 服务端异常
     *
     * @param msg
     */
    protected void onAPIError(String msg) {
//        LogUtil.e("服务端异常" + msg);
    }

    /**
     * 描述:客户端异常
     * Created by Vamoose on 2016/1/31.
     *
     * @param
     * @return
     */
    protected void onClientError(String msg) {
        LogUtil.e("网络异常 , 请检查网络!"/* + msg*/);
        onAPIFailure("");
    }

    protected void Token_Invalid() {
    }

    private int size = 0;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
