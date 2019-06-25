package com.example.myshoppingapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myshoppingapp.R;
import com.example.myshoppingapp.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartEmptyFragment extends Fragment {

    TextView textView;
    Button button;
    public CartEmptyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cart_empty, container, false);

        textView=view.findViewById(R.id.cart_empty_text_view);
        button=view.findViewById(R.id.cart_empty_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });

        return view;
    }

}
