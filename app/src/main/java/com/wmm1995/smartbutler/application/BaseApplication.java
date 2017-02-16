package com.wmm1995.smartbutler.application;

import android.app.Application;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.tencent.bugly.crashreport.CrashReport;
import com.wmm1995.smartbutler.utils.StaticClass;

import cn.bmob.v3.Bmob;

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
        //初始化Bmob
        Bmob.initialize(this, StaticClass.BMOB_APP_ID);
        //语音
        SpeechUtility.createUtility(getApplicationContext(), SpeechConstant.APPID + "=" + StaticClass.VOICE_KEY);
    }
}
