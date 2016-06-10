package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class  Preliminary_design extends AppCompatActivity {

Button bfrag1,bfrag2, bnext;
    @Override
    protected void onCreate(Bundle savedInstanceState){

    super.onCreate(savedInstanceState);
        setContentView(R.layout.preliminary_design);

        Pdesignfragment1 frag = new Pdesignfragment1();
        FragmentManager fragmentManager =  getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.pfragment1,frag).commit();




        bfrag1 = (Button)findViewById(R.id.butfrag1);
        bfrag2 = (Button)findViewById(R.id.butfrag2);
        bnext =(Button)findViewById(R.id.bt5);

        bnext.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent J = new Intent(Preliminary_design.this, freq.class);
                startActivity(J);
            }
        });





        bfrag1.setOnClickListener(new ButtonList());
        bfrag2.setOnClickListener(new ButtonList());





    }

    class  ButtonList implements View.OnClickListener{


        //Context context = getApplicationContext();
        @Override
        public void onClick(View v) {
            FragmentManager fragmentManager =  getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Fragment fragment = new Fragment();

            if(v.getId()==R.id.butfrag1){
                //Toast.makeText(context, "b1 is clicked", Toast.LENGTH_SHORT).show();

                    fragment = new Pdesignfragment1();


            }else if(v.getId()==R.id.butfrag2){


                    fragment = new Pdesignfragment2();

            }


            fragmentTransaction.replace(R.id.pfragment1,fragment).commit();


        }


        }

    }








