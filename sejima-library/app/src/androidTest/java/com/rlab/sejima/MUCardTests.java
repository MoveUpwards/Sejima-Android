package com.rlab.sejima;

import android.content.Context;
import android.graphics.Color;

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

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        context.setTheme(R.style.AppTheme);
        mMUCard = new MUCard(context);
        assertNotNull(mMUCard);
    }

    @Test
    public void defaultValues() {

        assertEquals(Color.GRAY, mMUCard.getBkgColor(), 0);
    }
}
