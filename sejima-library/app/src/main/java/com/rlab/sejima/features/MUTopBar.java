package com.rlab.sejima.features;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rlab.sejima.R;

// Created by Antoine RICHE on 20/03/2019.

public class MUTopBar extends RelativeLayout implements MUViewHelper {

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

    private int mFontStyle = -1;
    /**
     * The title's text color
     */
    private int mTitleColor = Color.WHITE;
    /**
     * The title's horizontal alignment
     */
    private int mTitleAlignment = RelativeLayout.ALIGN_PARENT_START;

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
     * Show button value
     */
    private boolean mShowButton = true;
    /**
     * The listener to handle clicks on MUTopBar
     */
    private MUTopBarClickListener mClickListener;


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

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MUTopBar);
        CharSequence s;

        // Deal with title's attributes
        s = attributes.getString(R.styleable.MUTopBar_title);
        mTitle = TextUtils.isEmpty(s) ? mTitle : s.toString();
        mTitleColor = attributes.getColor(R.styleable.MUTopBar_title_color, mTitleColor);
        mTitleFontSize = attributes.getDimensionPixelSize(R.styleable.MUTopBar_title_size, 0);
        mTitleFontWeight = attributes.getInt(R.styleable.MUTopBar_title_weight, mTitleFontWeight);
        mTitleAlignment = attributes.getInt(R.styleable.MUTopBar_topbar_title_alignment, mTitleAlignment);

        mLeftButtonWidth = attributes.getDimensionPixelSize(R.styleable.MUTopBar_topbar_img_width, 0);
        mLeftButtonLeading = attributes.getDimensionPixelSize(R.styleable.MUTopBar_topbar_btn_leading, 0);
        mButtonImage = attributes.getResourceId(R.styleable.MUTopBar_topbar_btn_img, mButtonImage);
        // Font Style
        mFontStyle = attributes.getResourceId(R.styleable.MUTopBar_font_style, mFontStyle);

        init(context);
        attributes.recycle();
    }

    /**
     * Get the left padding of ImageButton
     * @return the left padding of image button in dp.
     */
    public float getLeftButtonLeading() {
        return mLeftButtonLeading;
    }

    /**
     * Set the left padding of ImageButton
     * @param leftButtonLeading the left padding of ImageButton in dp.
     */
    public void setLeftButtonLeading(float leftButtonLeading) {
        mLeftButtonLeading =  normalizeFloatValue(0, leftButtonLeading, leftButtonLeading);
        mIBLeftButton.setLayoutParams(getLeftBtnLayoutParams(mLeftButtonWidth));
    }

    /**
     * Get the left button width
     * @return the left button width in dp.
     */
    public float getLeftButtonWidth() {
        return mLeftButtonWidth;
    }

    /**
     * Set the left button width of ImageButton
     * @param leftButtonWidth the left button width in dp.
     */
    public void setLeftButtonWidth(float leftButtonWidth) {
        mLeftButtonWidth = leftButtonWidth;
        updateImageWidth(mLeftButtonWidth);
    }

    /**
     * Get the title's text
     * @return the text of the Top Bar
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Set the text of the title
     * @param title the title's text
     */
    public void setTitle(String title) {
        mTitle = title;
        mTVLabel.setText(mTitle);
    }

    /**
     * Get the text horizontal alignment
     * @return an integer representing the horizontal alignment.
     * Must be
     * <ul>
     * <li>Gravity.START</li>
     * <li>Gravity.END</li>
     * <li>Gravity.CENTER</li>
     * </ul>
     */
    public int getTitleAlignment() {
        return mTitleAlignment;
    }

    /**
     * Set the text horizontal alignment
     * @param titleAlignment the integer representing the horizontal alignment.
     * Must be
     * <ul>
     * <li>Gravity.START</li>
     * <li>Gravity.END</li>
     * <li>Gravity.CENTER</li>
     * </ul>
     */
    public void setTitleAlignment(int titleAlignment) {
        if (titleAlignment != Gravity.END && titleAlignment != Gravity.CENTER) {
            titleAlignment = Gravity.START;
        }

        mTVLabel.setGravity(titleAlignment);
        mTitleAlignment = titleAlignment;
    }

    /**
     * Get the font size in
     * @return the font size in dp.
     */
    public float getTitleFontSize() {
        return mTitleFontSize;
    }

    /**
     * Set the font size
     * @param titleFontSize the title font size in dp.
     */
    public void setTitleFontSize(float titleFontSize) {
        mTitleFontSize = titleFontSize;
        mTVLabel.setTextSize(mTitleFontSize);
    }

    /**
     * Get the font weight
     * @return en integer representing the font weight
     */
    public int getTitleFontWeight() {
        return mTitleFontWeight;
    }

    /**
     * Set the font weight
     * @param titleFontWeight an integer representing the font weight
     */
    public void setTitleFontWeight(int titleFontWeight) {
        mTitleFontWeight = titleFontWeight;
        mTVLabel.setTypeface(Typeface.create(Typeface.DEFAULT, mTitleFontWeight));
    }

    /**
     * Get the resource id of the drawable used by left image button
     * @return the resource id of the drawable
     */
    public int getButtonImage() {
        return mButtonImage;
    }

    /**
     * Set the drawable of the image button by using its resource id
     * @param buttonImage the resource id of the left image button
     */
    public void setButtonImage(int buttonImage) {
        mButtonImage = buttonImage;
        Drawable drawable = null;
        try {
            drawable = getResources().getDrawable(mButtonImage);
            mIBLeftButton.setImageDrawable(drawable);
        } catch (Resources.NotFoundException e){
            drawable = null;
        } finally {
            setButtonHidden(null == drawable);
        }
    }

    /**
     * Get the code color of the title.
     * @return the code color as ARGB integer.
     */
    public int getTitleColor() {
        return mTitleColor;
    }

    /**
     * Set the color of the text
     * @param titleColor the title color as ARGB integer.
     */
    public void setTitleColor(int titleColor) {
        mTitleColor = titleColor;
        mTVLabel.setTextColor(mTitleColor);
    }

    /**
     * A boolean value to know if the button is visible
     * @return true if the button is visible, false otherwise
     */
    public boolean isButtonHidden() {
        return !mShowButton;
    }

    /**
     * Display/hide the left image button
     * @param hideButton boolean value to hide or display the button
     */
    public void setButtonHidden(boolean hideButton) {
        this.mShowButton = !hideButton;
        mIBLeftButton.setVisibility(this.mShowButton ? VISIBLE : GONE);
    }

    /**
     * Get the interface to handle clicks
     * @return the interface if its set, null otherwise.
     */
    public MUTopBarClickListener getMUTopBarClickListener() {
        return mClickListener;
    }

    /**
     * Attach an interface which manages the clicks on the view
     * @param clickListener the {@link MUTopBarClickListener} to attach.
     */
    public void setMUTopBarClickListener(MUTopBarClickListener clickListener) {
        mClickListener = clickListener;
        if (null != mClickListener) {
            mIBLeftButton.setOnClickListener(l -> mClickListener.clickOnMUTopBar());
            setOnClickListener(l -> mClickListener.clickOnMUTopBar());
        }
        else {
            mIBLeftButton.setOnClickListener(null);
            setOnClickListener(null);
        }
    }

    /**
     * Method called to apply attributes to the view
     * @param context the view context
     */
    private void init(Context context) {
        LayoutParams lpRoot = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lpRoot);

        mLeftButtonWidth = mLeftButtonWidth != 0 ? mLeftButtonWidth : DEFAULT_BUTTON_WIDTH_IN_SP;
        mIBLeftButton = new ImageButton(context);
        mIBLeftButton.setId(View.generateViewId());
        mIBLeftButton.setLayoutParams(getLeftBtnLayoutParams(mLeftButtonWidth));
        setButtonImage(mButtonImage);
        addView(mIBLeftButton);

        LayoutParams lpTVLabel = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpTVLabel.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        lpTVLabel.addRule(RelativeLayout.END_OF, mIBLeftButton.getId());


        mTVLabel = new TextView(context);
        mTVLabel.setLayoutParams(lpTVLabel);
        mTVLabel.setText(mTitle);
        mTVLabel.setTextColor(mTitleColor);
        mTitleFontSize = mTitleFontSize != 0 ? mTitleFontSize : DEFAULT_TITLE_SIZE_IN_SP;
        mTVLabel.setTextSize(mTitleFontSize);
        mTVLabel.setTypeface(Typeface.create(Typeface.DEFAULT, mTitleFontWeight));
        setTitleAlignment(mTitleAlignment);
        addView(mTVLabel);

        setFontStyle(mFontStyle);
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
        mFontStyle = fontStyle;
        mTVLabel.setTextAppearance(getContext(), fontStyle);
    }


    /**
     * Apply the given width on the left image button
     * @param width the width in dp.
     */
    private void updateImageWidth(float width){
        mIBLeftButton.setLayoutParams(getLeftBtnLayoutParams(width));
    }

    /**
     * Get the params to update width and padding of the left ImageButton
     * @param width the width in dp.
     * @return the layout params of the left image button.
     */
    private RelativeLayout.LayoutParams getLeftBtnLayoutParams(float width){
        LayoutParams lpImBtn = new LayoutParams((int) width, (int) width);
        lpImBtn.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        lpImBtn.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
        lpImBtn.setMargins((int) mLeftButtonLeading, (int) mLeftButtonLeading, (int) mLeftButtonLeading, (int) mLeftButtonLeading);
        return lpImBtn;
    }

    /**
     * Interface to handle clicks on ImageButton and TopBar
     */
    public interface MUTopBarClickListener {
        void clickOnMUTopBar();
    }
}
