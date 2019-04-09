package com.vbkam.rlab.mucomponents;

import android.graphics.Color;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.rlab.sejima.features.MUNavigationBar;

public class FragmentMUNavigationBar extends DefaultFragment {

    private MUNavigationBar mMUNavigationBar;

    public static com.rlab.sejima.fragments.FragmentMUNavigationBar newInstance(){
        return new com.rlab.sejima.fragments.FragmentMUNavigationBar();
    }

    @Override
    int layoutId() {
        return com.rlab.sejima.R.layout.fragment_mu_navigationbar;
    }

    @Override
    String title() {
        return "MUNavigationBar";
    }

    @Override
    void initView(View view) {
        mMUNavigationBar = view.findViewById(com.rlab.sejima.R.id.mu_navbar);
        float sepWidth = mMUNavigationBar.getSeparatorWidth();
        float sepMul = mMUNavigationBar.getSeparatorMultiplier();

        mMUNavigationBar.setMUNavigationBarListener(new MUNavigationBar.MUNavigationBarListener() {
            @Override
            public void clickOnLeftButton(MUNavigationBar muNavigationBar) {
                Toast.makeText(getContext(), "Click on left button", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void clickOnRightButton(MUNavigationBar muNavigationBar) {
                Toast.makeText(getContext(), "Click on right button", Toast.LENGTH_SHORT).show();
            }
        });

        // Bkg color
        ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_navbar_bkg_color)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUNavigationBar.setBkgColor(getResources().getColor(isChecked ? com.rlab.sejima.R.color.colorAccent : com.rlab.sejima.R.color.colorPrimary)));

        // Drawable
        ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_navbar_image)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUNavigationBar.setImgDrawable(getResources().getDrawable(isChecked ? com.rlab.sejima.R.drawable.ic_launcher_background : com.rlab.sejima.R.drawable.avatar)));

        // Separator color
        ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_navbar_separator_color)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUNavigationBar.setSeparatorColor(isChecked ? getResources().getColor(com.rlab.sejima.R.color.colorPrimaryDark) : Color.BLACK));

        // Separator width
        view.findViewById(com.rlab.sejima.R.id.control_mu_navbar_separator_less).setOnClickListener(l ->
                mMUNavigationBar.setSeparatorWidth((mMUNavigationBar.getSeparatorWidth() - 1)));
        view.findViewById(com.rlab.sejima.R.id.control_mu_navbar_separator_more).setOnClickListener(l ->
                mMUNavigationBar.setSeparatorWidth((mMUNavigationBar.getSeparatorWidth() + 1)));

        // Separator width
        view.findViewById(com.rlab.sejima.R.id.control_mu_navbar_separator_less).setOnClickListener(l ->
                mMUNavigationBar.setSeparatorWidth((mMUNavigationBar.getSeparatorWidth() - 1)));
        view.findViewById(com.rlab.sejima.R.id.control_mu_navbar_separator_more).setOnClickListener(l ->
                mMUNavigationBar.setSeparatorWidth((mMUNavigationBar.getSeparatorWidth() + 1)));

        // Separator multiplier
        view.findViewById(com.rlab.sejima.R.id.control_mu_navbar_separator_multi_less).setOnClickListener(l ->
                mMUNavigationBar.setSeparatorMultiplier((mMUNavigationBar.getSeparatorMultiplier() - 0.1f)));
        view.findViewById(com.rlab.sejima.R.id.control_mu_navbar_separator_multi_more).setOnClickListener(l ->
                mMUNavigationBar.setSeparatorMultiplier((mMUNavigationBar.getSeparatorMultiplier() + 0.1f)));

        // RAZ
        view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_raz).setOnClickListener(v -> {
            ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_navbar_bkg_color)).setChecked(false);
            ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_navbar_image)).setChecked(false);
            ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_navbar_separator_color)).setChecked(false);
            mMUNavigationBar.setSeparatorWidth(sepWidth);
            mMUNavigationBar.setSeparatorMultiplier(sepMul);
        });
    }
}
