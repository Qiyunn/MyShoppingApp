package com.example.myshoppingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myshoppingapp.R;
import com.example.myshoppingapp.activities.DetailActivity;
import com.example.myshoppingapp.models.Product;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    Context context;
    ArrayList<Product> list=new ArrayList<>();

    public MyRecyclerAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_recycler_view,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
          Product product=list.get(i);

          myViewHolder.textName.setText(product.getName());
          myViewHolder.textUnit.setText(product.getUnit());
          myViewHolder.textPrice.setText("$"+String.valueOf(product.getPrice()));

        Glide.with(context).load(product.getImage()).into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textName,textUnit,textPrice;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textName=itemView.findViewById(R.id.text_view_product_name);
            textUnit=itemView.findViewById(R.id.text_view_product_unit);
            textPrice=itemView.findViewById(R.id.text_view_product_price);
            imageView=itemView.findViewById(R.id.image_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Product product=new Product();
            product=list.get(getAdapterPosition());
            Intent intent=new Intent(context, DetailActivity.class);
            intent.putExtra("PRODUCT",product);
            context.startActivity(intent);

        }
    }

    public void setData(ArrayList<Product> arrayList) {
        list = arrayList;
        notifyDataSetChanged();
    }
}
