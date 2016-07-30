package com.example.meedy.irrigationapp;

import android.util.Log;

public class drip_frequency_formula {

    private float availablemoisture, dfactor, ReadilyMoisture;
    MySingleton mySingleton = MySingleton.getInstance();

    public float getAvailablemoisture() {
        return availablemoisture;
    }

    public void setAvailablemoisture(float availablemoisture) {

       float answer = (availablemoisture*mySingleton.Sp * mySingleton.Sr * mySingleton.Pw) / 100;
        Log.d("answer", ""+answer);

        this.availablemoisture = answer;
    }

    public float getDfactor() {
        return dfactor;
    }

    public void setDfactor(float dfactor) {

        this.dfactor = dfactor;

    }
    public float freq_drip(){

      return    getDfactor()*getAvailablemoisture();
    }
}
