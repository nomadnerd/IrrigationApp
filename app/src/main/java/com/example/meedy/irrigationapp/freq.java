package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by meedy on 5/3/2016.
 */
public class freq extends AppCompatActivity{
    TextView wu, dnt, dnt2,eff,irr;
    EditText ed1, ed2,ed3;
    Button comp, ns;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.freq);

        irr = (TextView) findViewById(R.id.textView3);
        dnt = (TextView) findViewById(R.id.textView4);
        wu = (TextView) findViewById(R.id.textView10);
        eff = (TextView) findViewById(R.id.textView9);
        dnt2 = (TextView) findViewById(R.id.textView11);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        comp = (Button) findViewById(R.id.buComp);
        ns = (Button) findViewById(R.id.button5);


        comp.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

            }
        });
        ns.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent J = new Intent(freq.this, sys_capacity.class);
                startActivity(J);
            }
        });
    }





}
