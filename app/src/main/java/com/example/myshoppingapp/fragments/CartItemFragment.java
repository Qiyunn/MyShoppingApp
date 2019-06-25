package com.example.myshoppingapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myshoppingapp.R;
import com.example.myshoppingapp.activities.CheckOutActivity;
import com.example.myshoppingapp.adapters.MyCartRecyclerAdapter;
import com.example.myshoppingapp.databases.DBHelper;
import com.example.myshoppingapp.models.Cart;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartItemFragment extends Fragment implements MyCartRecyclerAdapter.ClickListenner {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Cart> arrayList = new ArrayList<>();
    MyCartRecyclerAdapter adapter;
    DBHelper dbHelper;

    TextView textViewAmount,textViewPrice;
    Button buttonCheckOut;

    FragmentManager fragmentManager;

    public CartItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart_item, container, false);
        generateData();
        init(view);
        return view;
    }


    private void generateData() {
        dbHelper = new DBHelper(getContext());
        arrayList = dbHelper.selectFromCart();
    }


    private void init(View view) {
        fragmentManager=getActivity().getSupportFragmentManager();
        recyclerView = view.findViewById(R.id.cart_activity_recycler_view);
        textViewAmount=view.findViewById(R.id.cart_bar_text_view_amount);
        textViewPrice=view.findViewById(R.id.cart_bar_text_view_price);
        buttonCheckOut=view.findViewById(R.id.cart_item_button_check_out);

        buttonCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), CheckOutActivity.class));
            }
        });

        setUpAdapter();
        setUpAmount();
        setUpPrice();
    }

    private void setUpAdapter() {

        layoutManager = new LinearLayoutManager(getContext());
        adapter = new MyCartRecyclerAdapter(getContext(), arrayList);

        adapter.setClickListener(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        adapter.notifyDataSetChanged();

    }

    private void setUpPrice() {
        textViewPrice.setText("Total Price is:"+dbHelper.getPrice());
    }

    private void setUpAmount() {
        textViewAmount.setText("Total amount is:"+String.valueOf(dbHelper.getAmount()));
    }


    @Override
    public void itemClicked(View v, int position) {

        Cart cart = new Cart();
        cart = arrayList.get(position);

        switch (v.getId()) {
            case R.id.cart_button_delete:
                dbHelper.deleteProduct(cart);
                arrayList.remove(position);
                adapter.notifyDataSetChanged();
                setUpAmount();
                setUpPrice();
//                setUpPrice(textViewPrice);
                break;

            case R.id.cart_button_add:
                dbHelper.updateAddQuantity(cart);
                adapter.notifyDataSetChanged();
                cart.setQuantity(cart.getQuantity() + 1);
                setUpAmount();
                setUpPrice();
                break;

            case R.id.cart_button_remove:
                if (cart.getQuantity() == 1) {
                    Toast.makeText(getContext(), "Can not be less", Toast.LENGTH_SHORT).show();
                } else {
                    dbHelper.updateMinusQuantity(cart);
                    adapter.notifyDataSetChanged();
                    cart.setQuantity(cart.getQuantity() - 1);
                    setUpAmount();
                    setUpPrice();
                }
//
//                if (dbHelper.selectFromCart().size()==0){
//                    fragmentManager.beginTransaction().replace(R.id.cart_activity_fragmet1,new CartEmptyFragment()).commit();
//                }
//                break;
        }

    }


}
