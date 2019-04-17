package com.rlab.sejima.features;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rlab.sejima.R;

public class MUHeader extends RelativeLayout implements MUViewHelper {

    /**
     * The TextView for the title
     */
    private TextView mTVTitle;
    /**
     * The TextView for the detail
     */
    private TextView mTVDetail;

    /**
     * The default size of title
     */
    public final static int DEFAULT_TITLE_SIZE_IN_SP = 24;
    /**
     * The default size of detail
     */
    public final static int DEFAULT_DETAIL_SIZE_IN_SP = 14;
    /**
     * The default size of vertical spacing
     */
    private final static int DEFAULT_VERTICAL_SPACING_IN_SP = 8;

    /**
     * The current title
     */
    private String mTitle = "";
    /**
     * The title's size
     */
    private float mTitleSize;
    /**
     * The default title's weight
     */
    private int mTitleWeight = Typeface.NORMAL;
    /**
     * The title's font style
     */
    private int mTitleFontStyle = -1;

    /**
     * The title's text color
     */
    private int mTitleColor = Color.BLACK;

    /**
     * The current detail description
     */
    private String mDetail = "";
    /**
     * The detail's size
     */
    private float mDetailSize;
    /**
     * The default detail's weight
     */
    private int mDetailWeight = Typeface.BOLD;
    /**
     * The detail's text color
     */
    private int mDetailColor = Color.BLACK;
    /**
     * The detail's font style
     */
    private int mDetailFontStyle = -1;

    /**
     * The text's horizontal alignment
     */
    private int mAlignment = Gravity.START;
    /**
     * Vertical spacing between title and detail
     */
    private int mVerticalSpacing;

    /**
     * Default constructor
     * @param context the view context
     */
    public MUHeader(Context context) {
        super(context);
        init(context);
    }

    /**
     * Constructor with attributes
     * @param context the view context
     * @param attrs the XML attributes for the view
     */
    public MUHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MUHeader);

        CharSequence s;

        // Deal with title's attributes
        s = a.getString(R.styleable.MUHeader_title);
        mTitle = TextUtils.isEmpty(s) ? mTitle : s.toString();
        mTitleColor = a.getColor(R.styleable.MUHeader_title_color, mTitleColor);
        mTitleSize = a.getDimensionPixelSize(R.styleable.MUHeader_title_size, 0);
        mTitleWeight = a.getInt(R.styleable.MUHeader_title_weight, mTitleWeight);
        mTitleFontStyle = a.getResourceId(R.styleable.MUHeader_title_font_style, mTitleFontStyle);

        // Deal with detail's attributes
        s = a.getString(R.styleable.MUHeader_detail);
        mDetail = TextUtils.isEmpty(s) ? mDetail : s.toString();
        mDetailColor = a.getColor(R.styleable.MUHeader_detail_color, mDetailColor);
        mDetailSize = a.getDimensionPixelSize(R.styleable.MUHeader_detail_size, 0);
        mDetailWeight = a.getInt(R.styleable.MUHeader_detail_weight, mDetailWeight);
        mDetailFontStyle = a.getResourceId(R.styleable.MUHeader_detail_font_style, mDetailFontStyle);

        mVerticalSpacing = a.getDimensionPixelSize(R.styleable.MUHeader_vertical_spacing, 0);
        mAlignment = a.getInt(R.styleable.MUHeader_alignment, mAlignment);

        init(context);
        a.recycle();
    }

    /**
     * Constructor used when a super view contains a MUButton
     * @param context the view context
     * @param attributes the XML attributes of the super view
     */
    public MUHeader(Context context, TypedArray attributes) {
        super(context);

        if(null != attributes){

            mTitle = attributes.hasValue(R.styleable.MUCard_title) ?
                    attributes.getString(R.styleable.MUCard_title)
                    : mTitle;
            mTitleColor = attributes.hasValue(R.styleable.MUCard_title_color) ?
                    attributes.getColor(R.styleable.MUCard_title_color, mTitleColor)
                    : mTitleColor;
            mTitleSize = attributes.hasValue(R.styleable.MUCard_title_size) ?
                    attributes.getDimensionPixelSize(R.styleable.MUCard_title_size, 0)
                    : mTitleSize;
            mTitleFontStyle = attributes.hasValue(R.styleable.MUCard_title_font_style) ?
                    attributes.getResourceId(R.styleable.MUCard_title_font_style, mTitleFontStyle)
                    : mTitleFontStyle;

            mDetail = attributes.hasValue(R.styleable.MUCard_detail) ?
                    attributes.getString(R.styleable.MUCard_detail) : mDetail;
            mDetailColor = attributes.hasValue(R.styleable.MUCard_detail_color) ?
                    attributes.getColor(R.styleable.MUCard_detail_color, mDetailColor)
                    : mDetailColor;
            mDetailSize = attributes.hasValue(R.styleable.MUCard_detail_size) ?
                    attributes.getDimensionPixelSize(R.styleable.MUCard_detail_size, 0)
                    : mDetailSize;
            mDetailFontStyle = attributes.hasValue(R.styleable.MUCard_detail_font_style) ?
                    attributes.getResourceId(R.styleable.MUCard_detail_font_style, mDetailFontStyle)
                    : mDetailFontStyle;

            mAlignment = attributes.hasValue(R.styleable.MUCard_alignment) ?
                    attributes.getInt(R.styleable.MUCard_alignment, mAlignment)
                    : mAlignment;
        }

        init(context);
    }

    private void init(Context context) {

        LayoutParams lpRoot = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lpRoot);

        LayoutParams lpTitle = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpTitle.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        mTVTitle = new TextView(context);
        mTVTitle.setLayoutParams(lpTitle);
        mTVTitle.setId(View.generateViewId());
        mTitleSize = mTitleSize != 0 ? mTitleSize : DEFAULT_TITLE_SIZE_IN_SP;
        setUpTextView(mTVTitle, mTitle, mTitleColor, mTitleSize, mTitleWeight);
        addView(mTVTitle);

        mTVDetail = new TextView(context);
        LayoutParams lpDetail = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpDetail.addRule(RelativeLayout.BELOW, mTVTitle.getId());
        mTVDetail.setLayoutParams(lpDetail);
        mDetailSize = mDetailSize != 0 ? mDetailSize : DEFAULT_DETAIL_SIZE_IN_SP;
        setUpTextView(mTVDetail, mDetail, mDetailColor, mDetailSize, mDetailWeight);

        mVerticalSpacing = mVerticalSpacing != 0 ? mVerticalSpacing : DEFAULT_VERTICAL_SPACING_IN_SP;
        mTVDetail.setPadding(0, mVerticalSpacing, 0, 0);
        addView(mTVDetail);

        setTitleFontStyle(mTitleFontStyle);
        setDetailFontStyle(mDetailFontStyle);
    }

    /**
     * Get the current title
     * @return title of the header as String
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Set the given title to the header
     * @param title the text to set as String
     */
    public void setTitle(String title) {
        mTitle = title;
        mTVTitle.setText(title);
    }

    /**
     * Get the current font size of header's title
     * @return the font size of the title
     */
    public float getTitleSize() {
        return mTitleSize;
    }

    /**
     * Set the font size of header's title
     * @param titleSize the size of header's title
     */
    public void setTitleSize(float titleSize) {
        mTitleSize = titleSize;
        mTVTitle.setTextSize(mTitleSize);
    }

    /**
     * Get the current title's weight
     * @return integer representing weight of the title
     */
    public int getTitleWeight() {
        return mTitleWeight;
    }

    /**
     * Set the given font weight to the title
     * @param titleWeight the font weight to apply
     */
    public void setTitleWeight(int titleWeight) {
        mTitleWeight = titleWeight;
        mTVTitle.setTypeface(Typeface.create(Typeface.DEFAULT, mTitleWeight));
    }

    /**
     * Get the current title color
     * @return the color as ARGB integer
     */
    public int getTitleColor() {
        return mTitleColor;
    }

    /**
     * Set the color of title
     * @param titleColor the color to set as ARGB integer
     */
    public void setTitleColor(int titleColor) {
        mTitleColor = titleColor;
        mTVTitle.setTextColor(mTitleColor);
    }

    /**
     * Get the current detail
     * @return detail of the header as String
     */
    public String getDetail() {
        return mDetail;
    }

    /**
     * Set the given detail to the header
     * @param detail the text to set as String
     */
    public void setDetail(String detail) {
        mDetail = detail;
        mTVDetail.setText(mDetail);
    }

    /**
     * Get the current font size of header's detail
     * @return the font size of the detail
     */
    public float getDetailSize() {
        return mDetailSize;
    }

    /**
     * Set the font size of header's detail
     * @param detailSize the size of header's detail
     */
    public void setDetailSize(float detailSize) {
        mDetailSize = detailSize;
        mTVDetail.setTextSize(mDetailSize);
    }

    /**
     * Get the current detail's weight
     * @return integer representing weight of the detail
     */
    public int getDetailWeight() {
        return mDetailWeight;
    }

    /**
     * Set the given font weight to the detail
     * @param detailWeight the font weight to apply
     */
    public void setDetailWeight(int detailWeight) {
        mDetailWeight = detailWeight;
        mTVDetail.setTypeface(Typeface.create(Typeface.DEFAULT, mDetailWeight));
    }

    /**
     * Get the current detail color
     * @return the color as ARGB integer
     */
    public int getDetailColor() {
        return mDetailColor;
    }

    /**
     * Set the color of detail
     * @param detailColor the color to set as ARGB integer
     */
    public void setDetailColor(int detailColor) {
        mDetailColor = detailColor;
        mTVDetail.setTextColor(mDetailColor);
    }

    public int getAlignment() {
        return mAlignment;
    }

    public void setAlignment(int alignment) {
        if (alignment != Gravity.END && alignment != Gravity.CENTER){
            alignment = Gravity.START;
        }
        mTVDetail.setGravity(alignment);
        mTVTitle.setGravity(alignment);
        mAlignment = alignment;
    }

    private void setUpTextView(TextView tv, String text, int color, float size, int typeface){
        tv.setText(text);
        tv.setTextColor(color);
        tv.setTextSize(size);
        tv.setTypeface(Typeface.create(Typeface.DEFAULT, typeface));
    }

    public int getVerticalSpacing() {
        return mVerticalSpacing;
    }

    public void setVerticalSpacing(int verticalSpacing) {
        mVerticalSpacing = Math.max(0, verticalSpacing);
        mTVDetail.setPadding(0, mVerticalSpacing, 0, 0);
    }

    /**
     * Get the current font style for the title
     * @return the resource id of the font style
     */
    public int getTitleFontStyle() {
        return mTitleFontStyle;
    }

    /**
     * Set the font style for the title
     * @param fontStyle the resource id of the font style
     */
    public void setTitleFontStyle(int fontStyle) {
        if(checkResource(getResources(), fontStyle)){
            mTitleFontStyle = fontStyle;
            mTVTitle.setTextAppearance(getContext(), fontStyle);
        }
    }

    /**
     * Get the current font style for the detail
     * @return the resource id of the font style
     */
    public int getDetailFontStyle() {
        return mDetailFontStyle;
    }

    /**
     * Set the font style for the detail
     * @param fontStyle the resource id of the font style
     */
    public void setDetailFontStyle(int fontStyle) {
        if(checkResource(getResources(), fontStyle)){
            mDetailFontStyle = fontStyle;
            mTVDetail.setTextAppearance(getContext(), fontStyle);
        }
    }

}
