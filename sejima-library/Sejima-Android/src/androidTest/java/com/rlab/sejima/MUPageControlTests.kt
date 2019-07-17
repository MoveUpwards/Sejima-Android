package com.rlab.sejima

import android.content.Context
import android.graphics.Color
import android.util.TypedValue

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4

import com.rlab.sejima.features.MUPageControl

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

@RunWith(AndroidJUnit4::class)
class MUPageControlTests {

    private var mMUPageControl: MUPageControl? = null
    private var mContext: Context? = null
    @Before
    fun setUp() {
        mContext = ApplicationProvider.getApplicationContext()
        mContext?.setTheme(R.style.AppTheme)
        mMUPageControl = MUPageControl(mContext!!)
        assertNotNull(mMUPageControl)
    }

    @Test
    fun defaultValues() {

        assertEquals(0, mMUPageControl?.numberPages)
        assertEquals(null, mMUPageControl?.pageControlListener)
        assertEquals(convertDPToPixel(10f).toInt(), mMUPageControl?.elementWidth)
        assertEquals(Color.LTGRAY, mMUPageControl?.elementColor)
        assertEquals(convertDPToPixel(10f).toInt(), mMUPageControl?.activeElementWidth)
        assertEquals(Color.BLACK, mMUPageControl?.activeElementColor)
        assertEquals(-1, mMUPageControl?.currentPosition)
        assertEquals(0, mMUPageControl?.activeElementRadius)
        assertEquals(Color.TRANSPARENT, mMUPageControl?.borderColor)
        assertEquals(0, mMUPageControl?.borderWidth)
        assertEquals(6, mMUPageControl?.elementPadding)
        assertEquals(false, mMUPageControl?.isHideForSingleElementValue)
    }

    @Test
    fun customValues() {
/*
        // Alpha
        mMUPageControl?.disabledAlpha = -4f
        assertEquals(0f, mMUPageControl?.disabledAlpha)
        // Label
        mMUPageControl?.label = "c"
        assertEquals("c", mMUPageControl?.label)
        mMUPageControl?.labelFontSize = 12f
        assertEquals(12f, mMUPageControl?.labelFontSize)
        mMUPageControl?.labelFontWeight = Typeface.BOLD
        assertEquals(Typeface.BOLD, mMUPageControl?.labelFontWeight)
        mMUPageControl?.labelColor = Color.BLUE
        assertEquals(Color.BLUE, mMUPageControl?.labelColor)
        mMUPageControl?.labelAlignment = Gravity.START
        assertEquals(mMUPageControl?.labelAlignment, (Gravity.START or Gravity.CENTER_VERTICAL))
        mMUPageControl?.labelHighLightedColor = Color.RED
        assertEquals(Color.RED, mMUPageControl?.labelHighLightedColor)
        mMUPageControl?.labelProgressingColor = Color.BLACK
        assertEquals(Color.BLACK, mMUPageControl?.labelProgressingColor)
        // Is loading
        mMUPageControl?.isLoading = true
        assertEquals(true, mMUPageControl?.isLoading)
        // Bkg color
        mMUPageControl?.bkgColor = Color.CYAN
        assertEquals(Color.CYAN, mMUPageControl?.bkgColor)
        // Border color
        mMUPageControl?.borderColor = Color.RED
        assertEquals(Color.RED, mMUPageControl?.borderColor)
        mMUPageControl?.borderWidth = 15f
        assertEquals(15f, mMUPageControl?.borderWidth)
        mMUPageControl?.cornerRadius = 17
        assertEquals(17, mMUPageControl?.cornerRadius)
        // Padding
        mMUPageControl?.horizontalPadding = 17f
        assertEquals(17f, mMUPageControl?.horizontalPadding)
        mMUPageControl?.verticalPadding = 18f
        assertEquals(18f, mMUPageControl?.verticalPadding)
        // Separator
        mMUPageControl?.separatorColor = Color.YELLOW
        assertEquals(Color.YELLOW, mMUPageControl?.separatorColor)
        mMUPageControl?.separatorWidth = 25f
        assertEquals(25f, mMUPageControl?.separatorWidth)
        mMUPageControl?.separatorMultiplier = 3f
        assertEquals(1f, mMUPageControl?.separatorMultiplier)
        // Listener
        mMUPageControl?.setMUNavigationBarListener(object : MUNavigationBar.MUNavigationBarListener {
            override fun clickOnLeftButton(muNavigationBar: MUNavigationBar) {

            }

            override fun clickOnRightButton(muNavigationBar: MUNavigationBar) {

            }
        })
        assertNotNull(mMUPageControl?.listener)
        */
    }

    private fun convertDPToPixel(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                mContext!!.resources.displayMetrics)
    }

}

