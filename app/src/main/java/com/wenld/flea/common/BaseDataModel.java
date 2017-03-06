package com.wenld.flea.common;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/8.
 */

public class BaseDataModel implements Serializable {
    /**
     * code : 200
     * message : sucess
     * datatype : 1
     * data : [{"id":"1","img_name":"https://app.qipai.com:443/seven/images/appimg/O2O.jpg","app_name":"O2O导购","app_size":"20M","app_desc":"导购平台","app_id":"22222","app_in_version":"1","app_out_version":"1","app_url":"https://app.qipai.com:443/seven/images/appname/1111.apk","create_time":"111","flag":"0","order_no":"11"}]
     * limit :
     */

    private String code;
    private String message;
    private String datatype;
    private String limit;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    /**
     * id : 1
     * img_name : https://app.qipai.com:443/seven/images/appimg/O2O.jpg
     * app_name : O2O导购
     * app_size : 20M
     * app_desc : 导购平台
     * app_id : 22222
     * app_in_version : 1
     * app_out_version : 1
     * app_url : https://app.qipai.com:443/seven/images/appname/1111.apk
     * create_time : 111
     * flag : 0
     *order_no : 11
     */

    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

}
