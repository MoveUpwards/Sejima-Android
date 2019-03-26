package com.rlab.sejima;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.rlab.sejima.features.MUAvatar;
import com.rlab.sejima.features.MUTextField;
import com.rlab.sejima.features.MUTopBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        MUTextField.MUTextFieldListener, MUAvatar.MUAvatarClickListener {

    protected boolean bAvatar = false;
//    private MUAvatar mMUAvatar;
    private MUTextField mMUTextField;

    private boolean isSecure = false;
    private int alignment = RelativeLayout.ALIGN_PARENT_START;
    private int keyboardType = InputType.TYPE_CLASS_TEXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);

        MUTopBar mMUTopBar = findViewById(R.id.mu_topbar);
        mMUTopBar.setMUTopBarClickListener(() -> {
            Toast.makeText(getApplicationContext(), "Click on MUTopBar", Toast.LENGTH_SHORT).show();
        });

        mMUTextField = findViewById(R.id.mutf1);

        MUAvatar mMUAvatar = findViewById(R.id.muavatar);
        mMUAvatar.setPlaceholderImage(getResources().getDrawable(R.mipmap.ic_launcher));
        mMUAvatar.setImage(null);
        mMUAvatar.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_1:
                // Secure
                isSecure = !isSecure;
                mMUTextField.setSecure(isSecure);
                break;
            case R.id.btn_2:
                // Alignment
                alignment = alignment == RelativeLayout.ALIGN_PARENT_START ?
                        RelativeLayout.ALIGN_PARENT_END : RelativeLayout.ALIGN_PARENT_START;
                mMUTextField.setAlignment(alignment);
                break;
            case R.id.btn_3:
                // Keyboard type
                keyboardType = keyboardType == InputType.TYPE_CLASS_TEXT ?
                        InputType.TYPE_CLASS_NUMBER : InputType.TYPE_CLASS_TEXT;
                mMUTextField.setKeyboardType(keyboardType);
                break;
        }
    }

    @Override
    public void isSelecting(AppCompatEditText textField) {
    }

    @Override
    public void focusLost(AppCompatEditText textField) {
    }

    @Override
    public void textUpdated(AppCompatEditText textField) {
    }

    @Override
    public void clickOnImage(AppCompatImageView imageView) {
        ((MUAvatar) imageView).setImage(getResources().getDrawable(bAvatar ? R.drawable.ic_launcher_background : R.drawable.avatar));
        ((MUAvatar) imageView).setBorderType(bAvatar ? MUAvatar.ROUND_BORDER : MUAvatar.SQUARE_BORDER);
        ((MUAvatar) imageView).setCornerRadius(bAvatar ? -1 : 25);
        bAvatar = !bAvatar;
    }
}
