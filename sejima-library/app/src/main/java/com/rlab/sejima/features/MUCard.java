package com.rlab.sejima.features;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rlab.sejima.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

public class MUCard extends CardView implements MUViewHelper {

    private LinearLayout mContentView;
    private LinearLayout mRootView;
    private MUHeader mMUHeader;


    /**
     * The top container padding
     */
    private float mTopPadding =  (int) pixelsToDensity(getResources().getDisplayMetrics(), 24);
    /**
     * The content left padding
     */
    private float mContentLeftPadding = (int) pixelsToDensity(getResources().getDisplayMetrics(), 24);
    /**
     * The content right padding
     */
    private float mContentRightPadding = (int) pixelsToDensity(getResources().getDisplayMetrics(), 24);
    /**
     * The content top padding
     */
    private float mContentTopPadding = (int) pixelsToDensity(getResources().getDisplayMetrics(), 24);
    /**
     * The content bottom padding
     */
    private float mContentBottomPadding = (int) pixelsToDensity(getResources().getDisplayMetrics(), 24);
    /**
     * The background color id
     */
    private int mBkgColor = Color.YELLOW;
    /**
     * The style of the card resource id
     */
    private int mStyleResId = -1;
    /**
     * ,The current border width
     */
    private float mBorderWidth = (int) pixelsToDensity(getResources().getDisplayMetrics(), 4);
    /**
     *  The current border color
     */
    private int mBorderColor;
    /**
     *  The current corner radius
     */
    private float mCornerRadius = 5;
    /**
     * The horizontal padding for the header
     */
    private float mHeaderHorizontalPadding = (int) pixelsToDensity(getResources().getDisplayMetrics(), 24);

    /**
     * Default constructor
     * @param context the view context
     */
    public MUCard(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    /**
     * Constructor with attributes
     * @param context the view context
     * @param attrs the XML attributes for the view
     */
    public MUCard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MUCard);
        mTopPadding = attributes.getDimension(R.styleable.MUCard_top_padding, mTopPadding);
        mContentLeftPadding = attributes.getDimension(R.styleable.MUCard_content_left_padding, mContentLeftPadding);
        mContentRightPadding = attributes.getDimension(R.styleable.MUCard_content_right_padding, mContentRightPadding);
        mContentTopPadding = attributes.getDimension(R.styleable.MUCard_content_top_padding, mContentTopPadding);
        mContentBottomPadding = attributes.getDimension(R.styleable.MUCard_content_bottom_padding, mContentBottomPadding);

        mBorderWidth = attributes.getDimension(R.styleable.MUCard_border_width, mBorderWidth);
        mBorderColor = attributes.getColor(R.styleable.MUCard_border_color, mBorderColor);
        mCornerRadius = attributes.getDimension(R.styleable.MUCard_corner_radius, mCornerRadius);
        mBkgColor = attributes.getInt(R.styleable.MUCard_android_background, mBkgColor);
        mHeaderHorizontalPadding = attributes.getDimension(R.styleable.MUCard_android_paddingHorizontal, mHeaderHorizontalPadding);
//        mStyleResId = attributes.getResourceId(R.styleable.MUCard_android_style, mStyleResId);

        init(context, attributes);
        attributes.recycle();
    }

    private void init(Context context, TypedArray attributeSet) {
        mRootView = new LinearLayout(context);

        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins((int) mBorderWidth, (int) mBorderWidth, (int) mBorderWidth, (int) mBorderWidth);
        mRootView.setLayoutParams(lp);
        mRootView.setOrientation(LinearLayout.VERTICAL);
        addView(mRootView);

        mMUHeader = new MUHeader(context, attributeSet);
        mRootView.addView(mMUHeader);

        mContentView = new LinearLayout(context);
        mContentView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mContentView.setPadding((int) mContentLeftPadding, (int) mContentTopPadding, (int) mContentRightPadding, (int) mContentBottomPadding);
        mRootView.addView(mContentView);

        if(isInEditMode()){
            TextView tv = new TextView(context);
            tv.setText(context.getResources().getString(R.string.app_name));
            tv.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            addContentView(tv);
        }

        setRadius(mCornerRadius);
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Header padding
        mMUHeader.setPadding((int) (mHeaderHorizontalPadding / 2), 0 , (int) (mHeaderHorizontalPadding / 2), 0);
        // Border width
        LayoutParams lp = (LayoutParams) mRootView.getLayoutParams();
        lp.setMargins((int) mBorderWidth, (int) mBorderWidth, (int) mBorderWidth, (int) mBorderWidth);
        mRootView.setLayoutParams(lp);
        // Border color
        setCardBackgroundColor(mBorderColor);
        // Background color
        applyRoundCornerToView(mCornerRadius, mBkgColor, mRootView);

        mRootView.setPadding((int) (mHeaderHorizontalPadding / 2), (int) mTopPadding, (int) (mHeaderHorizontalPadding / 2), 0);
    }

    /**
     * Get the current view attached to the card
     * @return the view attached
     */
    public View getContentView() {
        return mContentView;
    }

    /**
     * Get the current top padding of the card
     * @return the top padding in dp
     */
    public float getTopPadding() {
        return mTopPadding;
    }

    /**
     * Set the top padding
     * @param topPadding the top padding to apply in dp
     */
    public void setTopPadding(float topPadding) {
        mTopPadding = topPadding;
        invalidate();
    }

    /**
     * Get the current left padding for the content
     * @return the current left padding in dp
     */
    public float getContentLeftPadding() {
        return mContentLeftPadding;
    }

    /**
     * Set the left padding for content
     * @param contentLeftPadding the left padding for content to apply in dp
     */
    public void setContentLeftPadding(float contentLeftPadding) {
        mContentLeftPadding = contentLeftPadding;
        invalidate();
    }

    /**
     * Get the current right padding for the content
     * @return the current right padding in dp
     */
    public float getContentRightPadding() {
        return mContentRightPadding;
    }

    /**
     * Set the right padding for content
     * @param contentRightPadding the right padding for content to apply in dp
     */
    public void setContentRightPadding(float contentRightPadding) {
        mContentRightPadding = contentRightPadding;
        invalidate();
    }

    /**
     * Get the current top padding for the content
     * @return the current top padding in dp
     */
    public float getContentTopPadding() {
        return mContentTopPadding;
    }

    /**
     * Set the top padding for content
     * @param contentTopPadding the top padding for content to apply in dp
     */
    public void setContentTopPadding(float contentTopPadding) {
        mContentTopPadding = contentTopPadding;
        invalidate();
    }

    /**
     * Get the current bottom padding for the content
     * @return the current bottom padding in dp
     */
    public float getContentBottomPadding() {
        return mContentBottomPadding;
    }

    /**
     * Set the bottom padding for content
     * @param contentBottomPadding the bottom padding for content to apply in dp
     */
    public void setContentBottomPadding(float contentBottomPadding) {
        mContentBottomPadding = contentBottomPadding;
        invalidate();
    }

    /**
     * Get the current background color for the card
     * @return the background color as ARGB integer
     */
    public int getBkgColor() {
        return mBkgColor;
    }

    /**
     * Set the background color
     * @param bkgColor the background color as ARGB integer
     */
    public void setBkgColor(int bkgColor) {
        mBkgColor = bkgColor;
        mRootView.setBackgroundColor(mBkgColor);
    }

    /**
     * Get the current style resource id attached to the card
     * @return the style resource id
     */
    public int getStyleResId() {
        return mStyleResId;
    }

    /**
     * Apply the given style to the card
     * @param styleResId the resource id of the style to be applied
     */
    public void setStyleResId(int styleResId) {
        if(checkResource(getResources(), styleResId)){
            mStyleResId = styleResId;
            invalidate();
        }
    }

    /**
     * Get the current border width value
     * @return the border width in dp
     */
    public float getBorderWidth() {
        return mBorderWidth;
    }

    /**
     * Set the border width to apply
     * @param borderWidth the border width in dp
     */
    public void setBorderWidth(float borderWidth) {
        mBorderWidth = normalizeFloatValue(borderWidth, 0, 100);
        invalidate();
    }

    /**
     * Get the current border color
     * @return the border color as ARGB integer
     */
    public int getBorderColor() {
        return mBorderColor;
    }

    /**
     * Set the given border color
     * @param borderColor the color as ARGB integer
     */
    public void setBorderColor(int borderColor) {
        mBorderColor = borderColor;
        setBackgroundColor(mBorderColor);
    }

    /**
     * Get the current corner radius of the card
     * @return the current corner radius
     */
    public float getCornerRadius() {
        return mCornerRadius;
    }

    /**
     * Set the giver corner radius to the card
     * @param cornerRadius the corner radius to be applied
     */
    public void setCornerRadius(float cornerRadius) {
        mCornerRadius = normalizeFloatValue(cornerRadius, 0, 120);
        setRadius(mCornerRadius);
    }

    @Override
    public int getTextAlignment() {
        return mMUHeader.getAlignment();
    }

    @Override
    public void setTextAlignment(int textAlignment) {
        mMUHeader.setAlignment(textAlignment);
    }

    /**
     * Get the current horizontal padding for the header
     * @return the current horizontal padding for the headerin dp
     */
    public float getHeaderHorizontalPadding() {
        return mHeaderHorizontalPadding;
    }

    /**
     * Set the given horizontal padding for the header
     * @param headerHorizontalPadding the horizontal padding to apply in dp
     */
    public void setHeaderHorizontalPadding(float headerHorizontalPadding) {
        //FIXME getWidth at runtime
        mHeaderHorizontalPadding = normalizeFloatValue(headerHorizontalPadding, 0, 200);
        invalidate();
    }

    /**
     * Current title of the header
     * @return the title of the header
     */
    public String getTitle() {
        return mMUHeader.getTitle();
    }

    /**
     * Set the given title to the header
     * @param title the title to set
     */
    public void setTitle(String title) {
        mMUHeader.setTitle(title);
    }

    /**
     * Get the current font style of header's title
     * @return the font style resource id of header's title
     */
    public int getTitleFontStyle() {
        return mMUHeader.getTitleFontStyle();
    }

    /**
     * Set the given font style to header's title
     * @param titleFontStyle the font style resource id
     */
    public void setTitleFontStyle(int titleFontStyle) {
        mMUHeader.setTitleFontStyle(titleFontStyle);
    }

    /**
     * Get the current header's title color
     * @return the color as ARGB integer
     */
    public int getTitleFontColor() {
        return mMUHeader.getTitleColor();
    }

    /**
     * Set the color of header's title
     * @param titleFontColor the color to set as ARGB integer
     */
    public void setTitleFontColor(int titleFontColor) {
        mMUHeader.setTitleColor(titleFontColor);
    }

    /**
     * Current detail of the header
     * @return the detail of the header
     */
    public String getDetail() {
        return mMUHeader.getDetail();
    }

    /**
     * Set the given detail to the header
     * @param detail the detail to set
     */
    public void setDetail(String detail) {
        mMUHeader.setDetail(detail);
    }

    /**
     * Get the current font style of header's detail
     * @return the font style resource id of header's title
     */
    public int getDetailFontStyle() {
        return mMUHeader.getDetailFontStyle();
    }

    /**
     * Set the given font style to header's detail
     * @param detailFontStyle the font style resource id
     */
    public void setDetailFontStyle(int detailFontStyle) {
        mMUHeader.setDetailFontStyle(detailFontStyle);
    }

    /**
     * Get the current header's detail color
     * @return the color as ARGB integer
     */
    public int getDetailFontColor() {
        return mMUHeader.getDetailColor();
    }

    /**
     * Set the color of header's detail
     * @param detailFontColor the color to set as ARGB integer
     */
    public void setDetailFontColor(int detailFontColor) {
        mMUHeader.setDetailColor(detailFontColor);
    }

    /**
     * Add a view into the card
     * @param view the view to add to the card
     */
    public void addContentView(View view){
        mContentView.removeAllViews();
        mContentView.addView(view, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
