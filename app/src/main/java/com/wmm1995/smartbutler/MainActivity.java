package com.wmm1995.smartbutler;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.wmm1995.smartbutler.fragment.ButlerFragment;
import com.wmm1995.smartbutler.fragment.GirlFragment;
import com.wmm1995.smartbutler.fragment.UserFragment;
import com.wmm1995.smartbutler.fragment.WechatFragment;
import com.wmm1995.smartbutler.ui.SettingActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //TabLayout
    private TabLayout mTabLayout;
    //ViewPager
    private ViewPager mViewPager;
    //Title
    private List<String> mTitle;
    //Fragment
    private List<Fragment>mFragment;
    //FAB
    private FloatingActionButton  fabSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //去掉阴影
        getSupportActionBar().setElevation(0);

        initData();
        initView();
    }

    //初始化数据
    private void initData() {
        mTitle = new ArrayList<>();
        mTitle.add(getString(R.string.butler_service));
        mTitle.add(getString(R.string.wechat));
        mTitle.add(getString(R.string.girl));
        mTitle.add(getString(R.string.user_center));

        mFragment = new ArrayList<>();
        mFragment.add(new ButlerFragment());
        mFragment.add(new WechatFragment());
        mFragment.add(new GirlFragment());
        mFragment.add(new UserFragment());
    }

    //初始化View
    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        fabSetting = (FloatingActionButton) findViewById(R.id.fab_setting);
        fabSetting.setOnClickListener(this);
        //默认隐藏fab
        fabSetting.setVisibility(View.GONE);

        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());

        //mViewPager滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //第一面由于有输入按钮，所以隐藏fab
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    fabSetting.setVisibility(View.GONE);
                } else {
                    fabSetting.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //选中的item
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            //返回item的个数
            @Override
            public int getCount() {
                return mFragment.size();
            }

            //设置标题
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });

        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
        }
    }
}
