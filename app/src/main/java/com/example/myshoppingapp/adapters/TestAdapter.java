package com.example.myshoppingapp.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myshoppingapp.R;
import com.example.myshoppingapp.models.Image;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {
    Context context;
    ArrayList<Image> arrayList=new ArrayList<>();
    LayoutInflater layoutInflater;

    public TestAdapter(Context context, ArrayList<Image> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=layoutInflater.inflate(R.layout.row_test,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
          Image image=new Image();
          image=arrayList.get(i);
         Glide.with(context).load(image.getImage()).into(myViewHolder.imageView);
         myViewHolder.textView.setText(image.getTitle());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.row_test_image_view);
            textView=itemView.findViewById(R.id.row_test_text_view);
        }
    }
}
