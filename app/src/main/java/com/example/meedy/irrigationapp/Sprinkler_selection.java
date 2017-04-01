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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meedy on 4/1/2017.
 */

public class Sprinkler_selection extends AppCompatActivity {
    TableLayout tableLayout;
    EditText Dia;
    Button submit, next, previous;
    sprinklerDB myDb;
    MySingleton mySingleton = MySingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sprinklerselection);

        tableLayout = (TableLayout) findViewById(R.id.ftable);

        Dia = (EditText) findViewById(R.id.diameter);
        submit = (Button) findViewById(R.id.submit);
        next = (Button) findViewById(R.id.next);
        previous = (Button) findViewById(R.id.previous);

        myDb = new sprinklerDB(this);

        // setting litsener fr buttons

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                    float rate = Float.parseFloat(Dia.getText().toString());

                    ArrayList<List<String>> Table = myDb.SelectRatebyDiameter(rate);

                    Log.d("Table", Table.toString());
                    sprinkler(Table);
                    }catch (Exception e){
                        Toast.makeText(Sprinkler_selection.this, "Fill the field(s) above", Toast.LENGTH_SHORT).show();

                    }

                }
            });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(Sprinkler_selection.this, "next step is not ready", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

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

    public void sprinkler(ArrayList<List<String>> Table) {
        myDb = new sprinklerDB(this);
        //myBD3.createTable();
        // spacing 9*12
        myDb.insert(3, 250, 0.57, 5.28, "9*12");
        myDb.insert(3, 300, 0.63, 5.83, "9*12");
        myDb.insert(3, 350, 0.68, 6.30, "9*12");

        //spacing 9*15
        myDb.insert(3, 250, 0.57, 4.22, "9*15");
        myDb.insert(3, 300, 0.63, 4.67, "9*15");
        myDb.insert(3, 350, 0.68, 5.04, "9*15");

        //spacing 12*12
        myDb.insert(3, 250, 0.57, 3.96, "12*12");
        myDb.insert(3, 300, 0.63, 4.38, "12*12");
        myDb.insert(3, 350, 0.68, 4.72, "12*12");


        TableRow rowHeader = new TableRow(this);
        TableLayout Tl = (TableLayout) findViewById(R.id.ftable);

        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        TableRow.LayoutParams lp2 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        rowHeader.setLayoutParams(lp2);

        String[] headerText = {"NOZZLE SIZE", "PRESSURE", "DISCHARGE", "RATE", "SPACING"};
        for (String c : headerText) {

            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(14);
            tv.setPadding(5, 5, 5, 5);
            tv.setText(c);
            rowHeader.addView(tv);


        }
        Tl.addView(rowHeader);

        // getting data


        // = myBD3.getData();
        Log.d("Table", Table.toString());
        for (List<String> val : Table) {
            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            int index = 0;
            for (String item : val) {

                if (index != 0) {
                    TextView tv = new TextView(this);
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv.setGravity(Gravity.CENTER);
                    tv.setTextSize(16);
                    tv.setPadding(5, 5, 5, 5);
                    tv.setText(item);
                    row.addView(tv);
                }
                index++;
            }
            Tl.addView(row);
        }
    }
}
