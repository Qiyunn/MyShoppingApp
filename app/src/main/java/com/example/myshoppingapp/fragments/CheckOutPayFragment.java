package com.example.myshoppingapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myshoppingapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckOutPayFragment extends Fragment {

    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2, radioButton3;
    Button buttonPay;
    int id;

    FragmentManager fragmentManager;

    public CheckOutPayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_out_pay, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();

        radioGroup = view.findViewById(R.id.pay_fragment_radio_group);
        radioButton1 = view.findViewById(R.id.pay_fragment_radio_button1);
        radioButton2 = view.findViewById(R.id.pay_fragment_radio_button2);
        radioButton3 = view.findViewById(R.id.pay_fragment_radio_button3);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.pay_fragment_radio_button1:
                        id = 1;
                        break;
                    case R.id.pay_fragment_radio_button2:
                        id = 2;
                        break;
                    case R.id.pay_fragment_radio_button3:
                        id=3;
                        break;
                }
            }
        });

        buttonPay = view.findViewById(R.id.pay_fragment_button);
        buttonPay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (id == 0) {
                    Toast.makeText(getContext(), "Please select a way to pay", Toast.LENGTH_LONG).setGravity(Gravity.CENTER,0,0);
                } else if(id==1){
                    fragmentManager.beginTransaction().replace(R.id.check_out_fragment, new CheckOutThanksFragment()).addToBackStack(null).commit();
                }else if(id==2){
                    Toast.makeText(getContext(), "you selected Credit", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "you selected Wallet", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
