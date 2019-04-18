package com.rlab.sejima;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.util.TypedValue;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.rlab.sejima.features.MUPinCode;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class MUPinCodeTests {

    private MUPinCode mMUPinCode;
    private Context mContext;

    @Before
    public void setUp() {
        mContext = ApplicationProvider.getApplicationContext();
        mMUPinCode = new MUPinCode(mContext);
        mMUPinCode.setCount(4);
        assertNotNull(mMUPinCode);
    }

    @Test
    public void defaultValues() {

        float converted  = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8,
                mContext.getResources().getDisplayMetrics());

        assertEquals(4, mMUPinCode.getCount(), 0);
        assertEquals(InputType.TYPE_NULL, mMUPinCode.getKeyboardType(), 0);
        assertEquals(".", mMUPinCode.getDefaultChar());
        assertEquals(-1, mMUPinCode.getFontStyle(), 0);
        assertEquals(converted, mMUPinCode.getCellSpacing(), 0);
        assertEquals(Color.WHITE, mMUPinCode.getCellColor());
        assertEquals(0, mMUPinCode.getCellCornerRadius(), 0);
    }

    @Test
    public void customValues() {

        mMUPinCode.setCount(12);
        assertEquals(12, mMUPinCode.getCount(), 0);
        mMUPinCode.setKeyboardType(InputType.TYPE_CLASS_DATETIME);
        assertEquals(InputType.TYPE_CLASS_DATETIME, mMUPinCode.getKeyboardType(), 0);
        mMUPinCode.setDefaultChar("55");
        assertEquals("5", mMUPinCode.getDefaultChar());
        mMUPinCode.setFontStyle(-4);
        assertEquals(-1, mMUPinCode.getFontStyle(), 0);
        mMUPinCode.setCellSpacing(15);
        assertEquals(15, mMUPinCode.getCellSpacing(), 0);
        mMUPinCode.setCellColor(Color.BLACK);
        assertEquals(Color.BLACK, mMUPinCode.getCellColor());
        mMUPinCode.setCellCornerRadius(18);
        assertEquals(18, mMUPinCode.getCellCornerRadius(), 0);
    }
}
