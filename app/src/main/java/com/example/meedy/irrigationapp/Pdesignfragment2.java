package com.example.meedy.irrigationapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Pdesignfragment2 extends Fragment {
    TextView tx1, tx2, tx3, tx4, tx5;
    EditText edt1;
    Button btn1, btn2;
    Spinner sp1, sp2;
    EditText  MaD;
    float amoisture;
    float rootZValue;

    DatabaseHelper myDb;
    DbHelper2 myDb2;
    MySingleton mySingleton = MySingleton.getInstance();



    public Pdesignfragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pdesignfragment2, container, false);


        return view;
    }

    public void AvailableMoistureTable() {

        myDb.insert("Sand", 55);
        myDb.insert("Fine Sand", 80);
        myDb.insert("Sandy Loam", 120);
        myDb.insert("Clay Loam", 150);
        myDb.insert("Clay", 235);

        final ArrayList<List<String>> mainlist = myDb.getData();

        List<String> moistureList = new ArrayList<>();
        for (List<String> ml : mainlist
                ) {

            moistureList.add(ml.get(1));

        }


        sp1.setAdapter(new ArrayAdapter<>(this.getActivity(), R.layout.spinner_layout, R.id.text, moistureList));
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                float amoisture  = Integer.parseInt(mainlist.get(position).get(2));


                /*String item = parent.getItemAtPosition(2).toString();
                amoisture = Float.parseFloat(item);*/

                mySingleton.avMoisture=amoisture;

                //Toast.makeText(Pdesignfragment2.this, "position: " + position + " " + mainlist.get(position).get(2), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);
        tx1 = (TextView) getActivity().findViewById(R.id.textView5);
        tx2 = (TextView) getActivity().findViewById(R.id.textView6);
        tx3 = (TextView) getActivity().findViewById(R.id.textView7);
        tx4 = (TextView) getActivity().findViewById(R.id.textView8);
        tx5= (TextView) getActivity().findViewById(R.id.textView82);
        //edt1 = (EditText) getActivity().findViewById(R.id.editText4);
        btn1 = (Button) getActivity().findViewById(R.id.button2);

        sp1 = (Spinner) getActivity().findViewById(R.id.spinner2);
        sp2 = (Spinner) getActivity().findViewById(R.id.spinner3);
        MaD = (EditText) getActivity().findViewById(R.id.edTxt);


        myDb = new DatabaseHelper(getActivity());
        myDb2 = new DbHelper2(getActivity());

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float mad = Float.parseFloat(MaD.getText().toString());
                float netdepth = mad*mySingleton.rzd*mySingleton.avMoisture;
                tx5.setText(Float.toString(netdepth));
                mySingleton.lit_dnet=netdepth;

            }
        });



        AvailableMoistureTable();
        RootZoneDepth();


    }

    public void RootZoneDepth() {

        ArrayList<Root_Zone> root_zone_list = new ArrayList<Root_Zone>();
        root_zone_list.add(new Root_Zone("Garlic", 0.4));
        root_zone_list.add(new Root_Zone("Millet", 1.2));
        root_zone_list.add(new Root_Zone("Almond", 1.0));
        root_zone_list.add(new Root_Zone("cabbage", 0.5));
        root_zone_list.add(new Root_Zone("Onion", 0.3));


        myDb2.insert(root_zone_list);
/*

        myDb2.insert(new root_zone("Millet", (float) 1.2));
        myDb2.insert(new root_zone("Almond", (float) 1.0));
        myDb2.insert(new root_zone("cabbage", (float) 0.5));
        myDb2.insert(new root_zone("Onion", (float) 0.3));
        myDb2.insert(new root_zone("Garlic", (float) 0.4));
*/

        final ArrayList<Root_Zone> rootZone = myDb2.getData();

        final List<List<String>> flist = new ArrayList<>();
        List<String> cropsList = new ArrayList<>();

        for (Root_Zone rz : rootZone
                ) {
            List<String> nlist = new ArrayList<>();

            nlist.add(rz.getCrop());
            nlist.add(rz.getRzd() + " ");
            cropsList.add(rz.getCrop() + " rzd: " + rz.getRzd());


            flist.add(nlist);


        }


        sp2.setAdapter(new ArrayAdapter<>(this.getActivity(), R.layout.spinner_layout, R.id.text, cropsList));
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                float rootZValue = Float.parseFloat(flist.get(position).get(1));

                /*String item = parent.getItemAtPosition(1).toString();
                rootZValue = Float.parseFloat(item);*/


                mySingleton.rzd = rootZValue;


                //Toast.makeText(MainActivity.this, "position: "+ position +" " +flist.get(position).get(1), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}