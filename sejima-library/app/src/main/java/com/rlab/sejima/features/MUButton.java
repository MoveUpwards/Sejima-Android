package com.rlab.sejima.features;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils;

import com.google.android.material.button.MaterialButton;
import com.rlab.sejima.R;

/*
    Created by Antoine RICHE on 27/03/2019.
 */
public class MUButton extends RelativeLayout implements MUViewHelper {

    /**
     * OnCLickListener to handle clicks
     */
    private OnClickListener mListener = v -> {};

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
    private float mLabelFontSize = 12;

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
     * The progressing color
     */
    private int mProgressingColor =  Color.BLACK;

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
    private int mVerticalPadding = (int) pixelsToDensity(getResources().getDisplayMetrics(), 8);

    /**
     * Horizontal padding
     */
    private int mHorizontalPadding =( int) pixelsToDensity(getResources().getDisplayMetrics(), 8);

    private int mFontStyle = -1;


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
//        mLabelFontSize = attributes.getDimensionPixelSize(R.styleable.MUButton_android_textSize, (int) mLabelFontSize);
        mLabelFontWeight = attributes.getInt(R.styleable.MUButton_android_textStyle, mLabelFontWeight);
        mLabelAlignment = attributes.getInt(R.styleable.MUButton_alignment, mLabelAlignment);
        mLabelHighLightedColor = attributes.getColor(R.styleable.MUButton_pressed_color, mLabelHighLightedColor);
        mProgressingColor = attributes.getColor(R.styleable.MUButton_progressing_color, mProgressingColor);
        // Border
        mBorderWidth = attributes.getDimensionPixelSize(R.styleable.MUButton_strokeWidth, (int) mBorderWidth);
        mBorderColor = attributes.getColor(R.styleable.MUButton_strokeColor, mBkgColor);
        mCornerRadius = attributes.getDimensionPixelSize(R.styleable.MUButton_cornerRadius, mCornerRadius);
        // Padding
        mVerticalPadding = attributes.getDimensionPixelSize(R.styleable.MUButton_android_paddingVertical, mVerticalPadding);
        mHorizontalPadding = attributes.getDimensionPixelSize(R.styleable.MUButton_android_paddingHorizontal, mVerticalPadding);
        // IsLoading
        mIsLoading = attributes.getBoolean(R.styleable.MUButton_is_loading, mIsLoading);
        // Font Style
        mFontStyle = attributes.getResourceId(R.styleable.MUButton_font_style, mFontStyle);

        init(context);
        attributes.recycle();
    }

    /**
     * Constructor used when a super view contains a MUButton
     * @param context the view context
     * @param attributes the XML attributes of the super view
     */
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
                    attributes.getDimensionPixelSize(R.styleable.MUNavigationBar_android_textSize, (int) mLabelFontSize)
                    : mLabelFontSize;
            mLabelFontWeight = attributes.hasValue(R.styleable.MUNavigationBar_android_textStyle) ?
                    attributes.getColor(R.styleable.MUNavigationBar_android_textStyle, mLabelFontWeight)
                    : mLabelFontWeight;
            mLabelAlignment = attributes.hasValue(R.styleable.MUNavigationBar_alignment) ?
                    attributes.getInt(R.styleable.MUNavigationBar_alignment, mLabelAlignment)
                    : mLabelAlignment;
            mLabelHighLightedColor = attributes.hasValue(R.styleable.MUNavigationBar_pressed_color) ?
                    attributes.getInt(R.styleable.MUNavigationBar_pressed_color, mLabelHighLightedColor)
                    : mLabelHighLightedColor;
            mProgressingColor = attributes.hasValue(R.styleable.MUNavigationBar_progressing_color) ?
                    attributes.getInt(R.styleable.MUNavigationBar_progressing_color, mProgressingColor)
                    : mProgressingColor;
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
            mHorizontalPadding = attributes.hasValue(R.styleable.MUNavigationBar_android_paddingHorizontal) ?
                    attributes.getDimensionPixelSize(R.styleable.MUNavigationBar_android_paddingHorizontal, mHorizontalPadding)
                    : mHorizontalPadding;
            mVerticalPadding = attributes.hasValue(R.styleable.MUNavigationBar_android_paddingVertical) ?
                    attributes.getDimensionPixelSize(R.styleable.MUNavigationBar_android_paddingVertical, mVerticalPadding)
                    : mVerticalPadding;
            mFontStyle = attributes.hasValue(R.styleable.MUNavigationBar_font_style) ?
                    attributes.getResourceId(R.styleable.MUNavigationBar_font_style, mFontStyle)
                    : mFontStyle;
        }

        init(context);
    }

    private void init(Context context){

        mButton = new MaterialButton(context);
        mButton.setGravity(Gravity.CENTER);
        mButton.setAllCaps(false);
        mButton.setId(View.generateViewId());
        addView(mButton);

        mProgressBar = new ProgressBar(context);
        mProgressBar.setIndeterminate(true);

        LayoutParams lp2 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.addRule(RelativeLayout.ALIGN_END, mButton.getId());
        lp2.addRule(RelativeLayout.ALIGN_START, mButton.getId());
        lp2.addRule(RelativeLayout.ALIGN_TOP, mButton.getId());
        lp2.addRule(RelativeLayout.ALIGN_BOTTOM, mButton.getId());
        addView(mProgressBar, lp2);

        // Background
        setBkgColor(mBkgColor);
        // Alphas
        setAlpha(mAlpha);
        setBorderAlpha(mBorderAlpha);
        setDisabledAlpha(mDisabledAlpha);
        //Label
        setLabel(mLabel);
        setLabelFontWeight(mLabelFontWeight);
        setLabelAlignment(mLabelAlignment);
//        setLabelFontSize(mLabelFontSize);
        setProgressingColor(mProgressingColor);
        // Style
//        setFontStyle(mFontStyle);
        // Border
        setBorderWidth(mBorderWidth);
        setCornerRadius(mCornerRadius);
        setBorderColor(mBorderColor);
        // Is loading
        setLoading(false);
        // Listener
        setOnClickListener(mListener);
        // Colors
        setFontColors();
        setBackgroundColors();
        //Padding
//        setVerticalPadding(mVerticalPadding);
//        setHorizontalPadding(mHorizontalPadding);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mButton.setWidth(getMeasuredWidth());
        mButton.setHeight(getMeasuredHeight());

        mButton.setPadding(mHorizontalPadding, mVerticalPadding, mHorizontalPadding, mVerticalPadding);
        mProgressBar.setPadding(mHorizontalPadding, mVerticalPadding, mHorizontalPadding, mVerticalPadding);
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
        setOnClickListener(listener);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        mListener = l;
        mButton.setOnClickListener(l);
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
        mLabel = !TextUtils.isEmpty(label) ? label : "";
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
     * @param labelFontSize the label font size in dp.
     */
    public void setLabelFontSize(float labelFontSize) {
        mLabelFontSize = Math.max(labelFontSize, 0);
        mButton.setTextSize(mLabelFontSize);
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
     *      <li>Gravity.START</li>
     *      <li>Gravity.CENTER</li>
     *      <li>Gravity.END</li>
     * </ul>
     */
    public void setLabelAlignment(int labelAlignment) {
        if (labelAlignment != Gravity.END && labelAlignment != Gravity.CENTER) {
            labelAlignment = Gravity.START;
        }
        mLabelAlignment = labelAlignment|Gravity.CENTER_VERTICAL;
        mButton.setGravity(mLabelAlignment);
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
    public int getProgressingColor() {
        return mProgressingColor;
    }

    /**
     * Set the progressing color
     * @param labelProgressingColor the progressing color as RGBA integer
     */
    public void setProgressingColor(int labelProgressingColor) {
        mProgressingColor = labelProgressingColor;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mProgressBar.setIndeterminateTintList(ColorStateList.valueOf(mProgressingColor));
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
        new Handler(Looper.getMainLooper()).post(() ->
                mProgressBar.setVisibility(mIsLoading ? VISIBLE : GONE)
        );
        mButton.setTextColor(mIsLoading ? Color.TRANSPARENT : mLabelColor);
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
     * @param borderColor the border color as ARGB integer
     */
    public void setBorderColor(int borderColor){
        mBorderColor = borderColor;
        mButton.setStrokeColor(ColorStateList.valueOf(borderColor));
        setBackgroundColors();
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
        mBorderWidth = Math.max(borderWidth, 0);
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
        mCornerRadius = Math.max(cornerRadius, 0);
        mButton.setCornerRadius(mCornerRadius);
    }

    /**
     * Get the current vertical padding value
     * @return the current vertical padding value in dp
     */
    public int getVerticalPadding() {
        return mVerticalPadding;
    }

    /**
     * Set the vertical padding value
     * @param verticalPadding the vertical padding value in pixels
     */
    public void setVerticalPadding(int verticalPadding) {
        mVerticalPadding = Math.max(verticalPadding, 0);
        mButton.setPadding(mButton.getPaddingLeft(), (verticalPadding / 2), mButton.getPaddingRight(),  (verticalPadding / 2));
    }

    /**
     * Get the current horizontal padding value
     * @return the current horizontal padding value in dp
     */
    public int getHorizontalPadding() {
        return mHorizontalPadding;
    }

    /**
     * Set the horizontal padding value
     * @param horizontalPadding the horizontal padding value in pixels
     */
    public void setHorizontalPadding(int horizontalPadding) {
        mHorizontalPadding = Math.max(horizontalPadding, 0);
        mButton.setPadding((horizontalPadding / 2), mButton.getPaddingTop(), (horizontalPadding / 2),  mButton.getPaddingBottom());
    }

    /**
     * Return the progessbar used to show loading
     * @return the ProgressBar
     */
    public ProgressBar getProgressBar() {
        return mProgressBar;
    }

    /**
     * Get the MaterialButton
     * @return the main button
     */
    public MaterialButton getButton() {
        return mButton;
    }





    private void updateColorWithAlphaValues(){
        mBkgColor = ColorUtils.setAlphaComponent(mBkgColor, (int) (mAlpha * 255));
        mBorderColor = ColorUtils.setAlphaComponent(mBorderColor, (int) (mBorderAlpha * 255));
    }

    /**
     * Static fields representing states; used for convenience only
     */

    private static final int[] STATE_PRESSED = new int[] { android.R.attr.state_pressed };
    private static final int[] STATE_DISABLED = new int[] { -android.R.attr.state_enabled };
    private static final int[] STATE_DEFAULT = new int[] {};
    private static final int[][] STATES = new int[][]{STATE_PRESSED, STATE_DISABLED, STATE_DEFAULT};

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
     * Get the current font style
     * @return the resource id of the font style
     */
    public int getFontStyle() {
        return mFontStyle;
    }

    /**
     * Set the font style
     * @param fontStyle the resource id of the font style
     */
    public void setFontStyle(int fontStyle) {
        if(checkResource(getResources(), fontStyle)){
            mFontStyle = fontStyle;
            mButton.setTextAppearance(getContext(), fontStyle);
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

}
