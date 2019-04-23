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
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;

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
    private MUAppCompatEditText[] mEditTexts;
    /**
     * The default character
     */
    private String mDefaultChar = ".";
    /**
     * The font style for EditTexts
     */
    private int mFontStyle = -1;
    /**
     * Space between the pincode cells
     */
    private float mCellSpacing = (int) pixelsToDensity(getResources().getDisplayMetrics(), 8);
    /**
     * Background color for pincode cells
     */
    private int mCellColor = Color.WHITE;
    /**
     * Corner radius for pincode cells
     */
    private float mCellCornerRadius;
    /**
     * Keyboard type
     */
    private int mKeyboardType = InputType.TYPE_CLASS_TEXT;
    /**
     * The listener for code updates
     */
    private MUPinCodeListener mPinCodeListener;

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
    public MUPinCode(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MUPinCode);
        mCount = attributes.getInteger(R.styleable.MUPinCode_count, mCount);
        mCellCornerRadius = attributes.getDimensionPixelSize(R.styleable.MUPinCode_corner_radius, (int) mCellCornerRadius);
        mFontStyle = attributes.getResourceId(R.styleable.MUPinCode_font_style, mFontStyle);
        mDefaultChar = attributes.getString(R.styleable.MUPinCode_default_char);
        mDefaultChar = !TextUtils.isEmpty(mDefaultChar) ? mDefaultChar : ".";
        mCellSpacing = attributes.getDimensionPixelSize(R.styleable.MUPinCode_cell_spacing, (int) mCellSpacing);
        mCellColor = attributes.getColor(R.styleable.MUPinCode_cell_color, mCellColor);
        mKeyboardType = attributes.getInt(R.styleable.MUPinCode_android_inputType, mKeyboardType);

        init(context);
        attributes.recycle();
    }

    private void init(Context context){
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);

        setDefaultChar(mDefaultChar);
        setCount(mCount);

        setFontStyle(mFontStyle);
        setFontColor(Color.BLACK);
        setKeyboardType(mKeyboardType);
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(EditText editText : mEditTexts){
            applyRoundCornerToView(mCellCornerRadius, mCellColor, editText);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int freeWidthSpace = getMeasuredWidth() - (int) (getCount() * mCellSpacing);
        int maxWidth = freeWidthSpace / getCount();
        int cellDim = Math.min(getMeasuredHeight(), maxWidth);

        //TODO: Compute max size

        for(EditText editText : mEditTexts){
            editText.setWidth(cellDim);
            editText.setHeight(cellDim);
            LayoutParams lp = (LayoutParams) editText.getLayoutParams();
            lp.setMarginEnd((int) (mCellSpacing / 2));
            lp.setMarginStart((int) (mCellSpacing / 2));
            editText.setLayoutParams(lp);
        }

        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());

    }

    public void setFontSize(float size) {
        for (EditText mEditText : mEditTexts) {
            mEditText.setTextSize(size);
        }
    }

    public void setFontColor(int color) {
        for (EditText mEditText : mEditTexts) {
            mEditText.setTextColor(color);
        }
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
        count = normalizeIntValue(count, 0, count);
        MUAppCompatEditText[] ets = new MUAppCompatEditText[count];

        // If current array exists, keep old EditTexts
        if(null != mEditTexts){                                                                     // if current array exists
            for (int i = 0 ; i < count ; i++){
                if(i < mEditTexts.length){
                    ets[i] = mEditTexts[i];                                                         // get old EditTexts
                } else {
                    ets[i] = setUpEditText(new MUAppCompatEditText(getContext()));                             // add new ones
                    addView(ets[i], i);
                }
            }

            for(int i = count ; i < mEditTexts.length ; i++){
                removeViewAt(i);
            }
            mEditTexts = ets;
        } else {                                                                                    // else, create a new EditText array
            for (int i = 0 ; i < count ; i++){
                ets[i] = setUpEditText(new MUAppCompatEditText(getContext()));
                addView(ets[i], i);
            }
        }

        mEditTexts = ets;
        requestLayout();
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
        requestLayout();
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
        invalidate();
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
        invalidate();
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
        StringBuilder strB = new StringBuilder();
        for (EditText editText : mEditTexts) {
            strB.append(
                    !TextUtils.isEmpty(editText.getText().toString()) ?
                            editText.getText().toString() : ""
            );
        }

        return strB.toString();
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
    }

    private MUPinCode getMUPinCode(){
        return this;
    }

    private MUAppCompatEditText setUpEditText(MUAppCompatEditText editText) {
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_LENGTH)});
        editText.setHint(mDefaultChar);
        editText.setHintTextColor(Color.BLACK);
        editText.setTextColor(Color.BLACK);
        editText.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        editText.setGravity(Gravity.CENTER);
        return editText;
    }

    private class MUAppCompatEditText extends androidx.appcompat.widget.AppCompatEditText {

        public MUAppCompatEditText(Context context) {
            super(context);
        }

        @Override
        protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
            super.onTextChanged(text, start, lengthBefore, lengthAfter);
            Log.e(getClass().getCanonicalName(), "onTextUpdated : " + text);
            Log.e(getClass().getCanonicalName(), "mPinCodeListener == null? " + (mPinCodeListener == null));
            if(null != mPinCodeListener){
                mPinCodeListener.didUpdate(getMUPinCode(), getCode());
            }
        }
    }

    /**
     * Get the listener for text updates
     * @return the listener attached to the pin code, null if there is not
     */
    public MUPinCodeListener getMUPinCodeListener() {
        return mPinCodeListener;
    }

    /**
     * Attach a listener to text updates
     * @param pinCodeListener the pincode listener
     */
    public void setPinCodeListener(MUPinCodeListener pinCodeListener) {
        mPinCodeListener = pinCodeListener;
    }

    public interface MUPinCodeListener {
        void didUpdate(MUPinCode muPinCode, String code);
    }
}
