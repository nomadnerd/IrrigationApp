package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by meedy on 5/2/2016.
 */
public class MyField extends AppCompatActivity {
    CheckBox Reg, Irr;
    TextView len, wid;
    Button nxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.field);


        Reg = (CheckBox) findViewById(R.id.CB1);
        Irr = (CheckBox) findViewById(R.id.CB2);
        len = (TextView) findViewById(R.id.len);
        wid = (TextView) findViewById(R.id.wid);
        nxt = (Button) findViewById(R.id.bnxt);


        nxt.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent J = new Intent(MyField.this, Preliminary_design.class);
                startActivity(J);
            }
            });
        }
    }