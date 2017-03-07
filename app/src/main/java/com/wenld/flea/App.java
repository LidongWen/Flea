package com.wenld.flea;

import android.app.Application;

import com.wenld.commontools.AllUtilConfig;

/**
 * <p/>
 * Author: 温利东 on 2017/3/6 17:28.
 * blog: http://blog.csdn.net/sinat_15877283
 * github: https://github.com/LidongWen
 */

public class App extends Application {
    public final static int REQUEST_PICK_PICTURE = 0x118;  //本地选择图片
    public final static int REQUEST_CAPTURE = 0x119;       //拍照
    @Override
    public void onCreate() {
        super.onCreate();
        AllUtilConfig.LogSwitch = true;
    }
}
