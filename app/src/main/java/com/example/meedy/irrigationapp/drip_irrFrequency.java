package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class drip_irrFrequency extends AppCompatActivity {


    TextView freq, Ram, net, freasnwer, days;
    EditText AvailableMoisture, depfactor;
    Button comp, bnext, prev;
    MySingleton mySingleton = MySingleton.getInstance();
    drip_frequency_formula drip_freq = new drip_frequency_formula();

@Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.drip_irrfrequency);

    freq = (TextView)findViewById(R.id.freq);
    Ram = (TextView)findViewById(R.id.AvailableMoisture);
    days =(TextView)findViewById(R.id.days);
    freasnwer =(TextView)findViewById(R.id.ansFreq);
    net =(TextView)findViewById(R.id.netpertree);
    comp =(Button)findViewById(R.id.compbtn);
    bnext =(Button)findViewById(R.id.bnext);
    prev=(Button)findViewById(R.id.pre);

    AvailableMoisture =(EditText)findViewById(R.id.AM);
    depfactor = (EditText)findViewById(R.id.depletion);



    comp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            try {

                if (drip_freq.freq_drip() > 0) {

                    float ans = (drip_freq.freq_drip()) / (mySingleton.netdepth_drip * mySingleton.Sp * mySingleton.Sr);

                    ans = new Float(Math.floor(ans));
                    Log.d("check", ans + "");
                    freasnwer.setText(Float.toString(ans));
                    mySingleton.frequency = ans;
                } else {

                }
            }catch (Exception e){
                Toast.makeText(drip_irrFrequency.this, "Fill the field(s) above", Toast.LENGTH_SHORT).show();
            }

        }
    });




    net.setText(Float.toString((mySingleton.netdepth_drip) * (mySingleton.Sp * mySingleton.Sr)));


    AvailableMoisture.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


            try {


                float AvailableM = Float.parseFloat(AvailableMoisture.getText().toString());
                drip_freq.setAvailablemoisture(AvailableM);
                //Ram.setText(Float.toString(drip_freq.getAvailablemoisture()));

                if (depfactor.getText().toString().matches("")) {
                    Log.d("answer 2", Float.toString(drip_freq.getAvailablemoisture()));
                    Ram.setText("0.0");
                } else {
                    Ram.setText(Float.toString(drip_freq.freq_drip()));
                }


            } catch (NumberFormatException e) {
            }
        }

        @Override
        public void afterTextChanged(Editable s) {


        }
    });

    depfactor.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


        }

        @Override
        public void afterTextChanged(Editable s) {

            //Log.d("deleting" , Float.parseFloat(depfactor.getText().toString())*2 +"ye");

            try {


                float factor = Float.parseFloat(depfactor.getText().toString());
                if (AvailableMoisture.getText().toString().matches("")) {
                    factor = 0;
                }

                drip_freq.setDfactor(factor/100);

                if (depfactor.getText().toString().matches("")) {
                    Ram.setText("0.0");
                } else {
                    Ram.setText(Float.toString(drip_freq.freq_drip()));
                }
                //float answer = factor;
                //mySingleton.factor = answer;
            } catch (NumberFormatException e) {

            }


        }
    });



    bnext.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent J = new Intent(drip_irrFrequency.this, SetTime.class);
            startActivity(J);

        }
    });
    prev.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent J = new Intent(drip_irrFrequency.this, Adjusted_Pw.class);
            startActivity(J);

        }
    });



    }



}

