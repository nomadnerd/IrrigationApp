package com.example.meedy.irrigationapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by meedy on 7/27/2016.
 */
public class drip_irrFrequency extends AppCompatActivity {


    TextView freq, Ram, net, freasnwer, days;
    EditText AvailableMoisture, depfactor;
    Button comp;
    MySingleton mySingleton = MySingleton.getInstance();

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

    AvailableMoisture =(EditText)findViewById(R.id.AM);
    depfactor = (EditText)findViewById(R.id.depletion);

    comp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            float ans = mySingleton.ReadilyMoisture/(mySingleton.netdepth_drip*(mySingleton.Sp*mySingleton.Sr));
            freasnwer.setText(Float.toString(ans));
        }
    });




    net.setText(Float.toString((mySingleton.netdepth_drip) * (mySingleton.Sp * mySingleton.Sr)));


    AvailableMoisture.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            try {


                float AvailableM = Float.parseFloat(AvailableMoisture.getText().toString());
                float answer = (AvailableM * mySingleton.factor*mySingleton.Sp * mySingleton.Sr * mySingleton.Pw) / 100;
                Ram.setText(Float.toString(answer));
                mySingleton.ReadilyMoisture = answer;


            } catch (NumberFormatException e) {
            }
        }
    });

    depfactor.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            try {


                float factor = Float.parseFloat(depfactor.getText().toString());
                float answer = factor;
                mySingleton.factor = answer;
            }catch (NumberFormatException e){

            }

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {




        }

        @Override
        public void afterTextChanged(Editable s) {


        }
    });


    }



}

