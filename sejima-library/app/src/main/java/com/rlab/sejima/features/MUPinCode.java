package com.rlab.sejima.features;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.rlab.sejima.R;

import androidx.annotation.Nullable;

public class MUPinCode extends LinearLayout implements MUViewHelper {

    private static final int MAX_LENGTH = 1;
    private int mCount = 4;
    private EditText[] mEditTexts;
    private String mDefaultChar = ".";

    public MUPinCode(Context context) {
        super(context);
        init(context);
    }

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
            et = new EditText(context);
            et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_LENGTH)});
            et.setHint(mDefaultChar);
            et.setTextAlignment(TEXT_ALIGNMENT_CENTER);
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(5,0,5,0);
            et.setLayoutParams(lp);
            addView(et, i);
            mEditTexts[i] = et;
        }
    }
}
