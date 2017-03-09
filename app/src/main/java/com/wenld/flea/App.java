package com.wenld.flea;

import android.app.Application;

import com.wenld.commontools.AllUtilConfig;
import com.wenld.flea.bean.User;

/**
 * <p/>
 * Author: 温利东 on 2017/3/6 17:28.
 * blog: http://blog.csdn.net/sinat_15877283
 * github: https://github.com/LidongWen
 */

public class App extends Application {
    public static User user;
    private static App globalInfo = null;
    @Override
    public void onCreate() {
        super.onCreate();
        AllUtilConfig.LogSwitch = true;
        globalInfo = this;
    }


    public static App getInstance() {
        return globalInfo;
    }

}
