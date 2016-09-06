package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by meedy on 9/6/2016.
 */
public class About extends AppCompatActivity {

    TextView about, body;
    Button back;
    @Override

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abouti);


        about = (TextView)findViewById(R.id.about);
        body = (TextView)findViewById(R.id.body);

        back = (Button)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent J = new Intent(About.this, Home.class);
                startActivity(J);
            }
        });




    }

}
