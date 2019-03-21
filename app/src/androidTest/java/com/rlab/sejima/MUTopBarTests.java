package com.rlab.sejima;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;

import com.rlab.sejima.features.MUTopBar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.widget.RelativeLayout.ALIGN_PARENT_START;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class MUTopBarTests {

    private Context mContext;
    private MUTopBar mMUTopBar;

    @Before
    public void setUp() {
        mContext = InstrumentationRegistry.getTargetContext();
        mMUTopBar = new MUTopBar(mContext);
        assertNotNull(mMUTopBar);
    }

    @Test
    public void defaultValues() {

        float defaultSize;
        float scale = ((float) mContext.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);

        // Title
        assertEquals(mMUTopBar.getTitle(), "");
        // Title's size
        defaultSize = MUTopBar.DEFAULT_TITLE_SIZE_IN_SP * scale;
        assertEquals(mMUTopBar.getTitleFontSize(), defaultSize, 0);
        // Title's font weight
        assertEquals(mMUTopBar.getTitleFontWeight(), Typeface.NORMAL);
        // Title's color
        assertEquals(mMUTopBar.getTitleColor(), Color.WHITE);
        // Title's alignment
        assertEquals(mMUTopBar.getTitleAlignment(), Gravity.START);

        // Button img
        assertEquals(mMUTopBar.getButtonImage(), R.drawable.ic_launcher_background);
        // Button's left leading
        assertEquals(mMUTopBar.getLeftButtonLeading(), 0, 0);
        // Button's width
        defaultSize = MUTopBar.DEFAULT_BUTTON_WIDTH_IN_SP * scale;
        assertEquals(mMUTopBar.getLeftButtonWidth(), defaultSize, 0);
        // Button's alignment
        assertEquals(mMUTopBar.getButtonAlignment(), RelativeLayout.ALIGN_PARENT_START);
    }

    @Test
    public void customValues() {

        float scale = ((float) mContext.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);

        // Title
        mMUTopBar.setTitle("CUSTOM");
        assertEquals(mMUTopBar.getTitle(), "CUSTOM");
        // Title's size
        mMUTopBar.setTitleFontSize(25);
        assertEquals(mMUTopBar.getTitleFontSize(), 25 * scale, 0);
        // Title's font weight
        mMUTopBar.setTitleFontWeight(Typeface.BOLD);
        assertEquals(mMUTopBar.getTitleFontWeight(), Typeface.BOLD);
        // Title's color
        mMUTopBar.setTitleColor(Color.BLUE);
        assertEquals(mMUTopBar.getTitleColor(), Color.BLUE);
        // Title's alignment
        mMUTopBar.setTitleAlignment(Gravity.END);
        assertEquals(mMUTopBar.getTitleAlignment(), Gravity.END);

        // Button img
        mMUTopBar.setButtonImage(R.mipmap.ic_launcher);
        assertEquals(mMUTopBar.getButtonImage(), R.mipmap.ic_launcher);
        // Button's left leading
        mMUTopBar.setLeftButtonLeading(25);
        assertEquals(mMUTopBar.getLeftButtonLeading(), scale * 25, 0);
        // Button's width
        mMUTopBar.setLeftButtonWidth(25);
        assertEquals(mMUTopBar.getLeftButtonWidth(), scale * 25, 0);
        // Button's alignment
        mMUTopBar.setButtonAlignment(RelativeLayout.ALIGN_PARENT_END);
        assertEquals(mMUTopBar.getButtonAlignment(), RelativeLayout.ALIGN_PARENT_END);
    }

    @Test
    public void hideBtn() {

        assertEquals(mMUTopBar.isButtonHidden(), false);
        mMUTopBar.setButtonImage(-4);
        assertEquals(mMUTopBar.isButtonHidden(), true);

        mMUTopBar.setButtonImage(R.mipmap.ic_launcher_round);
        assertEquals(mMUTopBar.isButtonHidden(), false);
    }
}

