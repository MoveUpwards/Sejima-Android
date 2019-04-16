package com.rlab.sejima;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;

import com.rlab.sejima.features.MUButton;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MUButtonTests {

    private MUButton mMUButton;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        context.setTheme(R.style.AppTheme);
        mMUButton = new MUButton(context);
        assertNotNull(mMUButton);
    }

    @Test
    public void defaultValues() {

        // Alpha
        assertEquals(1, mMUButton.getAlpha(), 0);
        assertEquals(0.7f, mMUButton.getDisabledAlpha(), 0);
        assertEquals(1, mMUButton.getBorderAlpha(), 0);
        // Label
        assertEquals("", mMUButton.getLabel());
        assertEquals(mMUButton.getLabelFontSize(), mMUButton.getLabelFontSize(), 0);
        assertEquals(Typeface.NORMAL, mMUButton.getLabelFontWeight());
        assertEquals(Color.BLACK, mMUButton.getLabelColor());
        assertEquals(mMUButton.getLabelAlignment(), Gravity.CENTER);
        assertEquals(Color.BLACK, mMUButton.getLabelHighLightedColor());
        assertEquals(Color.BLACK, mMUButton.getProgressingColor());
        // Is loading
        assertFalse(mMUButton.isLoading());
        // Bkg color
        assertEquals(Color.LTGRAY, mMUButton.getBkgColor());
        // Border color
        assertEquals(Color.LTGRAY, mMUButton.getBorderColor());
        assertEquals(0, mMUButton.getBorderWidth(),0);
        assertEquals(0, mMUButton.getCornerRadius(),0);
        // Padding
        assertEquals(18, mMUButton.getHorizontalPadding(),0);
        assertEquals(18, mMUButton.getVerticalPadding(),0);
        // Listener
        assertNotNull(mMUButton.getListener());
        // Style
        mMUButton.setFontStyle(-4);
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
        // Label
        mMUButton.setLabel("c");
        assertEquals("c", mMUButton.getLabel());
        mMUButton.setLabelFontSize(12);
        assertEquals(12, mMUButton.getLabelFontSize(), 0);
        mMUButton.setLabelFontWeight(Typeface.BOLD);
        assertEquals(Typeface.BOLD, mMUButton.getLabelFontWeight());
        mMUButton.setLabelColor(Color.BLUE);
        assertEquals(Color.BLUE, mMUButton.getLabelColor());
        mMUButton.setLabelAlignment(Gravity.START);
        assertEquals(mMUButton.getLabelAlignment(), Gravity.START|Gravity.CENTER_VERTICAL);
        mMUButton.setLabelHighLightedColor(Color.RED);
        assertEquals(Color.RED, mMUButton.getLabelHighLightedColor());
        mMUButton.setProgressingColor(Color.BLACK);
        assertEquals(Color.BLACK, mMUButton.getProgressingColor());
        // Is loading
        mMUButton.setLoading(true);
        assertTrue(mMUButton.isLoading());
        assertEquals(mMUButton.getButton().getCurrentTextColor(), Color.TRANSPARENT);
        // Bkg color
        mMUButton.setBkgColor(Color.CYAN);
        assertEquals(Color.CYAN, mMUButton.getBkgColor());
        // Border color
        mMUButton.setBorderColor(Color.CYAN);
        assertEquals(Color.CYAN, mMUButton.getBorderColor());
        mMUButton.setBorderWidth(15);
        assertEquals(15, mMUButton.getBorderWidth(),0);
        mMUButton.setCornerRadius(17);
        assertEquals(17, mMUButton.getCornerRadius(),0);
        // Padding
        mMUButton.setHorizontalPadding(17);
        assertEquals(17, mMUButton.getHorizontalPadding(),0);
        mMUButton.setVerticalPadding(18);
        assertEquals(18, mMUButton.getVerticalPadding(),0);
        // Listener
        mMUButton.setListener(v -> {});
        assertNotNull(mMUButton.getListener());
    }
}
