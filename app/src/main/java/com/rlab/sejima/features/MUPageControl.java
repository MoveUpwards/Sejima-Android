package com.rlab.sejima.features;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class MUPageControl extends LinearLayout {

    private int mNumberPages = 0;
    private List<IndicatorButton> mButtons = new ArrayList<>();

    public MUPageControl(Context context) {
        super(context);
        init(context);
    }

    public MUPageControl(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(IndicatorButton btn : mButtons){
            addView(btn);
        }
    }

    private void init(Context context){
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setGravity(Gravity.CENTER);

        for(int i = 0 ; i < mNumberPages ; i++){
            addIndicator();
        }
    }

    private IndicatorButton getDefaultIndicator(){
        IndicatorButton button = new IndicatorButton(getContext());
        LayoutParams btnLp = new LayoutParams(50, 50);
        btnLp.setMargins(5,0,5,0);
        button.setLayoutParams(btnLp);
        button.setBackgroundColor(Color.RED);
        return button;
    }

    private void addIndicator(){
        mButtons.add(getDefaultIndicator());
    }


    private class IndicatorButton extends MaterialButton {

        private boolean mIsCurrentPosition = false;
        private OnClickListener mListener = view -> setCurrentPosition(!mIsCurrentPosition);

        public IndicatorButton(Context context) {
            super(context);
            setOnClickListener(mListener);
        }

        public boolean isCurrentPosition() {
            return mIsCurrentPosition;
        }

        public void setCurrentPosition(boolean currentPosition) {
            mIsCurrentPosition = currentPosition;
        }
    }

}
