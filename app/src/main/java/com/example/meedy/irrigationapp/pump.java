package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by meedy on 11/16/2016.
 */
public class pump extends AppCompatActivity {

    TextView discharge, tdh,power;
    EditText derating, eff;
    Button nxt, pre, comp;
    MySingleton mySingleton = MySingleton.getInstance();

    @Override

    public void onCreate(Bundle saveInstanceState) {


        super.onCreate(saveInstanceState);
        setContentView(R.layout.pump);

        discharge = (TextView)findViewById(R.id.vol_main);
        tdh = (TextView)findViewById(R.id.TDH);
        power = (TextView)findViewById(R.id.power);

        derating = (EditText)findViewById(R.id.derate);
        eff = (EditText)findViewById(R.id.PowEFF);

        nxt = (Button)findViewById(R.id.buttonNXT);
        pre = (Button)findViewById(R.id.buttonPRE);
        comp = (Button)findViewById(R.id.compBtn);


        discharge.setText(Float.toString(mySingleton.drip_systemcapacity));
        tdh.setText(Float.toString(mySingleton.total_dynaic_head));


        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float derateFct = Float.parseFloat(derating.getText().toString());

                float POWEReff = Float.parseFloat(eff.getText().toString());

                float powerReq = ((mySingleton.drip_systemcapacity*mySingleton.total_dynaic_head)/(360*(POWEReff/100))*(1+(derateFct/100)));
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
                Intent J = new Intent(pump.this, Total_Dynamic_Head.class);
                startActivity(J);

            }
        });




    }
}