package com.example.myshoppingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myshoppingapp.R;
import com.example.myshoppingapp.activities.CartActivity;
import com.example.myshoppingapp.databases.DBHelper;
import com.example.myshoppingapp.models.Cart;
import com.example.myshoppingapp.models.Product;

import java.util.ArrayList;

public class MyCartRecyclerAdapter extends RecyclerView.Adapter<MyCartRecyclerAdapter.MyViewHolder> {
    Context context;
    ArrayList<Cart> arrayList = new ArrayList<>();
    DBHelper dbHelper;
    ClickListenner clickListenner;


    public MyCartRecyclerAdapter(Context context, ArrayList<Cart> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        dbHelper=new DBHelper(context);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_cart_activity_recycler_view, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Cart cart = arrayList.get(i);
        Glide.with(context).load(cart.getProductImage()).into(myViewHolder.imageView);
        myViewHolder.textName.setText(cart.getProductName());
        myViewHolder.textQuantity.setText(String.valueOf(cart.getQuantity()));
        myViewHolder.textPrice.setText("$"+String.valueOf(cart.getProductPrice()));

//        totalAmount=Integer.parseInt(myViewHolder.textQuantity.getText().toString());
//        int sumAmount=Integer.parseInt(myViewHolder.textQuantity.getText().toString());
//        Log.i("Mytag","clickListener: " +clickListenner);
//        int sumPrice=Integer.parseInt(myViewHolder.textQuantity.getText().toString())* Integer.parseInt(myViewHolder.textPrice.getText().toString());
//        clickListenner.sendData(sumAmount);
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textName, textQuantity, textPrice;
        Button buttonAdd, buttonRemove,buttonDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cart_image_view);
            textName = itemView.findViewById(R.id.cart_text_view_name);
            textQuantity = itemView.findViewById(R.id.cart_text_view_quantity);
            textPrice = itemView.findViewById(R.id.cart_text_view_price);
            buttonDelete=itemView.findViewById(R.id.cart_button_delete);

            buttonAdd = itemView.findViewById(R.id.cart_button_add);
            buttonRemove = itemView.findViewById(R.id.cart_button_remove);
            buttonAdd.setOnClickListener(this);
            buttonRemove.setOnClickListener(this);
            buttonDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListenner.itemClicked(v,getAdapterPosition());
//            onBindViewHolder(this,getAdapterPosition());
//                    dbHelper.deleteProduct(cart);
//                    context.startActivity(new Intent(context,CartActivity.class));
        }
    }

    public void setClickListener(ClickListenner clickListenner){
        this.clickListenner = clickListenner;
//        Log.i("Mytag","clickListener init: " + clickListener);
    }

    public interface ClickListenner{
        public void itemClicked(View v,int position);
//        public void sendData(int sumAmount);
    }

}
