package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by meedy on 7/21/2016.
 */
public class Manuals_pdf extends AppCompatActivity{

    TextView sm;
    Button sprinklerm, dripm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_pdf);


        sm = (TextView)findViewById(R.id.sm);
        sprinklerm = (Button)findViewById(R.id.sprinkbtn);
        dripm =(Button)findViewById(R.id.dripbtn);


        dripm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.fao.org/3/a-ai598e.pdf"));


                if(intent.resolveActivity(getPackageManager())!=null){
                    startActivity(intent);
                }
            }
        });

        sprinklerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.fao.org/3/a-ai598e.pdf"));


                if(intent.resolveActivity(getPackageManager())!=null){
                    startActivity(intent);
                }

            }
        });

    }


}

