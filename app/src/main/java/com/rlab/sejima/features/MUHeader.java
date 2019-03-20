package com.rlab.sejima.features;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rlab.sejima.R;

public class MUHeader extends RelativeLayout {

    /**
     * The default size of title
     */
    private final static int DEFAULT_TITLE_SIZE_IN_SP = 34;
    /**
     * The default size of detail
     */
    private final static int DEFAULT_DETAIL_SIZE_IN_SP = 14;

    /**
     * The current title
     */
    public String mTitle = "";
    /**
     * The title's size
     */
    public float mTitleSize;
    /**
     * The default title's weight
     */
    public int mTitleWeight = Typeface.NORMAL;

    /**
     * The title's text color
     */
    public int mTitleColor = Color.BLACK;

    /**
     * The current detail description
     */
    public String mDetail = "";
    /**
     * The detail's size
     */
    public float mDetailSize;
    /**
     * The default detail's weight
     */
    public int mDetailWeight = Typeface.BOLD;
    /**
     * The detail's text color
     */
    public int mDetailColor = Color.BLACK;
    /**
     * The text's horizontal alignment
     */
    public int mTextAlignment = TEXT_ALIGNMENT_TEXT_START;
    /**
     * Vertical spacing between title and detail
     */
    public int mVerticalSpacing = 8;

    /**
     * Default constructor
     * @param context the view context
     */
    public MUHeader(Context context) {
        super(context);
        init(context);
    }

    /**
     * Constructor with attributes
     * @param context the view context
     * @param attrs the XML attributes for the view
     */
    public MUHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MUHeader);

        CharSequence s;

        // Deal with title's attributes
        s = a.getString(R.styleable.MUHeader_title);
        mTitle = TextUtils.isEmpty(s) ? mTitle : s.toString();

        mTitleColor = a.getColor(R.styleable.MUHeader_title_color, mTitleColor);
        mTitleSize = a.getDimensionPixelSize(R.styleable.MUHeader_title_size, 0);
        mTitleWeight = a.getInt(R.styleable.MUHeader_title_weight, mTitleWeight);

        // Deal with detail's attributes
        s = a.getString(R.styleable.MUHeader_detail);
        mDetail = TextUtils.isEmpty(s) ? mDetail : s.toString();

        mDetailColor = a.getColor(R.styleable.MUHeader_detail_color, mTitleColor);
        mDetailSize = a.getDimensionPixelSize(R.styleable.MUHeader_detail_size, 0);
        mDetailWeight = a.getInt(R.styleable.MUHeader_detail_weight, mDetailWeight);

        mVerticalSpacing = a.getDimensionPixelSize(R.styleable.MUHeader_vertical_spacing, mVerticalSpacing);
        mTextAlignment = a.getInt(R.styleable.MUHeader_text_alignment, ALIGN_START);

        init(context);
        a.recycle();
    }

    private void init(Context context) {
        inflate(context, R.layout.feature_mu_header, this);

        mTitleSize = mTitleSize != 0 ? mTitleSize : DEFAULT_TITLE_SIZE_IN_SP *
                ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        setUpTextView(R.id.mu_header_title, mTitle, mTitleColor, mTitleSize, mTitleWeight);


        mDetailSize = mDetailSize != 0 ? mDetailSize : DEFAULT_DETAIL_SIZE_IN_SP *
                ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        setUpTextView(R.id.mu_header_detail, mDetail, mDetailColor, mDetailSize, mDetailWeight);

        findViewById(R.id.mu_header_detail).setPadding(0, mVerticalSpacing, 0, 0);
    }

    public void setUpTextView(int viewId, String text, int color, float size, int typeface){
        TextView tv = this.findViewById(viewId);
        tv.setText(text);
        tv.setTextColor(color);
        tv.setTextAlignment(mTextAlignment);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        tv.setTypeface(Typeface.create(Typeface.DEFAULT, typeface));
    }


}
