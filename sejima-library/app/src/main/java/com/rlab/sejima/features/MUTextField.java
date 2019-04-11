package com.rlab.sejima.features;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rlab.sejima.R;

import androidx.appcompat.widget.AppCompatEditText;

public class MUTextField extends RelativeLayout {

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
    private int mLabelFontWeight = Typeface.NORMAL;
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
    private int mFieldFontWeight = Typeface.NORMAL;
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
    private int mKeyboardType = InputType.TYPE_NULL;
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
     * The title's font style
     */
    private int mLabelFontStyle = -1;

    /**
     * The field's font style
     */
    private int mFieldFontStyle = -1;

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
        mLabelFontStyle = attributes.getResourceId(R.styleable.MUTextField_title_font_style, mLabelFontStyle);

        s = attributes.getString(R.styleable.MUTextField_field);
        mField = TextUtils.isEmpty(s) ? mField : s;
        mFieldColor = attributes.getColor(R.styleable.MUTextField_field_color, mFieldColor);
        mFieldFontSize = attributes.getDimensionPixelSize(R.styleable.MUTextField_field_size, 0);
        mFieldFontWeight = attributes.getInt(R.styleable.MUTextField_field_weight, mFieldFontWeight);
        mFieldFontStyle = attributes.getResourceId(R.styleable.MUTextField_field_font_style, mFieldFontStyle);

        mAlignment = attributes.getInt(R.styleable.MUTextField_alignment, mLabelFontStyle);
        mIsSecure   = attributes.getBoolean(R.styleable.MUTextField_is_secure, false);
        mIsEditable = attributes.getBoolean(R.styleable.MUTextField_android_editable, true);
        mAutoCorrection = attributes.getBoolean(R.styleable.MUTextField_auto_correct, true);
        mKeyboardType = attributes.getInt(R.styleable.MUTextField_android_inputType, mKeyboardType);

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
        mETInput = new AppCompatEditText(context){
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
        setUnderlineColor(mUnderlineColor); // Comment this line to pass tests

        // Field's comportment
        setSecure(mIsSecure);
        setEditable(mIsEditable);
        setAutoCorrection(mAutoCorrection);
        setKeyboardType(mKeyboardType);
        setReturnKeyAvailable(mIsReturnKeyAvailable);
        addView(mETInput);

        setAlignment(mAlignment);

        setLabelFontStyle(mLabelFontStyle);
        setFieldFontStyle(mFieldFontStyle);
    }

    /**
     * Get the label's text
     * @return the lqbel's text as String
     */
    public String getLabel() {
        return mLabel;
    }

    /**
     * Set the label's text
     * @param label the label's text as String
     */
    public void setLabel(String label) {
        mLabel = label;
        mTVLabel.setText(mLabel);
    }

    /**
     * Get the label font size
     * @return the label's font size as dp
     */
    public float getLabelFontSize() {
        return mLabelFontSize;
    }

    /**
     * Set the label's font size
     * @param labelFontSize the size in pixels
     */
    public void setLabelFontSize(float labelFontSize) {
        mLabelFontSize = labelFontSize * mScale;
        mTVLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLabelFontSize);
    }

    /**
     * Get the label's font weight
     * @return the label font weight
     */
    public int getLabelFontWeight() {
        return mLabelFontWeight;
    }

    /**
     * Set the label's font weight
     * @param labelFontWeight the label's font weight as Integer
     */
    public void setLabelFontWeight(int labelFontWeight) {
        mLabelFontWeight = labelFontWeight;
        mTVLabel.setTypeface(Typeface.create(Typeface.DEFAULT, mLabelFontWeight));
    }

    /**
     * Get the label's color
     * @return the label's color as ARGB int
     */
    public int getLabelColor() {
        return mLabelColor;
    }

    /**
     * Set the label's color
     * @param labelColor the label color as as ARGB int
     */
    public void setLabelColor(int labelColor) {
        mLabelColor = labelColor;
        mTVLabel.setTextColor(mLabelColor);
    }

    /**
     * Get the horizontal alignment of the label and the text for the input field
     * @return the horizontal alignment as integer
     */
    public int getAlignment() {
        return mAlignment;
    }

    /**
     * Seth the text horizontal alignment of the view
     * @param alignment the horizontal alignment for the label and the text field as integer.
     * Must be:
     * <ul>
     * <li>RelativeLayout.ALIGN_PARENT_START</li>
     * <li>RelativeLayout.ALIGN_PARENT_END</li>
     * <li>RelativeLayout.CENTER_HORIZONTAL</li>
     * </ul>
     */
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

    /**
     * Get the input field value
     * @return the input field value as String
     */
    public String getField() {
        return !TextUtils.isEmpty(mETInput.getText()) ?
                mETInput.getText().toString() : "";
    }

    /**
     * Set the input field value
     * @param field the input field value as String
     */
    public void setField(String field) {
        mField = field;
        mETInput.setText(mField);
    }

    /**
     * Get the input field's font size
     * @return the font size in dp
     */
    public float getFieldFontSize() {
        return mFieldFontSize;
    }

    /**
     * Set the input field's font size
     * @param fieldFontSize the input field's font size in pixels
     */
    public void setFieldFontSize(float fieldFontSize) {
        mFieldFontSize = fieldFontSize * mScale;
        mETInput.setTextSize(TypedValue.COMPLEX_UNIT_PX, mFieldFontSize);
    }

    /**
     * Get the input field's font weight
     * @return the font weight as integer
     */
    public int getFieldFontWeight() {
        return mFieldFontWeight;
    }

    /**
     * Set the input field's font weight
     * @param fieldFontWeight the font weight as integer
     */
    public void setFieldFontWeight(int fieldFontWeight) {
        mFieldFontWeight = fieldFontWeight;
        mETInput.setTypeface(Typeface.create(Typeface.DEFAULT, mFieldFontWeight));
    }

    /**
     * Get the input field's color
     * @return the input field's color as ARGB integer
     */
    public int getFieldColor() {
        return mFieldColor;
    }

    /**
     * Set the input field's color
     * @param fieldColor the color as ARGB integer
     */
    public void setFieldColor(int fieldColor) {
        mFieldColor = fieldColor;
        mETInput.setTextColor(mFieldColor);
    }

    /**
     * Get the boolean value indicating if the input text is hidden
     * @return the boolean value
     */
    public boolean isSecure() {
        return mIsSecure;
    }

    /**
     * Enable to switch between hidden and shown input text
     * @param secure the boolean value which show/hide the input field's text
     */
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

    /**
     * Get the editable value of the input field
     * @return true if the input field is editable, false otherwise
     */
    public boolean isEditable() {
        return mIsEditable;
    }

    /**
     * Enable or disable input field edition
     * @param editable the value of the edition-availability
     */
    public void setEditable(boolean editable) {
        mIsEditable = editable;
        mETInput.setEnabled(editable);
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
        mETInput.setInputType(mKeyboardType);
    }

    /**
     * Get the state of the auto-correction
     * @return true if the auto-correction is active, false otherwise
     */
    public boolean isAutoCorrection() {
        return mAutoCorrection;
    }

    /**
     * Enable / disable the auto-correction
     * @param autoCorrection the value of the auto-correction state
     */
    public void setAutoCorrection(boolean autoCorrection) {
        mAutoCorrection = autoCorrection;
        mETInput.setInputType(mAutoCorrection ?
                InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | mKeyboardType
                : InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | mKeyboardType);
    }

    /**
     * Know if the input field is focused or not
     * @return true if focus is on input field, false otherwise
     */
    public boolean isActive() {
        return mETInput.hasFocus();
    }

    /**
     * Request or clear focus from the input field
     * @param active true to set the focus on the input field, false to clear focus
     */
    public void setActive(boolean active) {
        if(active) {
            mETInput.requestFocusFromTouch();
        } else {
            mETInput.clearFocus();
        }
    }

    /**
     * Know if the return key is available on the soft keyboard
     * @return true if it is, false otherwise
     */
    public boolean isReturnKeyAvailable() {
        return mIsReturnKeyAvailable;
    }

    /**
     * Enable / disable the return key on soft keyboard
     * @param returnKeyAvailable true if the input allows multi line, false otherwise
     */
    public void setReturnKeyAvailable(boolean returnKeyAvailable) {
        mIsReturnKeyAvailable = returnKeyAvailable;
        mETInput.setSingleLine(!mIsReturnKeyAvailable);
    }

    /**
     * Get the placeholder's text
     * @return the placeholder's text as String
     */
    public String getPlaceHolderText() {
        return mPlaceHolderText;
    }

    /**
     * Set the placeholder's text
     * @param placeHolderText the placeholder's text as String
     */
    public void setPlaceHolderText(String placeHolderText) {
        mPlaceHolderText = placeHolderText;
        mETInput.setHint(mPlaceHolderText);
    }

    /**
     * Get the color of the placeholder's text
     * @return the color as ARGB integer
     */
    public int getPlaceHolderFontColor() {
        return mPlaceHolderFontColor;
    }

    /**
     * Set the placeholder's text color
     * @param placeHolderFontColor the color as ARGB integer
     */
    public void setPlaceHolderFontColor(int placeHolderFontColor) {
        mPlaceHolderFontColor = placeHolderFontColor;
        mETInput.setHintTextColor(mPlaceHolderFontColor);
    }

    /**
     * Get the interface attached to the view
     * @return the MUTextField interface attached to the view, null if there is not.
     */
    public MUTextFieldListener getTFListener() {
        return mTFListener;
    }

    /**
     * Attach a listener to the view
     * @param tFListener the listener to attach
     */
    public void setTFListener(MUTextFieldListener tFListener) {
        mTFListener = tFListener;
    }

    /**
     * Get the input field's underline color
     * @return the color as ARGB integer
     */
    public int getUnderlineColor() {
        return mUnderlineColor;
    }

    /**
     * Set the input field's underline color
     * @param underlineColor the color as ARGB integer
     */
    public void setUnderlineColor(int underlineColor) {
        mUnderlineColor = underlineColor;
        mETInput.getBackground().setColorFilter(mUnderlineColor, PorterDuff.Mode.SRC_IN);
    }

    /**
     * Get the current font style for the label
     * @return the resource id of the font style
     */
    public int getLabelFontStyle() {
        return mLabelFontStyle;
    }

    /**
     * Set the font style for the label
     * @param fontStyle the resource id of the font style
     */
    public void setLabelFontStyle(int fontStyle) {
        //TODO check the style existence
        mLabelFontStyle = fontStyle;
        mTVLabel.setTextAppearance(getContext(), mLabelFontStyle);
    }

    /**
     * Get the current font style for the field
     * @return the resource id of the font style
     */
    public int getFieldFontStyle() {
        return mFieldFontStyle;
    }

    /**
     * Set the font style for the field
     * @param fontStyle the resource id of the font style
     */
    public void setFieldFontStyle(int fontStyle) {
        //TODO check the style existence
        mFieldFontStyle = fontStyle;
        mETInput.setTextAppearance(getContext(), mFieldFontStyle);
    }

    /**
     * The interface of the view to listen for events.
     */
    public interface MUTextFieldListener {
        void focusLost(AppCompatEditText textField);
        void isSelecting(AppCompatEditText textField);
        void textUpdated(AppCompatEditText textField);
    }
}
