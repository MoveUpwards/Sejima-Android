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

    /**
     * The default size of label
     */
    public final static int DEFAULT_LABEL_SIZE_IN_SP = 14;

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
        mLabelFontSize = mLabelFontSize != 0 ? mLabelFontSize : DEFAULT_LABEL_SIZE_IN_SP * mScale;
        mTVLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLabelFontSize);
        addView(mTVLabel);

        LayoutParams lpEVInput = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpEVInput.addRule(mAlignment, RelativeLayout.TRUE);
        lpEVInput.addRule(RelativeLayout.BELOW, mTVLabel.getId());
        mETInput = new EditText(context);
        mETInput.setLayoutParams(lpEVInput);
        mETInput.setText("TEST");
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
}
