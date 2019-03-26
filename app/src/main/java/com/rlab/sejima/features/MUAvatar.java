package com.rlab.sejima.features;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.rlab.sejima.R;

public class MUAvatar extends android.support.v7.widget.AppCompatImageView {

    public Integer SQUARE_BORDER = 0;
    public Integer ROUND_BORDER = 1;

    private MUAvatarClickListener mClickListener;
    private Drawable mImage;
    private Drawable mPlaceholderImage;

    private float mBorderWidth = 0f;
    private int mBorderColor = 0;
    private int mCornerStyle = 0;
    private float mCornerRadius = 0f;

    public MUAvatar(Context context) {
        super(context);
        init(context);
    }

    public MUAvatar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MUAvatar);
        mBorderWidth = attributes.getFloat(R.styleable.MUAvatar_border_width, mBorderWidth);
        mBorderColor = attributes.getColor(R.styleable.MUAvatar_border_color, Color.BLACK);
        mCornerRadius = attributes.getFloat(R.styleable.MUAvatar_corner_radius, mCornerRadius);
        mCornerStyle = attributes.getInt(R.styleable.MUAvatar_corner_style, SQUARE_BORDER);
    }

    private void init(Context context) {
        Resources res = getResources();
        Bitmap src = BitmapFactory.decodeResource(res, R.mipmap.ic_launcher);
        RoundedBitmapDrawable dr =
                RoundedBitmapDrawableFactory.create(res, src);
        dr.setCornerRadius(25);
        setImageDrawable(dr);
    }

    public MUAvatarClickListener getClickListener() {
        return mClickListener;
    }

    public void setClickListener(MUAvatarClickListener clickListener) {
        mClickListener = clickListener;
    }

    public Drawable getImage() {
        return mImage;
    }

    public void setImage(Drawable imageDrawable) {
        mImage = imageDrawable;
    }

    public Drawable getPlaceholderImage() {
        return mPlaceholderImage;
    }

    public void setPlaceholderImage(Drawable placeholderImage) {
        mPlaceholderImage = placeholderImage;
    }

    public float getBorderWidth() {
        return mBorderWidth;
    }

    public void setBorderWidth(float borderWidth) {
        mBorderWidth = borderWidth;
    }

    public int getBorderColor() {
        return mBorderColor;
    }

    public void setBorderColor(int borderColor) {
        mBorderColor = borderColor;
    }

    public int getCornerStyle() {
        return mCornerStyle;
    }

    public void setCornerStyle(int cornerStyle) {
        mCornerStyle = cornerStyle;
    }

    public float getCornerRadius() {
        return mCornerRadius;
    }

    public void setCornerRadius(float cornerRadius) {
        mCornerRadius = cornerRadius;
    }

    public interface MUAvatarClickListener {
        void clickOnImage(AppCompatImageView imageView);
    }
}
