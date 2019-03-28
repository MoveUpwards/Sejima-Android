package com.rlab.sejima.features;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.rlab.sejima.R;

/*
    Created by Antoine RICHE on 28/03/2019.
 */
public class MUNavigationBar extends LinearLayout {

    private MaterialButton mRightButton;
    private ImageButton mLeftButton;
    private LinearLayout mSeparator;

    /**
     * The alpha value for disabled state
     */
    private float mDisabledAlpha;
    /**
     * Label of the button
     */
    private String mLabel = "";
    /**
     * The label font size
     */
    private float mLabelFontSize;
    /**
     * The label font weight
     */
    private int mLabelFontWeight;
    /**
     * The label font color
     */
    private int mLabelColor;
    /**
     * The label alignment
     */
    private int mLabelAlignment;
    /**
     * The label highlighted color
     */
    private int mLabelHighLightedColor;
    /**
     * The label progressing color
     */
    private int mLabelProgressingColor;
    /**
     * Show or hide the progress indicator
     */
    private boolean mIsLoading;
    /**
     * Background color
     */
    private int mBkgColor;
    /**
     * Border color
     */
    private int mBorderColor;
    /**
     * Border width
     */
    private float mBorderWidth;
    /**
     * Corner radius
     */
    private int mCornerRadius;
    /**
     * Vertical padding
     */
    private float mVerticalPadding;
    /**
     * Horizontal padding
     */
    private float mHorizontalPadding;
    /**
     * The drawable image of the ImageButton
     */
    private Drawable mImgDrawable;
    /**
     * The separator color
     */
    private int mSeparatorColor;
    /**
     * The separator width
     */
    private float mSeparatorWidth;
    /**
     * The separator height multiplier
     */
    private float mSeparatorMultiplier;

    /**
     * The MUNavigationBarListener listener
     */
    private MUNavigationBarListener mListener;

    /**
     * The scale to convert pixels into dp
     */
    private float mScale;

    public MUNavigationBar(Context context) {
        super(context);
        init(context);
    }

    public MUNavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MUNavigationBar);
        mImgDrawable = attributes.getDrawable(R.styleable.MUNavigationBar_android_src);
        mSeparatorColor = attributes.getColor(R.styleable.MUNavigationBar_separator_color, Color.WHITE);
        mSeparatorWidth = attributes.getDimensionPixelSize(R.styleable.MUNavigationBar_separator_width, 0);
        mSeparatorMultiplier = normalizeMultiplierValue(attributes.getFloat(R.styleable.MUNavigationBar_separator_height_multiplier, 1));
        mLabel = attributes.getString(R.styleable.MUNavigationBar_android_text);
        mLabelColor = attributes.getColor(R.styleable.MUNavigationBar_android_textColor, Color.WHITE);
        mLabelFontSize = attributes.getDimensionPixelSize(R.styleable.MUNavigationBar_android_textSize, 0);
        mLabelFontWeight = attributes.getInt(R.styleable.MUNavigationBar_android_textStyle, Typeface.NORMAL);
        mLabelAlignment = attributes.getInt(R.styleable.MUNavigationBar_text_alignment, Gravity.CENTER);
        mLabelHighLightedColor = attributes.getColor(R.styleable.MUNavigationBar_pressed_color, Color.BLACK);
        mLabelProgressingColor= attributes.getColor(R.styleable.MUNavigationBar_progressing_color, Color.YELLOW);
        mBorderWidth = attributes.getDimensionPixelSize(R.styleable.MUNavigationBar_border_width, 0);
        mBorderColor = attributes.getColor(R.styleable.MUNavigationBar_border_color, Color.BLUE);
        mCornerRadius = attributes.getDimensionPixelSize(R.styleable.MUNavigationBar_corner_radius, 0);
        mIsLoading = attributes.getBoolean(R.styleable.MUNavigationBar_is_loading, false);
        mBkgColor = attributes.getColor(R.styleable.MUNavigationBar_bkg_color, 0);
        mDisabledAlpha = attributes.getFloat(R.styleable.MUNavigationBar_disable_alpha, 0.7f);
        mVerticalPadding = attributes.getDimensionPixelSize(R.styleable.MUButton_android_paddingVertical, 0);
        mHorizontalPadding = attributes.getDimensionPixelSize(R.styleable.MUButton_android_paddingHorizontal, 0);

        init(context);
        attributes.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        updateSeparatorParams((int) mSeparatorWidth, (int) (mSeparatorMultiplier * h));
    }


    private void init(Context context){
        mScale = (float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT;

        setOrientation(HORIZONTAL);
        setBackgroundColor(mBkgColor);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setGravity(Gravity.CENTER_VERTICAL);

        mLeftButton = new ImageButton(context);
        mLeftButton.setPadding(0,0,0,0);
        mImgDrawable = mImgDrawable != null ? mImgDrawable : context.getResources().getDrawable(R.mipmap.ic_launcher);
        mLeftButton.setImageDrawable(mImgDrawable);
        mLeftButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mLeftButton.setOnClickListener(v -> {
            if(mListener != null) {
                mListener.clickOnLeftButton(this);
            }
        });
        addView(mLeftButton, new LayoutParams(60,60));

        mSeparator = new LinearLayout(context);
        LayoutParams lp = new LayoutParams((int) mSeparatorWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.setMargins((int) (5 * mScale),0, (int) (5 * mScale),0);
        mSeparator.setBackgroundColor(mSeparatorColor);
        addView(mSeparator, lp);

        mRightButton = new MaterialButton(context);
        mRightButton.setOnClickListener(v -> {
            if(mListener != null) {
                mListener.clickOnRightButton(this);
            }
        });
        addView(mRightButton, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        // Background
        setBkgColor(mBkgColor);
        // Alphas
        setDisabledAlpha(mDisabledAlpha);
        //Label
        mLabel = TextUtils.isEmpty(mLabel) ? "" : mLabel;
        setLabel(mLabel);
        setLabelColor(mLabelColor);
        MUButton.MUButtonUtils.setStatesFontColor(mRightButton, mLabelColor, mLabelHighLightedColor, mLabelHighLightedColor);
        mLabelFontSize = mLabelFontSize > 0 ? mLabelFontSize : mRightButton.getTextSize();
        mRightButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLabelFontSize);
        setLabelFontWeight(mLabelFontWeight);
        setLabelAlignment(mLabelAlignment);
        setLabelProgressingColor(mLabelProgressingColor);
        //Border
        setBorderWidth(mBorderWidth);
        MUButton.MUButtonUtils.setStatesBorderColor(mRightButton, mBorderColor, 1, mDisabledAlpha, mDisabledAlpha);
        setCornerRadius(mCornerRadius);
        //Padding
        setVerticalPadding(mVerticalPadding);
        setHorizontalPadding(mHorizontalPadding);
        // Is loading
        setLoading(mIsLoading);
    }

    /**
     * Get the current label
     * @return the current label as String
     */
    public String getLabel() {
        return mLabel;
    }

    /**
     * Set tthe current label
     * @param label the label as String
     */
    public void setLabel(String label) {
        mLabel = label;
        mRightButton.setText(label);
    }

    /**
     * Get the label font size
     * @return the font size in dp
     */
    public float getLabelFontSize() {
        return mLabelFontSize;
    }

    /**
     * Set the label font size
     * @param labelFontSize the label font size in pixels.
     */
    public void setLabelFontSize(float labelFontSize) {
        mLabelFontSize = labelFontSize * mScale;
        mRightButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLabelFontSize);
    }

    /**
     * Get the current label font weight
     * @return the current label font weight as integer
     */
    public int getLabelFontWeight() {
        return mLabelFontWeight;
    }

    /**
     * Set the label font weight
     * @param labelFontWeight the label font weight as integer
     */
    public void setLabelFontWeight(int labelFontWeight) {
        mLabelFontWeight = labelFontWeight;
        mRightButton.setTypeface(Typeface.create(Typeface.DEFAULT, mLabelFontWeight));
    }

    /**
     * Get the current label color as integer
     * @return the label color as RGBA integer
     */
    public int getLabelColor() {
        return mLabelColor;
    }

    /**
     * Set the label color
     * @param labelColor the label color as RGBA integer
     */
    public void setLabelColor(int labelColor) {
        mLabelColor = labelColor;
        MUButton.MUButtonUtils.setStatesFontColor(mRightButton, mLabelColor, mLabelHighLightedColor, mLabelHighLightedColor);
    }

    /**
     * Get the current label alignment
     * @return the integer representing the current horizontal alignment
     */
    public int getLabelAlignment() {
        return mLabelAlignment;
    }

    /**
     * Set the label alignment
     * @param labelAlignment the label alignment as integer.
     * Must be one of those
     * <ul>
     *      <li>Gravity.LEFT</li>
     *      <li>Gravity.CENTER</li>
     *      <li>Gravity.RIGHT</li>
     * </ul>
     */
    public void setLabelAlignment(int labelAlignment) {
        mLabelAlignment = labelAlignment|Gravity.CENTER_VERTICAL;
        mRightButton.setGravity(mLabelAlignment);
    }

    @Override
    public void setTextAlignment(int textAlignment) {
        super.setTextAlignment(textAlignment);
        setLabelAlignment(textAlignment);
    }

    /**
     * Get the current alpha value of disabled state
     * @return the current alpha value as float
     */
    public float getDisabledAlpha() {
        return mDisabledAlpha;
    }

    /**
     * Set the alpha value for disabled state
     * @param disabledAlpha the alpha value to apply
     * Alpha must be between 0 and 1
     */
    public void setDisabledAlpha(float disabledAlpha) {
        mDisabledAlpha = MUButton.MUButtonUtils.normalizeAlphaValue(disabledAlpha);
        MUButton.MUButtonUtils.setStatesBackground(mRightButton, mBkgColor, 1, mDisabledAlpha, mDisabledAlpha);
    }

    /**
     * Get the current color for highlighted (=pressed) state
     * @return the current pressed state color as RGBA integer
     */
    public int getLabelHighLightedColor() {
        return mLabelHighLightedColor;
    }

    /**
     * Set the color for pressed state
     * @param labelHighLightedColor the color as RGBA integer
     */
    public void setLabelHighLightedColor(int labelHighLightedColor) {
        mLabelHighLightedColor = labelHighLightedColor;
        MUButton.MUButtonUtils.setStatesFontColor(mRightButton, mLabelColor, mLabelHighLightedColor, mLabelHighLightedColor);
    }

    /**
     * Get the current progressing color
     * @return the color as RGBA integer
     */
    public int getLabelProgressingColor() {
        return mLabelProgressingColor;
    }

    /**
     * Set the progressing color
     * @param labelProgressingColor the progressing color as RGBA integer
     */
    public void setLabelProgressingColor(int labelProgressingColor) {
        mLabelProgressingColor = labelProgressingColor;
    }

    /**
     * Get the value of the loading state
     * @return the boolean value of the loading state
     */
    public boolean isLoading() {
        return mIsLoading;
    }

    /**
     * Set the loading value
     * @param loading the loading state value as boolean
     */
    public void setLoading(boolean loading) {
        mIsLoading = loading;
        if(mIsLoading){
            mRightButton.setTextColor(mLabelProgressingColor);
        } else {
            MUButton.MUButtonUtils.setStatesFontColor(mRightButton, mLabelColor, mLabelHighLightedColor, mLabelHighLightedColor);
        }
    }

    /**
     * Get the current background color
     * @return the background color as RGBA integer
     */
    public int getBkgColor() {
        return mBkgColor;
    }

    /**
     * Set the background color
     * @param bkgColor the color as RGBA integer
     */
    public void setBkgColor(int bkgColor) {
        mBkgColor = bkgColor;
        MUButton.MUButtonUtils.setStatesBackground(mRightButton, mBkgColor, 1, mDisabledAlpha, mDisabledAlpha);
    }

    /**
     * Get the current border color
     * @return the border color as RGBA integer
     */
    public int getBorderColor() {
        return mBorderColor;
    }

    /**
     * Set the border color
     * @param borderColor the border color as RGBA integer
     */
    public void setBorderColor(int borderColor) {
        mBorderColor = borderColor;
        mRightButton.setStrokeColorResource(mBorderColor);
    }

    /**
     * Get the current border width
     * @return the current border width in pixels.
     */
    public float getBorderWidth() {
        return mBorderWidth;
    }

    /**
     * Set the border width in pixels
     * @param borderWidth the border width in pixels
     */
    public void setBorderWidth(float borderWidth) {
        mBorderWidth = borderWidth;
        mRightButton.setStrokeWidth((int) mBorderWidth);
    }

    /**
     * Get the current corner radius value
     * @return the current corner radius
     */
    public int getCornerRadius() {
        return mCornerRadius;
    }

    /**
     * Set the radius value
     * @param cornerRadius the radius value
     */
    public void setCornerRadius(int cornerRadius) {
        mCornerRadius = cornerRadius;
        mRightButton.setCornerRadius(mCornerRadius);
    }

    /**
     * Get the current vertical padding value
     * @return the current vertical padding value in dp
     */
    public float getVerticalPadding() {
        return mVerticalPadding;
    }

    /**
     * Set the vertical padding value
     * @param verticalPadding the vertical padding value in pixels
     */
    public void setVerticalPadding(float verticalPadding) {
        mVerticalPadding = verticalPadding * mScale;
    }

    /**
     * Get the current horizontal padding value
     * @return the current horizontal padding value in dp
     */
    public float getHorizontalPadding() {
        return mHorizontalPadding;
    }

    /**
     * Set the horizontal padding value
     * @param horizontalPadding the horizontal padding value in pixels
     */
    public void setHorizontalPadding(float horizontalPadding) {
        mHorizontalPadding = horizontalPadding;
    }

    /**
     * Get the drawable attached to the button
     * @return the drawable image
     */
    public Drawable getImgDrawable() {
        return mImgDrawable;
    }

    /**
     * Set a drawable image to the button
     * @param imgDrawable the image drawable
     */
    public void setImgDrawable(Drawable imgDrawable) {
        mImgDrawable = imgDrawable;
        mLeftButton.setImageDrawable(imgDrawable);
        invalidate();
    }

    /**
     * Get the color of the separator
     * @return the separator color as ARGB integer
     */
    public int getSeparatorColor() {
        return mSeparatorColor;
    }

    /**
     * Set the color for separator
     * @param separatorColor the separator color as ARGB integer
     */
    public void setSeparatorColor(int separatorColor) {
        mSeparatorColor = separatorColor;
        mSeparator.setBackgroundColor(separatorColor);
    }

    /**
     * Get the separator width
     * @return the separator width as pixels
     */
    public float getSeparatorWidth() {
        return mSeparatorWidth;
    }

    /**
     * Set the separator width
     * @param separatorWidth the separator width as pixels.
     */
    public void setSeparatorWidth(float separatorWidth) {
        mSeparatorWidth = separatorWidth;
        updateSeparatorParams((int) mSeparatorWidth, mSeparator.getHeight());
    }

    /**
     * Get the separator height multiplier
     * @return a float between 0 and 1 representing the height ratio
     */
    public float getSeparatorMultiplier() {
        return mSeparatorMultiplier;
    }

    /**
     * Set the separator height multiplier
     * @param separatorMultiplier the separator height-multiplier, must be between 0 and 1
     */
    public void setSeparatorMultiplier(float separatorMultiplier) {
        mSeparatorMultiplier = separatorMultiplier;
        updateSeparatorParams((int) mSeparatorWidth, (int) (mSeparatorMultiplier * getHeight()));
    }

    /**
     * Update the separator layout with new width and new height
     * @param newWidth the width in pixels
     * @param newHeight the height in pixels
     */
    private void updateSeparatorParams(int newWidth, int newHeight){
        LayoutParams lp = new LayoutParams(newWidth, newHeight);
        lp.setMargins((int) (5 * mScale),0, (int) (5 * mScale),0);
        mSeparator.setLayoutParams(lp);
        invalidate();
    }

    /**
     * Get the listener attached to the view, null if there is not
     * @return the listener
     */
    public MUNavigationBarListener getListener() {
        return mListener;
    }

    /**
     * Attached a listener to handle user clicks
     * @param listener
     */
    public void setListener(MUNavigationBarListener listener) {
        mListener = listener;
    }

    /**
     * Interface to handle user clicks
     */
    public interface MUNavigationBarListener {
        /**
         * Handle left clicks
         * @param muNavigationBar the current view
         */
        void clickOnLeftButton(MUNavigationBar muNavigationBar);

        /**
         * Handle right clicks
         * @param muNavigationBar the current view
         */
        void clickOnRightButton(MUNavigationBar muNavigationBar);
    }

    /**
     * Normalize multiplier value between 0 and 1
     * @param multiplier the alpha value to check
     * @return the normalized value of multiplier
     */
    static float normalizeMultiplierValue(float multiplier) {
        multiplier = multiplier < 0 ? 0 : multiplier;
        return multiplier > 1 ? 1 : multiplier;
    }
}
