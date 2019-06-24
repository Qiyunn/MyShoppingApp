package com.example.myshoppingapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myshoppingapp.R;
import com.example.myshoppingapp.adapters.MyRecyclerAdapter;
import com.example.myshoppingapp.models.Product;
import com.example.myshoppingapp.models.SampleData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {

   RecyclerView recyclerView;
   MyRecyclerAdapter adapter;
   RecyclerView.LayoutManager layoutManager;
   ArrayList<Product> list=new ArrayList<>();
   String name;
    public ProductFragment() {
        // Required empty public constructor
    }


    public static ProductFragment newInstance(String catName){
         ProductFragment productFragment=new ProductFragment();
         Bundle args=new Bundle();
         args.putString("Name",catName);
         productFragment.setArguments(args);
         return productFragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_product, container, false);

        name=getArguments().getString("Name");
        list= SampleData.getData(name);

        init(view);
        return view;
    }

    private void init(View view) {
        recyclerView=view.findViewById(R.id.recycler_view);
        layoutManager=new LinearLayoutManager(getContext());
        adapter=new MyRecyclerAdapter(getContext(),list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setData(list);
    }


}
