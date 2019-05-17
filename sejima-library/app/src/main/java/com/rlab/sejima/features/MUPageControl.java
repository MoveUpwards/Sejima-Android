package com.rlab.sejima.features;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.rlab.sejima.R;

import java.util.LinkedHashMap;
import java.util.Map;

/*
    Created by Antoine RICHE on 2019/04/02.
 */
public class MUPageControl extends LinearLayout implements MUViewHelper {

    private int mNumberPages = 0;
    private int mCurrentPosition = -1;
    private MUPageControlListener mPageControlListener;
    private final Map<Integer, Indicator> mHMButtons = new LinkedHashMap<>();

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
    private int mElementColor = getResources().getColor(R.color.sejima_primary);
    /**
     * The active element width
     */
    private int mActiveElementWidth = (int) pixelsToDensity(getResources().getDisplayMetrics(), 10);
    /**
     * The active element width
     */
    private int mActiveElementRadius;
    /**
     * The unactive element color
     */
    private int mActiveElementColor = getResources().getColor(R.color.sejima_accent);
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
        mActiveElementWidth = attributes.getDimensionPixelSize(R.styleable.MUPageControl_active_element_width, mActiveElementWidth);
        mActiveElementRadius = attributes.getInt(R.styleable.MUPageControl_active_element_radius, mActiveElementRadius);
        mActiveElementColor = attributes.getColor(R.styleable.MUPageControl_active_element_color, mActiveElementColor);
        mBorderColor = attributes.getColor(R.styleable.MUPageControl_element_border_color, mBorderColor);
        mBorderWidth = attributes.getDimensionPixelSize(R.styleable.MUPageControl_element_border_width, mBorderWidth);
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

        setActiveElementRadius(mActiveElementRadius);
        updateVisibility();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
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
        for(int i = 0 ; i < mNumberPages ; i++){
            Indicator btn = new Indicator(getContext(), i, this);
            mHMButtons.put(i, btn);
            addView(btn);
        }
        getLayoutParams().height = (int) (mElementWidth * 1.5);
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
        mActiveElementRadius = normalizeIntValue(activeElementRadius, 0, mElementWidth / 2);
        updateButtonsAppearance();
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
        updateButtonsAppearance();
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
        mBorderWidth = normalizeIntValue(borderWidth, 0, mElementWidth);
        updateButtonsAppearance();
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
        mElementPadding = normalizeIntValue(elementPadding, 0, (getAvailableWidth() / getNumberPages()));
        updateButtonsAppearance();
    }

    /**
     * Get available width when adjusting padding between elements
     * @return the available width in dp
     */
    private int getAvailableWidth(){
        return getWidth() - mElementWidth * (getNumberPages() - 1) - mActiveElementWidth;
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

    private void updateButtonsAppearance(){
        for(Indicator btn : mHMButtons.values()){
            btn.updateLayout();
        }
        invalidate();
    }

    private void updateSelection(int newSelection){
        if (mCurrentPosition != newSelection) {
            mCurrentPosition = newSelection;
            for(Integer key : mHMButtons.keySet()){
                mHMButtons.get(key).setSelected(key == newSelection);
            }
        }
    }

    private class Indicator extends LinearLayout {

        private final int[][] STATES = new int[][]{new int[] { android.R.attr.state_pressed }, new int[] {}};
        private final int mPosition;

        private final LinearLayout mContentView;
        private final OnClickListener mClickListener;

        public Indicator(Context context, int position, MUPageControl muPageControl) {
            super(context);
            mPosition = position;
            mClickListener = v -> {
                updateSelection(position);
                if(null != getPageControlListener()){
                    getPageControlListener().clickOnIndex(muPageControl, position);
                }
            };
            mContentView = new LinearLayout(context);

            setOnClickListener(mClickListener);
            mContentView.setOnClickListener(mClickListener);
            addView(mContentView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            setSelected(false);
        }

        public void setSelected(boolean isSelected){
            LayoutParams btnLp = isSelected ?
                    new LayoutParams(mActiveElementWidth, mElementHeight) :
                    new LayoutParams(mElementWidth, mElementHeight);
            
            setLayoutParams(btnLp);
            updateLayout();
        }

        public void updateLayout(){
            boolean isSelected = getCurrentPosition() == this.mPosition;

            applyRoundCornerToView(mActiveElementRadius, isSelected ? mActiveElementColor : mElementColor, mContentView);
            applyRoundCornerToView(mActiveElementRadius, mBorderColor, this);

            // Deal with the border width
            LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
            lp.setMargins(mBorderWidth, mBorderWidth, mBorderWidth, mBorderWidth);
            mContentView. setLayoutParams(lp);

            // Deal with the external padding
            LayoutParams btnLp = (LayoutParams) getLayoutParams();
            btnLp.setMargins(mElementPadding / 2,0, mElementPadding / 2,0);
            setLayoutParams(btnLp);
        }
    }


    public interface MUPageControlListener {
        void clickOnIndex(MUPageControl muPageControl, int index);
    }
}
