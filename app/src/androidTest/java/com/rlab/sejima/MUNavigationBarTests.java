package com.rlab.sejima;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.Gravity;

import com.rlab.sejima.features.MUNavigationBar;

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
public class MUNavigationBarTests {

    private MUNavigationBar mMUNavigationBar;
    private float mScale;
    private Context mContext;

    @Before
    public void setUp() {
        mContext = ApplicationProvider.getApplicationContext();
        mContext.setTheme(R.style.AppTheme);
        mMUNavigationBar = new MUNavigationBar(mContext);
        assertNotNull(mMUNavigationBar);
        mScale = ((float) mContext.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    @Test
    public void defaultValues() {

        // Alpha
        assertEquals(0.7f, mMUNavigationBar.getDisabledAlpha(), 0);
        // Label
        assertEquals("", mMUNavigationBar.getLabel());
        assertEquals(mMUNavigationBar.getLabelFontSize(), mMUNavigationBar.getLabelFontSize(), 0);
        assertEquals(Typeface.NORMAL, mMUNavigationBar.getLabelFontWeight());
        assertEquals(Color.BLACK, mMUNavigationBar.getLabelColor());
        assertEquals(mMUNavigationBar.getLabelAlignment(), Gravity.CENTER);
        assertEquals(Color.BLACK, mMUNavigationBar.getLabelHighLightedColor());
        assertEquals(Color.BLACK, mMUNavigationBar.getLabelProgressingColor());
        // Is loading
        assertFalse(mMUNavigationBar.isLoading());
        // Bkg color
        assertEquals(Color.LTGRAY, mMUNavigationBar.getBkgColor());
        // Border color
        assertEquals(Color.LTGRAY, mMUNavigationBar.getBorderColor());
        assertEquals(0, mMUNavigationBar.getBorderWidth(),0);
        assertEquals(0, mMUNavigationBar.getCornerRadius(),0);
        // Padding
        assertEquals(0, mMUNavigationBar.getHorizontalPadding(),0);
        assertEquals(0, mMUNavigationBar.getVerticalPadding(),0);
        // Separator
        assertEquals(Color.BLACK, mMUNavigationBar.getSeparatorColor(),0);
        assertEquals(0, mMUNavigationBar.getSeparatorWidth(),0);
        assertEquals(1, mMUNavigationBar.getSeparatorMultiplier(),0);
        // Listener
        assertNull(mMUNavigationBar.getListener());
    }

    @Test
    public void customValues() {

        // Alpha
        mMUNavigationBar.setDisabledAlpha(-4);
        assertEquals(0, mMUNavigationBar.getDisabledAlpha(), 0);
        // Label
        mMUNavigationBar.setLabel("c");
        assertEquals("c", mMUNavigationBar.getLabel());
        mMUNavigationBar.setLabelFontSize(12);
        assertEquals(12 * mScale, mMUNavigationBar.getLabelFontSize(), 0);
        mMUNavigationBar.setLabelFontWeight(Typeface.BOLD);
        assertEquals(Typeface.BOLD, mMUNavigationBar.getLabelFontWeight());
        mMUNavigationBar.setLabelColor(Color.BLUE);
        assertEquals(Color.BLUE, mMUNavigationBar.getLabelColor());
        mMUNavigationBar.setLabelAlignment(Gravity.START);
        assertEquals(mMUNavigationBar.getLabelAlignment(), Gravity.START|Gravity.CENTER_VERTICAL);
        mMUNavigationBar.setLabelHighLightedColor(Color.RED);
        assertEquals(Color.RED, mMUNavigationBar.getLabelHighLightedColor());
        mMUNavigationBar.setLabelProgressingColor(Color.BLACK);
        assertEquals(Color.BLACK, mMUNavigationBar.getLabelProgressingColor());
        // Is loading
        mMUNavigationBar.setLoading(true);
        assertTrue(mMUNavigationBar.isLoading());
        // Bkg color
        mMUNavigationBar.setBkgColor(Color.CYAN);
        assertEquals(Color.CYAN, mMUNavigationBar.getBkgColor());
        // Border color
        mMUNavigationBar.setBorderColor(Color.RED);
        assertEquals(Color.RED, mMUNavigationBar.getBorderColor());
        mMUNavigationBar.setBorderWidth(15);
        assertEquals(15, mMUNavigationBar.getBorderWidth(),0);
        mMUNavigationBar.setCornerRadius(17);
        assertEquals(17, mMUNavigationBar.getCornerRadius(),0);
        // Padding
        mMUNavigationBar.setHorizontalPadding(17);
        assertEquals(17, mMUNavigationBar.getHorizontalPadding(),0);
        mMUNavigationBar.setVerticalPadding(18);
        assertEquals(18, mMUNavigationBar.getVerticalPadding(),0);
        // Separator
        mMUNavigationBar.setSeparatorColor(Color.YELLOW);
        assertEquals(Color.YELLOW, mMUNavigationBar.getSeparatorColor(),0);
        mMUNavigationBar.setSeparatorWidth(25);
        assertEquals(25, mMUNavigationBar.getSeparatorWidth(),0);
        mMUNavigationBar.setSeparatorMultiplier(3);
        assertEquals(1, mMUNavigationBar.getSeparatorMultiplier(),0);
        // Listener
        mMUNavigationBar.setMUNavigationBarListener(new MUNavigationBar.MUNavigationBarListener() {
            @Override
            public void clickOnLeftButton(MUNavigationBar muNavigationBar) {

            }

            @Override
            public void clickOnRightButton(MUNavigationBar muNavigationBar) {

            }
        });
        assertNotNull(mMUNavigationBar.getListener());
    }

}
