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

    @Before
    public void setUp() {
        mContext= InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void defaultValues() {

        MUHeader muHeader = new MUHeader(mContext);
        assertNotNull(muHeader);
        float scale = (float) mContext.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT;


        float defaultSize;

        // Title
        assertEquals(muHeader.getTitle(), "");
        // Title's color
        assertEquals(muHeader.getTitleColor(), Color.BLACK);
        // Title's size
        defaultSize = MUHeader.DEFAULT_TITLE_SIZE_IN_SP * scale;
        assertEquals(muHeader.getTitleSize(), defaultSize, 0);
        // Title's font
        assertEquals(muHeader.getTitleWeight(), Typeface.NORMAL);


        // Detail
        assertEquals(muHeader.getDetail(), "");
        // Detail's color
        assertEquals(muHeader.getDetailColor(), Color.BLACK);
        // Detail's size
        defaultSize = MUHeader.DEFAULT_DETAIL_SIZE_IN_SP * scale;
        assertEquals(muHeader.getDetailSize(), defaultSize, 0);
        // Detail's font
        assertEquals(muHeader.getDetailWeight(), Typeface.BOLD);


        // Horizontal alignment
        assertEquals(muHeader.getAlignment(), RelativeLayout.ALIGN_PARENT_START);

        // Vertical spacing
        assertEquals(muHeader.getVerticalSpacing(), 8);
    }

    @Test
    public void customValues() {

        MUHeader muHeader = new MUHeader(mContext);
        assertNotNull(muHeader);

        muHeader.setTitle("Custom Title");
        muHeader.setTitleColor(Color.BLUE);
        muHeader.setTitleSize(58);
        muHeader.setTitleWeight(Typeface.BOLD);

        // Title
        assertEquals(muHeader.getTitle(), "Custom Title");
        // Title's color
        assertEquals(muHeader.getTitleColor(), Color.BLUE);
        // Title's size
        assertEquals(muHeader.getTitleSize(), 58, 0);
        // Title's font
        assertEquals(muHeader.getTitleWeight(), Typeface.BOLD);

        muHeader.setDetail("Custom Detail");
        muHeader.setDetailColor(Color.RED);
        muHeader.setDetailSize(18);
        muHeader.setDetailWeight(Typeface.ITALIC);

        // Detail
        assertEquals(muHeader.getDetail(), "Custom Detail");
        // Detail's color
        assertEquals(muHeader.getDetailColor(), Color.RED);
        // Detail's size
        assertEquals(muHeader.getDetailSize(), 18, 0);
        // Detail's font
        assertEquals(muHeader.getDetailWeight(), Typeface.ITALIC);

        // Horizontal alignment
        muHeader.setAlignment(RelativeLayout.ALIGN_PARENT_END);
        assertEquals(muHeader.getAlignment(), RelativeLayout.ALIGN_PARENT_END);

        // Vertical spacing
        muHeader.setVerticalSpacing(55);
        assertEquals(muHeader.getVerticalSpacing(), 55);
    }
}

