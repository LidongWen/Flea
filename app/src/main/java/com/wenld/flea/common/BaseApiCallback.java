package com.wenld.flea.common;

import com.wenld.baselib.http.callback.EngineCallBack;
import com.wenld.commontools.LogUtil;
import com.wenld.commontools.StringUtils;


/**
 * Created by Wenld on 2016/06/27.
 */
public abstract class BaseApiCallback extends EngineCallBack<BaseDataModel> {

    @Override
    public void onError( Exception e, int id) { //客户端异常
//        onClientError(request + e.toString());
    }

    @Override
    public void onResponse(BaseDataModel response, int id) {
        if (response == null ) {
            onClientError("接口返回异常");
            return;
        }
        switch (response.getErr()) {
            case AppConfig.SUCCESS_CODE://0:成功
                onAPISuccess(StringUtils.processNullStr(response.getData()));
                break;
            default://其他类型的错误消息
                onAPIFailure(StringUtils.processNullStr(response.getMsg()));
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
