package com.example.meedy.irrigationapp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meedy on 4/22/2017.
 */

public class create_table {
    private  TableLayout tableLayout;

    private  ArrayList<List<String>> Tabledata;
    private  String[] TableHeader ;
    private Context context;

    public create_table(ArrayList<List<String>> Tabledata, String[] TableHeader, TableLayout tableLayout, Context context) {
        this.Tabledata = Tabledata;
        this.TableHeader = TableHeader;
        this.tableLayout = tableLayout;
        this.context = context.getApplicationContext();

    }
    public void buildTable(){


        TableRow rowHeader = new TableRow(context);

        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        TableRow.LayoutParams lp2 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        rowHeader.setLayoutParams(lp2);

        //String[] headerText = {"NZ SIZE", "PRESSURE", "DISCHARGE", "DIA  ", "RATE  ", "SPACING"};
        for (String c : TableHeader) {

            TextView tv = new TextView(context);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(14);
            tv.setPadding(5, 5, 5, 5);
            tv.setText(c);
            rowHeader.addView(tv);


        }
        tableLayout.addView(rowHeader);

        // getting data


        // = myBD3.getData();
        Log.d("Table", Tabledata.toString());
        for (List<String> val : Tabledata) {
            TableRow row = new TableRow(context);

            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            int index = 0;
            for (String item : val) {

                if (index != 0) {
                    TextView tv = new TextView(context);
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv.setGravity(Gravity.CENTER);
                    tv.setTextSize(16);
                    tv.setPadding(5, 5, 5, 5);
                    tv.setTextColor(Color.parseColor("#000000"));
                    tv.setText(item);
                    row.addView(tv);
                }
                index++;
            }
            tableLayout.addView(row);
        }


    }
}