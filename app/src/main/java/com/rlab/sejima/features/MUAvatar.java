package com.rlab.sejima.features;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class MUAvatar extends android.support.v7.widget.AppCompatImageView {

    private MUAvatarClickListener mClickListener;

    public MUAvatar(Context context) {
        super(context);
    }

    public MUAvatar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public interface MUAvatarClickListener {
        void clickOnImage(AppCompatImageView imageView);
    }
}
