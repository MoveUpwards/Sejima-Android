package com.rlab.sejima;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;

import com.rlab.sejima.features.MUCard;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class MUCardTests {

    private MUCard mMUCard;
    private Context mContext;


//    private float mCornerRadius = 5;
//    private float mHeaderHorizontalPadding = (int) pixelsToDensity(getResources().getDisplayMetrics(), 24);
//

    @Before
    public void setUp() {
        mContext = ApplicationProvider.getApplicationContext();
        mContext.setTheme(R.style.AppTheme);
        mMUCard = new MUCard(mContext);
        assertNotNull(mMUCard);
    }

    @Test
    public void defaultValues() {
        assertEquals(Color.GRAY, mMUCard.getBkgColor(), 0);
        assertEquals(convertDPToPixel(24), mMUCard.getTopPadding(), 0);
        assertEquals(convertDPToPixel(24), mMUCard.getContentLeftPadding(), 0);
        assertEquals(convertDPToPixel(24), mMUCard.getContentRightPadding(), 0);
        assertEquals(convertDPToPixel(24), mMUCard.getContentTopPadding(), 0);
        assertEquals(convertDPToPixel(24), mMUCard.getContentBottomPadding(), 0);
        assertEquals(convertDPToPixel(4), mMUCard.getBorderWidth(), 0);
        assertEquals(0, mMUCard.getBorderColor(), 0);
        assertEquals(-1, mMUCard.getStyleResId(), 0);
        assertEquals(5, mMUCard.getCornerRadius(), 0);
        assertEquals(convertDPToPixel(24), mMUCard.getHeaderHorizontalPadding(), 0);
    }

    @Test
    public void customValues() {
        mMUCard.setBkgColor(Color.RED);
        assertEquals(Color.RED, mMUCard.getBkgColor(), 0);
        mMUCard.setTopPadding(2);
        assertEquals(2, mMUCard.getTopPadding(), 0);
        mMUCard.setContentLeftPadding(3);
        assertEquals(3, mMUCard.getContentLeftPadding(), 0);
        mMUCard.setContentRightPadding(4);
        assertEquals(4, mMUCard.getContentRightPadding(), 0);
        mMUCard.setContentTopPadding(5);
        assertEquals(5, mMUCard.getContentTopPadding(), 0);
        mMUCard.setContentBottomPadding(6);
        assertEquals(6, mMUCard.getContentBottomPadding(), 0);
        mMUCard.setBorderWidth(14);
        assertEquals(14, mMUCard.getBorderWidth(), 0);
        mMUCard.setBorderColor(Color.YELLOW);
        assertEquals(Color.YELLOW, mMUCard.getBorderColor(), 0);
        mMUCard.setStyleResId(18);
        assertEquals(-1, mMUCard.getStyleResId(), 0);
        mMUCard.setCornerRadius(27);
        assertEquals(27, mMUCard.getCornerRadius(), 0);
        mMUCard.setHeaderHorizontalPadding(19);
        assertEquals(19, mMUCard.getHeaderHorizontalPadding(), 0);
    }

    private float convertDPToPixel(float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                mContext.getResources().getDisplayMetrics());
    }
}
