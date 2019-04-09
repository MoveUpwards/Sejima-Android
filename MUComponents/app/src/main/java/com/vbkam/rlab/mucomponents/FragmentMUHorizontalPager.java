package com.vbkam.rlab.mucomponents;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.rlab.sejima.features.MUHorizontalPager;
import com.rlab.sejima.features.MUPageControl;

import java.util.Locale;

public class FragmentMUHorizontalPager extends DefaultFragment implements MUHorizontalPager.MUHorizontalPagerListener {

    private MUHorizontalPager mMUHorizontalPager;

    public static com.rlab.sejima.fragments.FragmentMUHorizontalPager newInstance(){
        return new com.rlab.sejima.fragments.FragmentMUHorizontalPager();
    }

    @Override
    int layoutId() {
        return com.rlab.sejima.R.layout.fragment_mu_horizontalpager;
    }

    @Override
    String title() {
        return "MUHorizontalPager";
    }

    @Override
    void initView(View view) {
        mMUHorizontalPager = view.findViewById(com.rlab.sejima.R.id.mu_horizontalpager);
        float leftMargins = mMUHorizontalPager.getHorizontalMargins();

        // Navigation
        view.findViewById(com.rlab.sejima.R.id.control_mu_horizontalpager_next).setOnClickListener(
                l -> mMUHorizontalPager.setCurrentIndex((mMUHorizontalPager.getCurrentIndex() + 1)));

        view.findViewById(com.rlab.sejima.R.id.control_mu_horizontalpager_previous).setOnClickListener(
                l -> mMUHorizontalPager.setCurrentIndex((mMUHorizontalPager.getCurrentIndex() - 1)));

        // Horizontal margins
        view.findViewById(com.rlab.sejima.R.id.control_mu_horizontalpager_margin_less).setOnClickListener(
                l -> mMUHorizontalPager.setHorizontalMargins((mMUHorizontalPager.getHorizontalMargins() - 1)));

        view.findViewById(com.rlab.sejima.R.id.control_mu_horizontalpager_margin_more).setOnClickListener(
                l -> mMUHorizontalPager.setHorizontalMargins((mMUHorizontalPager.getHorizontalMargins() + 1)));

        // Listener
        ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_horizontalpager_listener)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUHorizontalPager.setMUHorizontalPagerListener(isChecked ? this : null));

        view.findViewById(com.rlab.sejima.R.id.control_mu_horizontalpager_raz).setOnClickListener(v -> {
            mMUHorizontalPager.setHorizontalMargins(leftMargins);
            ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_horizontalpager_listener)).setChecked(false);
        });

        MUPageControl MUPageControl = view.findViewById(com.rlab.sejima.R.id.mu_pagecontrol);
        initMUHorizontalPager();
        mMUHorizontalPager.setMUPageControl(MUPageControl);
    }

    @Override
    public void scrolledTo(MUHorizontalPager horizontalPager, int toIndex) {
        String toast = String.format(Locale.FRANCE, "Scroll to page %d", toIndex);
        Toast.makeText(getContext(),toast, Toast.LENGTH_SHORT).show();
    }

    private void initMUHorizontalPager(){
        TextView tv = new TextView(getContext());
        tv.setText(getString(com.rlab.sejima.R.string.app_name));
        mMUHorizontalPager.addSubView(tv, 12);

        LinearLayout ll = new LinearLayout(getContext());
        ll.setBackgroundColor(getResources().getColor(com.rlab.sejima.R.color.colorPrimaryDark));
        mMUHorizontalPager.addSubView(ll, 0);

        tv = new TextView(getContext());
        tv.setText(getString(com.rlab.sejima.R.string.app_name));
        mMUHorizontalPager.addSubView(tv, 12);

        ll = new LinearLayout(getContext());
        ll.setBackgroundColor(getResources().getColor(com.rlab.sejima.R.color.colorPrimaryDark));
        mMUHorizontalPager.addSubView(ll, 0);

        tv = new TextView(getContext());
        tv.setText(getString(com.rlab.sejima.R.string.app_name));
        mMUHorizontalPager.addSubView(tv, 12);
    }
}
