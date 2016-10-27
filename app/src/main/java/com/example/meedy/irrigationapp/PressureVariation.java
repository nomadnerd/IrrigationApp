package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class PressureVariation extends AppCompatActivity {
    TextView qa,emitters, hm, qm, qa2, qm2;
    EditText eu,cv,exp,Ha;
    Button comp1, comp2, pre, next;

    MySingleton mySingleton = MySingleton.getInstance();


    @Override
    public void onCreate(Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
        setContentView(R.layout.pressure_variation);


        qa = (TextView)findViewById(R.id.qac);
        emitters = (TextView)findViewById(R.id.NoEm);
        hm = (TextView)findViewById(R.id.Hm);
        qm = (TextView)findViewById(R.id.qm);
        qa2 = (TextView)findViewById(R.id.textView43);
        qm2 = (TextView)findViewById(R.id.qmi);

        eu = (EditText)findViewById(R.id.EU);
        cv = (EditText)findViewById(R.id.cv);
        exp = (EditText)findViewById(R.id.X);
        Ha = (EditText)findViewById(R.id.head);

        comp1 = (Button)findViewById(R.id.compbtn);
        comp2 = (Button)findViewById(R.id.Combutton2);
        next = (Button)findViewById(R.id.bnext);
        pre = (Button)findViewById(R.id.pre);

        qa.setText(Float.toString(mySingleton.adj_lph));
        qa2.setText(Float.toString(mySingleton.adj_lph));



        emitters.setText(Float.toString(mySingleton.new_num_Emitter));



        comp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {


                    float uniform = Float.parseFloat(eu.getText().toString());
                    float variation = Float.parseFloat(cv.getText().toString());

                    float minDis = (float) ((uniform * mySingleton.adj_lph) / ((100 * (1 - (1.27 * variation/ (Math.sqrt(mySingleton.new_num_Emitter)) )))));

                    Log.d("minDis", "" + minDis);

                    mySingleton.miniDischarge = minDis;

                    qm.setText(Float.toString(mySingleton.miniDischarge));


                    qm2.setText(Float.toString(mySingleton.miniDischarge));

                } catch (Exception e) {
                    Toast.makeText(PressureVariation.this, "Fill the field above", Toast.LENGTH_SHORT).show();
                }
            }
        });



        comp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    float expo = Float.parseFloat(exp.getText().toString());
                    float head = Float.parseFloat(Ha.getText().toString());
                    mySingleton.head = head;


                    if(mySingleton.setTime1<11.5) {

                        float minHead = (float) (head * Math.pow((mySingleton.miniDischarge / mySingleton.adj_lph), 1 / expo));
                        hm.setText(Float.toString(minHead));

                        mySingleton.miniHead = minHead;
                    }else {

                        float newOperatingPressure = (float) (head*Math.pow((mySingleton.adj_lph/mySingleton.intial_discharge), 1/expo));

                        mySingleton.newHead = newOperatingPressure;

                        float minHead = (float) (newOperatingPressure * Math.pow((mySingleton.miniDischarge / mySingleton.adj_lph), 1 / expo));
                        hm.setText(Float.toString(minHead));

                        mySingleton.miniHead = minHead;


                    }

                }catch (Exception e){

                    Toast.makeText(PressureVariation.this, "Fill the field above", Toast.LENGTH_SHORT).show();
                }
            }
        });

        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent J = new Intent(PressureVariation.this, SetTime.class);
                startActivity(J);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent J = new Intent(PressureVariation.this, Allowable_Variation.class);
                startActivity(J);

            }
        });




    }
}
