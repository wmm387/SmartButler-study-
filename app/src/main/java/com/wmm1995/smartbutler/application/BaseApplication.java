package com.wmm1995.smartbutler.application;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;
import com.wmm1995.smartbutler.utils.StaticClass;

/**
 * Created by Administrator on 2017/2/10.
 */

public class BaseApplication extends Application {

    //创建
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Bugly
        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BUGLY_APP_ID, true);
    }
}
