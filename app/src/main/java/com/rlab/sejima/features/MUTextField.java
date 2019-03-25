package com.rlab.sejima.features;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rlab.sejima.R;

public class MUTextField extends RelativeLayout {

    /*
    DONE
    - title
    - title font
    - title size
    - title color
    - alignment
    - field
    - field font
    - field size
    - field color
    - isSecure ?
    - isEditable ?
    - isCopyable ?
    - keyboard type
    - keyboard appearance
    - auto-correction type
    - placeholder
    - placeholder color
    - focus/unfocus text field (setActive?)
    - focusListener
    - editingListener
    - selectingListener
    - is the return key available ?
    - underline color
    TODO
    - keyboard return key type
    - title of the return key (envoyer)
     */

    /**
     * The scale used to convert px in dp
     */
    private float mScale;
    /**
     * The TextView which displays the label of the field
     */
    private TextView mTVLabel;
    /**
     * The EditText for the input
     */
    private AppCompatEditText mETInput;
    /**
     * The label's text
     */
    private String mLabel = "";
    /**
     * The title's font size
     */
    private float mLabelFontSize;
    /**
     * The title's font weight
     */
    private int mLabelFontWeight;
    /**
     * The title's text color
     */
    private int mLabelColor = Color.WHITE;
    /**
     * The text's horizontal alignment
     */
    private int mAlignment = RelativeLayout.ALIGN_PARENT_START;
    /**
     * The field's text
     */
    private String mField = "";
    /**
     * The field's font size
     */
    private float mFieldFontSize;
    /**
     * The field's font weight
     */
    private int mFieldFontWeight;
    /**
     * The field's text color
     */
    private int mFieldColor;
    /**
     * Is text field secure
     */
    private boolean mIsSecure = false;
    /**
     * Is text field editable
     */
    private boolean mIsEditable = true;
    /**
     * Keyboard type
     */
    private int mKeyboardType;
    /**
     * Enable/disable auto-correction
     */
    private boolean mAutoCorrection = true;
    /**
     * Boolean to specify if return key is available
     */
    private boolean mIsReturnKeyAvailable = true;
    /**
     * Placeholder for text field
     */
    private String mPlaceHolderText = "";
    /**
     * Placeholder color for text field
     */
    private int mPlaceHolderFontColor;
    /**
     * The underline's field color
     */
    private int mUnderlineColor = Color.TRANSPARENT;
    /**
     * The interface listener for text field
     */
    private MUTextFieldListener mTFListener;

    /**
     * Default constructor
     * @param context the view context
     */
    public MUTextField(Context context) {
        super(context);
        init(context);
    }

    /**
     * Constructor with attributes
     * @param context the view context
     * @param attrs the XML attributes for the view
     */
    public MUTextField(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MUTextField);

        String s = attributes.getString(R.styleable.MUTextField_title);
        mLabel = TextUtils.isEmpty(s) ? mLabel : s;
        mLabelColor = attributes.getColor(R.styleable.MUTextField_title_color, mLabelColor);
        mLabelFontSize = attributes.getDimensionPixelSize(R.styleable.MUTextField_title_size, 0);
        mLabelFontWeight = attributes.getInt(R.styleable.MUTextField_title_weight, mLabelFontWeight);
        mAlignment = attributes.getInt(R.styleable.MUTextField_alignment, mAlignment);

        s = attributes.getString(R.styleable.MUTextField_field);
        mField = TextUtils.isEmpty(s) ? mField : s;
        mFieldColor = attributes.getColor(R.styleable.MUTextField_field_color, mFieldColor);
        mFieldFontSize = attributes.getDimensionPixelSize(R.styleable.MUTextField_field_size, 0);
        mFieldFontWeight = attributes.getInt(R.styleable.MUTextField_field_weight, mFieldFontWeight);

        mIsSecure   = attributes.getBoolean(R.styleable.MUTextField_is_secure, false);
        mIsEditable = attributes.getBoolean(R.styleable.MUTextField_android_editable, true);
        mAutoCorrection = attributes.getBoolean(R.styleable.MUTextField_auto_correct, true);
        mKeyboardType = attributes.getInt(R.styleable.MUTextField_android_inputType, InputType.TYPE_NULL);

        s = attributes.getString(R.styleable.MUTextField_android_hint);
        mPlaceHolderText = TextUtils.isEmpty(s) ? mPlaceHolderText : s;
        mPlaceHolderFontColor = attributes.getColor(R.styleable.MUTextField_android_textColorHint, mPlaceHolderFontColor);
        init(context);
        attributes.recycle();
    }

    private void init(Context context){
        mScale = (float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT;

        LayoutParams lpRoot = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lpRoot);

        // Field's label
        LayoutParams lpTVLabel = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpTVLabel.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        lpTVLabel.addRule(mAlignment, RelativeLayout.TRUE);
        mTVLabel = new TextView(context);
        mTVLabel.setLayoutParams(lpTVLabel);
        mTVLabel.setId(View.generateViewId());
        setLabel(mLabel);
        setLabelColor(mLabelColor);
        setLabelFontWeight(mLabelFontWeight);
        mLabelFontSize = mLabelFontSize != 0 ? mLabelFontSize : mTVLabel.getTextSize();
        mTVLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLabelFontSize);
        addView(mTVLabel);

        // Input field
        LayoutParams lpEVInput = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpEVInput.addRule(mAlignment, RelativeLayout.TRUE);
        lpEVInput.addRule(RelativeLayout.BELOW, mTVLabel.getId());
        mETInput = new android.support.v7.widget.AppCompatEditText(context){
            @Override
            protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
                super.onFocusChanged(focused, direction, previouslyFocusedRect);
                if(null != mTFListener && !focused){
                    mTFListener.focusLost(this);
                }
            }

            @Override
            protected void onSelectionChanged(int selStart, int selEnd) {
                super.onSelectionChanged(selStart, selEnd);
                Log.e(getClass().getCanonicalName(), "Start: " + selStart + " x End: " + selEnd);
                if(null != mTFListener && (selEnd - selStart) > 0){
                    mTFListener.isSelecting(this);
                }
            }

            @Override
            protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
                super.onTextChanged(text, start, lengthBefore, lengthAfter);
                if(null != mTFListener){
                    mTFListener.textUpdated(this);
                }
            }
        };

        mETInput.setLayoutParams(lpEVInput);
        // Field's font
        setField(mField);
        mFieldColor = mFieldColor != 0 ? mFieldColor : mETInput.getCurrentTextColor();
        setFieldColor(mFieldColor);
        setFieldFontWeight(mFieldFontWeight);
        mFieldFontSize = mFieldFontSize != 0 ? mFieldFontSize : mETInput.getTextSize();
        mETInput.setTextSize(TypedValue.COMPLEX_UNIT_PX, mFieldFontSize);
        // Field's placeholder
        setPlaceHolderText(mPlaceHolderText);
        mPlaceHolderFontColor = mPlaceHolderFontColor != 0 ? mPlaceHolderFontColor : mETInput.getCurrentHintTextColor();
        setPlaceHolderFontColor(mPlaceHolderFontColor);
        setUnderlineColor(mUnderlineColor);

        // Field's comportment
        setSecure(mIsSecure);
        setEditable(mIsEditable);
        setAutoCorrection(mAutoCorrection);
        setKeyboardType(mKeyboardType);
        setReturnKeyAvailable(mIsReturnKeyAvailable);
        addView(mETInput);

        setAlignment(mAlignment);
    }

    /**
     * Get the label's text
     * @return the lqbel's text as String
     */
    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
        mTVLabel.setText(mLabel);
    }

    public float getLabelFontSize() {
        return mLabelFontSize;
    }

    public void setLabelFontSize(float labelFontSize) {
        mLabelFontSize = labelFontSize * mScale;
        mTVLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLabelFontSize);
    }

    public int getLabelFontWeight() {
        return mLabelFontWeight;
    }

    public void setLabelFontWeight(int labelFontWeight) {
        mLabelFontWeight = labelFontWeight;
        mTVLabel.setTypeface(Typeface.create(Typeface.DEFAULT, mLabelFontWeight));
    }

    public int getLabelColor() {
        return mLabelColor;
    }

    public void setLabelColor(int labelColor) {
        mLabelColor = labelColor;
        mTVLabel.setTextColor(mLabelColor);
    }

    public int getAlignment() {
        return mAlignment;
    }

    public void setAlignment(int alignment) {
        RelativeLayout.LayoutParams ll = (LayoutParams) mTVLabel.getLayoutParams();
        ll.removeRule(mAlignment);
        ll.addRule(alignment, RelativeLayout.TRUE);
        mTVLabel.setLayoutParams(ll);

        if(RelativeLayout.ALIGN_PARENT_END == alignment){
            mETInput.setGravity(Gravity.END);
        } else if(RelativeLayout.CENTER_HORIZONTAL == alignment){
            mETInput.setGravity(Gravity.CENTER_HORIZONTAL);
        } else {
            mETInput.setGravity(Gravity.START);
        }
        mAlignment = alignment;
    }

    public String getField() {
        String s = mETInput.getText().toString();
        mField = !TextUtils.isEmpty(s) ? s : "";
        return mField;
    }

    public void setField(String field) {
        mField = field;
        mETInput.setText(mField);
    }

    public float getFieldFontSize() {
        return mFieldFontSize;
    }

    public void setFieldFontSize(float fieldFontSize) {
        mFieldFontSize = fieldFontSize * mScale;
        mETInput.setTextSize(TypedValue.COMPLEX_UNIT_PX, mFieldFontSize);
    }

    public int getFieldFontWeight() {
        return mFieldFontWeight;
    }

    public void setFieldFontWeight(int fieldFontWeight) {
        mFieldFontWeight = fieldFontWeight;
        mETInput.setTypeface(Typeface.create(Typeface.DEFAULT, mFieldFontWeight));
    }

    public int getFieldColor() {
        return mFieldColor;
    }

    public void setFieldColor(int fieldColor) {
        mFieldColor = fieldColor;
        mETInput.setTextColor(mFieldColor);
    }

    public boolean isSecure() {
        return mIsSecure;
    }

    public void setSecure(boolean secure) {
        mIsSecure = secure;

        if (mKeyboardType == InputType.TYPE_CLASS_NUMBER) {
            mETInput.setInputType(mIsSecure ?
                    InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD
                    : mKeyboardType);
        } else {
            mETInput.setInputType(mIsSecure ?
                    InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                    : mKeyboardType);
        }

        mETInput.setLongClickable(!secure); // Disable contextual action like copying/selection
    }

    public boolean isEditable() {
        return mIsEditable;
    }

    public void setEditable(boolean editable) {
        mIsEditable = editable;
        mETInput.setEnabled(editable);
    }

    public int getKeyboardType() {
        return mKeyboardType;
    }

    public void setKeyboardType(int keyboardType) {
        mKeyboardType = keyboardType;
        mETInput.setInputType(mKeyboardType);
    }

    public boolean isAutoCorrection() {
        return mAutoCorrection;
    }

    public void setAutoCorrection(boolean autoCorrection) {
        mAutoCorrection = autoCorrection;
        mETInput.setInputType(mAutoCorrection ?
                InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | mKeyboardType
                : InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | mKeyboardType);
    }

    public boolean isActive() {
        return mETInput.hasFocus();
    }

    public void setActive(boolean active) {
        if(active) {
            mETInput.requestFocusFromTouch();
        } else {
            mETInput.clearFocus();
        }
    }

    public boolean isReturnKeyAvailable() {
        return mIsReturnKeyAvailable;
    }

    public void setReturnKeyAvailable(boolean returnKeyAvailable) {
        mIsReturnKeyAvailable = returnKeyAvailable;
        mETInput.setSingleLine(!mIsReturnKeyAvailable);
    }

    public String getPlaceHolderText() {
        return mPlaceHolderText;
    }

    public void setPlaceHolderText(String placeHolderText) {
        mPlaceHolderText = placeHolderText;
        mETInput.setHint(mPlaceHolderText);
    }

    public int getPlaceHolderFontColor() {
        return mPlaceHolderFontColor;
    }

    public void setPlaceHolderFontColor(int placeHolderFontColor) {
        mPlaceHolderFontColor = placeHolderFontColor;
        mETInput.setHintTextColor(mPlaceHolderFontColor);
    }

    public MUTextFieldListener getTFListener() {
        return mTFListener;
    }

    public void setTFListener(MUTextFieldListener tFListener) {
        mTFListener = tFListener;
    }

    public int getUnderlineColor() {
        return mUnderlineColor;
    }

    public void setUnderlineColor(int underlineColor) {
        mUnderlineColor = underlineColor;
        mETInput.getBackground().setColorFilter(mUnderlineColor, PorterDuff.Mode.SRC_IN);
    }

    public interface MUTextFieldListener {
        void focusLost(AppCompatEditText textField);
        void isSelecting(AppCompatEditText textField);
        void textUpdated(AppCompatEditText textField);
    }
}
