package com.rlab.sejima;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

import com.rlab.sejima.features.MUTopBar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MUTopBarTests {

    private MUTopBar mMUTopBar;
    private float mScale;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getTargetContext();
        mMUTopBar = new MUTopBar(context);
        assertNotNull(mMUTopBar);
        mScale = ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    @Test
    public void defaultValues() {
        float defaultSize;

        // Title
        assertEquals(mMUTopBar.getTitle(), "");
        // Title's size
        defaultSize = MUTopBar.DEFAULT_TITLE_SIZE_IN_SP * mScale;
        assertEquals(mMUTopBar.getTitleFontSize(), defaultSize, 0);
        // Title's font weight
        assertEquals(mMUTopBar.getTitleFontWeight(), Typeface.NORMAL);
        // Title's color
        assertEquals(mMUTopBar.getTitleColor(), Color.WHITE);
        // Title's alignment
        assertEquals(mMUTopBar.getTitleAlignment(), RelativeLayout.ALIGN_PARENT_START);
        // Button img
        assertEquals(mMUTopBar.getButtonImage(), R.drawable.ic_launcher_background);
        // Button's left leading
        assertEquals(mMUTopBar.getLeftButtonLeading(), 0, 0);
        // Button's width
        defaultSize = MUTopBar.DEFAULT_BUTTON_WIDTH_IN_SP * mScale;
        assertEquals(mMUTopBar.getLeftButtonWidth(), defaultSize, 0);
        // Button's visibility
        assertFalse(mMUTopBar.isButtonHidden());
    }

    @Test
    public void customValues() {
        // Title
        mMUTopBar.setTitle("CUSTOM");
        assertEquals(mMUTopBar.getTitle(), "CUSTOM");
        // Title's size
        mMUTopBar.setTitleFontSize(25);
        assertEquals(mMUTopBar.getTitleFontSize(), 25 * mScale, 0);
        // Title's font weight
        mMUTopBar.setTitleFontWeight(Typeface.BOLD);
        assertEquals(mMUTopBar.getTitleFontWeight(), Typeface.BOLD);
        // Title's color
        mMUTopBar.setTitleColor(Color.BLUE);
        assertEquals(mMUTopBar.getTitleColor(), Color.BLUE);
        // Title's alignment
        mMUTopBar.setTitleAlignment(RelativeLayout.ALIGN_PARENT_END);
        assertEquals(mMUTopBar.getTitleAlignment(), RelativeLayout.ALIGN_PARENT_END);

        // Button img
        mMUTopBar.setButtonImage(R.mipmap.ic_launcher);
        assertEquals(mMUTopBar.getButtonImage(), R.mipmap.ic_launcher);
        // Button's left leading
        mMUTopBar.setLeftButtonLeading(25);
        assertEquals(mMUTopBar.getLeftButtonLeading(), mScale * 25, 0);
        // Button's width
        mMUTopBar.setLeftButtonWidth(25);
        assertEquals(mMUTopBar.getLeftButtonWidth(), mScale * 25, 0);
        // Button's visibility
        mMUTopBar.setButtonHidden(true);
        assertTrue(mMUTopBar.isButtonHidden());
    }

    @Test
    public void hideBtn() {

        assertFalse(mMUTopBar.isButtonHidden());
        mMUTopBar.setButtonImage(-4);
        assertTrue(mMUTopBar.isButtonHidden());

        mMUTopBar.setButtonImage(R.mipmap.ic_launcher_round);
        assertFalse(mMUTopBar.isButtonHidden());
    }
}

