package com.wenld.flea.common;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/8.
 */

public class BaseDataModel implements Serializable {
    private String err;
    private String msg;
    private String data;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
