package com.rlab.sejima.features;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.rlab.sejima.R;

import java.util.LinkedHashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils;

/*
    Created by Antoine RICHE on 2019/04/02.
 */
public class MUPageControl extends LinearLayout implements MUViewHelper {

    private int mNumberPages = 0;
    private int mCurrentPosition = -1;
    private MUPageControlListener mPageControlListener;
    private final Map<Integer, IndicatorButton> mHMButtons = new LinkedHashMap<>();

    /**
     * The element width
     */
    private int mElementWidth = (int) pixelsToDensity(getResources().getDisplayMetrics(), 10);
    /**
     * The element height
     */
    private int mElementHeight = (int) pixelsToDensity(getResources().getDisplayMetrics(), 10);
    /**
     * The unactive element color
     */
    private int mElementColor = getResources().getColor(R.color.colorPrimary);
    /**
     * The active element width
     */
    private int mActiveElementWidth = mElementWidth;
    /**
     * The active element width
     */
    private int mActiveElementRadius = mActiveElementWidth * 2;
    /**
     * The unactive element color
     */
    private int mActiveElementColor = getResources().getColor(R.color.colorAccent);
    /**
     * The unactive element border color
     */
    private int mBorderColor = Color.WHITE;
    /**
     * The element border width
     */
    private int mBorderWidth = 0;
    /**
     * The padding between elements
     */
    private int mElementPadding = (int) pixelsToDensity(getResources().getDisplayMetrics(), 2);
    /**
     * The hideForSingleElement value
     */
    private boolean mHideForSingleElementValue = false;

    /**
     * Default constructor
     * @param context the view context
     */
    public MUPageControl(Context context) {
        super(context);
        init();
    }

    /**
     * Constructor with attributes
     * @param context the view context
     * @param attrs the XML attributes for the view
     */
    public MUPageControl(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MUPageControl);
        mElementWidth = attributes.getDimensionPixelSize(R.styleable.MUPageControl_element_width, mElementWidth);
        mElementHeight = attributes.getDimensionPixelSize(R.styleable.MUPageControl_element_height, mElementHeight);
        mElementColor = attributes.getColor(R.styleable.MUPageControl_element_color, mElementColor);
        mActiveElementWidth = attributes.getDimensionPixelOffset(R.styleable.MUPageControl_active_element_width, mActiveElementWidth);
        mActiveElementRadius = attributes.getInt(R.styleable.MUPageControl_active_element_radius, mActiveElementRadius);
        mActiveElementColor = attributes.getColor(R.styleable.MUPageControl_active_element_color, mActiveElementColor);
        mBorderColor = attributes.getColor(R.styleable.MUPageControl_element_border_color, mBorderColor);
        mBorderWidth = attributes.getDimensionPixelSize(R.styleable.MUPageControl_active_element_width, mBorderWidth);
        mElementPadding = attributes.getDimensionPixelSize(R.styleable.MUPageControl_element_padding, mElementPadding);
        mHideForSingleElementValue = attributes.getBoolean(R.styleable.MUPageControl_hide_for_single_page, mHideForSingleElementValue);

        init();
        attributes.recycle();
    }

    private void init(){
        setWillNotDraw(false);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setGravity(Gravity.CENTER);
        if(isInEditMode()){
            setNumberPages(2);
        } else {
            setNumberPages(mNumberPages);
        }
        updateVisibility();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getLayoutParams().height = mElementWidth * 2;
    }

    /**
     * The number of pages
     * @return the numer of pages
     */
    public int getNumberPages() {
        return mNumberPages;
    }

    /**
     * Set the number of pages and update views
     * @param numberPages the number of pages attached to the controller
     */
    public void setNumberPages(int numberPages) {
        mHMButtons.clear();
        removeAllViews();
        mNumberPages = Math.max(numberPages, 0);
        LinearLayout ll;
        for(int i = 0 ; i < mNumberPages ; i++){
            IndicatorButton btn = new IndicatorButton(getContext(), i);
            mHMButtons.put(i, btn);
            addView(btn);
        }
        updateVisibility();
    }

    /**
     * Get the current position
     * @return the current index
     */
    public int getCurrentPosition() {
        return mCurrentPosition;
    }

    /**
     * Set the current position of the controller
     * @param currentPosition the index of the current page
     */
    public void setCurrentPosition(int currentPosition) {
        currentPosition = normalizeIntValue(currentPosition, 0, mNumberPages - 1);
        updateSelection(currentPosition);
    }

    /**
     * The element size
     * @return size of the elements in dp
     */
    public int getElementWidth() {
        return mElementWidth;
    }

    /**
     * Set the element size
     * @param elementWidth the size in pixels
     */
    public void setElementWidth(int elementWidth) {
        mElementWidth = elementWidth;
    }

    /**
     * Get the color of elements
     * @return color as ARGB integer
     */
    public int getElementColor() {
        return mElementColor;
    }

    /**
     * Set the color of elements
     * @param elementColor color as ARGB integer
     */
    public void setElementColor(int elementColor) {
        mElementColor = elementColor;
    }

    /**
     * Get the active element width
     * @return the active element width in dp
     */
    public int getActiveElementWidth() {
        return mActiveElementWidth;
    }

    /**
     * Set the active element width
     * @param activeElementWidth the width in pixels
     */
    public void setActiveElementWidth(int activeElementWidth) {
        mActiveElementWidth = activeElementWidth;
    }

    /**
     * Get the element radius
     * @return the element radius as integer
     */
    public int getActiveElementRadius() {
        return mActiveElementRadius;
    }

    /**
     * Set the element radius
     * @param activeElementRadius the value of element radius
     */
    public void setActiveElementRadius(int activeElementRadius) {
        mActiveElementRadius = activeElementRadius;
    }

    /**
     * Get the active element color
     * @return color as ARGB integer
     */
    public int getActiveElementColor() {
        return mActiveElementColor;
    }

    /**
     * Set the active element color
     * @param activeElementColor color as ARGB integer
     */
    public void setActiveElementColor(int activeElementColor) {
        mActiveElementColor = activeElementColor;
    }

    /**
     * Get the element border color
     * @return color as ARGB integer
     */
    public int getBorderColor() {
        return mBorderColor;
    }

    /**
     * Set the element border color
     * @param borderColor color as ARGB integer
     */
    public void setBorderColor(int borderColor) {
        mBorderColor = borderColor;
    }

    /**
     * Get the current element width
     * @return the element width in dp
     */
    public int getBorderWidth() {
        return mBorderWidth;
    }

    /**
     * Set the element width
     * @param borderWidth the width in dp
     */
    public void setBorderWidth(int borderWidth) {
        mBorderWidth = borderWidth;
    }

    /**
     * Get the padding between elements
     * @return the padding in dp
     */
    public int getElementPadding() {
        return mElementPadding;
    }

    /**
     * Set the padding between elements
     * @param elementPadding the padding in dp
     */
    public void setElementPadding(int elementPadding) {
        mElementPadding = elementPadding;
    }

    /**
     * Determine if a single element has to be hidden
     * @return the hidden boolean value
     */
    public boolean isHideForSingleElementValue() {
        return mHideForSingleElementValue;
    }

    /**
     * Set the visibility of a single element
     * @param hideForSingleElementValue the boolean hidden value
     */
    public void setHideForSingleElementValue(boolean hideForSingleElementValue) {
        mHideForSingleElementValue = hideForSingleElementValue;
        updateVisibility();
    }

    /**
     * Get the PageControlListener attached to the current view
     * @return the pagecontrollistener instance, null if not
     */
    public MUPageControlListener getPageControlListener() {
        return mPageControlListener;
    }

    /**
     * Register a listener for the current pagecontrol
     * @param pageControlListener the listener which handles click on elements
     */
    public void setPageControlListener(MUPageControlListener pageControlListener) {
        mPageControlListener = pageControlListener;
    }

    private void updateVisibility(){
        if (mNumberPages == 0 || (mNumberPages == 1 && mHideForSingleElementValue)){
            setVisibility(GONE);
        } else {
            setVisibility(VISIBLE);
        }
    }

    private final OnClickListener mListener = v -> {
        if(v instanceof IndicatorButton) {
            int position = ((IndicatorButton) v).getPosition();
            updateSelection(position);

            if(null != mPageControlListener){
                mPageControlListener.clickOnIndex(this, position);
            }
        }
    };

    private void updateSelection(int newSelection){
        if (mCurrentPosition != newSelection) {
            mCurrentPosition = newSelection;
            for(Integer key : mHMButtons.keySet()){
                mHMButtons.get(key).setSelected(key == newSelection);
            }
        }
    }

    private class IndicatorButton extends MaterialButton {

        private final int[][] STATES = new int[][]{new int[] { android.R.attr.state_pressed }, new int[] {}};
        private final int mPosition;

        IndicatorButton(Context context, int position) {
            super(context);
            setSelected(false);
            mPosition = position;
            setOnClickListener(mListener);
        }

        int getPosition() {
            return mPosition;
        }

        public void setSelected(boolean isSelected){
            LayoutParams btnLp = isSelected ?
                    new LayoutParams(mActiveElementWidth, mElementHeight) :
                    new LayoutParams(mElementWidth, mElementHeight);
            setBackgroundTintList(new ColorStateList(STATES, isSelected ?
                    new int[]{ ColorUtils.setAlphaComponent(mActiveElementColor, (int) (0.7 * 255)), mActiveElementColor } :
                    new int[]{ ColorUtils.setAlphaComponent(mElementColor, (int) (0.7 * 255)), mElementColor }
            ));

            float radius = mActiveElementRadius;
            float[] outer = new float[]{ radius,radius,radius,radius,radius,radius,radius,radius };
            ShapeDrawable a = new ShapeDrawable(new RoundRectShape(outer, new RectF(), null));
            setBackgroundDrawable(a);

            setCornerRadius(mElementWidth * 2);
            setStrokeColor(ColorStateList.valueOf(mBorderColor));
            setRippleColor(ColorStateList.valueOf(Color.TRANSPARENT));

            btnLp.setMargins(mElementPadding / 2,0, mElementPadding / 2,0);
            setStrokeWidth(mBorderWidth);
            setLayoutParams(btnLp);
        }
    }

    public interface MUPageControlListener {
        void clickOnIndex(MUPageControl muPageControl, int index);
    }
}
