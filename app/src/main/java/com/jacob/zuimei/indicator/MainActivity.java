package com.jacob.zuimei.indicator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.jacob.zuimei.indicator.bean.AppBean;
import com.jacob.zuimei.indicator.view.ZuiMeiIndicator;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private ZuiMeiIndicator mIndicator;
    private List<AppBean> mAppList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAppData();
        mPagerAdapter =new PagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(mPagerAdapter);

        mIndicator = (ZuiMeiIndicator) findViewById(R.id.indicator);
        mIndicator.setIndicatorItems(mAppList);
        mIndicator.setViewPager(mViewPager,0);
    }

    private void initAppData(){
        AppBean appBean1 = new AppBean(R.drawable.ic_menu_1,"YouToBe");
        AppBean appBean2 = new AppBean(R.drawable.ic_menu_2,"Android");
        AppBean appBean3 = new AppBean(R.drawable.ic_menu_3,"Yahoo");
        AppBean appBean4 = new AppBean(R.drawable.ic_menu_4,"Twitter");
        AppBean appBean5 = new AppBean(R.drawable.ic_menu_5,"TomCat");
        AppBean appBean6 = new AppBean(R.drawable.ic_menu_6,"AngryBird");
        AppBean appBean7 = new AppBean(R.drawable.ic_menu_1,"YouToBe");
        AppBean appBean8 = new AppBean(R.drawable.ic_menu_2,"Android");
        AppBean appBean9 = new AppBean(R.drawable.ic_menu_3,"Yahoo");
        AppBean appBean10 = new AppBean(R.drawable.ic_menu_4,"Twitter");
        AppBean appBean11 = new AppBean(R.drawable.ic_menu_5,"TomCat");
        AppBean appBean12 = new AppBean(R.drawable.ic_menu_6,"AngryBird");
        mAppList.add(appBean1);
        mAppList.add(appBean2);
        mAppList.add(appBean3);
        mAppList.add(appBean4);
        mAppList.add(appBean5);
        mAppList.add(appBean6);
        mAppList.add(appBean7);
        mAppList.add(appBean8);
        mAppList.add(appBean9);
        mAppList.add(appBean10);
        mAppList.add(appBean11);
        mAppList.add(appBean12);
    }

    private class PagerAdapter extends FragmentPagerAdapter{

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return ViewPagerFragment.newInstance(mAppList.get(i).getName());
        }

        @Override
        public int getCount() {
            return mAppList.size();
        }
    }


}
