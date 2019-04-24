
package com.rlab.sejima.features;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rlab.sejima.R;


/*
    Created by Antoine RICHE on 28/03/2019.
 */

public class MUNavigationBar extends LinearLayout implements MUViewHelper {

    private MUButton mRightButton;
    private ImageButton mLeftButton;
    private LinearLayout mSeparator;


    /**
     * Background color
     */
    private int mBkgColor = Color.LTGRAY;

    /**
     * Vertical padding
     */
    private float mVerticalPadding = (int) pixelsToDensity(getResources().getDisplayMetrics(), 8);

    /**
     * Horizontal padding
     */
    private float mHorizontalPadding = (int) pixelsToDensity(getResources().getDisplayMetrics(), 8);

    /**
     * The drawable image of the ImageButton
     */
    private Drawable mImgDrawable;
    /**
     * The separator color
     */
    private int mSeparatorColor = Color.BLACK;

    /**
     * The separator width
     */
    private float mSeparatorWidth;

    /**
     * The separator height multiplier
     */
    private float mSeparatorMultiplier = 1;
    /**
     * The separator margins
     */
    private float mSeparatorMargins = (int) pixelsToDensity(getResources().getDisplayMetrics(), 8);;

    /**
     * The MUNavigationBarListener listener
     */
    private MUNavigationBarListener mListener;

    /**
     * Default constructor
     * @param context the view context
     */
    public MUNavigationBar(Context context) {
        super(context);
        init(context, null);
    }

    /**
     * Constructor with attributes
     * @param context the view context
     * @param attrs the XML attributes for the view
     */
    public MUNavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MUNavigationBar);
        mImgDrawable = attributes.getDrawable(R.styleable.MUNavigationBar_android_src);
        mSeparatorColor = attributes.getColor(R.styleable.MUNavigationBar_separator_color, mSeparatorColor);
        mSeparatorWidth = attributes.getDimensionPixelSize(R.styleable.MUNavigationBar_separator_width, (int) mSeparatorWidth);
        mSeparatorMargins = attributes.getDimensionPixelSize(R.styleable.MUNavigationBar_separator_margins, (int) mSeparatorMargins);
        mSeparatorMultiplier = normalizeMultiplierValue(attributes.getFloat(R.styleable.MUNavigationBar_separator_height_multiplier, mSeparatorMultiplier));
        mBkgColor = attributes.getColor(R.styleable.MUNavigationBar_bkg_color, mBkgColor);

        init(context, attributes);
        attributes.recycle();
    }

    private void init(Context context, TypedArray attributes){

        setOrientation(HORIZONTAL);
        setBackgroundColor(mBkgColor);
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
        addView(mLeftButton);

        mSeparator = new LinearLayout(context);
        mSeparator.setBackgroundColor(mSeparatorColor);
        addView(mSeparator);

        mRightButton = new MUButton(context, attributes);
        mRightButton.setOnClickListener(v -> {
            if(mListener != null) {
                mListener.clickOnRightButton(this);
            }
        });

        addView(mRightButton);
//        setVerticalPadding(mVerticalPadding);
        setHorizontalPadding(mHorizontalPadding);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        ViewGroup.LayoutParams right = mRightButton.getLayoutParams();
        right.width = LayoutParams.MATCH_PARENT;
        right.height = LayoutParams.WRAP_CONTENT;
        mRightButton.setLayoutParams(right);

        LayoutParams left = (LayoutParams) mSeparator.getLayoutParams();
        left.width = (int) mSeparatorWidth;
        left.height = (int) (getHeight() * mSeparatorMultiplier);
        left.setMarginStart((int) (mSeparatorMargins / 2));
        left.setMarginEnd((int) (mSeparatorMargins / 2));
        mSeparator.setLayoutParams(left);
    }
    /**
     * Get the current label
     * @return the current label as String
     */
    public String getLabel() {
        return mRightButton.getLabel();
    }

    /**
     * Set the current label
     * @param label the label as String
     */
    public void setLabel(String label) {
        mRightButton.setLabel(label);
    }


    /**
     * Get the label font size
     * @return the font size in dp
     */
    public float getLabelFontSize() {
        return mRightButton.getLabelFontSize();
    }

    /**
     * Set the label font size
     * @param labelFontSize the label font size in pixels.
     */
    public void setLabelFontSize(float labelFontSize) {
        mRightButton.setLabelFontSize(labelFontSize);
    }


    /**
     * Get the current label font weight
     * @return the current label font weight as integer
     */
    public int getLabelFontWeight() {
        return mRightButton.getLabelFontWeight();
    }


    /**
     * Set the label font weight
     * @param labelFontWeight the label font weight as integer
     */
    public void setLabelFontWeight(int labelFontWeight) {
        mRightButton.setLabelFontWeight(labelFontWeight);
    }


    /**
     * Get the current label color as integer
     * @return the label color as RGBA integer
     */
    public int getLabelColor() {
        return mRightButton.getLabelColor();
    }


    /**
     * Set the label color
     * @param labelColor the label color as RGBA integer
     */

    public void setLabelColor(int labelColor) {
        mRightButton.setLabelColor(labelColor);
    }


    /**
     * Get the current label alignment
     * @return the integer representing the current horizontal alignment
     */

    public int getLabelAlignment() {
        return mRightButton.getLabelAlignment();
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
        mRightButton.setLabelAlignment(labelAlignment);
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
        return mRightButton.getDisabledAlpha();
    }


    /**
     * Set the alpha value for disabled state
     * @param disabledAlpha the alpha value to apply
     * Alpha must be between 0 and 1
     */

    public void setDisabledAlpha(float disabledAlpha) {
        mRightButton.setDisabledAlpha(disabledAlpha);
    }


    /**
     * Get the current color for highlighted (=pressed) state
     * @return the current pressed state color as RGBA integer
     */

    public int getLabelHighLightedColor() {
        return mRightButton.getLabelHighLightedColor();
    }


    /**
     * Set the color for pressed state
     * @param labelHighLightedColor the color as RGBA integer
     */

    public void setLabelHighLightedColor(int labelHighLightedColor) {
        mRightButton.setLabelHighLightedColor(labelHighLightedColor);
    }


    /**
     * Get the current progressing color
     * @return the color as RGBA integer
     */

    public int getLabelProgressingColor() {
        return mRightButton.getProgressingColor();
    }


    /**
     * Set the progressing color
     * @param labelProgressingColor the progressing color as RGBA integer
     */

    public void setLabelProgressingColor(int labelProgressingColor) {
        mRightButton.setProgressingColor(labelProgressingColor);
    }


    /**
     * Get the value of the loading state
     * @return the boolean value of the loading state
     */

    public boolean isLoading() {
        return mRightButton.isLoading();
    }


    /**
     * Set the loading value
     * @param loading the loading state value as boolean
     */

    public void setLoading(boolean loading) {
        mRightButton.setLoading(loading);
    }


    /**
     * Get the current background color
     * @return the background color as RGBA integer
     */

    public int getBkgColor() {
        return mRightButton.getBkgColor();
    }


    /**
     * Set the background color
     * @param bkgColor the color as RGBA integer
     */

    public void setBkgColor(int bkgColor) {
        setBackgroundColor(bkgColor);
        mRightButton.setBkgColor(bkgColor);
    }


    /**
     * Get the current border color
     * @return the border color as RGBA integer
     */

    public int getBorderColor() {
        return mRightButton.getBorderColor();
    }


    /**
     * Set the border color
     * @param borderColor the border color as ARGB integer
     */

    public void setBorderColor(int borderColor) {
        mRightButton.setBorderColor(borderColor);
    }


    /**
     * Get the current border width
     * @return the current border width in pixels.
     */

    public float getBorderWidth() {
        return mRightButton.getBorderWidth();
    }


    /**
     * Set the border width in pixels
     * @param borderWidth the border width in pixels
     */

    public void setBorderWidth(float borderWidth) {
        mRightButton.setBorderWidth(borderWidth);
    }


    /**
     * Get the current corner radius value
     * @return the current corner radius
     */

    public int getCornerRadius() {
        return mRightButton.getCornerRadius();
    }


    /**
     * Set the radius value
     * @param cornerRadius the radius value
     */
    public void setCornerRadius(int cornerRadius) {
        mRightButton.setCornerRadius(cornerRadius);
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
        mVerticalPadding = Math.max(0, verticalPadding);
        mRightButton.setVerticalPadding((int) mVerticalPadding);
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
        mHorizontalPadding = Math.max(0, horizontalPadding);
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
        mSeparatorWidth = Math.max(separatorWidth, 0);
        requestLayout();
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
        mSeparatorMultiplier = normalizeMultiplierValue(separatorMultiplier);
        requestLayout();
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
     * @param listener the interface listener
     */

    public void setMUNavigationBarListener(MUNavigationBarListener listener) {
        mListener = listener;
    }


    /**
     * Get the current font style
     * @return the resource id of the font style
     */

    public int getFontStyle() {
        return mRightButton.getFontStyle();
    }


    /**
     * Set the font style
     * @param fontStyle the resource id of the font style
     */

    public void setFontStyle(int fontStyle) {
        mRightButton.setFontStyle(fontStyle);
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

}

