package com.example.meedy.irrigationapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by meedy on 7/31/2016.
 */
public class adjusting_pw extends AppCompatActivity {

    TextView wwidth, percent;
    EditText emsapcing,numEmitters;
    Button comp;
    MySingleton mySingleton = MySingleton.getInstance();

    @Override
    public void onCreate (Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
        setContentView(R.layout.adjusting_pw);

        wwidth = (TextView)findViewById(R.id.wwidth);
        percent = (TextView)findViewById(R.id.percent);

        emsapcing = (EditText)findViewById(R.id.specing);
        numEmitters = (EditText)findViewById(R.id.ems);
        comp = (Button)findViewById(R.id.Combutton);

        wwidth.setText(Float.toString((float) (mySingleton.Wetted_Width*0.80)));


    comp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            float spac =Float.parseFloat(emsapcing.getText().toString());
            float num = Float.parseFloat(numEmitters.getText().toString());

            float ans = (float) (100*spac*num*mySingleton.Wetted_Width*0.8/(mySingleton.Sp*mySingleton.Sr));
            ans = new Float(Math.round(ans));
            percent.setText(Float.toString(ans));
            mySingleton.newPw = ans;
        }
    });

    }
}
