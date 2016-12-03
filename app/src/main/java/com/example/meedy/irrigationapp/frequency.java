package com.example.meedy.irrigationapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class frequency extends Fragment {

    TextView irr, dnt,mm,mmd,wu, fetdnt, fetANS;
    Button copt;
    EditText  fetwu;
    MySingleton mySingleton = MySingleton.getInstance();



    public frequency() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frequency, container, false);

        irr =(TextView)view.findViewById(R.id.textView3);
        dnt= (TextView)view.findViewById(R.id.textView14);
        mm = (TextView)view.findViewById(R.id.textView15);
        mmd = (TextView)view.findViewById(R.id.WU);
        wu =(TextView)view.findViewById(R.id.textView16);
        copt = (Button)view.findViewById(R.id.buComp);


        fetwu = (EditText)view.findViewById(R.id.editText12);
        fetdnt = (TextView)view.findViewById(R.id.dnet);
        fetANS = (TextView)view.findViewById(R.id.Answer);

        fetdnt.setText(Float.toString(mySingleton.fdnet));


        copt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    float fwu = Float.parseFloat(fetwu.getText().toString());
                    float ans = mySingleton.fdnet / fwu;

                    ans = (new Float(Math.floor(ans)));
                    mySingleton.irrigationFrequency = ans;
                    fetANS.setText(Float.toString(ans));

                    // set new dnet with the floored frequency

                    float new_dnet = ans*fwu;
                    mySingleton.new_dnet_sprinkler=new_dnet;

                } catch (Exception e) {

                    Toast.makeText(getContext(), "Fill the field(s) above", Toast.LENGTH_SHORT).show();
                }
            }

        });



        return view;



    }



}
