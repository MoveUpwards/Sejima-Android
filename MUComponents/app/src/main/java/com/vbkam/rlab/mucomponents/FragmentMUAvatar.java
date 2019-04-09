package com.vbkam.rlab.mucomponents;

import android.graphics.Color;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.rlab.sejima.features.MUAvatar;

public class FragmentMUAvatar extends DefaultFragment {

    private MUAvatar mMUAvatar;

    public static FragmentMUAvatar newInstance(){
        return new FragmentMUAvatar();
    }

    @Override
    int layoutId() {
        return R.layout.fragment_mu_avatar;
    }

    @Override
    String title() {
        return "MUAvatar";
    }

    @Override
    void initView(View view) {
        mMUAvatar = view.findViewById(R.id.mu_avatar);
        float cornerRadius = mMUAvatar.getCornerRadius();
        float borderWidth = mMUAvatar.getBorderWidth();

        mMUAvatar.setClickListener(imageView ->
                Toast.makeText(getContext(), "Click on avatar", Toast.LENGTH_SHORT).show());

        //Border type
        ((Switch) view.findViewById(R.id.control_mu_avatar_shape)).setOnCheckedChangeListener((buttonView, isChecked) -> {
            mMUAvatar.setBorderType(isChecked ? MUAvatar.ROUND_BORDER : MUAvatar.SQUARE_BORDER);
            view.findViewById(R.id.control_mu_avatar_radius_less).setEnabled(!isChecked);
            view.findViewById(R.id.control_mu_avatar_radius_more).setEnabled(!isChecked);
        });

        // Drawable
        ((Switch) view.findViewById(R.id.control_mu_avatar_image)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUAvatar.setImage(getResources().getDrawable(isChecked ? R.drawable.ic_launcher_background : R.drawable.avatar)));

        // Background color
        ((Switch) view.findViewById(R.id.control_mu_avatar_bkg)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUAvatar.setBkgColor(isChecked ? getResources().getColor(R.color.colorAccent) : Color.TRANSPARENT));

        // Border color
        ((Switch) view.findViewById(R.id.control_mu_avatar_border_color)).setOnCheckedChangeListener((buttonView, isChecked) ->
                mMUAvatar.setBorderColor(isChecked ? getResources().getColor(R.color.colorAccent) : getResources().getColor(R.color.colorPrimaryDark)));

        // Corner radius
        view.findViewById(R.id.control_mu_avatar_radius_less).setOnClickListener(l -> mMUAvatar.setCornerRadius((mMUAvatar.getCornerRadius() - 10)));
        view.findViewById(R.id.control_mu_avatar_radius_more).setOnClickListener(l -> mMUAvatar.setCornerRadius((mMUAvatar.getCornerRadius() + 10)));

        // Border width
        view.findViewById(R.id.control_mu_avatar_border_less).setOnClickListener(l -> mMUAvatar.setBorderWidth((mMUAvatar.getBorderWidth() - 1)));
        view.findViewById(R.id.control_mu_avatar_border_more).setOnClickListener(l -> mMUAvatar.setBorderWidth((mMUAvatar.getBorderWidth() + 1)));

        view.findViewById(R.id.control_mu_avatar_raz).setOnClickListener(v -> {
            ((Switch) view.findViewById(R.id.control_mu_avatar_border_color)).setChecked(false);
            ((Switch) view.findViewById(R.id.control_mu_avatar_bkg)).setChecked(false);
            ((Switch) view.findViewById(R.id.control_mu_avatar_image)).setChecked(false);
            ((Switch) view.findViewById(R.id.control_mu_avatar_shape)).setChecked(false);
            mMUAvatar.setBorderWidth(borderWidth);
            mMUAvatar.setCornerRadius(cornerRadius);
        });
    }

}
