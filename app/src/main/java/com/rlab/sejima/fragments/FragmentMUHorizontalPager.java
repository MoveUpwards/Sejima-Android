package com.rlab.sejima.fragments;

import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.rlab.sejima.R;
import com.rlab.sejima.features.MUHorizontalPager;

import java.util.Locale;

public class FragmentMUHorizontalPager extends DefaultFragment implements MUHorizontalPager.MUHorizontalPagerListener {

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
    String title() {
        return "MUHorizontalPager";
    }

    @Override
    void initView(View view) {
        mMUHorizontalPager = view.findViewById(R.id.mu_horizontalpager);
        float leftMargins = mMUHorizontalPager.getHorizontalMargins();

        // Navigation
        view.findViewById(R.id.control_mu_horizontalpager_next).setOnClickListener(
                l -> mMUHorizontalPager.setCurrentIndex((mMUHorizontalPager.getCurrentIndex() + 1)));

        view.findViewById(R.id.control_mu_horizontalpager_previous).setOnClickListener(
                l -> mMUHorizontalPager.setCurrentIndex((mMUHorizontalPager.getCurrentIndex() - 1)));

        // Horizontal margins
        view.findViewById(R.id.control_mu_horizontalpager_margin_less).setOnClickListener(
                l -> mMUHorizontalPager.setHorizontalMargins((mMUHorizontalPager.getHorizontalMargins() - 1)));

        view.findViewById(R.id.control_mu_horizontalpager_margin_more).setOnClickListener(
                l -> mMUHorizontalPager.setHorizontalMargins((mMUHorizontalPager.getHorizontalMargins() + 1)));

        // Listener
        ((Switch) view.findViewById(R.id.control_mu_horizontalpager_listener)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUHorizontalPager.setMUHorizontalPagerListener(isChecked ? this : null));

        view.findViewById(R.id.control_mu_horizontalpager_raz).setOnClickListener(v -> {
            mMUHorizontalPager.setHorizontalMargins(leftMargins);
            mMUHorizontalPager.setCurrentIndex(0);
            ((Switch) view.findViewById(R.id.control_mu_horizontalpager_listener)).setChecked(false);
        });
    }

    @Override
    public void scrolledTo(MUHorizontalPager horizontalPager, int toIndex) {
        String toast = String.format(Locale.FRANCE, "Scroll to page %d", toIndex);
        Toast.makeText(getContext(),toast, Toast.LENGTH_SHORT).show();
    }
}
