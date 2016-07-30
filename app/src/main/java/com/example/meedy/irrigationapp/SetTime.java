package com.example.meedy.irrigationapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by meedy on 7/30/2016.
 */
public class SetTime extends AppCompatActivity {
    TextView gross, numofemitters, time;
    EditText discharge;
    Button comp;

    MySingleton mySingleton = MySingleton.getInstance();

    @Override

    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.set_time);

        gross = (TextView)findViewById(R.id.grossy);
        numofemitters =(TextView)findViewById(R.id.emitters);
        time =(TextView)findViewById(R.id.time);

        discharge =(EditText)findViewById(R.id.discharge);
        comp = (Button)findViewById(R.id.compbtn);


        gross.setText(Float.toString(mySingleton.grossdepth * mySingleton.Sr * mySingleton.Sp));
        numofemitters.setText(Float.toString(mySingleton.EmitterPerPlant));

        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float Qdisc = Float.parseFloat(discharge.getText().toString());
                float ans = (mySingleton.grossdepth * mySingleton.Sr * mySingleton.Sp)/(Qdisc*mySingleton.EmitterPerPlant);
                ans = (new Float(Math.round(ans)));
                time.setText(Float.toString(ans));
            }
        });




    }
}
