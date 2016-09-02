package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 8000;

    @Override
    public void onCreate (Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
        setContentView(R.layout.splashscreen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, Home.class);
                startActivity(i);

                finish();
            }
        },SPLASH_TIME_OUT);


    }
}
