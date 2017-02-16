package com.wmm1995.smartbutler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.wmm1995.smartbutler.R;
import com.wmm1995.smartbutler.utils.PicassoUtils;

import uk.co.senab.photoview.PhotoViewAttacher;

//TODO 图片下载功能

public class GirlActivity extends BaseActivity {

    private PhotoViewAttacher mAttacher;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl);

        imageView = (ImageView) findViewById(R.id.imageview);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        //解析图片
        PicassoUtils.loadImageView(this, url, imageView);
        //缩放
        mAttacher = new PhotoViewAttacher(imageView);
        //刷新
        mAttacher.update();
    }
}
