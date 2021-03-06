package com.example.meedy.irrigationapp;


import java.util.ArrayList;
import java.util.List;

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
    public float percent;
    public float newPw;
    public float new_num_Emitter;
    public float adj_lph;
    public float miniDischarge;
    public float head;
    public float miniHead;
    public float length;
    public float volume;
    public float outlet;
    public float allowableVariation;
    public float newEmitterSpacing;
    public float frequency;
    public float width;
    public float drip_systemcapacity;
    public float manifoldoutlet;
    public float ET_drip;
    public float intial_discharge;
    public float setTime1;
    public float newHead;
    public float laterl;
    public float emittersperLateral;
    public float manifoldHead;
    public float mainlinedHead;
    public float MainOutlet;
    public float subtotal;
    public float fitting;
    public float lthead;
    public float total_dynaic_head;
    public float new_dnet_sprinkler;
    public float avMoisture;
    public float rzd;
    public float lit_dnet;
    public float sprinklerlength;
    public float sprinklerwidth;
    public List<List<String>> finalFilterSingleton;
    public float No_Of_sprinler;
    public float Lateral_Per_Shift;
    public float main_head_sprinkler;
    public float lat_head_sprinkler;
    public float sprinkler_tdh;
    public  float sp_final_volume;
    public float drip_lat_head;
    public float drip_manifold_head;
    public float drip_main_head;


 public float getEmitterPerPlant() {
  return EmitterPerPlant;
 }

 public float rate;
    public ArrayList<List<String>> FilteredData;



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
