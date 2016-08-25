package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by meedy on 8/7/2016.
 */
public class Allowable_Variation extends AppCompatActivity{

    TextView ha, hm, delta, plants,emitter,vol;
    Button comp, pre ,bnext;
    MySingleton mySingleton = MySingleton.getInstance();


    @Override

    public void onCreate(Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
        setContentView(R.layout.allowallable_variation);

        ha = (TextView)findViewById(R.id.ha);
        hm = (TextView)findViewById(R.id.Hm);
        delta = (TextView)findViewById(R.id.delta);
        plants = (TextView)findViewById(R.id.plants);
        emitter = (TextView)findViewById(R.id.emitters);
        vol = (TextView)findViewById(R.id.vol);



        comp = (Button)findViewById(R.id.compbtn);
        bnext = (Button)findViewById(R.id.btnLoss);
        pre = (Button)findViewById(R.id.prevBtn);

        ha.setText(Float.toString(mySingleton.head));
        hm.setText(Float.toString(mySingleton.miniHead));


        plants.setText(Float.toString(mySingleton.length/mySingleton.Sp));
        emitter.setText(Float.toString((mySingleton.length/mySingleton.Sp)*mySingleton.newEmitterSpacing));
        vol.setText(Float.toString((mySingleton.length/mySingleton.Sp)*mySingleton.newEmitterSpacing*mySingleton.adj_lph));

        mySingleton.volume = ((mySingleton.length/mySingleton.Sp)*mySingleton.newEmitterSpacing*mySingleton.adj_lph);
        mySingleton.outlet = (int) ((mySingleton.length/mySingleton.Sp)*mySingleton.newEmitterSpacing);

        Log.v("first outlrt",mySingleton.outlet+ "" );
        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float deltaHs = (float) (2.5 * (mySingleton.head - mySingleton.miniHead));
                delta.setText(Float.toString(deltaHs));
                mySingleton.allowableVariation = deltaHs;


            }
        });

        bnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent J = new Intent(Allowable_Variation.this, LateralDiameter2.class);
                startActivity(J);



            }
        });

        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent J = new Intent(Allowable_Variation.this, PressureVariation.class);
                startActivity(J);

            }
        });

    }

}
