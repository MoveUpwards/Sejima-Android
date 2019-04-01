package com.rlab.sejima.fragments;

import android.view.View;

import com.rlab.sejima.R;
import com.rlab.sejima.features.MUHorizontalPager;

public class FragmentMUHorizontalPager extends DefaultFragment {

    private MUHorizontalPager mMUHorizontalPager;

    public FragmentMUHorizontalPager(){
    }

    public static FragmentMUHorizontalPager newInstance(){
        return new FragmentMUHorizontalPager();
    }

    @Override
    int layoutId() {
        return R.layout.fragment_mu_horizontalpager;
    }

    @Override
    void initView(View view) {
        mMUHorizontalPager = view.findViewById(R.id.mu_horizontalepager);
    }
}
