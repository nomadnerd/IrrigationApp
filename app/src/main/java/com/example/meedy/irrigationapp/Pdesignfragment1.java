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
    TextView dnt, ans;
    EditText rz, am, md;
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
        //pd = (TextView) view.findViewById(R.id.pre);
        dnt = (TextView) view.findViewById(R.id.tv2);

        rz = (EditText) view.findViewById(R.id.RZD);
        am = (EditText) view.findViewById(R.id.AM);
        md = (EditText) view.findViewById(R.id.MAD);
        ans = (TextView) view.findViewById(R.id.answer);
        comp = (Button) view.findViewById(R.id.btNCOMP);

        comp.setOnClickListener(new buttonclick());

        return  view;
    }
    class buttonclick implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            Float fam =Float.parseFloat(am.getText().toString());
            Float frzd = Float.parseFloat(rz.getText().toString());
            Float fmd = Float.parseFloat(md.getText().toString());


            form_dnet netdepth = new form_dnet();
            netdepth.setAllowablemoisture(fmd);
            netdepth.setAvaiblemoisture(fam);
            netdepth.setRootzonedepth(frzd);

            float answer = netdepth.ndepth()/10000;
            ans.setText(Float.toString(answer));

            MySingleton mySingleton = MySingleton.getInstance();
            mySingleton.fdnet = answer;
        }
    }
}








