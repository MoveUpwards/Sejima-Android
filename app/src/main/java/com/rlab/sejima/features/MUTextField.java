package com.rlab.sejima.features;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rlab.sejima.R;

public class MUTextField extends RelativeLayout {

    /*
    DONE
    title
    title font
    title size
    title color
    alignment
    field
    field font
    field size
    TODO
    keyboard type
    keyboard appearance
    keyboard return key type
    title of the return key (envoyer)
    is the return key available ?
    auto-correction type
    placeholder
    placeholder color
    field color
    isCopyable ?
    isSecure ?
    underline color
    focus/unfocus textfield (setActive?)
    selectingListener
    returnListener
    editingListener
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
    private EditText mETInput;
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

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MUTextField);

        String s = a.getString(R.styleable.MUTextField_title);
        mLabel = TextUtils.isEmpty(s) ? mLabel : s;
        mLabelColor = a.getColor(R.styleable.MUTextField_title_color, mLabelColor);
        mLabelFontSize = a.getDimensionPixelSize(R.styleable.MUTextField_title_size, 0);
        mLabelFontWeight = a.getInt(R.styleable.MUTextField_title_weight, mLabelFontWeight);
        mAlignment = a.getInt(R.styleable.MUHeader_alignment, mAlignment);

        s = a.getString(R.styleable.MUTextField_field);
        mField = TextUtils.isEmpty(s) ? mField : s;
        mFieldColor = a.getColor(R.styleable.MUTextField_field_color, mFieldColor);
        mFieldFontSize = a.getDimensionPixelSize(R.styleable.MUTextField_field_size, 0);
        mFieldFontWeight = a.getInt(R.styleable.MUTextField_field_weight, mFieldFontWeight);
        init(context);
        a.recycle();
    }

    private void init(Context context){
        mScale = (float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT;

        LayoutParams lpRoot = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lpRoot);

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

        LayoutParams lpEVInput = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpEVInput.addRule(mAlignment, RelativeLayout.TRUE);
        lpEVInput.addRule(RelativeLayout.BELOW, mTVLabel.getId());
        mETInput = new EditText(context);
        mETInput.setLayoutParams(lpEVInput);
        setField(mField);
        mFieldColor = mFieldColor != 0 ? mFieldColor : mETInput.getCurrentTextColor();
        setFieldColor(mFieldColor);
        setFieldFontWeight(mFieldFontWeight);
        mFieldFontSize = mFieldFontSize != 0 ? mFieldFontSize : mETInput.getTextSize();
        mETInput.setTextSize(TypedValue.COMPLEX_UNIT_PX, mFieldFontSize);
        addView(mETInput);

        setAlignment(mAlignment);
    }

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
}
