package com.example.meedy.irrigationapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by meedy on 5/21/2016.
 */
public class sys_capacity extends AppCompatActivity {

    EditText area, dg, irrc, sh, tim;
    TextView sys;
    Button com;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_capacity);

        area = (EditText)findViewById(R.id.editText5);

        dg = (EditText)findViewById(R.id.editText6);
        irrc = (EditText)findViewById(R.id.editText7);
        sh = (EditText)findViewById(R.id.editText8);
        tim = (EditText)findViewById(R.id.editText9);
        sys =(TextView)findViewById(R.id.textView13);

        com = (Button)findViewById(R.id.button6);


    }
    }
