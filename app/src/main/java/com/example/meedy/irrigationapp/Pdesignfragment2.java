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
public class Pdesignfragment2 extends Fragment {
    TextView tx1, tx2, tx3, tx4;
    EditText edt1;
    Button btn1, btn2;
    Spinner sp1, sp2, sp3;


    public Pdesignfragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pdesignfragment2, container, false);
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);

        tx1 = (TextView) getActivity().findViewById(R.id.textView5);
        tx2 = (TextView) getActivity().findViewById(R.id.textView6);
        tx3 = (TextView) getActivity().findViewById(R.id.textView7);
        tx4 = (TextView) getActivity().findViewById(R.id.textView8);
        edt1 = (EditText) getActivity().findViewById(R.id.editText4);
        btn1 = (Button) getActivity().findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

            }


        });
    }

}