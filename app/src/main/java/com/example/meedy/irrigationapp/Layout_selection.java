package com.example.meedy.irrigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meedy on 5/12/2017.
 */

public class Layout_selection extends AppCompatActivity {

    TableLayout tableLayout;
    TextView lateralPosition, NumOfSprinkler, SprinklerDischarge, SystemCapacity, Nozzle_size, dischSp, spacingSp, setTime;
    EditText NumOfLateralPerShift;
    Button Compute, Next, previous;

    MySingleton mySingleton = MySingleton.getInstance();
    create_table selectSpTable;
    ArrayList<List<String>> Table;

    float lateralposition, NumberofSprinklerPerlat, spDischarge;

    ArrayList<List<String>> timefiltered_data = new ArrayList<List<String>>();
    List<String> timeArray = new ArrayList<String>();
    List<List<String>> finalFilter = new ArrayList<List<String>>();

    @Override

    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.layout_selection);

        lateralPosition = (TextView)findViewById(R.id.lateralPositon);
        NumOfSprinkler = (TextView)findViewById(R.id.numOfSprinklers);
        SprinklerDischarge = (TextView)findViewById(R.id.sprinklerDischarge);
        SystemCapacity = (TextView)findViewById(R.id.SystemCapacity);
        Nozzle_size = (TextView)findViewById(R.id.Nzl);
        dischSp = (TextView)findViewById(R.id.dis);
        spacingSp = (TextView)findViewById(R.id.sp);
        setTime = (TextView)findViewById(R.id.hrs);


        NumOfLateralPerShift = (EditText)findViewById(R.id.Nc);

        Compute = (Button)findViewById(R.id.compBtn);
        Next = (Button)findViewById(R.id.next);
        previous = (Button)findViewById(R.id.pre);
        int i,t = 0;

        for (i=0; i<mySingleton.FilteredData.size(); i++){


        float time =  Float.parseFloat(mySingleton.FilteredData.get(i).get(7));

            float  cal_time = time*2;
            if(cal_time<=24){

                float diff_time = 24-cal_time;
                timeArray.add(t, Float.toString(diff_time));

                timefiltered_data.add(t,mySingleton.FilteredData.get(i));
                t++;

            }
        }

            float smallest = Float.parseFloat(timeArray.get(0));


            for(i=0; i<timeArray.size(); i++){
                if(Float.parseFloat(timeArray.get(i))<smallest){

                    smallest = Float.parseFloat(timeArray.get(0));
                    finalFilter.add(0,timefiltered_data.get(i));

                }

            }

                mySingleton.finalFilterSingleton = finalFilter;


            String spW[] = finalFilter.get(0).get(6).split("\\*", 0);
       // String strarray [] = finalFilter.get(0);

        lateralposition = mySingleton.sprinklerlength/Float.parseFloat(spW[0]);
        NumberofSprinklerPerlat = mySingleton.sprinklerwidth/Float.parseFloat(spW[1]);
        spDischarge = Float.parseFloat(finalFilter.get(0).get(3));

        lateralPosition.setText(Float.toString(lateralposition));
        SprinklerDischarge.setText(Float.toString(spDischarge));
        NumOfSprinkler.setText(Float.toString(NumberofSprinklerPerlat));

        Nozzle_size.setText(finalFilter.get(0).get(1));
        dischSp.setText(finalFilter.get(0).get(3));
        spacingSp.setText(finalFilter.get(0).get(6));
        setTime.setText(finalFilter.get(0).get(7));


        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent J  = new Intent(Layout_selection.this, Lateral_Selection_Sprinkler.class);
                startActivity(J);
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent J  = new Intent(Layout_selection.this, Sprinkler_selection.class);
                startActivity(J);

            }
        });




        Compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                float NoLateralPerShift = Float.parseFloat(NumOfLateralPerShift.getText().toString());
                float system_capacity = spDischarge*NoLateralPerShift*NumberofSprinklerPerlat;
                SystemCapacity.setText(Float.toString(system_capacity));


            }
        });



            mySingleton.No_Of_sprinler= NumberofSprinklerPerlat;

            Log.d("spw", spW[0].toString() );


                Log.d("FF1", ""+finalFilter.get(0).size());
        Log.d("FF2", timefiltered_data.toString());
        Log.d("FF3", finalFilter.get(0).get(6).toString());

                    /*if(Float.parseFloat(mySingleton.FilteredData.get(i).get(7))*2>=20){

                        int SpL =  Integer.parseInt(String.valueOf(mySingleton.FilteredData.get(i).get(6).split("\\*")));


                        RL =
                    }*/
            //Arrays[] LW = mySingleton.FilteredData.get(i).get(6).split("\\*",-1);









         }
            {












    }


}
