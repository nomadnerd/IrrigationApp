package com.example.meedy.irrigationapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meedy on 4/1/2017.
 */

public class sprinklerDB extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "Root_Zone";

    public static final String TABLE_NAME = "Performance_of_sprinkler";

    public static final int DATABASE_VERSION = 1;

    public static final String COLUMN_Nozzle_Size = "Nozzle";
    public static final String COLUMN_ID= "ID";
    public static final String COLUMN_Pressure = "Pressure";
    public static final String COLUMN_Discharge= "Discharge";
    public static final String COLUMN_App_Rate= "Rate";
    public static final String COLUMN_Spacing= "Spacing";



    public sprinklerDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_Nozzle_Size + " INTEGER, "
                + COLUMN_Pressure + " INTEGER, "
                + COLUMN_Discharge + " DOUBLE, "
                + COLUMN_App_Rate  + " DOUBLE, "
                + COLUMN_Spacing + " TEXT" + " )";
        Log.d("bl create",CREATE_TABLE);
        db.execSQL(CREATE_TABLE);


    }
    public ArrayList<List<String>> SelectRatebyDiameter(float rate){
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " where Rate < " + rate + " or Rate = " + rate;

        ArrayList<List<String>>spinkler = new ArrayList<List<String>>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){

            do{
                List<String>subSoil=new ArrayList<String>();

                subSoil.add(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                subSoil.add(cursor.getString(cursor.getColumnIndex(COLUMN_Nozzle_Size)));
                subSoil.add(cursor.getString(cursor.getColumnIndex(COLUMN_Pressure)));
                subSoil.add(cursor.getString(cursor.getColumnIndex(COLUMN_Discharge)));
                subSoil.add(cursor.getString(cursor.getColumnIndex(COLUMN_App_Rate)));
                subSoil.add(cursor.getString(cursor.getColumnIndex(COLUMN_Spacing)));

                spinkler.add(subSoil);



            }while (cursor.moveToNext());
        }

        return spinkler;
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);

    }
    public void insert(int nz, int pressure, double discharge, double rate, String spacing){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_Nozzle_Size, nz);
        values.put(COLUMN_Pressure, pressure);
        values.put(COLUMN_Discharge, discharge);
        values.put(COLUMN_App_Rate,rate);
        values.put(COLUMN_Spacing, spacing);

        db.insert(TABLE_NAME, null, values);
        db.close();

    }
    public ArrayList<List<String>> getData(){
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        ArrayList<List<String>>spinkler = new ArrayList<List<String>>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){

            do{
                List<String>subSoil=new ArrayList<String>();

                subSoil.add(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                subSoil.add(cursor.getString(cursor.getColumnIndex(COLUMN_Nozzle_Size)));
                subSoil.add(cursor.getString(cursor.getColumnIndex(COLUMN_Pressure)));
                subSoil.add(cursor.getString(cursor.getColumnIndex(COLUMN_Discharge)));
                subSoil.add(cursor.getString(cursor.getColumnIndex(COLUMN_App_Rate)));
                subSoil.add(cursor.getString(cursor.getColumnIndex(COLUMN_Spacing)));

                spinkler.add(subSoil);



            }while (cursor.moveToNext());
        }

        return spinkler;
    }

}
