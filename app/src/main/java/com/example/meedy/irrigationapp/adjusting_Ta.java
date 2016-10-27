package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class adjusting_Ta extends AppCompatActivity{

    TextView gross2, Adjusted_discharge;
    EditText emt, time;
    Button comp, pre, next;

    MySingleton mySingleton = MySingleton.getInstance();

    @Override

    public void onCreate(Bundle saveInstanceState){


        super.onCreate(saveInstanceState);
        setContentView(R.layout.adjusting_ta);

        gross2 = (TextView)findViewById(R.id.grossy);
        Adjusted_discharge = (TextView)findViewById(R.id.time);
        emt = (EditText)findViewById(R.id.NoEm);
        time = (EditText)findViewById(R.id.discharge);
        comp = (Button)findViewById(R.id.compbtn);
        pre = (Button)findViewById(R.id.pre);
        next = (Button)findViewById(R.id.next);


        gross2.setText(Float.toString(mySingleton.grossdepth * mySingleton.Sr * mySingleton.Sp*mySingleton.frequency));


        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    float emitters = Float.parseFloat(emt.getText().toString());
                    float tim = Float.parseFloat(time.getText().toString());

                    float ans = (mySingleton.grossdepth * mySingleton.Sr * mySingleton.Sp*mySingleton.frequency )/ (emitters * tim);

                    Adjusted_discharge.setText(Float.toString(ans));

                    mySingleton.adj_lph = ans;

                } catch (Exception e) {

                }

            }
        });

        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent J = new Intent(adjusting_Ta.this, irr_requirement_drip.class);
                startActivity(J);
            }
        });

    next.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent J = new Intent(adjusting_Ta.this, PressureVariation.class);
            startActivity(J);
        }
    });

    }
}
