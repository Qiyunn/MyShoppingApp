package com.example.myshoppingapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myshoppingapp.R;
import com.example.myshoppingapp.adapters.MyCartRecyclerAdapter;
import com.example.myshoppingapp.databases.DBHelper;
import com.example.myshoppingapp.models.Cart;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartBarFragment extends Fragment{

    DBHelper dbHelper;
//    ArrayList<Cart> arrayList=new ArrayList<>();
    TextView textViewAmount;
//    MyCartRecyclerAdapter adapter;

    public CartBarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cart_bar, container, false);
        textViewAmount=view.findViewById(R.id.cart_bar_text_view_amount);
//        textViewPrice=view.findViewById(R.id.cart_bar_text_view_price);
        dbHelper=new DBHelper(getContext());

        textViewAmount.setText(String.valueOf(dbHelper.getAmount()));

        return view;
    }

//    @Override
//    public void itemClicked(View v, int position) {
//        switch (v.getId()) {
//            case R.id.cart_button_delete:
//                setUpAmount(textViewAmount);
//                setUpPrice(textViewPrice);
//                break;
//
//            case R.id.cart_button_add:
//                setUpAmount(textViewAmount);
//                setUpPrice(textViewPrice);
//                break;
//
//            case R.id.cart_button_remove:
//                setUpAmount(textViewAmount);
//                setUpPrice(textViewPrice);
//                break;
//        }
//    }
}
