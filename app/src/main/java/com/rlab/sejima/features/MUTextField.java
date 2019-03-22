package com.rlab.sejima.features;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MUTextField extends RelativeLayout {

    /**
     * The TextView which displays the label of the field
     */
    private TextView mTVLabel;
    /**
     * The EditText for the input
     */
    private EditText mETInput;

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
        init(context);
    }

    private void init(Context context){
        LayoutParams lpRoot = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lpRoot);

        LayoutParams lpTVLabel = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpTVLabel.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        lpTVLabel.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
        mTVLabel = new TextView(context);
        mTVLabel.setLayoutParams(lpTVLabel);
        mTVLabel.setId(View.generateViewId());
        mTVLabel.setText("TEST");
        addView(mTVLabel);

        LayoutParams lpEVInput = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpEVInput.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
        lpEVInput.addRule(RelativeLayout.BELOW, mTVLabel.getId());
        mETInput = new EditText(context);
        mETInput.setLayoutParams(lpEVInput);
        mETInput.setText("TEST");
        addView(mETInput);
    }
}
