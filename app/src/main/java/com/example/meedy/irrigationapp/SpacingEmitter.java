package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SpacingEmitter extends AppCompatActivity {

    TextView ES, NE, ems,answer;
    Button compbtn, next, previous;
    EditText distancrR;
    MySingleton mySingleton = MySingleton.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spacing_emitters);


        ES = (TextView)findViewById(R.id.Espacing);
        NE = (TextView)findViewById(R.id.NumOf);
        ems = (TextView)findViewById(R.id.ems);
        answer =(TextView)findViewById(R.id.answerSpacing);
        distancrR =(EditText)findViewById(R.id.distanceRow);
        compbtn =(Button)findViewById(R.id.compspacing);
        next = (Button)findViewById(R.id.nxt);
        previous =(Button)findViewById(R.id.pre);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent k = new Intent(SpacingEmitter.this, Adjusted_Pw.class);
                startActivity(k);

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent k = new Intent(SpacingEmitter.this, EmittersPerPlant.class);
                startActivity(k);
            }
        });

        NE.setText(Float.toString(mySingleton.EmitterPerPlant));

        compbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float dis = Float.parseFloat(distancrR.getText().toString());
                float EmitterSpacing = dis/mySingleton.EmitterPerPlant;
                answer.setText(Float.toString(EmitterSpacing));
                mySingleton.Spacing =EmitterSpacing;


            }
        });





    }
}
