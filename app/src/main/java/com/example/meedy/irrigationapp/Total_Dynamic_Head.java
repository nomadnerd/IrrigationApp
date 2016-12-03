package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by meedy on 11/15/2016.
 */
public class Total_Dynamic_Head extends AppCompatActivity {


    TextView main, manifold, lateral,opressure, subtotal, tdh,fitting;
    EditText suctions, control, diff_elevation;
    Button comp1, comp2, next, previous;

    MySingleton mySingleton = MySingleton.getInstance();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_dynamic_head);

        main = (TextView)findViewById(R.id.headmain);
        manifold = (TextView)findViewById(R.id.headmanifold);
        lateral = (TextView)findViewById(R.id.headlateral);

        opressure = (TextView)findViewById(R.id.pressure);
        subtotal = (TextView)findViewById(R.id.subtotal);
        tdh = (TextView)findViewById(R.id.tdh);
        fitting = (TextView)findViewById(R.id.fitting);

        suctions = (EditText)findViewById(R.id.suctionlift);
        control = (EditText)findViewById(R.id.controlhead);
        diff_elevation = (EditText)findViewById(R.id.diff_ele);



        comp1 = (Button)findViewById(R.id.compbutton);
        comp2 = (Button)findViewById(R.id.computeBtn);
        next = (Button)findViewById(R.id.NxtBtn);
        previous = (Button)findViewById(R.id.preBtn);



        float lathead = (float) (0.4*mySingleton.allowableVariation);
        mySingleton.lthead = lathead;

        lateral.setText(Float.toString(lathead));

        //setting range for manifold and mainline

        float formanifold = (float) (0.4*mySingleton.allowableVariation);
        mySingleton.manifoldHead=formanifold;
        manifold.setText(Float.toString(formanifold));

        float formainline = (float) (0.2*mySingleton.allowableVariation);
        mySingleton.mainlinedHead=formainline;
        main.setText(Float.toString(formainline));



        opressure.setText(Float.toString(mySingleton.head));







        comp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float suct = Float.parseFloat(suctions.getText().toString());
                float cont = Float.parseFloat(suctions.getText().toString());

                float answer = suct + cont + mySingleton.head + mySingleton.mainlinedHead + mySingleton.manifoldHead;
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
                mySingleton.total_dynaic_head = dynamichead;


            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent J = new Intent(Total_Dynamic_Head.this, pump.class);
                startActivity(J);

            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent J = new Intent(Total_Dynamic_Head.this, Mainline.class);
                startActivity(J);


            }
        });




    }
}