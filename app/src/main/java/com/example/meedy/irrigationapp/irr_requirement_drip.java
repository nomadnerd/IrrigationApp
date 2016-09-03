package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class irr_requirement_drip extends AppCompatActivity {


    Button bfrag1,bfrag2, bnext, prebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.irr_requirement_drip);

       netdepth_drip frag = new netdepth_drip();
        FragmentManager fragmentManager =  getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.pfragment2,frag).commit();




        bfrag1 = (Button)findViewById(R.id.butfrag1);
        bfrag2 = (Button)findViewById(R.id.butfrag2);
        bnext =(Button)findViewById(R.id.bt5);
        prebtn = (Button)findViewById(R.id.prebtn2);


        prebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(irr_requirement_drip.this, field_drip.class);
                startActivity(k);
            }
        });


        bnext.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent M = new Intent(irr_requirement_drip.this, EmittersPerPlant.class);
                startActivity(M);
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

                fragment = new netdepth_drip();


            }else if(v.getId()==R.id.butfrag2){


                fragment = new grossdepth_drip();

            }


            fragmentTransaction.replace(R.id.pfragment2,fragment).commit();


        }


    }

}



