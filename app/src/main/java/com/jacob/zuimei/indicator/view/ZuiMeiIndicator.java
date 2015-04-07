package com.jacob.zuimei.indicator.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
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

    /**
     *传入的ViewPager
     */
    private ViewPager mViewPager;
    /**
     *默认每个Indicator的宽度是屏幕的1/7
     */
    public static final float RADIO_WIDTH = 1/7f;
    /**
     * 默认可见的Indicator的个数是7个
     */
    public static final int  TAB_VISIBLE_COUNT = 7;
    /**
     * 每个item的宽度
     */
    private int mTabItemWidth;
    /**
     * 当前选中的页面的position
     */
    private int mCurrentPosition;
    /**
     *传入的数据
     */
    private List<AppBean> mAppBeanList;
    /**
     * 可见的item的个数，默认7个
     */
    private int mTabVisibleCount = TAB_VISIBLE_COUNT;
    /**
     * 对外的接口
     */
    private OnPageChangeListener mPageChangeListener;

    /**
     * 整个layout偏移的距离
     */
    private int mTranslationX =0;


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

    /**
     *传入的ViewPager，并且添加OnPageChangeListener
     */
    public void setViewPager(ViewPager viewPager ,int position){
        this.mViewPager = viewPager;
        mCurrentPosition = position;
        if (mViewPager != null){
            mViewPager.setCurrentItem(position);
            mViewPager.setOnPageChangeListener(this);
        }
        showIndicatorAtPosition(position);
    }

    /**
     * 显示指定位置上的Indicator，含有动画效果
     */
    private void showIndicatorAtPosition(int position){
        View childOld = getChildAt(position);
        if (childOld instanceof  IndicatorItemView){
            ((IndicatorItemView) childOld).showWithAnim();
        }
    }

    /**
     * 隐藏指定位置上的Indicator，含有动画效果
     */
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
        if (mPageChangeListener != null){
            mPageChangeListener.onPageSelected(position);
        }
    }

    @Override
    public void onPageScrolled(int position, float offset, int i2) {
//        Log.e("TAG",position+"++"+offset);
        onScroll(position, offset);
        if (mPageChangeListener != null){
            mPageChangeListener.onPageScrolled(position, offset, i2);
        }
    }

    /**
     *  重点：这里为了保证viewpager在滑动的过程中，indicator一致显示在第4个位置（中间的位置）
     *  所以在滑动过程中，需要调整Layout的位置
     */
    private void onScroll(int position, float offset) {
        int count = getChildCount();
        if (count>mTabVisibleCount){
            if ((position >= mTabVisibleCount-4)&& (position<count-4)){
                mTranslationX = (int) (mTabItemWidth * ((position+1)-(mTabVisibleCount-3)+offset));
                this.scrollTo(mTranslationX,0);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        if (mPageChangeListener != null){
            mPageChangeListener.onPageScrollStateChanged(i);
        }
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
                        if (mViewPager != null&& mCurrentPosition!=index){
                            mViewPager.setCurrentItem(index,true);
                        }
                }
            });
            addView(indicatorItemView);
        }
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                Log.e("TAG","actionDown");
////                showThePointIndicator(ev);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.e("TAG","ACTION_MOVE"+ev.getX());
//                showThePointIndicator(ev);
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.e("TAG","ACTION_UP");
//                showThePointIndicator(ev);
//                mViewPager.setCurrentItem(mCurrentPosition);
//                break;
//        }
//
//        return super.dispatchTouchEvent(ev);
//    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("TAG","actionDown");
//                showThePointIndicator(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("TAG","ACTION_MOVE"+ev.getX());
                showThePointIndicator(ev);
                break;
            case MotionEvent.ACTION_UP:
                Log.e("TAG","ACTION_UP");
                showThePointIndicator(ev);
                mViewPager.setCurrentItem(mCurrentPosition);
                break;
        }

        return super.onTouchEvent(ev);
    }

    private void showThePointIndicator(MotionEvent ev) {
        int position = getPositionByMovePoint(ev.getX());
        hideIndicatorAtPosition(mCurrentPosition);
        showIndicatorAtPosition(position);
        mCurrentPosition = position;
    }

    private int getPositionByMovePoint(float touchX){
        float distance = touchX+mTranslationX;
        return (int) (distance/mTabItemWidth);
    }

    /**
     * 对外的接口，由于viewpager的监听事件是在内部处理的，
     * 所以再写一个一样的接口对外使用
     */
    public interface OnPageChangeListener{
        public void onPageSelected(int position);
        public void onPageScrolled(int position, float offset, int i2);
        public void onPageScrollStateChanged(int i);

    }

    public void setOnPageChangeListener(OnPageChangeListener listener){
        this.mPageChangeListener = listener;
    }
}
