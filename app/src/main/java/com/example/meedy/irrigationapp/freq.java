package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;


public class freq extends AppCompatActivity{
    Button bfrag1, bfrag2,bnext, prebtn;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.freq);

        frequency frefrag = new frequency();

        FragmentManager fragmentManager =  getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frfragment,frefrag).commit();

        bfrag1 = (Button)findViewById(R.id.butfrag1);
        bfrag2 = (Button)findViewById(R.id.butfrag2);
        bnext =(Button)findViewById(R.id.button555);
        prebtn =(Button)findViewById(R.id.prevBtn);


        prebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent( freq.this, Preliminary_design.class);
                startActivity(i);

            }
        });



        bnext.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent L = new Intent(freq.this, sys_capacity.class);
                startActivity(L);
            }
        });




        bfrag1.setOnClickListener(new ButtonList());
        bfrag2.setOnClickListener(new ButtonList());
    }

    class  ButtonList implements View.OnClickListener{

        @Override
        public void onClick(View v) {



            FragmentManager fragmentManager =  getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Fragment fragment = new Fragment();

            if(v.getId()==R.id.butfrag1){
                //Toast.makeText(context, "b1 is clicked", Toast.LENGTH_SHORT).show();

                fragment = new frequency();


            }else if(v.getId()==R.id.butfrag2){


                fragment = new GrossDepth();

            }


            fragmentTransaction.replace(R.id.frfragment,fragment).commit();


        }
    }



}
