package com.rlab.sejima.features;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

import com.rlab.sejima.R;

public class MUTopBar extends RelativeLayout {


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

    private void init(Context context) {
        inflate(context, R.layout.feature_mu_header, this);
    }
}
