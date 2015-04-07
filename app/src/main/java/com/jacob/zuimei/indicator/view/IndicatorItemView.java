package com.jacob.zuimei.indicator.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jacob.zuimei.indicator.R;

/**
 * Created by jacob-wj on 2015/4/7.
 */
public class IndicatorItemView extends RelativeLayout {

    private ImageView mImageView;
    private ObjectAnimator mAnimShow;
    private ObjectAnimator mAnimHide;
    public IndicatorItemView(Context context) {
        this(context, null);
    }

    public IndicatorItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_indicator_item,this);
        mImageView  = (ImageView) findViewById(R.id.image_view_item);
        mAnimShow = ObjectAnimator.ofFloat(this, View.TRANSLATION_Y,70,-8,5,0);
        mAnimShow.setDuration(400);
        mAnimShow.setInterpolator(new AccelerateInterpolator());

        mAnimHide = ObjectAnimator.ofFloat(this, View.TRANSLATION_Y,0,78,65,70);
        mAnimHide.setDuration(400);
        mAnimHide.setInterpolator(new AccelerateInterpolator());
    }

    public void setImageDrawable(int drawable){
        mImageView.setImageResource(drawable);
    }

    public void hide() {
        this.setTranslationY(70);
    }

    public void showWithAnim(){
        mAnimShow.start();
    }

    public void hideWidthAnim(){
        mAnimHide.start();
    }
}
