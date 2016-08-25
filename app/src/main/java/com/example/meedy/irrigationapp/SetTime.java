package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SetTime extends AppCompatActivity {
    TextView gross,time;
    EditText discharge, numofemitters;
    Button comp, adjTa, pre, next;

    MySingleton mySingleton = MySingleton.getInstance();

    @Override

    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.set_time);

        gross = (TextView)findViewById(R.id.grossy);
        numofemitters =(EditText)findViewById(R.id.NoEm);
        time =(TextView)findViewById(R.id.time);

        discharge =(EditText)findViewById(R.id.discharge);
        comp = (Button)findViewById(R.id.compbtn);
        adjTa = (Button)findViewById(R.id.ta);
        pre = (Button)findViewById(R.id.Prebutton);
        next = (Button)findViewById(R.id.Nxtbutton);


        adjTa.setVisibility(View.INVISIBLE);


        gross.setText(Float.toString(mySingleton.grossdepth * mySingleton.Sr * mySingleton.Sp));

        //numofemitters.setText(Float.toString(mySingleton.EmitterPerPlant));

        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    float Qdisc = Float.parseFloat(discharge.getText().toString());
                    float num = Float.parseFloat(numofemitters.getText().toString());
                    float ans = (mySingleton.grossdepth * mySingleton.Sr * mySingleton.Sp) / (Qdisc * num);
                    //ans = (new Float(Math.round(ans)));
                    time.setText(Float.toString(ans));

                 if(ans>=11.0){

                     adjTa.setVisibility(View.VISIBLE);
                 }


                } catch (Exception e){

                    Toast.makeText(SetTime.this, "Fill the field(s) above", Toast.LENGTH_SHORT).show();
                }


            }
        });

        adjTa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent J = new Intent(SetTime.this, adjusting_Ta.class);
                startActivity(J);
            }
        });



        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent J = new Intent(SetTime.this, drip_irrFrequency.class);
                startActivity(J);

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(SetTime.this,PressureVariation.class);
                startActivity(k);
            }
        });



    }
}
