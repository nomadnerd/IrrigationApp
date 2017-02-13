package com.example.meedy.irrigationapp;


public class Root_Zone {
    private  double rzd, ID;
    private String Crop;
    public Root_Zone(String Crop, double rzd) {
     this.Crop = Crop;
        this.rzd = rzd;
    }

    public Root_Zone() {

    }


    public String getCrop() {
        return Crop;
    }

    public void setCrop(String crop) {
        Crop = crop;
    }

    public double getRzd() {
        return rzd;
    }

    public void setRzd(double rzd) {
        this.rzd = rzd;
    }

    public double getID() {
        return ID;
    }

    public void setID(double ID) {
        this.ID = ID;
    }
}
