package com.jacob.zuimei.indicator.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.jacob.zuimei.indicator.bean.AppBean;

import java.util.List;

/**
 * Created by jacob-wj on 2015/4/7.
 */
public class ZuiMeiIndicator extends LinearLayout implements ViewPager.OnPageChangeListener{

    private ViewPager mViewPager;

    public static final float RADIO_WIDTH = 1/7f;

    public static final int  COUNT = 7;

    private float mItemWidth;

    private int mCurrentPosition;

    private List<AppBean> mAppBeanList;


    public ZuiMeiIndicator(Context context) {
        this(context, null);
    }

    public ZuiMeiIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZuiMeiIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mItemWidth = getScreenWidth()*RADIO_WIDTH;


    }


    public void setViewPager(ViewPager viewPager ,int position){
        this.mViewPager = viewPager;
        mCurrentPosition = position;
        if (mViewPager != null){
            mViewPager.setCurrentItem(position);
            mViewPager.setOnPageChangeListener(this);
        }

    }


    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {
        mCurrentPosition  = i;
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }


    /**
     * 获取屏幕的宽度
     */
    private int getScreenWidth(){
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public void setIndicatorItems(List<AppBean> appBeanList) {
        this.mAppBeanList = appBeanList;
    }
}
