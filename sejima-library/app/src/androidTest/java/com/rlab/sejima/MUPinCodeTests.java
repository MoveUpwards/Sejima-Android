package com.rlab.sejima;

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

    @Before
    public void setUp() {
        mMUPinCode = new MUPinCode(ApplicationProvider.getApplicationContext());
        assertNotNull(mMUPinCode);
    }

    @Test
    public void defaultValues() {

        // Alpha
        assertEquals(4, mMUPinCode.getCount(), 0);
    }
}
