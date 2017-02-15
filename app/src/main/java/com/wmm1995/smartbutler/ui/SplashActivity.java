package com.wmm1995.smartbutler.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.wmm1995.smartbutler.MainActivity;
import com.wmm1995.smartbutler.R;
import com.wmm1995.smartbutler.utils.ShareUtils;
import com.wmm1995.smartbutler.utils.StaticClass;
import com.wmm1995.smartbutler.utils.UtilTools;

import static android.graphics.Typeface.createFromAsset;
import static com.wmm1995.smartbutler.utils.ShareUtils.getBoolean;

/**
 * Created by Administrator on 2017/2/10.
 * 闪屏页
 */

public class SplashActivity extends AppCompatActivity {

    /**
     * 1.延时2000ms
     * 2.判断程序是否第一次运行
     * 3.自定义字体
     * 4.Activity全屏主题
     */

    private TextView tvSplash;

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.HANDLER_SPLASH:
                    //判断程序是否是第一次运行
                    if (isFirst()) {
                        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                    } else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
    }

    private void initView() {

        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH, 2000);
        tvSplash = (TextView) findViewById(R.id.tv_splash);

        //设置字体
        UtilTools.setFont(this,tvSplash);
    }

    //判断程序是否第一次运行
    public boolean isFirst() {
        boolean isFirst = ShareUtils.getBoolean(this, StaticClass.SHARE_IS_FIRST, true);
        if (isFirst) {
            ShareUtils.putBoolean(this,StaticClass.SHARE_IS_FIRST,false);//标记已经启动过app
            //是第一次运行
            return true;
        } else {
            return false;
        }
    }

    //禁止返回键
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
