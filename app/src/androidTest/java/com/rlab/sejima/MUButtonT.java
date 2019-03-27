package com.rlab.sejima;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.DisplayMetrics;

import com.rlab.sejima.features.MUButton;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class MUButtonT {

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

    @Test
    public void defaultValues() {
        // Alpha
        assertEquals(mMUButton.getAlpha(), 1, 0);
    }
}
