package com.rlab.sejima.features;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rlab.sejima.R;

// Created by Antoine RICHE on 20/03/2019.

public class MUTopBar extends RelativeLayout {

    /**
     * The default size of title
     */
    public final static int DEFAULT_TITLE_SIZE_IN_SP = 24;
    /**
     * The default width of button
     */
    public final static int DEFAULT_BUTTON_WIDTH_IN_SP = 40;

    /**
     * The TextView to display the title
     */
    private TextView mTVLabel;
    /**
     * The left image button
     */
    private ImageButton mIBLeftButton;

    /**
     * The current title
     */
    private String mTitle = "";
    /**
     * The title's font size
     */
    private float mTitleFontSize;
    /**
     * The title's font weight
     */
    private int mTitleFontWeight;
    /**
     * The title's text color
     */
    private int mTitleColor = Color.WHITE;
    /**
     * The title's horizontal alignment
     */
    private int mTitleAlignment = Gravity.START;

    /**
     * The image for the button
     */
    private int mButtonImage = R.drawable.ic_launcher_background;
    /**
     * The left padding
     */
    private float mLeftButtonLeading = 0;
    /**
     * The left button width
     */
    private float mLeftButtonWidth;
    /**
     * The button's horizontal alignment
     */
    private int mButtonAlignment = RelativeLayout.ALIGN_PARENT_START;
    /**
     * Show button value
     */
    private boolean showButton = true;


    /**
     * Default constructor
     * @param context the view context
     */
    public MUTopBar(Context context) {
        super(context);
        init(context);
    }

    /**
     * Constructor with attributes
     * @param context the view context
     * @param attrs the XML attributes for the view
     */
    public MUTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MUTopBar);

        CharSequence s;

        // Deal with title's attributes
        s = a.getString(R.styleable.MUTopBar_topbar_title);
        mTitle = TextUtils.isEmpty(s) ? mTitle : s.toString();

        mTitleColor = a.getColor(R.styleable.MUTopBar_topbar_title_color, mTitleColor);
        mTitleFontSize = a.getDimensionPixelSize(R.styleable.MUTopBar_topbar_title_size, 0);
        mTitleFontWeight = a.getInt(R.styleable.MUTopBar_topbar_title_weight, mTitleFontWeight);
        mTitleAlignment = a.getInt(R.styleable.MUTopBar_topbar_title_alignment, TEXT_ALIGNMENT_TEXT_START);

        mButtonAlignment = a.getInt(R.styleable.MUTopBar_topbar_btn_alignment, mButtonAlignment);
        mLeftButtonWidth = a.getDimensionPixelSize(R.styleable.MUTopBar_topbar_img_width, 0);
        mLeftButtonLeading = a.getDimensionPixelSize(R.styleable.MUTopBar_topbar_btn_leading, 0);
        mButtonImage = a.getResourceId(R.styleable.MUTopBar_topbar_btn_img, mButtonImage);

        init(context);
    }

    public float getLeftButtonLeading() {
        return mLeftButtonLeading;
    }

    public void setLeftButtonLeading(float leftButtonLeading) {
        float scale = ((float) getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        mLeftButtonLeading = leftButtonLeading * scale;
        LayoutParams lpImBtn = (LayoutParams) mIBLeftButton.getLayoutParams();
        lpImBtn.setMargins((int) mLeftButtonLeading, (int) mLeftButtonLeading, (int) mLeftButtonLeading, (int) mLeftButtonLeading);
        mIBLeftButton.setLayoutParams(lpImBtn);
    }

    public float getLeftButtonWidth() {
        return mLeftButtonWidth;
    }

    public void setLeftButtonWidth(float leftButtonWidth) {
        float scale = ((float) getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        this.mLeftButtonWidth = leftButtonWidth * scale;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
        mTVLabel.setText(mTitle);
    }

    public int getButtonAlignment() {
        return mButtonAlignment;
    }

    public void setButtonAlignment(int buttonAlignment) {
        mButtonAlignment = buttonAlignment;
        LayoutParams lpImBtn = (LayoutParams) mIBLeftButton.getLayoutParams();
        lpImBtn.addRule(mButtonAlignment, RelativeLayout.TRUE);
        mIBLeftButton.setLayoutParams(lpImBtn);
    }

    public int getTitleAlignment() {
        return mTitleAlignment;
    }

    public void setTitleAlignment(int titleAlignment) {
        mTitleAlignment = titleAlignment;
    }

    public float getTitleFontSize() {
        return mTitleFontSize;
    }

    public void setTitleFontSize(int titleFontSize) {
        float scale = ((float) getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        mTitleFontSize = titleFontSize * scale;
        mTVLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTitleFontSize);
    }

    public int getTitleFontWeight() {
        return mTitleFontWeight;
    }

    public void setTitleFontWeight(int titleFontWeight) {
        mTitleFontWeight = titleFontWeight;
        mTVLabel.setTypeface(Typeface.create(Typeface.DEFAULT, mTitleFontWeight));
    }

    public int getButtonImage() {
        return mButtonImage;
    }

    public void setButtonImage(int buttonImage) {
        mButtonImage = buttonImage;
        Drawable drawable = null;
        try {
            drawable = getResources().getDrawable(mButtonImage);
        } catch (Resources.NotFoundException e){
            Log.e(getClass().getCanonicalName(), "Unfound img drawable", e);
            drawable = null;
        } finally {
            mIBLeftButton.setImageDrawable(drawable);
            setButtonHidden(null == drawable);
        }
    }

    public int getTitleColor() {
        return mTitleColor;
    }

    public void setTitleColor(int titleColor) {
        mTitleColor = titleColor;
        mTVLabel.setTextColor(mTitleColor);
    }

    public boolean isButtonHidden() {
        return !showButton;
    }

    public void setButtonHidden(boolean hideButton) {
        this.showButton = !hideButton;
        mIBLeftButton.setVisibility(this.showButton ? VISIBLE : GONE);
    }

    /**
     * Method called to apply attributes to the view
     * @param context the view context
     */
    private void init(Context context) {
        float scale = ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        setOnClickListener(l -> didClick());

        LayoutParams lpRoot = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lpRoot);

        mLeftButtonWidth = mLeftButtonWidth != 0 ? mLeftButtonWidth : (int) (DEFAULT_BUTTON_WIDTH_IN_SP * scale);
        LayoutParams lpImBtn = new LayoutParams((int) mLeftButtonWidth, (int) mLeftButtonWidth);
        lpImBtn.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        lpImBtn.addRule(mButtonAlignment, RelativeLayout.TRUE);
        lpImBtn.setMargins((int) mLeftButtonLeading, (int) mLeftButtonLeading, (int) mLeftButtonLeading, (int) mLeftButtonLeading);

        mIBLeftButton = new ImageButton(context);
        mIBLeftButton.setLayoutParams(lpImBtn);
        Drawable drawable = context.getResources().getDrawable(mButtonImage);
        mIBLeftButton.setImageDrawable(drawable);
        mIBLeftButton.setId(View.generateViewId());
        addView(mIBLeftButton);

        LayoutParams lpTVLabel = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpTVLabel.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        lpTVLabel.addRule(RelativeLayout.END_OF, mIBLeftButton.getId());

        mTVLabel = new TextView(context);
        mTVLabel.setLayoutParams(lpTVLabel);
        mTVLabel.setText(mTitle);
        mTVLabel.setTextColor(mTitleColor);
        mTitleFontSize = mTitleFontSize != 0 ? mTitleFontSize : DEFAULT_TITLE_SIZE_IN_SP * scale;
        mTVLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTitleFontSize);
        mTVLabel.setTypeface(Typeface.create(Typeface.DEFAULT, mTitleFontWeight));
        addView(mTVLabel);
    }


    /**
     * Method triggered each time button is tapped.
     */
    public void didClick(){
        Log.d(this.getClass().getName(), "MUTopBar has been clicked");
    }

    /**
     * Update Image width
     */
    private void updateImageWidth(){
        //TODO implement method
    }
}
