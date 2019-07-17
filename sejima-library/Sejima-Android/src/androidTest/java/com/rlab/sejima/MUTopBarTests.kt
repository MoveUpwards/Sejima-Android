package com.rlab.sejima

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.widget.RelativeLayout

import com.rlab.sejima.features.MUTopBar

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull

@RunWith(AndroidJUnit4::class)
class MUTopBarTests {

    private var mMUTopBar: MUTopBar? = null

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        mMUTopBar = MUTopBar(context)
        assertNotNull(mMUTopBar)
    }

    @Test
    fun defaultValues() {
        // Title
        assertEquals(mMUTopBar?.title, "")
        // Title's size
        assertEquals(mMUTopBar?.titleFontSize, 24f)
        // Title's font weight
        assertEquals(mMUTopBar?.titleFontWeight, Typeface.NORMAL)
        // Title's color
        assertEquals(mMUTopBar?.titleColor, Color.WHITE)
        // Title's alignment
        assertEquals(mMUTopBar?.titleAlignment, RelativeLayout.ALIGN_PARENT_START)
        // Button img
        assertEquals(mMUTopBar?.buttonImage, R.drawable.ic_launcher_background)
        // Button's left leading
        assertEquals(mMUTopBar?.leftButtonLeading, 0f)
        // Button's width
        assertEquals(mMUTopBar?.leftButtonWidth, 40f)
        // Button's visibility
        assertEquals(mMUTopBar?.isButtonHidden, false)
        // Default interface null
        assertNull(mMUTopBar?.muTopBarClickListener)
        // font style
        assertEquals(mMUTopBar?.fontStyle, -1)
    }

    @Test
    fun customValues() {
        // Title
        mMUTopBar?.title = "CUSTOM"
        assertEquals(mMUTopBar?.title, "CUSTOM")
        // Title's size
        mMUTopBar?.titleFontSize = 25f
        assertEquals(mMUTopBar?.titleFontSize, 25f)
        // Title's font weight
        mMUTopBar?.titleFontWeight = Typeface.BOLD
        assertEquals(mMUTopBar?.titleFontWeight, Typeface.BOLD)
        // Title's color
        mMUTopBar?.titleColor = Color.BLUE
        assertEquals(mMUTopBar?.titleColor, Color.BLUE)
        // Title's alignment
        mMUTopBar?.titleAlignment = RelativeLayout.ALIGN_PARENT_END
        assertEquals(mMUTopBar?.titleAlignment, RelativeLayout.ALIGN_PARENT_END)

        // Button img
        mMUTopBar?.buttonImage = R.mipmap.ic_launcher
        assertEquals(mMUTopBar?.buttonImage, R.mipmap.ic_launcher)
        // Button's left leading
        mMUTopBar?.leftButtonLeading = 25f
        assertEquals(mMUTopBar?.leftButtonLeading, 25f)
        // Button's width
        mMUTopBar?.leftButtonWidth = 25f
        assertEquals(mMUTopBar?.leftButtonWidth, 25f)
        // Button's visibility
        mMUTopBar?.isButtonHidden = true
        assertEquals(mMUTopBar?.isButtonHidden, true)

        // Interface
        //val listener = { }
        //mMUTopBar?.muTopBarClickListener = listener
        //assertNotNull(mMUTopBar?.muTopBarClickListener)

        // font style
        mMUTopBar?.fontStyle = 1
        assertEquals(mMUTopBar?.fontStyle, -1)
    }

    @Test
    fun hideBtn() {

        assertEquals(mMUTopBar?.isButtonHidden, false)
        mMUTopBar?.buttonImage = -4
        assertEquals(mMUTopBar?.isButtonHidden, true)

        mMUTopBar?.buttonImage = R.mipmap.ic_launcher_round
        assertEquals(mMUTopBar?.isButtonHidden, false)
    }
}

