package com.rlab.sejima;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

import com.rlab.sejima.features.MUHeader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class MUHeaderTests {

    private Context mContext;
    private MUHeader mMUHeader;
    private float mScale;

    @Before
    public void setUp() {
        mContext = InstrumentationRegistry.getTargetContext();
        mMUHeader = new MUHeader(mContext);
        mScale = (float) mContext.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT;
        assertNotNull(mMUHeader);
    }

    @Test
    public void defaultValues() {
        float defaultSize;

        // Title
        assertEquals(mMUHeader.getTitle(), "");
        // Title's color
        assertEquals(mMUHeader.getTitleColor(), Color.BLACK);
        // Title's size
        defaultSize = MUHeader.DEFAULT_TITLE_SIZE_IN_SP * mScale;
        assertEquals(mMUHeader.getTitleSize(), defaultSize, 0);
        // Title's font
        assertEquals(mMUHeader.getTitleWeight(), Typeface.NORMAL);
        // Detail
        assertEquals(mMUHeader.getDetail(), "");
        // Detail's color
        assertEquals(mMUHeader.getDetailColor(), Color.BLACK);
        // Detail's size
        defaultSize = MUHeader.DEFAULT_DETAIL_SIZE_IN_SP * mScale;
        assertEquals(mMUHeader.getDetailSize(), defaultSize, 0);
        // Detail's font
        assertEquals(mMUHeader.getDetailWeight(), Typeface.BOLD);
        // Horizontal alignment
        assertEquals(mMUHeader.getAlignment(), RelativeLayout.ALIGN_PARENT_START);
        // Vertical spacing
        assertEquals(mMUHeader.getVerticalSpacing(), 8 * mScale, 0.5);
    }

    @Test
    public void customValues() {
        // Title
        mMUHeader.setTitle("Custom Title");
        assertEquals(mMUHeader.getTitle(), "Custom Title");
        // Title's color
        mMUHeader.setTitleColor(Color.BLUE);
        assertEquals(mMUHeader.getTitleColor(), Color.BLUE);
        // Title's size
        mMUHeader.setTitleSize(58);
        assertEquals(mMUHeader.getTitleSize(), 58 * mScale, 0);
        // Title's font
        mMUHeader.setTitleWeight(Typeface.BOLD);
        assertEquals(mMUHeader.getTitleWeight(), Typeface.BOLD);
        // Detail
        mMUHeader.setDetail("Custom Detail");
        assertEquals(mMUHeader.getDetail(), "Custom Detail");
        // Detail's color
        mMUHeader.setDetailColor(Color.RED);
        assertEquals(mMUHeader.getDetailColor(), Color.RED);
        // Detail's size
        mMUHeader.setDetailSize(18);
        assertEquals(mMUHeader.getDetailSize(), 18 * mScale, 0);
        // Detail's font
        mMUHeader.setDetailWeight(Typeface.ITALIC);
        assertEquals(mMUHeader.getDetailWeight(), Typeface.ITALIC);
        // Horizontal alignment
        mMUHeader.setAlignment(RelativeLayout.ALIGN_PARENT_END);
        assertEquals(mMUHeader.getAlignment(), RelativeLayout.ALIGN_PARENT_END);
        // Vertical spacing
        mMUHeader.setVerticalSpacing(55);
        assertEquals(mMUHeader.getVerticalSpacing(), 55 * mScale, 0.5);
    }
}

