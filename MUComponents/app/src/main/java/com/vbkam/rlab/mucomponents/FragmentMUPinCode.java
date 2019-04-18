package com.vbkam.rlab.mucomponents;

import android.view.View;

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
        mMUPinCode = view.findViewById(R.id.mu_pincode);
        mMUPinCode.setCount(2);


        // Length
        view.findViewById(R.id.control_mu_pincode_less).setOnClickListener(l ->
                mMUPinCode.setCount((mMUPinCode.getCount() - 1)));
        view.findViewById(R.id.control_mu_pincode_more).setOnClickListener(l ->
                mMUPinCode.setCount((mMUPinCode.getCount() + 1)));

        // Corner radius
        view.findViewById(R.id.control_mu_pincode_corner_less).setOnClickListener(l ->
                mMUPinCode.setCellCornerRadius((mMUPinCode.getCellCornerRadius() - 5)));
        view.findViewById(R.id.control_mu_pincode_corner_more).setOnClickListener(l ->
                mMUPinCode.setCellCornerRadius((mMUPinCode.getCellCornerRadius() + 5)));

        // Cell space
        view.findViewById(R.id.control_mu_pincode_space_less).setOnClickListener(l ->
                mMUPinCode.setCellSpacing((mMUPinCode.getCellSpacing() - 5)));
        view.findViewById(R.id.control_mu_pincode_space_more).setOnClickListener(l ->
                mMUPinCode.setCellSpacing((mMUPinCode.getCellSpacing() + 5)));
    }
}
