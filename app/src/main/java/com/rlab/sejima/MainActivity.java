package com.rlab.sejima;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rlab.sejima.features.MUTextField;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean editable = true;
    private boolean autocorrect = true;

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
                ((MUTextField) findViewById(R.id.mutf1)).setSecure(true);
                ((MUTextField) findViewById(R.id.mutf2)).setSecure(true);
                ((MUTextField) findViewById(R.id.mutf3)).setSecure(true);
                break;
            case R.id.btn_2:
                ((MUTextField) findViewById(R.id.mutf1)).setSecure(false);
                ((MUTextField) findViewById(R.id.mutf2)).setSecure(false);
                ((MUTextField) findViewById(R.id.mutf3)).setSecure(false);
                break;
            case R.id.btn_3:
                ((MUTextField) findViewById(R.id.mutf1)).setEditable(!editable);
                ((MUTextField) findViewById(R.id.mutf2)).setEditable(!editable);
                ((MUTextField) findViewById(R.id.mutf3)).setEditable(!editable);
                editable = !editable;
                break;
            case R.id.btn_4:
                ((MUTextField) findViewById(R.id.mutf1)).setAutoCorrection(!autocorrect);
                ((MUTextField) findViewById(R.id.mutf2)).setAutoCorrection(!autocorrect);
                ((MUTextField) findViewById(R.id.mutf3)).setAutoCorrection(!autocorrect);
                Toast.makeText(this, "autocorrect="+!autocorrect, Toast.LENGTH_SHORT).show();
                autocorrect = !autocorrect;
                break;
        }
    }
}
