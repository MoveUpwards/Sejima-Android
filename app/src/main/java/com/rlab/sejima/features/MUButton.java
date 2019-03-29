package com.rlab.sejima.features;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.android.material.button.MaterialButton;
import com.rlab.sejima.R;

import androidx.core.graphics.ColorUtils;

/*
    Created by Antoine RICHE on 27/03/2019.
 */
public class MUButton extends RelativeLayout {

    /**
     * OnCLickListener to handle clicks
     */
    private OnClickListener mListener = v -> { };
    /**
     * The current background alpha
     */
    private float mAlpha = 1;
    /**
     * The alpha value for disabled state
     */
    private float mDisabledAlpha = 0.7f;
    /**
     * The current border alpha
     */
    private float mBorderAlpha = 1;
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
    private int mLabelFontWeight = Typeface.NORMAL;
    /**
     * The label font color
     */
    private int mLabelColor = Color.BLACK;
    /**
     * The label alignment
     */
    private int mLabelAlignment = Gravity.CENTER;
    /**
     * The label highlighted color
     */
    private int mLabelHighLightedColor = Color.BLACK;
    /**
     * The label progressing color
     */
    private int mLabelProgressingColor =  Color.BLACK;
    /**
     * Show or hide the progress indicator
     */
    private boolean mIsLoading = false;
    /**
     * Background color
     */
    private int mBkgColor = Color.LTGRAY;
    /**
     * Border color
     */
    private int mBorderColor = Color.LTGRAY;
    /**
     * Border width
     */
    private float mBorderWidth = 0;
    /**
     * Corner radius
     */
    private int mCornerRadius = 0;
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
     * The main button
     */
    private MaterialButton mButton;
    /**
     * ProgressBar showing loading
     */
    private ProgressBar mProgressBar;

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
        mBkgColor = attributes.getColor(R.styleable.MUButton_bkg_color, mBkgColor);
        // Alphas
        mAlpha = attributes.getFloat(R.styleable.MUButton_android_alpha, mAlpha);
        mBorderAlpha = attributes.getFloat(R.styleable.MUButton_border_alpha, mBorderAlpha);
        mDisabledAlpha = attributes.getFloat(R.styleable.MUButton_disable_alpha, mDisabledAlpha);
        // Label
        mLabel = attributes.getString(R.styleable.MUButton_android_text);
        mLabelColor = attributes.getColor(R.styleable.MUButton_android_textColor, mLabelColor);
        mLabelFontSize = attributes.getDimensionPixelSize(R.styleable.MUButton_android_textSize, 0);
        mLabelFontWeight = attributes.getInt(R.styleable.MUButton_android_textStyle, mLabelFontWeight);
        mLabelAlignment = attributes.getInt(R.styleable.MUButton_text_alignment, mLabelAlignment);
        mLabelHighLightedColor = attributes.getColor(R.styleable.MUButton_pressed_color, mLabelHighLightedColor);
        mLabelProgressingColor = attributes.getColor(R.styleable.MUButton_progressing_color, mLabelProgressingColor);
        // Border
        mBorderWidth = attributes.getDimensionPixelSize(R.styleable.MUButton_strokeWidth, 0);
        mBorderColor = attributes.getColor(R.styleable.MUButton_strokeColor, mBkgColor);
        mCornerRadius = attributes.getDimensionPixelSize(R.styleable.MUButton_cornerRadius, 0);
        // Padding
        mVerticalPadding = attributes.getDimensionPixelSize(R.styleable.MUButton_android_paddingVertical, 0);
        mHorizontalPadding = attributes.getDimensionPixelSize(R.styleable.MUButton_android_paddingHorizontal, 0);
        // IsLoading
        mIsLoading = attributes.getBoolean(R.styleable.MUButton_is_loading, false);

        init(context);
        attributes.recycle();
    }

    public MUButton(Context context, TypedArray attributes) {
        super(context);

        if(null != attributes){

            mDisabledAlpha = attributes.hasValue(R.styleable.MUNavigationBar_disable_alpha) ?
                    attributes.getFloat(R.styleable.MUNavigationBar_disable_alpha, mDisabledAlpha)
                    : mDisabledAlpha;
            mBkgColor = attributes.hasValue(R.styleable.MUNavigationBar_bkg_color) ?
                    attributes.getColor(R.styleable.MUNavigationBar_bkg_color, mBkgColor)
                    : mBkgColor;
            mLabel = attributes.hasValue(R.styleable.MUNavigationBar_android_text) ?
                    attributes.getString(R.styleable.MUNavigationBar_android_text)
                    : mLabel;
            mLabelColor = attributes.hasValue(R.styleable.MUNavigationBar_android_textColor) ?
                    attributes.getColor(R.styleable.MUNavigationBar_android_textColor, mLabelColor)
                    : mLabelColor;
            mLabelFontSize = attributes.hasValue(R.styleable.MUNavigationBar_android_textSize) ?
                    attributes.getDimensionPixelSize(R.styleable.MUNavigationBar_android_textSize, 0)
                    : mLabelFontSize;
            mLabelFontWeight = attributes.hasValue(R.styleable.MUNavigationBar_android_textStyle) ?
                    attributes.getColor(R.styleable.MUNavigationBar_android_textStyle, mLabelFontWeight)
                    : mLabelFontWeight;
            mLabelAlignment = attributes.hasValue(R.styleable.MUNavigationBar_text_alignment) ?
                    attributes.getInt(R.styleable.MUNavigationBar_text_alignment, mLabelAlignment)
                    : mLabelAlignment;
            mLabelHighLightedColor = attributes.hasValue(R.styleable.MUNavigationBar_pressed_color) ?
                    attributes.getInt(R.styleable.MUNavigationBar_pressed_color, mLabelHighLightedColor)
                    : mLabelHighLightedColor;
            mLabelProgressingColor = attributes.hasValue(R.styleable.MUNavigationBar_progressing_color) ?
                    attributes.getInt(R.styleable.MUNavigationBar_progressing_color, mLabelProgressingColor)
                    : mLabelProgressingColor;
            mBorderWidth = attributes.hasValue(R.styleable.MUNavigationBar_border_width) ?
                    attributes.getDimensionPixelSize(R.styleable.MUNavigationBar_border_width, 0)
                    : mBorderWidth;
            mBorderColor = attributes.hasValue(R.styleable.MUNavigationBar_border_color) ?
                    attributes.getColor(R.styleable.MUNavigationBar_border_color, mBorderColor)
                    : mBorderColor;
            mCornerRadius = attributes.hasValue(R.styleable.MUNavigationBar_corner_radius) ?
                    attributes.getDimensionPixelSize(R.styleable.MUNavigationBar_corner_radius, mCornerRadius)
                    : mBorderColor;
            mIsLoading = attributes.hasValue(R.styleable.MUNavigationBar_is_loading) ?
                    attributes.getBoolean(R.styleable.MUNavigationBar_is_loading, false)
                    : mIsLoading;
        }

        init(context);
    }

    private void init(Context context){
        mScale = (float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT;

        mButton = new MaterialButton(context);
        mButton.setId(View.generateViewId());
        addView(mButton);

        mProgressBar = new ProgressBar(context);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_END, mButton.getId());
        lp.addRule(RelativeLayout.ALIGN_START, mButton.getId());
        lp.addRule(RelativeLayout.ALIGN_TOP, mButton.getId());
        lp.addRule(RelativeLayout.ALIGN_BOTTOM, mButton.getId());
        addView(mProgressBar, lp);

        // Background
        setBkgColor(mBkgColor);
        // Alphas
        setAlpha(mAlpha);
        setBorderAlpha(mBorderAlpha);
        setDisabledAlpha(mDisabledAlpha);
        //Label
        setLabel(mLabel);
        mButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, mButton.getTextSize());
        setLabelFontWeight(mLabelFontWeight);
        setLabelAlignment(mLabelAlignment);
        setLabelProgressingColor(mLabelProgressingColor);
        //Border
        setBorderWidth(mBorderWidth);
        setCornerRadius(mCornerRadius);
        setBorderColor(mBorderColor);
        //Padding
        setVerticalPadding(mVerticalPadding);
        setHorizontalPadding(mHorizontalPadding);
        // Is loading
        setLoading(mIsLoading);
        //Listener
        setOnClickListener(mListener);
        // Colors
        setFontColors();
        setBackgroundColors();

        Button b = new Button(getContext());
        mButton.setPadding(b.getPaddingLeft(), b.getPaddingTop(), b.getPaddingRight(), b.getPaddingBottom());
        mProgressBar.setPadding(b.getPaddingLeft(), b.getPaddingTop(), b.getPaddingRight(), b.getPaddingBottom());
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
    @Override
    public void setAlpha(float alpha) {
        mAlpha = normalizeAlphaValue(alpha);
        updateColorWithAlphaValues();
        setBackgroundColors();
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
        updateColorWithAlphaValues();
        setBackgroundColors();
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
        mButton.setText(label);
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
        mButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLabelFontSize);
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
        mButton.setTypeface(Typeface.create(Typeface.DEFAULT, mLabelFontWeight));
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
        setFontColors();
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
        setBackgroundColors();
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
        setFontColors();
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mProgressBar.setIndeterminateTintList(ColorStateList.valueOf(mLabelProgressingColor));
        }
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
        mProgressBar.setVisibility(mIsLoading ? VISIBLE : GONE);
        mButton.setTextColor(mIsLoading ? mBkgColor : mLabelColor);
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
        updateColorWithAlphaValues();
        setBackgroundColors();
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
     * @param borderColorID the border color as RGBA integer
     */
    //FIXME use color integer instead
    public void setBorderColor(int borderColorID) {
        int c;
        try {
            c = getResources().getColor(borderColorID);
            mBorderColor = c;
        } catch (Resources.NotFoundException e) {
            borderColorID = R.color.colorPrimary;
            mBorderColor = getResources().getColor(R.color.colorPrimary);
        } finally {
//            setStrokeColor(new ColorStateList(STATES, colors));
            mButton.setStrokeColorResource(borderColorID);
        }
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
        mButton.setStrokeWidth((int) mBorderWidth);
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
        mButton.setCornerRadius(mCornerRadius);
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
        mVerticalPadding = verticalPadding;
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

    private void updateColorWithAlphaValues(){
        mBkgColor = ColorUtils.setAlphaComponent(mBkgColor, (int) (mAlpha * 255));
        mBorderColor = ColorUtils.setAlphaComponent(mBorderColor, (int) (mBorderAlpha * 255));
    }

    /**
     * Static fields representing states; used for convenience only
     */
    static final int[] STATE_PRESSED = new int[] { android.R.attr.state_pressed };
    static final int[] STATE_DISABLED = new int[] { -android.R.attr.state_enabled };
    static final int[] STATE_DEFAULT = new int[] {};
    static final int[][] STATES = new int[][]{STATE_PRESSED, STATE_DISABLED, STATE_DEFAULT};

    /**
     * Set the background color for different states of the button
     */
    private void setBackgroundColors() {
        int[] colors = new int[] {
                mBorderColor,                                                                           // pressed color
                ColorUtils.setAlphaComponent(mBorderColor, (int) (mAlpha * mDisabledAlpha * 255)),      // disabled color
                mBkgColor                                                                               // default color
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mButton.setBackgroundTintList(new ColorStateList(STATES, colors));
        }
    }

    /**
     * Set the font color for different states
     */
    private void setFontColors() {
        int[] colors = new int[] {
                mLabelHighLightedColor,                                                                 // pressed color
                mLabelHighLightedColor,                                                                 // disabled color
                mLabelColor                                                                             // default color
        };

        mButton.setTextColor(new ColorStateList(STATES, colors));
    }

    /**
     * Normalize alpha value between 0 and 1
     * @param alpha the alpha value to check
     * @return the normalized value of alpha
     */
    static float normalizeAlphaValue(float alpha) {
        alpha = alpha < 0 ? 0 : alpha;
        return alpha > 1 ? 1 : alpha;
    }
}
