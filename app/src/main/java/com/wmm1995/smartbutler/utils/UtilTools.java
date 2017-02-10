package com.wmm1995.smartbutler.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/2/10.
 * 工具统一类
 */

public class UtilTools {

    //设置字体
    public static void setFont(Context mContext, TextView textView) {
        Typeface font = Typeface.createFromAsset(mContext.getAssets(), "fonts/FONT.TTF");
        textView.setTypeface(font);
    }
}
