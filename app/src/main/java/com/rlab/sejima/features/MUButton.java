package com.rlab.sejima.features;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;

import com.google.android.material.button.MaterialButton;
import com.rlab.sejima.R;

import androidx.core.graphics.ColorUtils;

/*
    Created by Antoine RICHE on 27/03/2019.
 */
public class MUButton extends MaterialButton {

    /**
     * Static fields representing states; used for convenience only
     */
    public static final int[] STATE_PRESSED = new int[] { android.R.attr.state_pressed };
    public static final int[] STATE_DISABLED = new int[] { -android.R.attr.state_enabled };
    public static final int[] STATE_DEFAULT = new int[] {};
    public static final int[][] STATES = new int[][]{STATE_PRESSED, STATE_DISABLED, STATE_DEFAULT};

    /**
     * OnCLickListener to handle clicks
     */
    private OnClickListener mListener;
    /**
     * The current background alpha
     */
    private float mAlpha;
    /**
     * The alpha value for disabled state
     */
    private float mDisabledAlpha;
    /**
     * The current border alpha
     */
    private float mBorderAlpha;
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
     * The scale to convert pixels into dp
     */
    private float mScale;

    /**
     * Default constructor
     * @param context the view context
     */
    public MUButton(Context context) {
        super(context);
        init(context);
    }

    /**
     * Constructor with attributes
     * @param context the view context
     * @param attrs the XML attributes for the view
     */
    public MUButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MUButton);
        // Background
        mBkgColor = attributes.getColor(R.styleable.MUButton_bkg_color, Color.LTGRAY);
        // Alphas
        mAlpha = attributes.getFloat(R.styleable.MUButton_android_alpha, 1.0f);
        mBorderAlpha = attributes.getFloat(R.styleable.MUButton_border_alpha, 1.0f);
        mDisabledAlpha = attributes.getFloat(R.styleable.MUButton_disable_alpha, 0.7f);
        // Label
        mLabel = attributes.getString(R.styleable.MUButton_android_text);
        mLabelColor = attributes.getColor(R.styleable.MUButton_android_textColor, getCurrentTextColor());
        mLabelFontSize = attributes.getDimensionPixelSize(R.styleable.MUButton_android_textSize, (int) getTextSize());
        mLabelFontWeight = attributes.getInt(R.styleable.MUButton_android_textStyle, Typeface.NORMAL);
        mLabelAlignment = attributes.getInt(R.styleable.MUButton_text_alignment, Gravity.CENTER);
        mLabelHighLightedColor = attributes.getColor(R.styleable.MUButton_pressed_color, getCurrentTextColor());
        mLabelProgressingColor = attributes.getColor(R.styleable.MUButton_progressing_color, getCurrentTextColor());
        // Border
        mBorderWidth = attributes.getDimensionPixelSize(R.styleable.MUButton_strokeWidth, 0);
        mBorderColor = attributes.getColor(R.styleable.MUButton_strokeColor, mBkgColor);
        mCornerRadius = attributes.getDimensionPixelSize(R.styleable.MUButton_cornerRadius, 0);
        // Padding
//        mVerticalPadding = attributes.getDimensionPixelSize(R.styleable.MUButton_android_paddingVertical, 0);
//        mHorizontalPadding = attributes.getDimensionPixelSize(R.styleable.MUButton_android_paddingHorizontal, 0);
        // IsLoading
        mIsLoading = attributes.getBoolean(R.styleable.MUButton_is_loading, false);

        init(context);
        attributes.recycle();
    }

    private void init(Context context){
        mScale = (float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT;

        // Background
        setBkgColor(mBkgColor);
        // Alphas
        setAlpha(mAlpha);
        setBorderAlpha(mBorderAlpha);
        setDisabledAlpha(mDisabledAlpha);
        //Label
        setLabel(mLabel);
        setStatesFontColor();
        setTextSize(TypedValue.COMPLEX_UNIT_PX, mLabelFontSize);
        setLabelFontWeight(mLabelFontWeight);
        setLabelAlignment(mLabelAlignment);
        setLabelProgressingColor(mLabelProgressingColor);
        //Border
        setBorderWidth(mBorderWidth);
        setStatesBorderColor();
        setCornerRadius(mCornerRadius);
        //Padding
        setVerticalPadding(mVerticalPadding);
        setHorizontalPadding(mHorizontalPadding);
        // Is loading
        setLoading(mIsLoading);
    }

    /**
     * Get the listener for clicks
     * @return the current listener attached to the view, null if there is no listener.
     */
    public OnClickListener getListener() {
        return mListener;
    }

    /**
     * Attach a listener to handle clicks
     * @param listener the listener to attach
     */
    public void setListener(OnClickListener listener) {
        mListener = listener;
        super.setOnClickListener(listener);
    }

    /**
     * Get the current background alpha
     * @return the current alpha as float
     */
    @Override
    public float getAlpha() {
        return mAlpha;
    }

    /**
     * Set the background alpha
     * @param alpha the background alpha as float
     * Alpha must be between 0 and 1
     */
    // FIXME to test
    @Override
    public void setAlpha(float alpha) {
        mAlpha = normalizeAlphaValue(alpha);
        setStatesBackground();
    }

    /**
     * Get the border alpha
     * @return the border alpha as float
     */
    public float getBorderAlpha() {
        return mBorderAlpha;
    }

    /**
     * Set the border alpha
     * @param borderAlpha the border alpha as float
     */
    public void setBorderAlpha(float borderAlpha) {
        mBorderAlpha = normalizeAlphaValue(borderAlpha);
        setStatesBorderColor();
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
        setText(label);
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
        setTextSize(TypedValue.COMPLEX_UNIT_PX, mLabelFontSize);
    }

    @Override
    public void setTextSize(float size) {
        mLabelFontSize = size;
        super.setTextSize(size);
    }

    @Override
    public void setTextSize(int unit, float size) {
        mLabelFontSize = size;
        super.setTextSize(unit, size);
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
        setTypeface(Typeface.create(Typeface.DEFAULT, mLabelFontWeight));
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
        setStatesFontColor();
    }

    @Override
    public void setTextColor(int color) {
        mLabelColor = color;
        super.setTextColor(color);
        setStatesFontColor();
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
        setGravity(mLabelAlignment);
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
        mDisabledAlpha = normalizeAlphaValue(disabledAlpha);
        setStatesBackground();
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
        setStatesFontColor();
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
            setTextColor(mLabelProgressingColor);
        } else {
            setStatesFontColor();
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
        setStatesBackground();
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
        setStrokeColorResource(mBorderColor);
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
        setStrokeWidth((int) mBorderWidth);
    }

    /**
     * Get the current corner radius value
     * @return the current corner radius
     */
    @Override
    public int getCornerRadius() {
        return mCornerRadius;
    }

    /**
     * Set the radius value
     * @param cornerRadius the radius value
     */
    @Override
    public void setCornerRadius(int cornerRadius) {
        mCornerRadius = cornerRadius;
        super.setCornerRadius(mCornerRadius);
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

    @Override
    public void setBackground(Drawable background) {
        if(!(background instanceof ColorDrawable)){
            super.setBackground(background);
        } else {
            Log.e(getClass().getCanonicalName(), "Background was a color");
        }
    }

    /**
     * Set up the alpha values to handle clicks
     */
    private void setStatesBackground() {
        int[] colors = new int[] {
                ColorUtils.setAlphaComponent(mBkgColor, (int) (mAlpha * mDisabledAlpha * 255)),
                ColorUtils.setAlphaComponent(mBkgColor, (int) (mAlpha * mDisabledAlpha * 255)),
                ColorUtils.setAlphaComponent(mBkgColor, (int) (mAlpha * 255)),
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setBackgroundTintList(new ColorStateList(STATES, colors));
        }
    }

    /**
     * Set up the font color states
     */
    private void setStatesFontColor() {
        int[] colors = new int[] { mLabelHighLightedColor, mLabelHighLightedColor, mLabelColor };
        setTextColor(new ColorStateList(STATES, colors));
    }

    private void setStatesBorderColor() {
        int[] colors = new int[] {
                ColorUtils.setAlphaComponent(mBorderColor, (int) (mBorderAlpha * mDisabledAlpha * 255)),
                ColorUtils.setAlphaComponent(mBorderColor, (int) (mBorderAlpha * mDisabledAlpha * 255)),
                ColorUtils.setAlphaComponent(mBorderColor, (int) (mBorderAlpha * 255))
        };
        setStrokeColor(new ColorStateList(STATES, colors));
    }

    /**
     * Normalize alpha value between 0 and 1
     * @param alpha the alpha value to check
     * @return the normalized value of alpha
     */
    private float normalizeAlphaValue(float alpha) {
        alpha = alpha < 0 ? 0 : alpha;
        return alpha > 1 ? 1 : alpha;
    }
}
