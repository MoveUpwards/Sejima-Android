package com.vbkam.rlab.mucomponents;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.rlab.sejima.features.MUHorizontalPager;
import com.rlab.sejima.features.MUPageControl;

import java.util.Locale;

public class FragmentMUHorizontalPager extends DefaultFragment implements MUHorizontalPager.MUHorizontalPagerListener {

    private MUHorizontalPager mMUHorizontalPager;

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

        view.findViewById(R.id.control_mu_horizontalpager_margin_more).setOnClickListener(
                l -> mMUHorizontalPager.setHorizontalMargins((mMUHorizontalPager.getHorizontalMargins() + 1)));

        // Control radius
        view.findViewById(R.id.control_mu_horizontalpager_corner_more).setOnClickListener(
                l -> mMUHorizontalPager.getMUPageControl().setActiveElementRadius((mMUHorizontalPager.getMUPageControl().getActiveElementRadius() + 5)));

        view.findViewById(R.id.control_mu_horizontalpager_corner_less).setOnClickListener(
                l -> mMUHorizontalPager.getMUPageControl().setActiveElementRadius((mMUHorizontalPager.getMUPageControl().getActiveElementRadius() - 5)));

        // Control padding
        view.findViewById(R.id.control_mu_horizontalpager_control_padding_less).setOnClickListener(
                l -> mMUHorizontalPager.getMUPageControl().setElementPadding((mMUHorizontalPager.getMUPageControl().getElementPadding() - 5)));

        view.findViewById(R.id.control_mu_horizontalpager_control_padding_more).setOnClickListener(
                l -> mMUHorizontalPager.getMUPageControl().setElementPadding((mMUHorizontalPager.getMUPageControl().getElementPadding() + 5)));

        // Control border color
        ((Switch) view.findViewById(R.id.control_mu_horizontalpager_border_color)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUHorizontalPager.getMUPageControl().setBorderColor(isChecked ? getResources().getColor(R.color.colorAccent) : Color.BLACK));

        // Control border width
        view.findViewById(R.id.control_mu_horizontalpager_control_border_less).setOnClickListener(
                l -> mMUHorizontalPager.getMUPageControl().setBorderWidth((mMUHorizontalPager.getMUPageControl().getBorderWidth() - 1)));

        view.findViewById(R.id.control_mu_horizontalpager_control_border_more).setOnClickListener(
                l -> mMUHorizontalPager.getMUPageControl().setBorderWidth((mMUHorizontalPager.getMUPageControl().getBorderWidth() + 1)));

        // Listener
        ((Switch) view.findViewById(R.id.control_mu_horizontalpager_listener)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUHorizontalPager.setMUHorizontalPagerListener(isChecked ? this : null));



        MUPageControl MUPageControl = view.findViewById(R.id.mu_pagecontrol);
        initMUHorizontalPager();
        mMUHorizontalPager.setMUPageControl(MUPageControl);

        float leftMargins = mMUHorizontalPager.getHorizontalMargins();
        int borderWidth = mMUHorizontalPager.getMUPageControl().getBorderWidth();
        int elementPadding = mMUHorizontalPager.getMUPageControl().getElementPadding();
        int elementRadius = mMUHorizontalPager.getMUPageControl().getActiveElementRadius();

        view.findViewById(R.id.control_mu_horizontalpager_raz).setOnClickListener(v -> {
            mMUHorizontalPager.setHorizontalMargins(leftMargins);
            mMUHorizontalPager.getMUPageControl().setBorderWidth(borderWidth);
            mMUHorizontalPager.getMUPageControl().setElementPadding(elementPadding);
            mMUHorizontalPager.getMUPageControl().setActiveElementRadius(elementRadius);
            ((Switch) view.findViewById(R.id.control_mu_horizontalpager_listener)).setChecked(false);
            ((Switch) view.findViewById(R.id.control_mu_horizontalpager_border_color)).setChecked(false);
        });
    }

    @Override
    public void scrolledTo(MUHorizontalPager horizontalPager, int toIndex) {
        String toast = String.format(Locale.FRANCE, "Scroll to page %d", toIndex);
        Log.e(getClass().getCanonicalName(), toast);
    }

    private void initMUHorizontalPager(){
        TextView tv = new TextView(getContext());
        tv.setText(getString(R.string.app_name));
        mMUHorizontalPager.addSubView(tv, 12);

        LinearLayout ll = new LinearLayout(getContext());
        ll.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        mMUHorizontalPager.addSubView(ll, 0);

        tv = new TextView(getContext());
        tv.setText(getString(R.string.app_name));
        mMUHorizontalPager.addSubView(tv, 12);

        ll = new LinearLayout(getContext());
        ll.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        mMUHorizontalPager.addSubView(ll, 0);

        tv = new TextView(getContext());
        tv.setText(getString(R.string.app_name));
        mMUHorizontalPager.addSubView(tv, 12);
    }
}
