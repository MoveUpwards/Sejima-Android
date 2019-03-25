package com.rlab.sejima;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;

import com.rlab.sejima.features.MUTextField;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MUTextField.MUTextFieldListener {

//    private boolean editable = true;
//    private boolean autocorrect = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_1:
                Log.e(getClass().getCanonicalName(), "add listener");
//                ((MUTextField) findViewById(R.id.mutf1)).setSecure(true);
//                ((MUTextField) findViewById(R.id.mutf2)).setSecure(true);
//                ((MUTextField) findViewById(R.id.mutf3)).setSecure(true);
                ((MUTextField) findViewById(R.id.mutf1)).setTFListener(this);
                ((MUTextField) findViewById(R.id.mutf1)).setUnderlineColor(Color.BLACK);

                break;
            case R.id.btn_2:
                Log.e(getClass().getCanonicalName(), "remove listener");
//                ((MUTextField) findViewById(R.id.mutf1)).setSecure(false);
//                ((MUTextField) findViewById(R.id.mutf2)).setSecure(false);
//                ((MUTextField) findViewById(R.id.mutf3)).setSecure(false);
                ((MUTextField) findViewById(R.id.mutf1)).setTFListener(null);
                ((MUTextField) findViewById(R.id.mutf1)).setUnderlineColor(Color.BLUE
                );
                break;
            case R.id.btn_3:
                ((MUTextField) findViewById(R.id.mutf1)).setReturnKeyAvailable(true);
                break;
            case R.id.btn_4:
                ((MUTextField) findViewById(R.id.mutf1)).setReturnKeyAvailable(false);
                break;
        }
    }

    @Override
    public void isSelecting(AppCompatEditText textField) {
        Log.e(getClass().getCanonicalName(), "selection listener: ok");
    }

    @Override
    public void focusLost(AppCompatEditText textField) {
        Log.e(getClass().getCanonicalName(), "focus lost listener: ok");
    }

    @Override
    public void textUpdated(AppCompatEditText textField) {
        Log.e(getClass().getCanonicalName(), "text update listener: ok");
    }
}
