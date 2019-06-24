package com.example.myshoppingapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.myshoppingapp.R;
import com.example.myshoppingapp.adapters.MyCartRecyclerAdapter;
import com.example.myshoppingapp.adapters.MyRecyclerAdapter;
import com.example.myshoppingapp.databases.DBHelper;
import com.example.myshoppingapp.models.Cart;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements MyCartRecyclerAdapter.ClickListenner {
    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Cart> arrayList = new ArrayList<>();
    MyCartRecyclerAdapter adapter;
    DBHelper dbHelper;
    TextView textViewPrice,textViewAmount;

//    int totalAmount;
//    int totalPrice;

//    OnCalculateAgain onCalculateAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        generateData();
        init();
        setUpAmount(textViewAmount);
        setUpPrice(textViewPrice);
    }

    private void generateData() {
        dbHelper=new DBHelper(this);
        arrayList=dbHelper.selectFromCart();

    }

    private void init(){


        toolbar=findViewById(R.id.tool_bar);
        toolbar.setTitle("Cart");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewPrice=findViewById(R.id.cart_activity_text_view_price);

        textViewAmount=findViewById(R.id.cart_activity_text_view_amount);

        recyclerView=findViewById(R.id.cart_activity_recycler_view);
        layoutManager=new LinearLayoutManager(this);
        adapter=new MyCartRecyclerAdapter(this,arrayList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


        recyclerView.setLayoutManager(layoutManager);

        adapter.notifyDataSetChanged();
    }

    private void setUpAmount(TextView textView) {
        int amount=0;
        for (int i=0;i<arrayList.size();i++){
            amount=amount+arrayList.get(i).getQuantity();
        }
        textView.setText("Total Amount is:"+amount);
    }


    private void setUpPrice(TextView textView) {
        int price=0;
        for (int i=0;i<arrayList.size();i++){
            price=price+arrayList.get(i).getProductPrice();
        }
        textView.setText("Total price is:$"+price);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.item_search:
                break;
            case R.id.item_cart:
                startActivity(new Intent(this,CartActivity.class));
                break;
            case R.id.item_profile:
                break;
            case R.id.item_login:
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
        return true;
    }

    @Override
    public void itemClicked(View v, int position) {

        Cart cart=new Cart();
        cart=arrayList.get(position);

        switch(v.getId()){
            case R.id.cart_button_delete:
                dbHelper.deleteProduct(cart);
                adapter.notifyDataSetChanged();
                arrayList.remove(position);
                 setUpAmount(textViewAmount);
                 setUpPrice(textViewPrice);
                break;
            case R.id.cart_button_add:
                dbHelper.updateAddQuantity(cart);
                adapter.notifyDataSetChanged();
                cart.setProductPrice(cart.getProductPrice()+cart.getProductPrice()/cart.getQuantity());
                cart.setQuantity(cart.getQuantity()+1);
                setUpAmount(textViewAmount);
                setUpPrice(textViewPrice);
                break;
            case R.id.cart_button_remove:
                dbHelper.updateMinusQuantity(cart);
                adapter.notifyDataSetChanged();
                cart.setProductPrice(cart.getProductPrice()-cart.getProductPrice()/cart.getQuantity());
                cart.setQuantity(cart.getQuantity()-1);
                if(cart.getQuantity()==0){
                    dbHelper.deleteProduct(cart);
                    adapter.notifyDataSetChanged();
                    arrayList.remove(position);
                }
                setUpAmount(textViewAmount);
                setUpPrice(textViewPrice);
                break;
        }

    }

//    @Override
//    public void sendData(int sumAmount) {
//        totalAmount=totalAmount+sumAmount;
//        textViewAmount.setText(String.valueOf(totalAmount));
//
//    }

}
