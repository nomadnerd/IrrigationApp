package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class field_drip extends AppCompatActivity {

    CheckBox Reg, Irr;
    TextView len, wid, area, answe;
    Button nxt, prebtn, compute;
    MySingleton mySingleton = MySingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.field_drip);


        Reg = (CheckBox) findViewById(R.id.CB1);
        Irr = (CheckBox) findViewById(R.id.CB2);
        len = (TextView) findViewById(R.id.len);
        area =(TextView)findViewById(R.id.area);
        answe =(TextView)findViewById(R.id.answer);
        wid = (TextView) findViewById(R.id.wid);
        nxt = (Button) findViewById(R.id.bnxt);
        prebtn =(Button)findViewById(R.id.prevBtn);
        compute =(Button)findViewById(R.id.area_btn);


        compute.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                try {
                    float length = Float.parseFloat(len.getText().toString());
                    float width = Float.parseFloat(wid.getText().toString());

                    mySingleton.length = length;

                    form_FieldArea Area = new form_FieldArea();

                    Area.setLength(length);
                    Area.setWidth(width);

                    float answer = Area.fieldArea();
                    answe.setText(Float.toString(answer));

                    MySingleton mySingleton = MySingleton.getInstance();
                    mySingleton.field_area = answer;
                }catch (Exception e){

                    Toast.makeText(field_drip.this, "Fill the field(s) above", Toast.LENGTH_SHORT).show();

                }
            }
        });


        prebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(field_drip.this, FrontPage.class);
                startActivity(i);
            }
        });



        nxt.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent J = new Intent(field_drip.this, irr_requirement_drip.class);
                startActivity(J);
            }
        });
    }


}
