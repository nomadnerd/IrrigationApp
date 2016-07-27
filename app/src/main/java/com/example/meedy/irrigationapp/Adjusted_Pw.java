package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;





public class Adjusted_Pw extends AppCompatActivity {

    TextView adj_pw, answer;
    TextView Wettedwidth;
    TextView numofemitters;
    TextView emspacing;
    EditText wettedArea, Sp, Sr;
    Button asnwerbtn, bnext, prev;
    float pi = (float) 3.142;

    MySingleton mySingleton = MySingleton.getInstance();


    @Override
    protected void onCreate (Bundle saveInstanceStae){
        super.onCreate(saveInstanceStae);
        setContentView(R.layout.adjusted_pw);

        adj_pw = (TextView)findViewById(R.id.adjusted);
        Wettedwidth = (TextView)findViewById(R.id.wwidth);
        numofemitters = (TextView)findViewById(R.id.SP);
        emspacing =(TextView)findViewById(R.id.SR);
        answer =(TextView)findViewById(R.id.percent);
        asnwerbtn =(Button)findViewById(R.id.adjbtn);
        bnext =(Button)findViewById(R.id.bnext);
        prev =(Button)findViewById(R.id.previous);

        wettedArea =(EditText)findViewById(R.id.wettedarea);
        Sp = (EditText)findViewById(R.id.distance);
        Sr = (EditText)findViewById(R.id.rowdistance);

        numofemitters.setText(Float.toString(mySingleton.EmitterPerPlant));
        emspacing.setText(Float.toString(mySingleton.Spacing));



        wettedArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                try {
                    float ww = Float.parseFloat(wettedArea.getText().toString());

                    float answer = (float) Math.sqrt(4 * ww / pi);
                    Wettedwidth.setText(Float.toString(answer));
                    mySingleton.Wetted_Width = answer;


                } catch (NumberFormatException e) {
                }
            }
        });


        asnwerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float dis = Float.parseFloat(Sp.getText().toString());
                float rowdistance = Float.parseFloat(Sr.getText().toString());

                float ans = (100 * mySingleton.EmitterPerPlant * mySingleton.Spacing * mySingleton.Wetted_Width) / (dis * rowdistance);
                answer.setText(Float.toString(ans));
                mySingleton.Pw = ans;
                mySingleton.Sp = dis;
                mySingleton.Sr = rowdistance;
            }
        });


        bnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent V = new Intent(Adjusted_Pw.this, drip_irrFrequency.class);
                startActivity(V);
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent V = new Intent(Adjusted_Pw.this, SpacingEmitter.class);
                startActivity(V);

            }
        });

    }
}
