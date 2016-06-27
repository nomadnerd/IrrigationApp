package com.example.meedy.irrigationapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GrossDepth extends Fragment {
    TextView  gd,eff,irr;
    Spinner per;
    Button cop;
    EditText edt;



    public GrossDepth() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gross_depth, container, false);

        gd = (TextView)view.findViewById(R.id.textView9);
        eff =(TextView)view.findViewById(R.id.textView11);
        irr = (TextView)view.findViewById(R.id.textView12);
        cop=(Button)view.findViewById(R.id.button4);
        edt=(EditText)view.findViewById(R.id.editText1111);
        return view;

    }

}
