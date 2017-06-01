package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by meedy on 6/1/2017.
 */

public class TDH_Sprinkler extends AppCompatActivity {


    TextView main, manifold, lateral,opressure, subtotal, tdh,fitting, raiser;
    EditText suctions, diff_elevation;
    Button comp1, comp2, next, previous;

    MySingleton mySingleton = MySingleton.getInstance();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tdh_sprinkler);

        main = (TextView)findViewById(R.id.headmain);
        manifold = (TextView)findViewById(R.id.headmanifold);
        lateral = (TextView)findViewById(R.id.headlateral);
        raiser = (TextView) findViewById(R.id.Raiser);

        opressure = (TextView)findViewById(R.id.pressure);
        subtotal = (TextView)findViewById(R.id.subtotal);
        tdh = (TextView)findViewById(R.id.tdh);
        fitting = (TextView)findViewById(R.id.fitting);

        suctions = (EditText)findViewById(R.id.suctionlift);

        diff_elevation = (EditText)findViewById(R.id.diff_ele);



        comp1 = (Button)findViewById(R.id.compbutton);
        comp2 = (Button)findViewById(R.id.computeBtn);
        next = (Button)findViewById(R.id.NxtBtn);
        previous = (Button)findViewById(R.id.preBtn);



        final float lathead = mySingleton.lat_head_sprinkler;
        mySingleton.lthead = lathead;

        lateral.setText(Float.toString(lathead));

        Log.d("laterat Head", Float.toString(mySingleton.lat_head_sprinkler));

        //setting range for manifold and mainline

        final float supplyLine = (float) (((mySingleton.sprinklerwidth/2)+70+3)*0.0035);

        mySingleton.manifoldHead=supplyLine;
        manifold.setText(Float.toString(supplyLine));


        final float Raiser = (float) 2.50;
        raiser.setText(Float.toString(Raiser));




        final float formainline = mySingleton.main_head_sprinkler;
        mySingleton.mainlinedHead=formainline;
        main.setText(Float.toString(formainline));

        Log.d("main Head", Float.toString(mySingleton.main_head_sprinkler));


        final float SOP = (float) (Float.parseFloat(mySingleton.finalFilterSingleton.get(0).get(2))*0.1);

        opressure.setText(Float.toString(SOP));







        comp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float suct = Float.parseFloat(suctions.getText().toString());
                //float cont = Float.parseFloat(suctions.getText().toString());

                float answer = suct + Raiser+ lathead + formainline + supplyLine+SOP;

                subtotal.setText(Float.toString(answer));

                mySingleton.subtotal = answer;

                float fit = (float) (0.1*answer);
                fitting.setText(Float.toString(fit));
                mySingleton.fitting = fit;








            }

        });

        comp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float elevationdiff = Float.parseFloat(diff_elevation.getText().toString());

                float dynamichead = elevationdiff + mySingleton.subtotal + mySingleton.fitting;
                tdh.setText(Float.toString(dynamichead));
                mySingleton.sprinkler_tdh = dynamichead;


            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent J = new Intent(TDH_Sprinkler.this, Pump_sprinkler.class);
                startActivity(J);

            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent J = new Intent(TDH_Sprinkler.this, Sp_Mainline.class);
                startActivity(J);


            }
        });



    }
}


