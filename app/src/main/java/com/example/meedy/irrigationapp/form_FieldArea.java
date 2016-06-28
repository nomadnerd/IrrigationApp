package com.example.meedy.irrigationapp;

/**
 * Created by meedy on 6/21/2016.
 */
public class form_FieldArea {

    private float width, length;

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float fieldArea(){

        return getLength()*getWidth()/10000;

    }
}
