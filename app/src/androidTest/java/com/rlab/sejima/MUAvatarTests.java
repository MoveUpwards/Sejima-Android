package com.rlab.sejima;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

import com.rlab.sejima.features.MUAvatar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import androidx.test.core.app.ApplicationProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class MUAvatarTests {


    private MUAvatar mMUAvatar;
    private float mScale;
    private Context mContext;

    @Before
    public void setUp() {

        mContext = ApplicationProvider.getApplicationContext();
        mMUAvatar = new MUAvatar(mContext);
        assertNotNull(mMUAvatar);
        mScale = ((float) mContext.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    @Test
    public void defaultValues() {

        // Bkg color
        assertEquals(mMUAvatar.getBkgColor(), Color.TRANSPARENT);
        // Border color
        assertEquals(mMUAvatar.getBorderColor(), Color.BLACK);
        // Border width
        assertEquals(mMUAvatar.getBorderWidth(), MUAvatar.DEFAULT_BORDER_WIDTH_IN_DP  * mScale, 0);
        // Border type
        assertEquals(mMUAvatar.getBorderType(), MUAvatar.SQUARE_BORDER, 0);
        // Corner radius
        assertEquals(mMUAvatar.getBorderType(), 0, 0);
        // Image
        assertNull(mMUAvatar.getDrawable());
        // Placeholder image
        assertNull(mMUAvatar.getPlaceholderImage());
        // Listener
        assertNull(mMUAvatar.getClickListener());
    }

    @Test
    public void customValues() {
        Drawable drawable;
        // Bkg color
        mMUAvatar.setBkgColor(Color.BLACK);
        assertEquals(mMUAvatar.getBkgColor(), Color.BLACK);
        // Border color
        mMUAvatar.setBorderColor(Color.RED);
        assertEquals(mMUAvatar.getBorderColor(), Color.RED);
        // Border width
        mMUAvatar.setBorderWidth(12);
        assertEquals(mMUAvatar.getBorderWidth(), 12  * mScale, 0);
        // Corner radius
        mMUAvatar.setCornerRadius(15);
        assertEquals(mMUAvatar.getCornerRadius(), 15, 0);
        // Border type
        mMUAvatar.setBorderType(MUAvatar.ROUND_BORDER);
        assertEquals(mMUAvatar.getBorderType(), MUAvatar.ROUND_BORDER, 0);
        // Image
        drawable = mContext.getResources().getDrawable(R.drawable.avatar);
        mMUAvatar.setImage(drawable);
        assertEquals(mMUAvatar.getImage(), drawable);
        // Placeholder image
        drawable = mContext.getResources().getDrawable(R.mipmap.ic_launcher);
        mMUAvatar.setPlaceholderImage(drawable);
        assertEquals(mMUAvatar.getPlaceholderImage(), drawable);
        // Listener
        mMUAvatar.setClickListener(imageView -> {
        });
        assertNotNull(mMUAvatar.getClickListener());
    }

    @Test
    public void nullImageWillBeReplacedByPlaceholder() {
        Drawable placeholder = mContext.getResources().getDrawable(R.mipmap.ic_launcher);
        mMUAvatar.setPlaceholderImage(placeholder);
        mMUAvatar.setImage(null);
        assertEquals(mMUAvatar.getImage(), placeholder);
    }
}
