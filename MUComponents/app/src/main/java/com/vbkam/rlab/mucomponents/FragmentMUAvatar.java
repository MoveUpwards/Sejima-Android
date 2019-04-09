package com.vbkam.rlab.mucomponents;

import android.graphics.Color;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.rlab.sejima.features.MUAvatar;

public class FragmentMUAvatar extends DefaultFragment {

    private MUAvatar mMUAvatar;

    public static com.rlab.sejima.fragments.FragmentMUAvatar newInstance(){
        return new com.rlab.sejima.fragments.FragmentMUAvatar();
    }

    @Override
    int layoutId() {
        return com.rlab.sejima.R.layout.fragment_mu_avatar;
    }

    @Override
    String title() {
        return "MUAvatar";
    }

    @Override
    void initView(View view) {
        mMUAvatar = view.findViewById(com.rlab.sejima.R.id.mu_avatar);
        float cornerRadius = mMUAvatar.getCornerRadius();
        float borderWidth = mMUAvatar.getBorderWidth();

        mMUAvatar.setClickListener(imageView ->
                Toast.makeText(getContext(), "Click on avatar", Toast.LENGTH_SHORT).show());

        //Border type
        ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_avatar_shape)).setOnCheckedChangeListener((buttonView, isChecked) -> {
            mMUAvatar.setBorderType(isChecked ? MUAvatar.ROUND_BORDER : MUAvatar.SQUARE_BORDER);
            view.findViewById(com.rlab.sejima.R.id.control_mu_avatar_radius_less).setEnabled(!isChecked);
            view.findViewById(com.rlab.sejima.R.id.control_mu_avatar_radius_more).setEnabled(!isChecked);
        });

        // Drawable
        ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_avatar_image)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUAvatar.setImage(getResources().getDrawable(isChecked ? com.rlab.sejima.R.drawable.ic_launcher_background : com.rlab.sejima.R.drawable.avatar)));

        // Background color
        ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_avatar_bkg)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUAvatar.setBkgColor(isChecked ? getResources().getColor(com.rlab.sejima.R.color.colorAccent) : Color.TRANSPARENT));

        // Border color
        ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_avatar_border_color)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUAvatar.setBorderColor(isChecked ? getResources().getColor(com.rlab.sejima.R.color.colorAccent) : getResources().getColor(com.rlab.sejima.R.color.colorPrimaryDark)));

        // Corner radius
        view.findViewById(com.rlab.sejima.R.id.control_mu_avatar_radius_less).setOnClickListener(l -> mMUAvatar.setCornerRadius((mMUAvatar.getCornerRadius() - 10)));
        view.findViewById(com.rlab.sejima.R.id.control_mu_avatar_radius_more).setOnClickListener(l -> mMUAvatar.setCornerRadius((mMUAvatar.getCornerRadius() + 10)));

        // Border width
        view.findViewById(com.rlab.sejima.R.id.control_mu_avatar_border_less).setOnClickListener(l -> mMUAvatar.setBorderWidth((mMUAvatar.getBorderWidth() - 1)));
        view.findViewById(com.rlab.sejima.R.id.control_mu_avatar_border_more).setOnClickListener(l -> mMUAvatar.setBorderWidth((mMUAvatar.getBorderWidth() + 1)));

        view.findViewById(com.rlab.sejima.R.id.control_mu_avatar_raz).setOnClickListener(v -> {
            ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_avatar_border_color)).setChecked(false);
            ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_avatar_bkg)).setChecked(false);
            ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_avatar_image)).setChecked(false);
            ((Switch) view.findViewById(com.rlab.sejima.R.id.control_mu_avatar_shape)).setChecked(false);
            mMUAvatar.setBorderWidth(borderWidth);
            mMUAvatar.setCornerRadius(cornerRadius);
        });
    }

}
