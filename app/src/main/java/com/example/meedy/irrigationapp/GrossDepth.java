package com.example.meedy.irrigationapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class GrossDepth extends Fragment implements AdapterView.OnItemSelectedListener {
    TextView gd, eff, irr, depth, grossAs, grossDe;
    Spinner percentage;
    float effeciency;
    Button cop;
   MySingleton mySingleton = MySingleton.getInstance();


    public GrossDepth() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gross_depth, container, false);

        gd = (TextView) view.findViewById(R.id.textView9);
        grossAs = (TextView)view.findViewById(R.id.grossAns);
        grossDe = (TextView)view.findViewById(R.id.grossDep);

        eff = (TextView) view.findViewById(R.id.textView11);
        irr = (TextView) view.findViewById(R.id.textView12);
        cop = (Button) view.findViewById(R.id.button4);
        depth = (TextView) view.findViewById(R.id.depthnet);
        percentage = (Spinner) view.findViewById(R.id.irrEfficiency);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.effciency, android.R.layout.simple_spinner_item);
        percentage.setAdapter(adapter);
        percentage.setOnItemSelectedListener(this);


       depth.setText(Float.toString(mySingleton.fdnet));


        cop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    float eff = effeciency;
                    //Toast.makeText(getContext(), "Selected clicks: " +percentage.getSelectedItem().toString() , Toast.LENGTH_LONG).show();
                    float ans = mySingleton.fdnet / eff;
                    mySingleton.grossdepth = ans;
                    grossAs.setText(Float.toString(ans));

                }catch (Exception e){

                    Toast.makeText(getContext(), "fill the field(s) above ", Toast.LENGTH_SHORT).show();
                }
            }
        });






        return view;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        effeciency=Float.parseFloat(item);
        Toast.makeText(parent.getContext(), "Selected item: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
