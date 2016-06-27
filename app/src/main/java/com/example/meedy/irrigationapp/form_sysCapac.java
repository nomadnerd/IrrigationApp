package com.example.meedy.irrigationapp;

/**
 * Created by meedy on 6/21/2016.
 */
public class form_sysCapac {

    private float dgross, irrcycle, numbofshift,timepershift;

    public float getDgross() {
        return dgross;
    }

    public void setDgross(float dgross) {
        this.dgross = dgross;
    }

    public float getIrrcycle() {
        return irrcycle;
    }

    public void setIrrcycle(float irrcycle) {
        this.irrcycle = irrcycle;
    }

    public float getNumbofshift() {
        return numbofshift;
    }

    public void setNumbofshift(float numbofshift) {
        this.numbofshift = numbofshift;
    }

    public float getTimepershift() {
        return timepershift;
    }

    public void setTimepershift(float timepershift) {
        this.timepershift = timepershift;
    }

    public float syscapacity(){
        return 10*getDgross()/getIrrcycle()*getNumbofshift()*getTimepershift();
    }
}
