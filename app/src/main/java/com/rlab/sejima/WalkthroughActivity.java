package com.rlab.sejima;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.rlab.sejima.features.MUButton;
import com.rlab.sejima.features.MUHeader;
import com.rlab.sejima.features.MUHorizontalPager;

import androidx.appcompat.app.AppCompatActivity;

public class WalkthroughActivity extends AppCompatActivity implements MUHorizontalPager.MUHorizontalPagerListener {

    private MUHorizontalPager mMUHorizontalPager;
    private MUButton mMUButton;
    private MUHeader mMUHeader;

    private String[] mHEADERS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);

        mHEADERS = getResources().getStringArray(R.array.walkthrough);

        mMUHorizontalPager = findViewById(R.id.mu_horizontalpager);
        mMUHeader = findViewById(R.id.page_header);
        mMUButton = findViewById(R.id.mu_button);
        mMUButton.setOnClickListener(v -> {
            mMUHorizontalPager.setCurrentIndex(mMUHorizontalPager.getCurrentIndex() + 1);
        });

        initPager();
        mMUHorizontalPager.setMUPageControl(findViewById(R.id.mu_pagecontrol));
        mMUHorizontalPager.setMUHorizontalPagerListener(this);
    }

    private void initPager() {
        LayoutInflater inflater = getLayoutInflater();
        View myLayout;
        View[] pages = new View[mHEADERS.length];

        for(int i = 0 ; i < mHEADERS.length ; i++){
            myLayout = inflater.inflate(R.layout.walkthrough_default_page, null, false);
            ((ImageView) myLayout.findViewById(R.id.page_image)).setImageDrawable(getResources().getDrawable(
                    i % 2 == 0 ? R.drawable.avatar : R.mipmap.ic_launcher));
            pages[i] = myLayout;
        }

        mMUHorizontalPager.addViews(pages, 10);
        scrolledTo(mMUHorizontalPager, 0);
    }

    @Override
    public void scrolledTo(MUHorizontalPager horizontalPager, int toIndex) {

        mMUHeader.setTitle(mHEADERS[toIndex].split(";;")[0]);
        mMUHeader.setDetail(mHEADERS[toIndex].split(";;")[1]);

        if(toIndex == mMUHorizontalPager.getPageCount() - 1){
            mMUButton.setLabel("GO");
        } else {
            mMUButton.setLabel("Next");
        }
    }
}
