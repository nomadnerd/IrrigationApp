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
public class Pdesignfragment1 extends Fragment {
    TextView pd, dnt;
    EditText rz, fc, md, ans;
    Button comp;
    Spinner spinner;


    public Pdesignfragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pdesignfragment1, container, false);
        pd = (TextView) view.findViewById(R.id.pre);
        dnt = (TextView) view.findViewById(R.id.tv2);

        rz = (EditText) view.findViewById(R.id.ed2);
        fc = (EditText) view.findViewById(R.id.ed1);
        md = (EditText) view.findViewById(R.id.ed3);
        ans = (EditText) view.findViewById(R.id.ed4);
        ans = (EditText) view.findViewById(R.id.ed4);
        comp = (Button) view.findViewById(R.id.bt4);

        comp.setOnClickListener(new buttonclick());

        return  view;
    }
    class buttonclick implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }
}








