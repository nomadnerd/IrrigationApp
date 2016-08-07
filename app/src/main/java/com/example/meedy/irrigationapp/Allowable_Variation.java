package com.example.meedy.irrigationapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by meedy on 8/7/2016.
 */
public class Allowable_Variation extends AppCompatActivity{

    TextView ha, hm, delta;
    Button comp;
    MySingleton mySingleton = MySingleton.getInstance();

    @Override

    public void onCreate(Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
        setContentView(R.layout.allowallable_variation);

        ha = (TextView)findViewById(R.id.ha);
        hm = (TextView)findViewById(R.id.Hm);
        delta = (TextView)findViewById(R.id.delta);

        comp = (Button)findViewById(R.id.compbtn);

        ha.setText(Float.toString(mySingleton.head));
        hm.setText(Float.toString(mySingleton.miniHead));

        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float deltaHs = (float) (2.5*(mySingleton.head-mySingleton.miniHead));
                delta.setText(Float.toString(deltaHs));


            }
        });

    }

}
