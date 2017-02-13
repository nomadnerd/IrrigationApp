package com.example.meedy.irrigationapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "soil_category";

    public static final String TABLE_NAME = "Available_moisture";

    public static final int DATABASE_VERSION = 1;

    public static final String COLUMN_SOIL_CAT = "SOILCATEGORY";
    public static final String COLUMN_ID = "ID";
    public static final String COL_AVAILABLE_MOISTURE = "AVAILABLEMOISTURE";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +"( "
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_SOIL_CAT + " TEXT, "
            + COL_AVAILABLE_MOISTURE +" INTEGER" +" )";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);
    }
    public void insert( String Soilcategory, int availableMoisture){


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SOIL_CAT, Soilcategory);
        values.put(COL_AVAILABLE_MOISTURE, availableMoisture);


        db.insert(TABLE_NAME, null, values);
        db.close();



    }

    public ArrayList<List<String>> getData(){
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        ArrayList<List<String>>mySoil = new ArrayList<List<String>>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){

            do{
                List<String>subSoil=new ArrayList<String>();

                subSoil.add(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                subSoil.add(cursor.getString(cursor.getColumnIndex(COLUMN_SOIL_CAT)));
                subSoil.add(cursor.getString(cursor.getColumnIndex(COL_AVAILABLE_MOISTURE)));

                mySoil.add(subSoil);



            }while (cursor.moveToNext());
        }

        return mySoil;
    }
}






