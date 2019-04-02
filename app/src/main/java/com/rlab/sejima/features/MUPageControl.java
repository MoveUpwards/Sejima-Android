package com.rlab.sejima.features;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.ColorUtils;

public class MUPageControl extends LinearLayout {

    private int mNumberPages = 3;
    private int mCurrentPosition = 0;
    private MUPageControlListener mPageControlListener;
    private Map<Integer, IndicatorButton> mHMButtons = new LinkedHashMap<>();

    public MUPageControl(Context context) {
        super(context);
        init(context);
    }

    public MUPageControl(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        setWillNotDraw(false);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
    }

    public int getNumberPages() {
        return mNumberPages;
    }

    public void setNumberPages(int numberPages) {
        mHMButtons.clear();
        removeAllViews();
        mNumberPages = numberPages;
        for(int i = 0 ; i < mNumberPages ; i++){
            IndicatorButton btn = new IndicatorButton(getContext(), i);
            mHMButtons.put(btn.getId(), btn);
            addView(btn);
        }
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        mCurrentPosition = currentPosition;
        if(mPageControlListener != null){
            for(Integer key : mHMButtons.keySet()){
                IndicatorButton btn = mHMButtons.get(key);
                if(currentPosition == btn.getPosition()){
                    btn.performClick();
                }
            }
        }
    }

    public MUPageControlListener getPageControlListener() {
        return mPageControlListener;
    }

    public void setPageControlListener(MUPageControlListener pageControlListener) {
        mPageControlListener = pageControlListener;
    }

    private OnClickListener mListener = v -> {
        if(v instanceof IndicatorButton) {

            for(Integer key : mHMButtons.keySet()){
                if(mHMButtons.containsKey(v.getId())){
                    IndicatorButton btn = mHMButtons.get(key);
                    if(v.getId() == key){
                        btn.setCurrentPosition(true);
                        mPageControlListener.clickOnIndex(this, btn.getPosition());
                    } else {
                        btn.setCurrentPosition(false);
                    }
                }
            }
        }
    };

    private class IndicatorButton extends MaterialButton {

        private boolean mIsCurrentPosition = false;
        private int mPosition;

        public IndicatorButton(Context context) {
            this(context, -1);
        }

        public IndicatorButton(Context context, int position) {
            super(context);
            setCurrentPosition(false);
            setId(generateViewId());
            mPosition = position;
            setOnClickListener(mListener);
        }

        public boolean isCurrentPosition() {
            return mIsCurrentPosition;
        }

        public int getPosition() {
            return mPosition;
        }

        public void setPosition(int position) {
            mPosition = position;
        }

        public void setCurrentPosition(boolean currentPosition) {
            mIsCurrentPosition = currentPosition;
            if(currentPosition) {
                applySelectedAppearance();
            } else {
                applyDefaultAppearance();
            }
        }

        private void applyDefaultAppearance(){
            LayoutParams btnLp = new LayoutParams(20, 40);
            btnLp.setMargins(5,0,5,0);
            setLayoutParams(btnLp);
            setCornerRadius(120);

            // FIXME : make it configurable
            int[][] STATES = new int[][]{new int[] { android.R.attr.state_pressed }, new int[] {}};
            int[] colors = new int[] {Color.BLUE, ColorUtils.setAlphaComponent(Color.BLUE, (int) (0.7 * 255))};
            setBackgroundTintList(new ColorStateList(STATES, colors));
        }

        private void applySelectedAppearance(){
            LayoutParams btnLp = new LayoutParams(50, 40);
            btnLp.setMargins(5,0,5,0);
            setLayoutParams(btnLp);
            setCornerRadius(10);

            // FIXME : make it configurable
            int[][] STATES = new int[][]{new int[] { android.R.attr.state_pressed }, new int[] {}};
            int[] colors = new int[] {Color.BLUE, ColorUtils.setAlphaComponent(Color.RED, (int) (0.7 * 255))};
            setBackgroundTintList(new ColorStateList(STATES, colors));
        }
    }

    public interface MUPageControlListener {
        void clickOnIndex(MUPageControl muPageControl, int index);
    }

}
