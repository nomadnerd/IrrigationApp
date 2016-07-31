package com.example.meedy.irrigationapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class adjusting_Ta extends AppCompatActivity{

    TextView gr2, qAns;
    EditText emt, time;
    Button comp;

    MySingleton mySingleton = MySingleton.getInstance();

    @Override

    public void onCreate(Bundle saveInstanceState){


        super.onCreate(saveInstanceState);
        setContentView(R.layout.adjusting_ta);

        gr2 = (TextView)findViewById(R.id.grossy);
        qAns = (TextView)findViewById(R.id.time);
        emt = (EditText)findViewById(R.id.NoEm);
        time = (EditText)findViewById(R.id.discharge);
        comp = (Button)findViewById(R.id.compbtn);

        gr2.setText(Float.toString(mySingleton.grossdepth * mySingleton.Sr * mySingleton.Sp));


        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    float emitters = Float.parseFloat(emt.getText().toString());
                    float tim = Float.parseFloat(time.getText().toString());

                    float ans = mySingleton.grossdepth * mySingleton.Sr * mySingleton.Sp / (emitters * tim);

                    qAns.setText(Float.toString(ans));

                }catch (Exception e){

                }

            }
        });



    }
}
