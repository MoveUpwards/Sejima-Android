package com.rlab.sejima;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import android.util.DisplayMetrics;

import com.rlab.sejima.features.MUHeader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.view.View.TEXT_ALIGNMENT_TEXT_END;
import static android.view.View.TEXT_ALIGNMENT_TEXT_START;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class MUHeaderTests {

    private Context mContext;

    @Before
    public void setUp() {
        mContext= InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void defaultValues() {

        MUHeader muHeader = new MUHeader(mContext);
        assertNotNull(muHeader);

        float defaultSize;

        // Title
        assertEquals(muHeader.mTitle, "");
        // Title's color
        assertEquals(muHeader.mTitleColor, Color.BLACK);
        // Title's size
        defaultSize = 34 * ((float) mContext.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        assertEquals(muHeader.mTitleSize, defaultSize, 0);
        // Title's font
        assertEquals(muHeader.mTitleWeight, Typeface.NORMAL);


        // Detail
        assertEquals(muHeader.mDetail, "");
        // Detail's color
        assertEquals(muHeader.mDetailColor, Color.BLACK);
        // Detail's size
        defaultSize = 14 * ((float) mContext.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        assertEquals(muHeader.mDetailSize, defaultSize, 0);
        // Detail's font
        assertEquals(muHeader.mDetailWeight, Typeface.BOLD);


        // Horizontal alignment
        assertEquals(muHeader.mTextAlignment, TEXT_ALIGNMENT_TEXT_START);

        // Vertical spacing
        assertEquals(muHeader.mVerticalSpacing, 8);
    }

    @Test
    public void customValues() {

        MUHeader muHeader = new MUHeader(mContext);
        assertNotNull(muHeader);

        muHeader.mTitle = "Custom Title";
        muHeader.mTitleColor = Color.BLUE;
        muHeader.mTitleSize = 58;
        muHeader.mTitleWeight = Typeface.BOLD;

        // Title
        assertEquals(muHeader.mTitle, "Custom Title");
        // Title's color
        assertEquals(muHeader.mTitleColor, Color.BLUE);
        // Title's size
        assertEquals(muHeader.mTitleSize, 58, 0);
        // Title's font
        assertEquals(muHeader.mTitleWeight, Typeface.BOLD);

        muHeader.mDetail = "Custom Detail";
        muHeader.mDetailColor = Color.RED;
        muHeader.mDetailSize = 18;
        muHeader.mDetailWeight = Typeface.ITALIC;

        // Detail
        assertEquals(muHeader.mDetail, "Custom Detail");
        // Detail's color
        assertEquals(muHeader.mDetailColor, Color.RED);
        // Detail's size
        assertEquals(muHeader.mDetailSize, 18, 0);
        // Detail's font
        assertEquals(muHeader.mDetailWeight, Typeface.ITALIC);

        // Horizontal alignment
        muHeader.mTextAlignment = TEXT_ALIGNMENT_TEXT_END;
        assertEquals(muHeader.mTextAlignment, TEXT_ALIGNMENT_TEXT_END);

        // Vertical spacing
        muHeader.mVerticalSpacing = 55;
        assertEquals(muHeader.mVerticalSpacing, 55);
    }
}

