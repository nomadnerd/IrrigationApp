package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by meedy on 6/1/2017.
 */

public class Pump_sprinkler extends AppCompatActivity {

    TextView discharge, tdh, power;
    EditText derating, eff;
    Button nxt, pre, comp;
    MySingleton mySingleton = MySingleton.getInstance();

    @Override

    public void onCreate(Bundle saveInstanceState) {


        super.onCreate(saveInstanceState);
        setContentView(R.layout.pump);

        discharge = (TextView) findViewById(R.id.vol_main);
        tdh = (TextView) findViewById(R.id.TDH);
        power = (TextView) findViewById(R.id.power);

        derating = (EditText) findViewById(R.id.derate);
        eff = (EditText) findViewById(R.id.PowEFF);

        nxt = (Button) findViewById(R.id.buttonNXT);
        pre = (Button) findViewById(R.id.buttonPRE);
        comp = (Button) findViewById(R.id.compBtn);


        discharge.setText(Float.toString(mySingleton.sp_final_volume));
        tdh.setText(Float.toString(mySingleton.sprinkler_tdh));


        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float derateFct = Float.parseFloat(derating.getText().toString());

                float POWEReff = Float.parseFloat(eff.getText().toString());

                float powerReq = ((mySingleton.sp_final_volume * mySingleton.sprinkler_tdh) / (360 * (POWEReff / 100)) * (1 + (derateFct / 100)));
                power.setText(Float.toString(powerReq));


            }
        });

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               /* Intent J = new Intent(pump.this, SetTime.class);
                startActivity(J);*/

            }
        });

        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent J = new Intent(Pump_sprinkler.this, TDH_Sprinkler.class);
                startActivity(J);

            }
        });

    }
}