package com.rlab.sejima;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.rlab.sejima.features.MUAvatar;
import com.rlab.sejima.features.MUButton;
import com.rlab.sejima.features.MUNavigationBar;
import com.rlab.sejima.features.MUTextField;
import com.rlab.sejima.features.MUTopBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        MUTextField.MUTextFieldListener, MUAvatar.MUAvatarClickListener {

    /**
     * A flag that enables to switch between images and shapes for MUAvatar
     */
    protected boolean bAvatar = false;
    /**
     * The MUTextField to be tested
     */
    private MUTextField mMUTextField;
    /**
     * The MUAvatar to be tested
     */
    private MUNavigationBar mMUNavBar;

    /**
     * A flag to update security of MUTextField
     */
    private boolean isSecure = false;
    /**
     * The alignment value of MUTextField
     */
    private int alignment = RelativeLayout.ALIGN_PARENT_START;
    /**
     * The keyboard type of MUTextField
     */
    private int keyboardType = InputType.TYPE_CLASS_TEXT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.mu_button).setOnClickListener(this);

        MUTopBar mMUTopBar = findViewById(R.id.mu_topbar);
        mMUTopBar.setMUTopBarClickListener(() -> {
            Toast.makeText(getApplicationContext(), "Click on MUTopBar", Toast.LENGTH_SHORT).show();
        });

        mMUTextField = findViewById(R.id.mutf1);
        mMUNavBar = findViewById(R.id.mu_navbar);

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
            case R.id.btn_4:
                // Border width
                mMUNavBar.setBorderWidth((float) (mMUNavBar.getBorderWidth() * 1.1));
                break;
            case R.id.btn_5:
                // Separator height
                mMUNavBar.setSeparatorMultiplier((float) (mMUNavBar.getSeparatorMultiplier() * 0.9));
                break;
            case R.id.btn_6:
                // Loading
                mMUNavBar.setLoading(!mMUNavBar.isLoading());
                break;
            case R.id.mu_button:
                MUButton b = (MUButton) v;
                b.setLoading(!b.isLoading());
                Toast.makeText(getApplicationContext(), "Click on MUButton", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void clickOnImage(AppCompatImageView imageView) {
        ((MUAvatar) imageView).setImage(getResources().getDrawable(bAvatar ? R.drawable.ic_launcher_background : R.drawable.avatar));
        ((MUAvatar) imageView).setBorderType(bAvatar ? MUAvatar.ROUND_BORDER : MUAvatar.SQUARE_BORDER);
        ((MUAvatar) imageView).setCornerRadius(bAvatar ? -1 : 25);
        bAvatar = !bAvatar;
    }

    @Override
    public void focusLost(AppCompatEditText textField) {

    }

    @Override
    public void isSelecting(AppCompatEditText textField) {

    }

    @Override
    public void textUpdated(AppCompatEditText textField) {

    }
}
