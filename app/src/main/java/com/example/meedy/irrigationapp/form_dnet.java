package com.example.meedy.irrigationapp;

/**
 * Created by meedy on 6/19/2016.
 */
public class form_dnet {

   private float avaiblemoisture, rootzonedepth, allowablemoisture;

    public float getAvaiblemoisture() {
        return avaiblemoisture;
    }

    public void setAvaiblemoisture(float avaiblemoisture) {
        this.avaiblemoisture = avaiblemoisture;
    }

    public float getRootzonedepth() {
        return rootzonedepth;
    }

    public void setRootzonedepth(float rootzonedepth) {
        this.rootzonedepth = rootzonedepth;
    }

    public float getAllowablemoisture() {
        return allowablemoisture;
    }

    public void setAllowablemoisture(float allowablemoisture) {
        this.allowablemoisture = allowablemoisture;
    }

    public float ndepth (){

        return getAllowablemoisture()*getAvaiblemoisture()*getRootzonedepth();

    }

}
