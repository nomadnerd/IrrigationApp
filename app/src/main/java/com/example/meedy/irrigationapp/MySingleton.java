package com.example.meedy.irrigationapp;


public class MySingleton {
    private static MySingleton mySingleton = null;

   public  float fdnet;
    public float field_area;
    public float irrigationFrequency;
    public float grossdepth;
    public float netdepth_drip;
    public float EmitterPerPlant;
    public float Spacing;
    public float Wetted_Width;
    public float Pw;
    public float Sr;
    public float Sp;
    public float net_irr_pertree;
    public float ReadilyMoisture;
    public float factor;

    private MySingleton() {

    }

    public static MySingleton getInstance() {
        if (mySingleton == null) {
            mySingleton = new MySingleton();
        }
        return mySingleton;
    }


}
