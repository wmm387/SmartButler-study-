package com.wmm1995.smartbutler;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.wmm1995.smartbutler.fragment.ButlerFragment;
import com.wmm1995.smartbutler.fragment.GirlFragment;
import com.wmm1995.smartbutler.fragment.WechatFragment;
import com.wmm1995.smartbutler.ui.CourierActivity;
import com.wmm1995.smartbutler.ui.PhoneActivity;
import com.wmm1995.smartbutler.ui.SettingActivity;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    //TabLayout
    private TabLayout mTabLayout;
    //ViewPager
    private ViewPager mViewPager;
    //Title
    private List<String> mTilte;
    //Fragment
    private List<Fragment>mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //去掉阴影
        getSupportActionBar().setElevation(0);

        initData();
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, 0 , 0, "快递查询");
        menu.add(Menu.NONE, 0 , 1, "归属地查询");
        menu.add(Menu.NONE, 0 , 2, "设置");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getOrder()){
            case  0 :
                startActivity(new Intent(this, CourierActivity.class));
                break;
            case  1 :
                startActivity(new Intent(this, PhoneActivity.class));
                break;
            case  2 :
                startActivity(new Intent(this, SettingActivity.class));
                break;
        }
        return true;
    }

    //初始化数据
    private void initData() {
        mTilte = new ArrayList<>();
        mTilte.add(getString(R.string.butler_service));
        mTilte.add(getString(R.string.wechat));
        mTilte.add(getString(R.string.girl));

        mFragment = new ArrayList<>();
        mFragment.add(new ButlerFragment());
        mFragment.add(new WechatFragment());
        mFragment.add(new GirlFragment());
    }

    //初始化View
    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);

        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());

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
                return mTilte.get(position);
            }
        });

        mTabLayout.setupWithViewPager(mViewPager);
    }
}
