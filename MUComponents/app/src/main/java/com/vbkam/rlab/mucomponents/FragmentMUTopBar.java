package com.vbkam.rlab.mucomponents;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.rlab.sejima.features.MUTopBar;

public class FragmentMUTopBar extends DefaultFragment {

    private MUTopBar mMUTopBar;

    public static com.rlab.sejima.fragments.FragmentMUTopBar newInstance(){
        return new com.rlab.sejima.fragments.FragmentMUTopBar();
    }

    @Override
    int layoutId() {
        return com.rlab.sejima.R.layout.fragment_mu_topbar;
    }

    @Override
    String title() {
        return "MUTopBar";
    }

    @Override
    void initView(View view) {
        mMUTopBar = view.findViewById(com.rlab.sejima.R.id.mu_topbar);
        float titleSize = mMUTopBar.getTitleFontSize();
        float defaultLeading = mMUTopBar.getLeftButtonLeading();
        float btnWidth = mMUTopBar.getLeftButtonWidth();
        int alignment = mMUTopBar.getTitleAlignment();

        mMUTopBar.setMUTopBarClickListener(() ->
                Toast.makeText(getContext(), "Click on MUTopBar", Toast.LENGTH_SHORT).show());

        // Title
        EditText etTitle = view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_et_title);
        view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_title).setOnClickListener(l ->
                mMUTopBar.setTitle(TextUtils.isEmpty(etTitle.getText()) ? "" : etTitle.getText().toString()));

        // Title color
        ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_title_color)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUTopBar.setTitleColor(isChecked ? getResources().getColor(com.rlab.sejima.R.color.colorAccent) : Color.BLACK));

        // Title weight
        ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_title_font)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUTopBar.setTitleFontWeight(isChecked ? Typeface.BOLD : Typeface.NORMAL));

        // Title size
        view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_title_less).setOnClickListener(l -> mMUTopBar.setTitleFontSize((mMUTopBar.getTitleFontSize() - 1)));
        view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_title_more).setOnClickListener(l -> mMUTopBar.setTitleFontSize((mMUTopBar.getTitleFontSize()  + 1)));

        // Drawable
        ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_image)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUTopBar.setButtonImage(isChecked ? com.rlab.sejima.R.drawable.ic_launcher_background : com.rlab.sejima.R.drawable.avatar));

        // Left leading
        view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_leading_less).setOnClickListener(l ->
                mMUTopBar.setLeftButtonLeading((mMUTopBar.getLeftButtonLeading() - 1)));
        view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_leading_more).setOnClickListener(l ->
                mMUTopBar.setLeftButtonLeading((mMUTopBar.getLeftButtonLeading()  + 1)));

        // Btn width
        view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_width_less).setOnClickListener(l ->
                mMUTopBar.setLeftButtonWidth((mMUTopBar.getLeftButtonWidth() - 1)));
        view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_width_more).setOnClickListener(l ->
                mMUTopBar.setLeftButtonWidth((mMUTopBar.getLeftButtonWidth()  + 1)));

        // Drawable
        ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_image)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUTopBar.setButtonImage(isChecked ? com.rlab.sejima.R.drawable.ic_launcher_background : com.rlab.sejima.R.drawable.avatar));

        // Show button
        ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_button_hidden)).setOnCheckedChangeListener(
                (buttonView, isChecked) -> mMUTopBar.setButtonHidden(isChecked));

        // Alignment
        view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_left).setOnClickListener(l -> mMUTopBar.setTitleAlignment(Gravity.START));
        view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_right).setOnClickListener(l -> mMUTopBar.setTitleAlignment(Gravity.END));
        view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_center).setOnClickListener(l -> mMUTopBar.setTitleAlignment(Gravity.CENTER));

        // RAZ
        view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_raz).setOnClickListener(v -> {
            ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_title_color)).setChecked(false);
            ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_title_font)).setChecked(false);
            ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_image)).setChecked(false);
            ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_image)).setChecked(false);
            ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_topbar_button_hidden)).setChecked(false);
            mMUTopBar.setTitleFontSize(titleSize);
            mMUTopBar.setLeftButtonLeading(defaultLeading);
            mMUTopBar.setLeftButtonWidth((int) btnWidth);
            mMUTopBar.setTitleAlignment(alignment);
        });
    }
}
