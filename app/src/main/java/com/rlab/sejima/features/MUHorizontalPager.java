package com.rlab.sejima.features;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/*
    Created by Antoine RICHE on 01/04/2019.
 */
public class MUHorizontalPager extends ViewPager {

    private int mCurrentOndex = 0;
    private int mNumberPages = 0;
    private float mMargins = 0;

    /**
     * Default constructor
     * @param context the view context
     */
    public MUHorizontalPager(@NonNull Context context) {
        super(context);
    }

    /**
     * Constructor with attributes
     * @param context the view context
     * @param attrs the XML attributes for the view
     */
    public MUHorizontalPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(Context context){
        TextView tv = new TextView(context);
        tv.setText("Test d'une page ajoutée à la volée.");
        addView(tv);
    }

    public interface MUHorizontalPagerScrollListener {
        void scrollToIndex(MUHorizontalPager horizontalPager, int toIndex);
    }

}
