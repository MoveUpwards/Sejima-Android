package com.rlab.sejima;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.rlab.sejima.features.MUTextField;

/*
    An empty Activity which contains buttons so as to test some features at runtime.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, MUTextField.MUTextFieldListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_1:
                // For tests only
                break;
            case R.id.btn_2:
                // For tests only
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
}
