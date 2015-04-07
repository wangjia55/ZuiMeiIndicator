package com.jacob.zuimei.indicator.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.jacob.zuimei.indicator.bean.AppBean;

import java.util.List;

/**
 * Created by jacob-wj on 2015/4/7.
 */
public class ZuiMeiIndicator extends LinearLayout implements ViewPager.OnPageChangeListener{

    private Context mContext;

    private ViewPager mViewPager;

    public static final float RADIO_WIDTH = 1/7f;

    public static final int  TAB_VISIBLE_COUNT = 7;

    private int mTabItemWidth;

    private int mCurrentPosition;

    private List<AppBean> mAppBeanList;

    private int mTabVisibleCount = TAB_VISIBLE_COUNT;


    public ZuiMeiIndicator(Context context) {
        this(context, null);
    }

    public ZuiMeiIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZuiMeiIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        mTabItemWidth = (int) (getScreenWidth()*RADIO_WIDTH);
    }

    public void setViewPager(ViewPager viewPager ,int position){
        this.mViewPager = viewPager;
        mCurrentPosition = position;
        if (mViewPager != null){
            mViewPager.setCurrentItem(position);
            mViewPager.setOnPageChangeListener(this);
        }
        showIndicatorAtPosition(position);
    }

    private void showIndicatorAtPosition(int position){
        View childOld = getChildAt(position);
        if (childOld instanceof  IndicatorItemView){
            ((IndicatorItemView) childOld).showWithAnim();
        }
    }

    private void hideIndicatorAtPosition(int position){
        View childOld = getChildAt(position);
        if (childOld instanceof  IndicatorItemView){
            ((IndicatorItemView) childOld).hideWidthAnim();
        }
    }

    @Override
    public void onPageSelected(int position) {
        showIndicatorAtPosition(position);
        hideIndicatorAtPosition(mCurrentPosition);
        mCurrentPosition  = position;
    }

    @Override
    public void onPageScrolled(int position, float offset, int i2) {
//        Log.e("TAG",position+"++"+offset);
        onScroll(position, offset);
    }

    private void onScroll(int position, float offset) {
        int count = getChildCount();
        if (count>mTabVisibleCount){
            if ((position >= mTabVisibleCount-4)&& (position<count-4)){
                int translation = (int) (mTabItemWidth * ((position+1)-(mTabVisibleCount-3)+offset));
                this.scrollTo(translation,0);
            }
        }
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

    /**
     * 外部传入所有Indicator的资源文件
     */
    public void setIndicatorItems(List<AppBean> appBeanList) {
        this.mAppBeanList = appBeanList;
        int size = appBeanList.size();
        for (int i = 0; i < size; i++) {
            final  int index = i;
            IndicatorItemView indicatorItemView = new IndicatorItemView(mContext);
            LinearLayout.LayoutParams layoutParams = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.width = mTabItemWidth;
            indicatorItemView.hide();
            indicatorItemView.setLayoutParams(layoutParams);
            indicatorItemView.setImageDrawable(mAppBeanList.get(i).getDrawable());
            indicatorItemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                        if (mViewPager != null){
                            mViewPager.setCurrentItem(index,true);
                        }
                }
            });
            addView(indicatorItemView);
        }
    }
}
