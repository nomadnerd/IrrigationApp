package com.example.meedy.irrigationapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;




public class LateralDiameter2 extends AppCompatActivity{

    EditText Dia;
    TableLayout ftable;
    Button submit;
    MySingleton mySingleton = MySingleton.getInstance();

    @Override

    public void onCreate(Bundle savedInstanceState){


        super.onCreate(savedInstanceState);
        setContentView(R.layout.lateral_diameter);


        Dia = (EditText)findViewById(R.id.diameter);
        ftable = (TableLayout)findViewById( R.id.ftable);
        submit = (Button)findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mystring = Dia.getText().toString();
                int size = mystring.split("\\,", -1).length;
                float [] diameter = new float[size];

                float [] ArrayHf = new float[size];
                int i =0;

                for (String item: mystring.split("\\,", -1)){


                     if(i<size) {
                         diameter[i] = Float.parseFloat(item);


                         ArrayHf[i] = HeadLoss_HF(mySingleton.volume, mySingleton.length, diameter[i]);

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
        row.setLayoutParams(lp);

        TextView Dia = new TextView(this);
        TextView Hf = new TextView(this);
        TextView Fn = new TextView(this);
        TextView ActualHf = new TextView(this);
        TextView Remark = new TextView(this);

        //This generates the caption row
        Dia.setText("Diameter");
        Dia.setPadding(3, 3, 3, 3);
        row.addView(Dia);

        Hf.setText("Hf");
        Hf.setPadding(3, 3, 3, 3);
        row.addView(Hf);

        Fn.setText("Fn");
        Fn.setPadding(3, 3, 3, 3);
        row.addView(Fn);

        ActualHf.setText("Hfactual");
        ActualHf.setPadding(3, 3, 3, 3);
        row.addView(ActualHf);

        Remark.setText("Remark");
        Dia.setPadding(3, 3, 3, 3);
        row.addView(Remark);

        Tl.addView(row,0);



        for (int i =0; i<diameter.length; i++) {

            TableRow row2= new TableRow(this);
            TableRow.LayoutParams  lp2 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row2.setLayoutParams(lp2);


            TextView dia = new TextView(this);
            dia.setText(Float.toString(diameter[i]));
            row2.addView(dia);


            TextView hf = new TextView(this);
            float Head = Ahf[i];
            hf.setText(Float.toString(Head));
            row2.addView(hf);


            TextView Fn2 = new TextView(this);
            float factor =ChristiansenF(mySingleton.outlet);
            Fn2.setText(Float.toString(factor));
            row2.addView(Fn2);

            TextView ActualHf2 = new TextView(this);
            float Act_Hf = factor*Head;
            ActualHf2.setText(Float.toString(Act_Hf));
            row2.addView(ActualHf2);

            TextView Remark2 = new TextView(this);

            String remarks;
            if(Act_Hf>mySingleton.allowableVariation){
                remarks = "Reject";

                Remark2.setText(remarks);
                row2.addView(Remark2);
            }else if (Act_Hf<mySingleton.allowableVariation){

                 remarks = "Accept";
                Remark2.setText(remarks);
                row2.addView(Remark2);

            }else{

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
        float Fn = a+e+(float)b;
        return  Fn;


    }

    public  float HeadLoss_HF(float volume, float length, float diameter){

        float a= (float) Math.pow((volume/150),1.852);
        float  b = (float) Math.pow(diameter, -4.58);
        float hf = 3163*length*a*b;

        return  hf;

    }

    }





