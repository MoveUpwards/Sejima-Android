package com.rlab.sejima;

import android.content.Context;
import android.graphics.Color;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.DisplayMetrics;

import com.rlab.sejima.features.MUButton;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class MUButtonTests {

    private MUButton mMUButton;
    private float mScale;
    private Context mContext;

    @Before
    public void setUp() {
        mContext = InstrumentationRegistry.getTargetContext();
        mMUButton = new MUButton(mContext);
        assertNotNull(mMUButton);
        mScale = ((float) mContext.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    //    private View.OnClickListener mListener;
//    private float mAlpha;
//    private float mDisabledAlpha;
//    private float mBorderAlpha;
//    private String mLabel = "";
//    private float mLabelFontSize;
//    private int mLabelFontWeight;
//    private int mLabelColor;
//    private int mLabelAlignment;
//    private int mLabelHighLightedColor;
//    private int mLabelProgressingColor;
//    private boolean mIsLoading;
//    private int mBkgColor;
//    private int mBorderColor;
//    private float mBorderWidth;
//    private int mCornerRadius;
//    private float mVerticalPadding;
//    private float mHorizontalPadding;

    @Test
    public void defaultValues() {
        // Alpha
        assertEquals(mMUButton.getAlpha(), 1, 0);
        assertEquals(mMUButton.getDisabledAlpha(), 0.7, 0);
        assertEquals(mMUButton.getBorderAlpha(), 1, 0);
        // Label
        assertEquals(mMUButton.getLabel(), "");
        assertEquals(mMUButton.getLabelFontSize(), mMUButton.getTextSize(), 0);
        assertEquals(mMUButton.getLabelFontWeight(), mMUButton.getLabelFontWeight());
        assertEquals(mMUButton.getLabelColor(), mMUButton.getCurrentTextColor());
        assertEquals(mMUButton.getLabelHighLightedColor(), mMUButton.getCurrentTextColor());
        assertEquals(mMUButton.getLabelProgressingColor(), mMUButton.getCurrentTextColor());
        // Is loading
        assertFalse(mMUButton.isLoading());
        // Bkg color
        assertEquals(mMUButton.getBkgColor(), Color.LTGRAY);
        // Border color
        assertEquals(mMUButton.getBorderColor(), Color.LTGRAY);
        assertEquals(mMUButton.getBorderWidth(), 0,0);
        assertEquals(mMUButton.getCornerRadius(), 0,0);
        // Padding
        assertEquals(mMUButton.getHorizontalPadding(), 0,0);
        assertEquals(mMUButton.getVerticalPadding(), 0,0);
        // Listener
        assertNull(mMUButton.getListener());
    }
}
