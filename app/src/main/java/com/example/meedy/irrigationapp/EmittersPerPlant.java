package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class EmittersPerPlant extends AppCompatActivity {

    TextView NoEpp, AsnE;
    EditText Sp,Sr, Pw, Aw;
    Button computebtn, prebtn, bnext;
    MySingleton mySingleton = MySingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emitter_per_plant);

        NoEpp = (TextView)findViewById(R.id.emtrs);
        AsnE =(TextView)findViewById(R.id.ansEmitters);
        Sr = (EditText)findViewById(R.id.areaperplant);
        Pw =(EditText)findViewById(R.id.Pw);
        Aw = (EditText)findViewById(R.id.Aw);
        computebtn = (Button)findViewById(R.id.Compemitter);
        prebtn =(Button)findViewById(R.id.prevBtn);
        bnext = (Button)findViewById(R.id.bnxt);


        computebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {


                    float rowspacing = Float.parseFloat(Sr.getText().toString());
                    float plantspacing = Float.parseFloat(Sp.getText().toString());
                    float percentage = Float.parseFloat(Pw.getText().toString());
                    float wettedArea = Float.parseFloat(Aw.getText().toString());

                    float answer = (rowspacing*plantspacing * percentage) / (100 * wettedArea);
                    answer = (new Float(Math.ceil(answer)));
                    mySingleton.EmitterPerPlant = answer;
                    AsnE.setText(Float.toString(answer));
                    mySingleton.percent = percentage;

                } catch (Exception e){

                    Toast.makeText(EmittersPerPlant.this, "Fill the field(s) above", Toast.LENGTH_SHORT).show();
                }
            }
        });

        prebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent L = new Intent(EmittersPerPlant.this, irr_requirement_drip.class);
                startActivity(L);
            }
        });

        bnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent N = new Intent(EmittersPerPlant.this, SpacingEmitter.class);
                startActivity(N);
            }
        });



    }
}
