package com.vbkam.rlab.mucomponents;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.rlab.sejima.features.MUHeader;

public class FragmentMUHeader extends DefaultFragment {

    private MUHeader mMUHeader;

    public static FragmentMUHeader newInstance(){
        return new FragmentMUHeader();
    }

    @Override
    int layoutId() {
        return R.layout.fragment_mu_header;
    }

    @Override
    String title() {
        return "MUHeader";
    }

    @Override
    void initView(View view) {
        mMUHeader = view.findViewById(R.id.mu_header);
        float titleSize = mMUHeader.getTitleSize();
        float detailSize = mMUHeader.getDetailSize();
        float verticalSpace = mMUHeader.getVerticalSpacing();
        float alignment = mMUHeader.getAlignment();

        // Title
        EditText etTitle = view.findViewById(R.id.control_mu_header_et_title);
        view.findViewById(R.id.control_mu_header_title).setOnClickListener(l ->
                mMUHeader.setTitle(TextUtils.isEmpty(etTitle.getText()) ? "" : etTitle.getText().toString()));

        // Title color
        ((Switch) view.findViewById(R.id.control_mu_header_title_color)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUHeader.setTitleColor(isChecked ? getResources().getColor(R.color.colorAccent) : Color.BLACK));

        // Title weight
        ((Switch) view.findViewById(R.id.control_mu_header_title_font)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUHeader.setTitleWeight(isChecked ? Typeface.BOLD : Typeface.NORMAL));

        // Title size
        view.findViewById(R.id.control_mu_header_title_less).setOnClickListener(l -> mMUHeader.setTitleSize((mMUHeader.getTitleSize() - 1)));
        view.findViewById(R.id.control_mu_header_title_more).setOnClickListener(l -> mMUHeader.setTitleSize((mMUHeader.getTitleSize()  + 1)));


        // Details
        EditText etDetails = view.findViewById(R.id.control_mu_header_et_details);
        view.findViewById(R.id.control_mu_header_details).setOnClickListener(l ->
                mMUHeader.setDetail(TextUtils.isEmpty(etDetails.getText()) ? "" : etDetails.getText().toString()));

        // Details color
        ((Switch) view.findViewById(R.id.control_mu_header_details_color)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUHeader.setDetailColor(isChecked ? getResources().getColor(R.color.colorAccent) : Color.BLACK));

        // Details weight
        ((Switch) view.findViewById(R.id.control_mu_header_details_font)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUHeader.setDetailWeight(isChecked ? Typeface.BOLD : Typeface.NORMAL));

        // Details size
        view.findViewById(R.id.control_mu_header_details_less).setOnClickListener(l -> mMUHeader.setDetailSize((mMUHeader.getDetailSize() - 1)));
        view.findViewById(R.id.control_mu_header_details_more).setOnClickListener(l -> mMUHeader.setDetailSize((mMUHeader.getDetailSize()  + 1)));

        // Alignment
        view.findViewById(R.id.control_mu_header_left).setOnClickListener(l -> mMUHeader.setAlignment(Gravity.START));
        view.findViewById(R.id.control_mu_header_right).setOnClickListener(l -> mMUHeader.setAlignment(Gravity.END));
        view.findViewById(R.id.control_mu_header_center).setOnClickListener(l -> mMUHeader.setAlignment(Gravity.CENTER));

        // Vertical
        view.findViewById(R.id.control_mu_header_vertical_less).setOnClickListener(l -> mMUHeader.setVerticalSpacing(mMUHeader.getVerticalSpacing() - 10));
        view.findViewById(R.id.control_mu_header_vertical_more).setOnClickListener(l -> mMUHeader.setVerticalSpacing(mMUHeader.getVerticalSpacing() + 10));

        // RAZ
        view.findViewById(R.id.control_mu_header_raz).setOnClickListener(v -> {
            ((Switch) view.findViewById(R.id.control_mu_header_details_font)).setChecked(false);
            ((Switch) view.findViewById(R.id.control_mu_header_details_color)).setChecked(false);
            ((Switch) view.findViewById(R.id.control_mu_header_title_font)).setChecked(false);
            ((Switch) view.findViewById(R.id.control_mu_header_title_color)).setChecked(false);
            mMUHeader.setTitleSize(titleSize);
            mMUHeader.setDetailSize(detailSize);
            mMUHeader.setVerticalSpacing((int) verticalSpace);
            mMUHeader.setAlignment((int) alignment);
        });
    }
}
