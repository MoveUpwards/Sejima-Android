package com.rlab.sejima.features;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rlab.sejima.R;

public class MUHeader extends RelativeLayout {

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
    public final static int DEFAULT_TITLE_SIZE_IN_SP = 34;
    /**
     * The default size of detail
     */
    public final static int DEFAULT_DETAIL_SIZE_IN_SP = 14;

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
     * The text's horizontal alignment
     */
    private int mAlignment = RelativeLayout.ALIGN_PARENT_START;
    /**
     * Vertical spacing between title and detail
     */
    private int mVerticalSpacing = 8;

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
        s = a.getString(R.styleable.MUHeader_header_title);
        mTitle = TextUtils.isEmpty(s) ? mTitle : s.toString();

        mTitleColor = a.getColor(R.styleable.MUHeader_header_title_color, mTitleColor);
        mTitleSize = a.getDimensionPixelSize(R.styleable.MUHeader_header_title_size, 0);
        mTitleWeight = a.getInt(R.styleable.MUHeader_header_title_weight, mTitleWeight);

        // Deal with detail's attributes
        s = a.getString(R.styleable.MUHeader_header_detail);
        mDetail = TextUtils.isEmpty(s) ? mDetail : s.toString();

        mDetailColor = a.getColor(R.styleable.MUHeader_header_detail_color, mTitleColor);
        mDetailSize = a.getDimensionPixelSize(R.styleable.MUHeader_header_detail_size, 0);
        mDetailWeight = a.getInt(R.styleable.MUHeader_header_detail_weight, mDetailWeight);

        mVerticalSpacing = a.getDimensionPixelSize(R.styleable.MUHeader_header_vertical_spacing, mVerticalSpacing);
        mAlignment = a.getInt(R.styleable.MUHeader_header_alignment, mAlignment);

        init(context);
        a.recycle();
    }

    private void init(Context context) {
        float scale = (float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT;

        LayoutParams lpRoot = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lpRoot);

        LayoutParams lpTitle = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpTitle.addRule(mAlignment);
        lpTitle.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        mTVTitle = new TextView(context);
        mTVTitle.setLayoutParams(lpTitle);
        mTVTitle.setId(View.generateViewId());
        mTitleSize = mTitleSize != 0 ? mTitleSize : DEFAULT_TITLE_SIZE_IN_SP * scale;
        setUpTextView(mTVTitle, mTitle, mTitleColor, mTitleSize, mTitleWeight);
        addView(mTVTitle);

        mTVDetail = new TextView(context);
        LayoutParams lpDetail = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpDetail.addRule(mAlignment);
        lpDetail.addRule(RelativeLayout.BELOW, mTVTitle.getId());
        mTVDetail.setLayoutParams(lpDetail);
        mDetailSize = mDetailSize != 0 ? mDetailSize : DEFAULT_DETAIL_SIZE_IN_SP * scale;
        setUpTextView(mTVDetail, mDetail, mDetailColor, mDetailSize, mDetailWeight);
        mTVDetail.setPadding(0, mVerticalSpacing, 0, 0);
        addView(mTVDetail);
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
        mTVTitle.setText(title);
    }

    public float getTitleSize() {
        return mTitleSize;
    }

    public void setTitleSize(float titleSize) {
        float scale = (float) getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT;
        mTitleSize = titleSize * scale;
        mTVTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTitleSize);
    }

    public int getTitleWeight() {
        return mTitleWeight;
    }

    public void setTitleWeight(int titleWeight) {
        mTitleWeight = titleWeight;
        mTVTitle.setTypeface(Typeface.create(Typeface.DEFAULT, mTitleWeight));
    }

    public int getTitleColor() {
        return mTitleColor;
    }

    public void setTitleColor(int titleColor) {
        mTitleColor = titleColor;
        mTVTitle.setTextColor(mTitleColor);
    }

    public String getDetail() {
        return mDetail;
    }

    public void setDetail(String detail) {
        mDetail = detail;
        mTVDetail.setText(mDetail);
    }

    public float getDetailSize() {
        return mDetailSize;
    }

    public void setDetailSize(float detailSize) {
        float scale = (float) getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT;
        mDetailSize = detailSize * scale;
        mTVDetail.setTextSize(TypedValue.COMPLEX_UNIT_PX, mDetailSize);
    }

    public int getDetailWeight() {
        return mDetailWeight;
    }

    public void setDetailWeight(int detailWeight) {
        mDetailWeight = detailWeight;
        mTVDetail.setTypeface(Typeface.create(Typeface.DEFAULT, mDetailWeight));
    }

    public int getDetailColor() {
        return mDetailColor;
    }

    public void setDetailColor(int detailColor) {
        mDetailColor = detailColor;
        mTVDetail.setTextColor(mDetailColor);
    }

    public int getAlignment() {
        return mAlignment;
    }

    public void setAlignment(int alignment) {
        RelativeLayout.LayoutParams ll = (LayoutParams) mTVTitle.getLayoutParams();
        ll.removeRule(mAlignment);
        ll.addRule(alignment);
        mTVTitle.setLayoutParams(ll);
        mTVDetail.setLayoutParams(ll);
        mAlignment = alignment;
    }

    public void setUpTextView(TextView tv, String text, int color, float size, int typeface){
        tv.setText(text);
        tv.setTextColor(color);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        tv.setTypeface(Typeface.create(Typeface.DEFAULT, typeface));
    }

    public int getVerticalSpacing() {
        return mVerticalSpacing;
    }

    public void setVerticalSpacing(int verticalSpacing) {
        mVerticalSpacing = verticalSpacing;
    }

}
