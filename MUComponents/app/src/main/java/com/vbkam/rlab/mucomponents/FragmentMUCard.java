package com.vbkam.rlab.mucomponents;

import android.view.View;
import android.widget.TextView;

import com.rlab.sejima.features.MUCard;

public class FragmentMUCard extends DefaultFragment {

    private MUCard mMUCard;

    public static FragmentMUCard newInstance(){
        return new FragmentMUCard();
    }

    @Override
    int layoutId() {
        return R.layout.fragment_mu_card;
    }

    @Override
    String title() {
        return "MUCard";
    }

    @Override
    void initView(View view) {
        mMUCard = view.findViewById(R.id.mu_card);

        TextView tv = new TextView(getContext());
        tv.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));
        tv.setText("This is just a test");
        mMUCard.addContentView(tv);
    }
}
