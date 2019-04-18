package com.rlab.sejima.features;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.rlab.sejima.R;

public class MUPinCode extends LinearLayout implements MUViewHelper {

    /**
     * Number of character for an EditText
     */
    private static final int MAX_LENGTH = 1;

    /**
     * Number of characters
     */
    private int mCount = 4;
    /**
     * Contains the different EditText
     */
    private EditText[] mEditTexts;
    /**
     * The default character
     */
    private String mDefaultChar = ".";
    /**
     * Flag specifying if the EditText have to be resized
     */
    private boolean needResize = true;

    private int mFontStyle = -1;

    private float mCellSpacing = (int) pixelsToDensity(getResources().getDisplayMetrics(), 8);

    private int mCellColor = Color.WHITE;

    private float mCellCornerRadius;
    /**
     * Keyboard type
     */
    private int mKeyboardType = InputType.TYPE_NULL;

    private String mCode = "";

    /**
     * Default constructor
     * @param context the view context
     */
    public MUPinCode(Context context) {
        super(context);
        init(context);
    }

    /**
     * Constructor with attributes
     * @param context the view context
     * @param attrs the XML attributes for the view
     */
    public MUPinCode(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MUCard);

        init(context);
        attributes.recycle();
    }

    private void init(Context context){
        setOrientation(HORIZONTAL);
        mEditTexts = new EditText[mCount];

        EditText et;
        for (int i = 0 ; i < mEditTexts.length ; i++) {
            et = setUpEditText(new EditText(context));
            mEditTexts[i] = et;
            addView(et, i);
        }
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(needResize) {
//            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//            lp.setMargins(2,0,2,0);
            for (EditText editText : mEditTexts) {
                //editText.setLayoutParams(lp);
                int maxDim = Math.max(editText.getWidth(), editText.getHeight());
                editText.setWidth(maxDim);
                editText.setHeight(maxDim);
                applyRoundCornerToView(12, Color.BLUE, editText);
            }
            needResize = false;
        }
    }

    public void setFontSize(float size) {
        for (EditText mEditText : mEditTexts) {
            mEditText.setTextSize(size);
        }
        needResize = true;
    }

    public void setFontColor(int color) {
        for (EditText mEditText : mEditTexts) {
            mEditText.setTextColor(color);
        }
        needResize = true;
    }

    /**
     * Get the number of characters composing the code
     * @return the pin code count
     */
    public int getCount() {
        return mEditTexts.length;
    }

    /**
     * Set the number of characters needed for the code
     * @param count the code length
     */
    public void setCount(int count) {
        EditText[] ets = new EditText[count];

        EditText et;

        for (int i = 0 ; i < count ; i++){
            if(i < mCount) {
                ets[i] = mEditTexts[i];
            } else {
                et = setUpEditText(new EditText(getContext()));
                ets[i] = et;
                addView(et, i);
            }
        }

        if(count < mCount){
            for (int i = count ; i < mCount ; i++){
                removeView(mEditTexts[i]);
            }
        }

        mEditTexts = ets;
        mCount = count;
        needResize = true;
    }


    /**
     * Get the default character
     * @return the default character
     */
    public String getDefaultChar() {
        return mDefaultChar;
    }

    /**
     * Set the default character
     * @param defaultChar the default character
     */
    public void setDefaultChar(String defaultChar) {
        if(!TextUtils.isEmpty(defaultChar)){
            mDefaultChar = String.valueOf(defaultChar.charAt(0));
        }
    }

    /**+
     * Get the font style of pincode
     * @return the font style resource id
     */
    public int getFontStyle() {
        return mFontStyle;
    }

    /**
     * Set the font style
     * @param fontStyle the resource id of the font style
     */
    public void setFontStyle(int fontStyle) {
        if(checkResource(getResources(), fontStyle)){
            mFontStyle = fontStyle;
            for (EditText editText : mEditTexts){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    editText.setTextAppearance(fontStyle);
                } else {
                    editText.setTextAppearance(getContext(), fontStyle);
                }
            }
        }
        mFontStyle = fontStyle;
    }

    /**
     * Get the space between pincode cells
     * @return the space in dp
     */
    public float getCellSpacing() {
        return mCellSpacing;
    }

    /**
     * Set the space between pincode cells
     * @param cellSpacing the space to apply in dp
     */
    public void setCellSpacing(float cellSpacing) {
        mCellSpacing = cellSpacing;
    }

    /**
     * Get the background color of pincode cells
     * @return the color as ARGB integer
     */
    public int getCellColor() {
        return mCellColor;
    }

    /**
     * Set the background color of pincode cells
     * @param cellColor the background color of pincode cells as ARGB integer
     */
    public void setCellColor(int cellColor) {
        mCellColor = cellColor;
    }

    /**
     * Get the radius corner applied to pincode cells
     * @return the corner radius
     */
    public float getCellCornerRadius() {
        return mCellCornerRadius;
    }

    /**
     * Set the corner radius of pincode cells
     * @param cellCornerRadius the radius of pincode cells
     */
    public void setCellCornerRadius(float cellCornerRadius) {
        mCellCornerRadius = normalizeFloatValue(cellCornerRadius, 0, cellCornerRadius);
    }

    /**
     * Get the keyboard type (number, text, mail)
     * @return the type as integer
     */
    public int getKeyboardType() {
        return mKeyboardType;
    }

    /**
     * Switch between different mode of input
     * @param keyboardType the input mode (number, text, mail) as integer
     */
    public void setKeyboardType(int keyboardType) {
        mKeyboardType = keyboardType;
        for (EditText editText : mEditTexts){
            editText.setInputType(mKeyboardType);
        }
    }

    /**
     * Get the current code
     * @return the current code as string
     */
    public String getCode() {
        return mCode;
    }

    /**
     * Insert the given code in the pincode
     * @param code the code to set
     */
    public void setCode(String code) {
        if(code.length() > mCount){
            code = code.substring(0, mCount);
        }

        for(int i = 0 ; i < mCount ; i++){
            mEditTexts[i].setText(code.charAt(i));
        }
        mCode = code;
    }

    private EditText setUpEditText(EditText editText) {
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_LENGTH)});
        editText.setHint(mDefaultChar);
        editText.setTextAlignment(TEXT_ALIGNMENT_CENTER);
//        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        lp.setMargins(5,0,5,0);
//        editText.setLayoutParams(lp);
        return editText;
    }

    public interface MUPinCodeListener {
        void didUpdate(MUPinCode muPinCode, String code);
    }
}
