package com.example.meedy.irrigationapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DbHelper2 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Root_Zone";

    public static final String TABLE_NAME = "Effective_Rooting_zone";

    public static final int DATABASE_VERSION = 1;

    public static final String COLUMN_CROP = "CROP";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_MAX_RZD = "MAXIMUM_ROOTZONE_DEPTH";


    public DbHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CROP + " TEXT, "
                + COLUMN_MAX_RZD + " INTEGER" + " )";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);

    }

    public void insert(ArrayList<Root_Zone> root_zone_list) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        for (Root_Zone root_zone :root_zone_list) {
            values.put(COLUMN_CROP,root_zone.getCrop() );
            values.put(COLUMN_MAX_RZD, root_zone.getRzd());
            db.insert(TABLE_NAME, null, values);
        }



        db.close();


    }

    public ArrayList<Root_Zone> getData() {
        ArrayList<Root_Zone> rzdlist = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;


        //List<String>mySoil = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {
                //List<String>subSoil=new ArrayList<String>();
                Root_Zone rootZone = new Root_Zone();


                rootZone.setID(cursor.getColumnIndex(COLUMN_ID));
                rootZone.setCrop(cursor.getString(cursor.getColumnIndex(COLUMN_CROP)));
                rootZone.setRzd(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_MAX_RZD))));

                /*subSoil.add(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                subSoil.add(cursor.getString(cursor.getColumnIndex(COLUMN_SOIL_CAT)));
                subSoil.add(cursor.getString(cursor.getColumnIndex(COL_AVAILABLE_MOISTURE)));

                mySoil.add(subSoil.toString());*/
                rzdlist.add(rootZone);

            } while (cursor.moveToNext());
        }
        return rzdlist;
    }
}