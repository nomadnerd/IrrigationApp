package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.meedy.irrigationapp.R.layout.set_time;

/**
 * Created by meedy on 4/1/2017.
 */

public class Sprinkler_selection extends AppCompatActivity {
    TableLayout tableLayout;
    TextView dgross;
    EditText Dia;
    Button submit, filterbtn, next, previous;
    sprinklerDB myDb;
    MySingleton mySingleton = MySingleton.getInstance();
    create_table selectSpTable;
    String[] headerText2 = {"NZ SIZE", "PRESSURE", "DISCHARGE", "DIA  ", "RATE  ", "SPACING", "SET TIME"};
    ArrayList<List<String>> FiteredTable;
    ArrayList<List<String>> Table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sprinklerselection);

        tableLayout = (TableLayout) findViewById(R.id.ftable);

        Dia = (EditText) findViewById(R.id.diameter);
        submit = (Button) findViewById(R.id.submit);
        filterbtn = (Button) findViewById(R.id.FilterBtn);
        next = (Button) findViewById(R.id.next);
        previous = (Button) findViewById(R.id.previous);
        dgross = (TextView) findViewById(R.id.dg_numeric);

        myDb = new sprinklerDB(this);

        dgross.setText(Float.toString(mySingleton.grossdepth));

       // filterbtn.setVisibility(View.INVISIBLE);

        /*if (Table.size()>0) {
            filterbtn.setVisibility(View.VISIBLE);
        }*/


            filterbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     //filter();
                    try {



                        FiteredTable = filter();
                        mySingleton.FilteredData = FiteredTable;
                        sprinkler(FiteredTable, headerText2);



                    }catch (Exception e){

                        Toast.makeText(Sprinkler_selection.this, "Fill the field(s) above", Toast.LENGTH_SHORT).show();

                    }
                }
            });

        // setting litsener fr buttons

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 try {


                     float rate = Float.parseFloat(Dia.getText().toString());

                     Table = myDb.SelectRatebyDiameter(rate);

                     //Set_time(mySingleton.grossdepth);
                     //filter();
                     String[] headerText = {"NZ SIZE", "PRESSURE", "DISCHARGE", "DIA  ", "RATE  ", "SPACING"};
                     sprinkler(Table, headerText);

                 }catch (Exception e){

                     Toast.makeText(Sprinkler_selection.this, "Fill the field(s) above", Toast.LENGTH_SHORT).show();

                 }


                //Log.d("Table", Table.toString());



            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String[] headerText = {"NZ SIZE", "PRESSURE", "DISCHARGE", "DIA  ", "RATE  ", "SPACING"};
                    Intent j = new Intent(Sprinkler_selection.this, Layout_selection.class);
                    j.putExtra("Table", FiteredTable);
                    startActivity(j);


                /*Toast toast = Toast.makeText(Sprinkler_selection.this, "next step is not ready", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();*/
                }catch (Exception e){
                    Toast.makeText(Sprinkler_selection.this, "No data", Toast.LENGTH_SHORT).show();
                }

            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(Sprinkler_selection.this, sys_capacity.class);
                startActivity(j);
            }
        });
    }

    public void sprinkler(ArrayList<List<String>> Table,String[] headerText) {
        myDb = new sprinklerDB(this);
        //myBD3.createTable();
        // spacing 9*12

        TableLayout Tl = (TableLayout) findViewById(R.id.ftable);

        selectSpTable = new create_table(Table, headerText, Tl, this);
        selectSpTable.buildTable();



    }


    public ArrayList filter() {
        ArrayList emAryylist = new ArrayList();
        Log.d("table"," " +Table.get(1).get(5));
        int i;

        for (i = 0; i < Table.size(); i++) {

            int x;

            int size =  Table.get(i).size();

            int cur =0;
            for (x = 1; x <size; x++) {

                Log.d("setTime "+ Float.toString(set_time) + " table size " +  size ,"");

                if (x == 5) {
                    float set_time = mySingleton.grossdepth/Float.parseFloat(Table.get(i).get(x));


                    if (set_time<11) {

                        Table.get(i).add(size, Float.toString(set_time));
                        emAryylist.add(cur, Table.get(i));
                        cur++;
                    }




                }


                }

            }
            return emAryylist;

        }
        //Log.d("ddd", String.valueOf(emAryylist.size()));

        }



