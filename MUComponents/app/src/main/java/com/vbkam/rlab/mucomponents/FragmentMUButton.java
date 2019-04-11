package com.vbkam.rlab.mucomponents;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.rlab.sejima.features.MUButton;

public class FragmentMUButton extends DefaultFragment {

    private MUButton mMUButton;

    public static FragmentMUButton newInstance(){
        return new FragmentMUButton();
    }

    @Override
    int layoutId() {
        return R.layout.fragment_mu_button;
    }

    @Override
    String title() {
        return "MUButton";
    }

    @Override
    void initView(View view) {
        mMUButton = view.findViewById(R.id.mu_button);

        float titleSize = mMUButton.getLabelFontSize();
        int alignment = mMUButton.getLabelAlignment();
        int cornerRadius = mMUButton.getCornerRadius();
        float borderWidth = mMUButton.getBorderWidth();
        int hPad = mMUButton.getHorizontalPadding();
        int vPad = mMUButton.getVerticalPadding();

        mMUButton.setListener(button ->
                Toast.makeText(getContext(), "Click on button", Toast.LENGTH_SHORT).show());

        // Label
        EditText etTitle = view.findViewById(R.id.control_mu_button_et_label);
        view.findViewById(R.id.control_mu_button_label).setOnClickListener(l ->
                mMUButton.setLabel(TextUtils.isEmpty(etTitle.getText()) ? "" : etTitle.getText().toString()));

        // Label color
        ((Switch) view.findViewById(R.id.control_mu_button_title_color)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUButton.setLabelColor(isChecked ? getResources().getColor(R.color.colorAccent) : Color.BLACK));

        // Label weight
        ((Switch) view.findViewById(R.id.control_mu_button_title_font)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUButton.setLabelFontWeight(isChecked ? Typeface.BOLD : Typeface.NORMAL));

        // Label size
        view.findViewById(R.id.control_mu_button_title_less).setOnClickListener(
                l -> mMUButton.setLabelFontSize((mMUButton.getLabelFontSize() - 1)));
        view.findViewById(R.id.control_mu_button_title_more).setOnClickListener(
                l -> mMUButton.setLabelFontSize((mMUButton.getLabelFontSize()  + 1)));

        // Alignment
        view.findViewById(R.id.control_mu_button_left).setOnClickListener(
                l -> mMUButton.setLabelAlignment(Gravity.START));
        view.findViewById(R.id.control_mu_button_right).setOnClickListener(
                l -> mMUButton.setLabelAlignment(Gravity.END));
        view.findViewById(R.id.control_mu_button_center).setOnClickListener(
                l -> mMUButton.setLabelAlignment(Gravity.CENTER));

        // Alpha
        ((Switch) view.findViewById(R.id.control_mu_button_alpha)).setOnCheckedChangeListener((buttonView, isChecked) -> {
            mMUButton.setAlpha(isChecked ? 0.5f : 1.0f);
            mMUButton.setBorderAlpha(isChecked ? 0.5f : 1.0f);
        });
        ((Switch) view.findViewById(R.id.control_mu_button_disabled_alpha)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUButton.setAlpha(isChecked ? 0.3f : 0.7f));

        // Pressed color
        ((Switch) view.findViewById(R.id.control_mu_button_pressed_color)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUButton.setLabelHighLightedColor(isChecked ? getResources().getColor(R.color.colorAccent) : Color.BLACK));
        // Progressing color
        ((Switch) view.findViewById(R.id.control_mu_button_progressing_color)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUButton.setProgressingColor(isChecked ? getResources().getColor(R.color.colorAccent) : Color.BLACK));

        // Loading
        ((Switch) view.findViewById(R.id.control_mu_button_loading)).setOnCheckedChangeListener(
                (buttonView, isChecked) -> mMUButton.setLoading(isChecked));

        // Background color
        ((Switch) view.findViewById(R.id.control_mu_button_background)).setOnCheckedChangeListener(
                (buttonView, isChecked) -> mMUButton.setBkgColor(
                        isChecked ? Color.LTGRAY : getResources().getColor(R.color.colorPrimary)));

        // Border color
        ((Switch) view.findViewById(R.id.control_mu_button_border_color)).setOnCheckedChangeListener(
                (buttonView, isChecked) -> mMUButton.setBorderColor(isChecked ?
                        getResources().getColor(R.color.colorAccent) :
                        getResources().getColor(R.color.colorPrimaryDark)));

        // Border width
        view.findViewById(R.id.control_mu_button_border_less).setOnClickListener(l ->
                mMUButton.setBorderWidth((mMUButton.getBorderWidth() - 1)));
        view.findViewById(R.id.control_mu_button_border_more).setOnClickListener(l ->
                mMUButton.setBorderWidth((mMUButton.getBorderWidth() + 1)));

        // Corner radius
        view.findViewById(R.id.control_mu_button_radius_less).setOnClickListener(l ->
                mMUButton.setCornerRadius((mMUButton.getCornerRadius() - 1)));
        view.findViewById(R.id.control_mu_button_radius_more).setOnClickListener(l ->
                mMUButton.setCornerRadius((mMUButton.getCornerRadius() + 1)));

        // Horizontal padding
        view.findViewById(R.id.control_mu_button_horizontal_less).setOnClickListener(l ->
                mMUButton.setHorizontalPadding((mMUButton.getHorizontalPadding() - 1)));
        view.findViewById(R.id.control_mu_button_horizontal_more).setOnClickListener(l ->
                mMUButton.setHorizontalPadding((mMUButton.getHorizontalPadding() + 1)));

        // Vertical padding
        view.findViewById(R.id.control_mu_button_vertical_less).setOnClickListener(l ->
                mMUButton.setVerticalPadding((mMUButton.getVerticalPadding() - 1)));
        view.findViewById(R.id.control_mu_button_vertical_more).setOnClickListener(l ->
                mMUButton.setVerticalPadding((mMUButton.getVerticalPadding() + 1)));

        mMUButton.setFontStyle(R.style.Header);

        // RAZ
        view.findViewById(R.id.control_mu_button_raz).setOnClickListener(v -> {
            ((Switch) view.findViewById(R.id.control_mu_button_title_color)).setChecked(false);
            ((Switch) view.findViewById(R.id.control_mu_button_title_font)).setChecked(false);
            mMUButton.setLabelFontSize(titleSize);
            mMUButton.setLabelAlignment(alignment);
            ((Switch) view.findViewById(R.id.control_mu_button_alpha)).setChecked(false);
            ((Switch) view.findViewById(R.id.control_mu_button_disabled_alpha)).setChecked(false);
            ((Switch) view.findViewById(R.id.control_mu_button_pressed_color)).setChecked(false);
            ((Switch) view.findViewById(R.id.control_mu_button_progressing_color)).setChecked(false);
            ((Switch) view.findViewById(R.id.control_mu_button_loading)).setChecked(false);
            ((Switch) view.findViewById(R.id.control_mu_button_background)).setChecked(false);
            ((Switch) view.findViewById(R.id.control_mu_button_border_color)).setChecked(false);
            mMUButton.setBorderWidth(borderWidth);
            mMUButton.setCornerRadius(cornerRadius);
            mMUButton.setHorizontalPadding(hPad);
            mMUButton.setVerticalPadding(vPad);
        });

    }
}
