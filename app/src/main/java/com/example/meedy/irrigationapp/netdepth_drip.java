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
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class netdepth_drip extends Fragment implements AdapterView.OnItemSelectedListener{

    TextView dnt, Dans, kr;
    EditText ET;
    Button comp;
    Spinner KR;
    float Kr_factor;
    float ETo;
    MySingleton mySingleton = MySingleton.getInstance();


    public netdepth_drip() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_netdepth_drip, container, false);

        dnt = (TextView) view.findViewById(R.id.dnet);
        Dans = (TextView) view.findViewById(R.id.answer);
        kr = (TextView) view.findViewById(R.id.kr);
        ET = (EditText) view.findViewById(R.id.et);
        comp = (Button) view.findViewById(R.id.dcomp);
        KR = (Spinner) view.findViewById(R.id.spinnerKR);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(), R.array.Kr, android.R.layout.simple_spinner_item);
        KR.setAdapter(adapter);
        KR.setOnItemSelectedListener(this);


        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    float crop_factor = Float.parseFloat(ET.getText().toString());
                    float crop = Kr_factor;
                    //float Evapo = ETo;
                    //Toast.makeText(getContext(), "Selected clicks: " +percentage.getSelectedItem().toString() , Toast.LENGTH_LONG).show();
                    float ans = (float) (Math.round((crop_factor * crop)*1000)/1000.0);
                    mySingleton.netdepth_drip = ans;
                    Dans.setText(Float.toString(ans));
                }catch (Exception e){

                    Toast.makeText(getContext(), "Fill the field(s) above", Toast.LENGTH_SHORT).show();
                }
            }
            });

            return view;


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();
        Kr_factor=Float.parseFloat(item);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}