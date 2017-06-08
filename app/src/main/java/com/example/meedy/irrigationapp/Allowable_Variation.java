package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by meedy on 8/7/2016.
 */
public class Allowable_Variation extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    TextView ha, hm, delta, plants,emitter,vol, flow, system, laterals;
    Button comp, pre ,bnext;
    String place;
    EditText cycle;
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
        laterals = (TextView)findViewById(R.id.lat);

        cycle =(EditText)findViewById(R.id.cycle);


        comp = (Button)findViewById(R.id.compbtn);
        bnext = (Button)findViewById(R.id.btnLoss);
        pre = (Button)findViewById(R.id.prevBtn);






        if(mySingleton.setTime1<11.5) {
            ha.setText(Float.toString(mySingleton.head));
        }else {

            ha.setText(Float.toString(mySingleton.newHead));
        }


        /*irrigation cycle*/



        hm.setText(Float.toString(mySingleton.miniHead));


        cycle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                try{
                float AssumedCycle = Float.parseFloat(cycle.getText().toString());

                float lateral = Math.round(mySingleton.length/(mySingleton.Sr*AssumedCycle))*2;
                laterals.setText(Float.toString(lateral));

                mySingleton.laterl=lateral;

                mySingleton.MainOutlet= AssumedCycle;




                float volume = mySingleton.emittersperLateral*mySingleton.adj_lph;
                mySingleton.volume = volume;

                float flowpermanifold = (float) (Math.round(((volume*mySingleton.laterl)/1000)*1000)/1000.0);

                flow.setText(Float.toString(flowpermanifold));

                vol.setText(Float.toString(volume));

                float systemcapacity = flowpermanifold;

                system.setText(Float.toString(systemcapacity));
                mySingleton.drip_systemcapacity = systemcapacity;

            }catch (NumberFormatException e){

                }

            }

        });




        float plant = Math.round(mySingleton.width/mySingleton.Sp);
        plants.setText(Float.toString(plant));

        float emittersperLateral = Math.round(mySingleton.width /mySingleton.newEmitterSpacing);
        emitter.setText(Float.toString(emittersperLateral));
        mySingleton.emittersperLateral=emittersperLateral;



         /*Number of laterals*/

        /*float NL = (mySingleton.length/mySingleton.Sr)*2;
        float flowpermanifold= (NL*emittersperLateral*mySingleton.adj_lph)/1000;
        mySingleton.manifoldoutlet=NL;

*/




/*
        mySingleton.volume = ((mySingleton.length/mySingleton.Sp)*mySingleton.newEmitterSpacing*mySingleton.adj_lph);
*/
        mySingleton.outlet = (int) ((mySingleton.length/mySingleton.Sp)*mySingleton.newEmitterSpacing);





        Log.v("first outlrt",mySingleton.outlet+ "" );
        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mySingleton.setTime1 < 11.5) {

                    float deltaHs = (float) (2.5 * (mySingleton.head - mySingleton.miniHead));

                    deltaHs = (new Float(Math.ceil(deltaHs)));

                    delta.setText(Float.toString(deltaHs));
                    mySingleton.allowableVariation = deltaHs;

                    float lathead = (float) (0.4*deltaHs);
                    mySingleton.lthead = lathead;

                    //setting range for manifold and mainline

                    float formanifold = (float) (0.4*deltaHs);
                    mySingleton.manifoldHead=formanifold;

                    float formainline = (float) (0.2*deltaHs);
                    mySingleton.mainlinedHead=formainline;


                }else {

                    float deltaHs = (float) (2.5 * (mySingleton.newHead - mySingleton.miniHead));

                    deltaHs = (new Float(Math.ceil(deltaHs)));
                    delta.setText(Float.toString(deltaHs));
                    mySingleton.allowableVariation = deltaHs;

                    float lathead = (float) (0.4*deltaHs);
                    mySingleton.lthead = lathead;

                    //setting range for manifold and mainline

                    float formanifold = (float) (0.4*deltaHs);
                    mySingleton.manifoldHead=formanifold;

                    float formainline = (float) (0.2*deltaHs);
                    mySingleton.mainlinedHead=formainline;

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



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        place = String.valueOf(Float.parseFloat(item));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
