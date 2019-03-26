package com.rlab.sejima;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.widget.EditText;
import android.widget.TextView;

import com.rlab.sejima.features.MUTextField;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MUTextFieldTests {

    private MUTextField mMUTextField;
    private float mScale;
    private float defaultTextViewSize, defaultEditTextSize;
    private int defaultEditTextFontColor, defaultEditTextHintColor;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getTargetContext();
        mMUTextField = new MUTextField(context);
        assertNotNull(mMUTextField);
        mScale = ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);

        defaultTextViewSize = new TextView(context).getTextSize();
        EditText et = new EditText(context);
        defaultEditTextSize = et.getTextSize();
        defaultEditTextFontColor = et.getCurrentTextColor();
        defaultEditTextHintColor = et.getCurrentHintTextColor();
    }

    @Test
    public void defaultValues() {

        // Label
        assertEquals(mMUTextField.getLabel(), "");
        // Label's size
        assertEquals(mMUTextField.getLabelFontSize(), defaultTextViewSize, 0);
        // Label's font weight
        assertEquals(mMUTextField.getLabelFontWeight(), Typeface.NORMAL);
        // Label's color
        assertEquals(mMUTextField.getLabelColor(), Color.WHITE);

        // Field
        assertEquals(mMUTextField.getField(), "");
        // Field's size
        assertEquals(mMUTextField.getFieldFontSize(), defaultEditTextSize, 0);
        // Field's font weight
        assertEquals(mMUTextField.getFieldFontWeight(), Typeface.NORMAL);
        // Field's color
        assertEquals(mMUTextField.getFieldColor(), defaultEditTextFontColor);

        // Placeholder
        assertEquals(mMUTextField.getPlaceHolderText(), "");
        // Placeholder color
        assertEquals(mMUTextField.getPlaceHolderFontColor(), defaultEditTextHintColor);
        // Underline
//        assertEquals(mMUTextField.getUnderlineColor(), Color.TRANSPARENT);

        // Field's comportment
        assertFalse(mMUTextField.isSecure());
        assertTrue(mMUTextField.isEditable());
        assertTrue(mMUTextField.isAutoCorrection());
        assertTrue(mMUTextField.isReturnKeyAvailable());

        assertEquals(mMUTextField.getKeyboardType(),  InputType.TYPE_NULL);
        assertNull(mMUTextField.getTFListener());
    }

    @Test
    public void customValues() {

        // Label
        mMUTextField.setLabel("Custom");
        assertEquals(mMUTextField.getLabel(), "Custom");
        // Label's size
        mMUTextField.setLabelFontSize(124);
        assertEquals(mMUTextField.getLabelFontSize(), 124 * mScale, 0);
        // Label's font weight
        mMUTextField.setLabelFontWeight(Typeface.BOLD);
        assertEquals(mMUTextField.getLabelFontWeight(), Typeface.BOLD);
        // Label's color
        mMUTextField.setLabelColor(Color.RED);
        assertEquals(mMUTextField.getLabelColor(), Color.RED);

        // Field
        mMUTextField.setField("CUSTOM FIELD");
        assertEquals(mMUTextField.getField(), "CUSTOM FIELD");
        // Field's size
        mMUTextField.setFieldFontSize(35);
        assertEquals(mMUTextField.getFieldFontSize(), 35 * mScale, 0);
        // Field's font weight
        mMUTextField.setFieldFontWeight(Typeface.BOLD_ITALIC);
        assertEquals(mMUTextField.getFieldFontWeight(), Typeface.BOLD_ITALIC);
        // Field's color
        mMUTextField.setFieldColor(Color.BLUE);
        assertEquals(mMUTextField.getFieldColor(), Color.BLUE);

        // Placeholder
        mMUTextField.setPlaceHolderText("Custom placeholder");
        assertEquals(mMUTextField.getPlaceHolderText(), "Custom placeholder");
        // Placeholder color
        mMUTextField.setPlaceHolderFontColor(Color.TRANSPARENT);
        assertEquals(mMUTextField.getPlaceHolderFontColor(), Color.TRANSPARENT);
        // Underline
        mMUTextField.setUnderlineColor(Color.RED);
        assertEquals(mMUTextField.getUnderlineColor(), Color.RED);

        // Field's comportment
        mMUTextField.setSecure(true);
        assertTrue(mMUTextField.isSecure());
        mMUTextField.setEditable(false);
        assertFalse(mMUTextField.isEditable());
        mMUTextField.setAutoCorrection(false);
        assertFalse(mMUTextField.isAutoCorrection());
        mMUTextField.setReturnKeyAvailable(false);
        assertFalse(mMUTextField.isReturnKeyAvailable());

        mMUTextField.setKeyboardType(InputType.TYPE_CLASS_NUMBER);
        assertEquals(mMUTextField.getKeyboardType(),  InputType.TYPE_CLASS_NUMBER);
        mMUTextField.setTFListener(new MUTextField.MUTextFieldListener() {
            @Override
            public void focusLost(AppCompatEditText textField) {

            }

            @Override
            public void isSelecting(AppCompatEditText textField) {

            }

            @Override
            public void textUpdated(AppCompatEditText textField) {

            }
        });
        assertNotNull(mMUTextField.getTFListener());
    }
}

