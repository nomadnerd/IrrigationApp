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

    TextView ha, hm, delta, plants,emitter,vol, flow, system;
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
        flow = (TextView)findViewById(R.id.flow);
        system = (TextView)findViewById(R.id.sys);



        comp = (Button)findViewById(R.id.compbtn);
        bnext = (Button)findViewById(R.id.btnLoss);
        pre = (Button)findViewById(R.id.prevBtn);




        if(mySingleton.setTime1<11.5) {
            ha.setText(Float.toString(mySingleton.head));
        }else {

            ha.setText(Float.toString(mySingleton.newHead));
        }





        hm.setText(Float.toString(mySingleton.miniHead));


        float plant = Math.round(mySingleton.length/mySingleton.Sp);
        plants.setText(Float.toString(plant));

        float emittersperplant = Math.round(mySingleton.length / mySingleton.Sp)*mySingleton.newEmitterSpacing*2;
        emitter.setText(Float.toString(emittersperplant));

        float volume = Math.round(mySingleton.length / mySingleton.Sp)*mySingleton.newEmitterSpacing*mySingleton.adj_lph;

        vol.setText(Float.toString(volume));

         /*Number of laterals*/

        float NL = (mySingleton.length/mySingleton.Sr)*2;
        float flowpermanifold= (NL*emittersperplant*mySingleton.adj_lph)/1000;
        mySingleton.manifoldoutlet=NL;

        flow.setText(Float.toString(flowpermanifold));

        float systemcapacity = flowpermanifold*2;
        system.setText(Float.toString(systemcapacity));
        mySingleton.drip_systemcapacity = systemcapacity;


        mySingleton.volume = ((mySingleton.length/mySingleton.Sp)*mySingleton.newEmitterSpacing*mySingleton.adj_lph);
        mySingleton.outlet = (int) ((mySingleton.length/mySingleton.Sp)*mySingleton.newEmitterSpacing);

        Log.v("first outlrt",mySingleton.outlet+ "" );
        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mySingleton.setTime1 < 11.5) {

                    float deltaHs = (float) (2.5 * (mySingleton.head - mySingleton.miniHead));
                    delta.setText(Float.toString(deltaHs));
                    mySingleton.allowableVariation = deltaHs;


                }else {

                    float deltaHs = (float) (2.5 * (mySingleton.newHead - mySingleton.miniHead));
                    delta.setText(Float.toString(deltaHs));
                    mySingleton.allowableVariation = deltaHs;

                }
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
