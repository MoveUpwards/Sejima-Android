package com.rlab.sejima.features;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rlab.sejima.R;

// Created by Antoine RICHE on 20/03/2019.

public class MUTopBar extends RelativeLayout {

    /**
     * The TextView to display the title
     */
    private TextView mTVLabel;
    /**
     * The left image button
     */
    private ImageButton mIBLeftButton;

    /**
     * The left padding
     */
    private float leftButtonLeading;
    /**
     * The left button width
     */
    private float leftButtonWidth;
    /**
     * The current title
     */
    private String mTitile = "";
    /**
     * The button's horizontal alignment
     */
    private int mButtonAlignment = ALIGN_START;
    /**
     * The title's horizontal alignment
     */
    private int mTitleAlignment = ALIGN_START;
    /**
     * The title's font size
     */
    private int mTitleFontSize;
    /**
     * The title's font weight
     */
    private int mTitleFontWeight;
    /**
     * The image for the button
     */
    private Drawable mButtonImage;
    /**
     * The title's text color
     */
    private int mTitleColor;
    /**
     * Show button value
     */
    private boolean showButton;


    /**
     * Default constructor
     * @param context the view context
     */
    public MUTopBar(Context context) {
        super(context);
    }

    /**
     * Constructor with attributes
     * @param context the view context
     * @param attrs the XML attributes for the view
     */
    public MUTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public float getLeftButtonLeading() {
        return leftButtonLeading;
    }

    public void setLeftButtonLeading(float leftButtonLeading) {
        this.leftButtonLeading = leftButtonLeading;
    }

    public float getLeftButtonWidth() {
        return leftButtonWidth;
    }

    public void setLeftButtonWidth(float leftButtonWidth) {
        this.leftButtonWidth = leftButtonWidth;
    }

    public String getTitile() {
        return mTitile;
    }

    public void setTitile(String titile) {
        mTitile = titile;
    }

    public int getButtonAlignment() {
        return mButtonAlignment;
    }

    public void setButtonAlignment(int buttonAlignment) {
        mButtonAlignment = buttonAlignment;
    }

    public int getTitleAlignment() {
        return mTitleAlignment;
    }

    public void setTitleAlignment(int titleAlignment) {
        mTitleAlignment = titleAlignment;
    }

    public int getTitleFontSize() {
        return mTitleFontSize;
    }

    public void setTitleFontSize(int titleFontSize) {
        mTitleFontSize = titleFontSize;
    }

    public int getTitleFontWeight() {
        return mTitleFontWeight;
    }

    public void setTitleFontWeight(int titleFontWeight) {
        mTitleFontWeight = titleFontWeight;
    }

    public Drawable getButtonImage() {
        return mButtonImage;
    }

    public void setButtonImage(Drawable buttonImage) {
        mButtonImage = buttonImage;
    }

    public int getTitleColor() {
        return mTitleColor;
    }

    public void setTitleColor(int titleColor) {
        mTitleColor = titleColor;
    }

    /**
     * Method called to apply attributes to the view
     * @param context the view context
     */
    private void init(Context context) {
        inflate(context, R.layout.feature_mu_header, this);
        setOnClickListener(l -> didClick());
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
