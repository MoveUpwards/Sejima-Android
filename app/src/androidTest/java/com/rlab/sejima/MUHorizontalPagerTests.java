package com.rlab.sejima;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.rlab.sejima.features.MUHorizontalPager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class MUHorizontalPagerTests {

    private MUHorizontalPager mMUHorizontalPager;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        mMUHorizontalPager = new MUHorizontalPager(context);
        assertNotNull(mMUHorizontalPager);
    }

    @Test
    public void defaultValues() {
        assertEquals(mMUHorizontalPager.getHorizontalMargins(), 0, 0);
        assertEquals(mMUHorizontalPager.getCurrentIndex(), 0, 0);
        assertNull(mMUHorizontalPager.getMUHorizontalPagerListener());
        assertNotNull(mMUHorizontalPager.getAdapter());
        assertNull(mMUHorizontalPager.getMUPageControl());
    }

    @Test
    public void customValues() {
        mMUHorizontalPager.setHorizontalMargins(12f);
        assertEquals(mMUHorizontalPager.getHorizontalMargins(), 12, 0);

        mMUHorizontalPager.setCurrentIndex(5);
        assertEquals(mMUHorizontalPager.getCurrentIndex(), 0, 0);

        mMUHorizontalPager.setMUHorizontalPagerListener((horizontalPager, toIndex) -> {
        });
        assertNotNull(mMUHorizontalPager.getMUHorizontalPagerListener());

        mMUHorizontalPager.setAdapter(null);
        assertNull(mMUHorizontalPager.getAdapter());
    }

    @Test
    public void navigate() {
        Context c = ApplicationProvider.getApplicationContext();
        mMUHorizontalPager.addViews(new View[]{ new TextView(c), new TextView(c) }, 14);
        mMUHorizontalPager.setCurrentIndex(1);
        assertEquals(mMUHorizontalPager.getCurrentIndex(), 1, 0);
    }
}
