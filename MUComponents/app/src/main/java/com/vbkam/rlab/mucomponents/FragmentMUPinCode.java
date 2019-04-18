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
        return "MUCard";
    }

    @Override
    void initView(View view) {
        mMUPinCode = view.findViewById(R.id.mu_pincode);
    }
}
