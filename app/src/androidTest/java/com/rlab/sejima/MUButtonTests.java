package com.rlab.sejima;

import android.content.Context;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;

import com.rlab.sejima.features.MUButton;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MUButtonTests {

    private MUButton mMUButton;
    private float mScale;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        context.setTheme(R.style.AppTheme);
        mMUButton = new MUButton(context);
        assertNotNull(mMUButton);
        mScale = ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    @Test
    public void defaultValues() {

        // Alpha
        assertEquals(1, mMUButton.getAlpha(), 0);
        assertEquals(0.7f, mMUButton.getDisabledAlpha(), 0);
        assertEquals(1, mMUButton.getBorderAlpha(), 0);
        // Label
        assertEquals("", mMUButton.getLabel());
        assertEquals(mMUButton.getTextSize(), mMUButton.getLabelFontSize(), 0);
        assertEquals(Typeface.NORMAL, mMUButton.getLabelFontWeight());
        assertEquals(mMUButton.getCurrentTextColor(), mMUButton.getLabelColor());
        assertEquals(mMUButton.getLabelAlignment(), Gravity.CENTER);
        assertEquals(mMUButton.getCurrentTextColor(), mMUButton.getLabelHighLightedColor());
        assertEquals(mMUButton.getCurrentTextColor(), mMUButton.getLabelProgressingColor());
        // Is loading
        assertFalse(mMUButton.isLoading());
        // Bkg color
        assertEquals(Color.LTGRAY, mMUButton.getBkgColor());
        // Border color
        assertEquals(Color.LTGRAY, mMUButton.getBorderColor());
        assertEquals(0, mMUButton.getBorderWidth(),0);
        assertEquals(0, mMUButton.getCornerRadius(),0);
        // Padding
        assertEquals(0, mMUButton.getHorizontalPadding(),0);
        assertEquals(0, mMUButton.getVerticalPadding(),0);
        // Listener
        assertNull(mMUButton.getListener());
    }

    @Test
    public void customValues() {

        // Alpha
        mMUButton.setAlpha(2);
        assertEquals(1, mMUButton.getAlpha(), 0);
        mMUButton.setDisabledAlpha(-4);
        assertEquals(0, mMUButton.getDisabledAlpha(), 0);
        mMUButton.setBorderAlpha(0.5f);
        assertEquals(0.5f, mMUButton.getBorderAlpha(), 0);
//        // Label
        mMUButton.setLabel("c");
        assertEquals("c", mMUButton.getLabel());
        mMUButton.setLabelFontSize(12);
        assertEquals(12 * mScale, mMUButton.getLabelFontSize(), 0);
        mMUButton.setLabelFontWeight(Typeface.BOLD);
        assertEquals(Typeface.BOLD, mMUButton.getLabelFontWeight());
        mMUButton.setLabelColor(Color.BLUE);
        assertEquals(Color.BLUE, mMUButton.getLabelColor());
        mMUButton.setLabelAlignment(Gravity.START);
        assertEquals(mMUButton.getLabelAlignment(), Gravity.START|Gravity.CENTER_VERTICAL);
        mMUButton.setLabelHighLightedColor(Color.RED);
        assertEquals(Color.RED, mMUButton.getLabelHighLightedColor());
        mMUButton.setLabelProgressingColor(Color.BLACK);
        assertEquals(Color.BLACK, mMUButton.getLabelProgressingColor());
        // Is loading
        mMUButton.setLoading(true);
        assertTrue(mMUButton.isLoading());
//        // Bkg color
        mMUButton.setBkgColor(Color.CYAN);
        assertEquals(Color.CYAN, mMUButton.getBkgColor());
//        // Border color
        mMUButton.setBorderColor(R.color.colorPrimary);
        assertEquals(R.color.colorPrimary, mMUButton.getBorderColor());
        mMUButton.setBorderWidth(15);
        assertEquals(15, mMUButton.getBorderWidth(),0);
        mMUButton.setCornerRadius(17);
        assertEquals(17, mMUButton.getCornerRadius(),0);
//        // Padding
        mMUButton.setHorizontalPadding(17);
        assertEquals(17, mMUButton.getHorizontalPadding(),0);
        mMUButton.setVerticalPadding(18);
        assertEquals(18, mMUButton.getVerticalPadding(),0);
//        // Listener
        mMUButton.setListener(v -> {
        });
        assertNotNull(mMUButton.getListener());
    }
}
