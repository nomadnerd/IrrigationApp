package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class sys_capacity extends AppCompatActivity {

    EditText   irrc, sh, tim;
    TextView sys,dg,area, vol, answr;
    Button com, nexbtn, prevbtn;
    MySingleton mySingleton = MySingleton.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_capacity);

        area = (TextView)findViewById(R.id.area);
        vol = (TextView)findViewById(R.id.volume);
        answr = (TextView)findViewById(R.id.AnsVol);

        dg = (TextView)findViewById(R.id.dgross);
        irrc = (EditText)findViewById(R.id.irrcycle);
        sh = (EditText)findViewById(R.id.shift);
        tim = (EditText)findViewById(R.id.time);
        sys =(TextView)findViewById(R.id.sys);

        com = (Button)findViewById(R.id.comp);
        nexbtn = (Button)findViewById(R.id.bnext_sys);
        prevbtn = (Button)findViewById(R.id.PREBTN);

        area.setText(Float.toString(mySingleton.field_area));
        dg.setText(Float.toString(mySingleton.grossdepth));


        com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    float gross = Float.parseFloat(dg.getText().toString());
                    float cycle = Float.parseFloat(irrc.getText().toString());
                    float shift = Float.parseFloat(sh.getText().toString());
                    float time = Float.parseFloat(tim.getText().toString());


                /*form_sysCapac systemcapacity = new form_sysCapac();
                systemcapacity.setDgross(gross);
                systemcapacity.setIrrcycle(cycle);
                systemcapacity.setNumbofshift(shift);
                systemcapacity.setTimepershift(time);*/

                    float answer = (mySingleton.field_area * 10 * gross) / (cycle * shift * time);
                    answr.setText(Float.toString(answer));


                } catch (Exception e) {

                    Toast.makeText(sys_capacity.this, "Fill the field(s) above", Toast.LENGTH_SHORT).show();
                }
            }
        });



        nexbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast = Toast.makeText(sys_capacity.this, "next step is not ready", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            }
        });

        prevbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent( sys_capacity.this, freq.class);
                startActivity(i);

            }
        });

    }
    }
