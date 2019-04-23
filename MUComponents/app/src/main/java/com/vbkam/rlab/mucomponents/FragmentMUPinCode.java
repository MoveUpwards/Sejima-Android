package com.vbkam.rlab.mucomponents;

import android.view.View;
import android.widget.TextView;

import com.rlab.sejima.features.MUPinCode;

public class FragmentMUPinCode extends DefaultFragment {

    private MUPinCode mMUPinCode;

    public static FragmentMUPinCode newInstance(){
        return new FragmentMUPinCode();
    }

    @Override
    int layoutId() {
        return R.layout.fragment_mu_pincode;
    }

    @Override
    String title() {
        return "MUPinCode";
    }

    @Override
    void initView(View view) {
        
        mMUPinCode = view.findViewById(com.rlab.sejima.R.id.mu_pincode);

        // Length
        view.findViewById(com.rlab.sejima.R.id.control_mu_pincode_less).setOnClickListener(l ->
                mMUPinCode.setCount((mMUPinCode.getCount() - 1)));
        view.findViewById(com.rlab.sejima.R.id.control_mu_pincode_more).setOnClickListener(l ->
                mMUPinCode.setCount((mMUPinCode.getCount() + 1)));

        // Corner radius
        view.findViewById(com.rlab.sejima.R.id.control_mu_pincode_corner_less).setOnClickListener(l ->
                mMUPinCode.setCellCornerRadius((mMUPinCode.getCellCornerRadius() - 5)));
        view.findViewById(com.rlab.sejima.R.id.control_mu_pincode_corner_more).setOnClickListener(l ->
                mMUPinCode.setCellCornerRadius((mMUPinCode.getCellCornerRadius() + 5)));

        // Cell space
        view.findViewById(com.rlab.sejima.R.id.control_mu_pincode_space_less).setOnClickListener(l ->
                mMUPinCode.setCellSpacing((mMUPinCode.getCellSpacing() - 5)));
        view.findViewById(com.rlab.sejima.R.id.control_mu_pincode_space_more).setOnClickListener(l ->
                mMUPinCode.setCellSpacing((mMUPinCode.getCellSpacing() + 5)));

        TextView tvCode = view.findViewById(com.rlab.sejima.R.id.control_mu_pincode_tv_code);
        view.findViewById(com.rlab.sejima.R.id.control_mu_pincode_code).setOnClickListener(l ->
                tvCode.setText(mMUPinCode.getCode()));
    }
}
