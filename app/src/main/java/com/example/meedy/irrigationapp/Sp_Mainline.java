package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by meedy on 6/1/2017.
 */

public class Sp_Mainline extends AppCompatActivity {

    EditText Dia;
    TableLayout ftable;
    Button submit, next,previous;
    MySingleton mySingleton = MySingleton.getInstance();
    TableRow selectedTR;

    @Override

    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.sp_mainline);


        Dia = (EditText) findViewById(R.id.diameter_main);
        ftable = (TableLayout) findViewById(R.id.ftable);
        submit = (Button) findViewById(R.id.submit);
        next = (Button) findViewById(R.id.next);
        previous = (Button) findViewById(R.id.previous);









        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent J = new Intent(Sp_Mainline.this, TDH_Sprinkler.class);
                startActivity(J);
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent J = new Intent(Sp_Mainline.this, Lateral_Selection_Sprinkler.class);
                startActivity(J);
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String mystring = Dia.getText().toString();
                int size = mystring.split("\\,", -1).length;
                float[] diameter = new float[size];

                float[] ArrayHf = new float[size];
                int i = 0;




                for (String item : mystring.split("\\,", -1)) {


                    if (i < size) {
                        diameter[i] = Float.parseFloat(item);


                        float lateral_capacity = (Float.parseFloat(mySingleton.finalFilterSingleton.get(0).get(3))*mySingleton.No_Of_sprinler)*1000;
                        Log.d("vol", Float.toString(lateral_capacity));

                        //Assuming the mainline will pass at the center
                        float lateral_length = mySingleton.sprinklerlength/2;
                        Log.d("vol", Float.toString(lateral_length));

                        ArrayHf[i] = HeadLoss_HF(lateral_capacity*mySingleton.Lateral_Per_Shift, lateral_length, diameter[i]);

                        Log.d("Hf Array", String.valueOf(ArrayHf[0]));
                        Log.d("diameter", String.valueOf(diameter[i]));

                        i++;
                    } else {
                        break;
                    }


                }

               createTable(diameter, ArrayHf);



            }
        });


    }



    public void createTable(float[] diameter, float[] Ahf){

        TableLayout Tl = (TableLayout) findViewById(R.id.ftable);
        TableRow row= new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        lp.setMargins(30,20,30,0);
        row.setLayoutParams(lp);





        final TextView Dia = new TextView(this);
        TextView Hf = new TextView(this);
        TextView Fn = new TextView(this);
        final TextView ActualHf = new TextView(this);
        TextView Remark = new TextView(this);

        //is clickable
        ActualHf.isClickable();
        Dia.isClickable();



        //This generates the caption row
        Dia.setText("Diameter");
        Dia.setPadding(3, 3, 3, 3);
        Dia.setTextColor(Color.parseColor("#424242"));
        row.addView(Dia);

        Hf.setText(" Hf");
        Hf.setPadding(3, 5, 5, 5);
        Hf.setGravity(Gravity.CENTER_VERTICAL);
        Hf.setTextColor(Color.parseColor("#424242"));
        row.addView(Hf);

        Fn.setText(" Fn");
        Fn.setPadding(3, 5, 5, 5);
        Fn.setTextColor(Color.parseColor("#424242"));
        Fn.setGravity(Gravity.CENTER);
        row.addView(Fn);

        ActualHf.setText(" Hfactual");
        ActualHf.setPadding(3, 5, 5, 5);
        ActualHf.setTextColor(Color.parseColor("#424242"));
        ActualHf.setGravity(Gravity.CENTER_HORIZONTAL);
        row.addView(ActualHf);

        Remark.setText(" Remark");
        Remark.setPadding(0,3,3,0);
        Remark.setTextColor(Color.parseColor("#424242"));
        Remark.setGravity(Gravity.LEFT);
        row.addView(Remark);

        Tl.addView(row,0);


        double allowalable_var_sprinkler_lateral = Float.parseFloat(mySingleton.finalFilterSingleton.get(0).get(2))*0.02;


        for (int i =0; i<diameter.length; i++) {

            TableRow row2= new TableRow(this);
            TableRow.LayoutParams  lp2 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            lp2.setMargins(30,20,30,0);
            row2.setLayoutParams(lp2);


            /*View v = new View(this);
            v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
            v.setBackgroundColor(Color.rgb(51, 51, 51));*/



            TextView dia = new TextView(this);
            dia.setText(Float.toString(diameter[i]));
            dia.setTextColor(Color.parseColor("#9E9E9E"));
            row2.addView(dia);


            TextView hf = new TextView(this);
            float Head = Ahf[i];
            hf.setText(Float.toString(Head));
            ActualHf.setPadding(3, 5, 5, 5);
            hf.setTextColor(Color.parseColor("#9E9E9E"));
            hf.setGravity(Gravity.CENTER);
            row2.addView(hf);

            Log.d("Head", Float.toString(Head));


            TextView Fn2 = new TextView(this);
            float factor =ChristiansenF(mySingleton.Lateral_Per_Shift);
            Fn2.setText(Float.toString(factor));
            ActualHf.setPadding(3, 5, 5, 5);
            Fn2.setTextColor(Color.parseColor("#9E9E9E"));
            Fn2.setGravity(Gravity.CENTER);
            row2.addView(Fn2);
            //okay

            Log.d("factor", Float.toString(factor));


            TextView ActualHf2 = new TextView(this);
            float Act_Hf = (float) (Math.round((factor*Head)*100)/100.0);

            ActualHf2.setText(Float.toString(Act_Hf));
            ActualHf.setPadding(3, 5, 5, 5);
            ActualHf2.setTextColor(Color.parseColor("#9E9E9E"));
            ActualHf2.setGravity(Gravity.CENTER);
            row2.addView(ActualHf2);

            ActualHf2.isClickable();

            row2.setBackgroundResource(R.drawable.selector);
            row2.setClickable(true);

            row2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    TableRow row = (TableRow)view;
                    TextView actu_Hf, diam;
                    String string_ActHf, string_Diam;

                    actu_Hf = (TextView) row.getChildAt(3);
                    //diam = (TextView) row.getChildAt(3);

                    string_ActHf = actu_Hf.getText().toString();
                    //string_Diam = diam.getText().toString();
                    selectedTR = (TableRow)view;
                    Log.d("diameeeeee", string_ActHf.toString());

                    mySingleton.main_head_sprinkler = Float.parseFloat(string_ActHf);

                }
            });




            Log.d("actualHf", Float.toString(Act_Hf));

            TextView Remark2 = new TextView(this);
            Remark2.setGravity(Gravity.LEFT);
            Remark.setPadding(0,3,3,0);

            String remarks;
            if(Act_Hf>=allowalable_var_sprinkler_lateral*0.4){
                remarks = "Reject";

                Remark2.setText(remarks);
                Remark2.setTextColor(Color.parseColor("#9E9E9E"));
                row2.addView(Remark2);


            }else if (Act_Hf<=allowalable_var_sprinkler_lateral*0.4){

                remarks = "Accept";
                Remark2.setText(remarks);
                Remark2.setTextColor(Color.parseColor("#9E9E9E"));
                row2.addView(Remark2);

            }else{

                /*remarks = "Over Designed";
                Remark2.setText(remarks);
                Remark2.setTextColor(Color.parseColor("#9E9E9E"));
                row2.addView(Remark2);*/



            }

            Tl.addView(row2, i+1);



        }



    }



    public float ChristiansenF(float outlet){
        float m = 2;
        float a = 1/(m+1);
        double b = 1.00/(2*outlet);
        float c = (float) Math.pow((m-1),0.5);
        float d = (float) (Math.pow(outlet,2)*6);
        float e = c/d;
        float Fn = (float) (Math.round((a + e + (float) b)*1000)/1000.0);

        return  Fn;


    }

    public  float HeadLoss_HF(float volume, float length, float diameter){


        double c = (volume/150);
        Log.d("C", String.valueOf(c));

        float a= (float) Math.pow((c),1.852);
        Log.d("A", String.valueOf(a));
        float  b = (float) Math.pow(diameter, -4.87);
        Log.d("B", String.valueOf(b));

        float hf = (float) (Math.round((3163 * length * a * b)*100)/100.0);

        Log.d("hf", String.valueOf(hf));
        return  hf;

    }
}
