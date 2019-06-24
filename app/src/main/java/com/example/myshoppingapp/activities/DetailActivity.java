package com.example.myshoppingapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myshoppingapp.R;
import com.example.myshoppingapp.databases.DBHelper;
import com.example.myshoppingapp.models.Cart;
import com.example.myshoppingapp.models.Product;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;
    TextView textName,textUnit,textPrice;
    Button buttonAddToCart,buttonBuyNow;
    Toolbar toolbar;

    Product product;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
        setUpToolBar();
    }

    private void setUpToolBar() {
        toolbar=findViewById(R.id.tool_bar);
        toolbar.setTitle(product.getName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void init() {
        dbHelper=new DBHelper(this);

        imageView=findViewById(R.id.image_view);
        textName=findViewById(R.id.text_view_name);
        textUnit=findViewById(R.id.text_view_unit);
        textPrice=findViewById(R.id.text_view_price);
        buttonAddToCart=findViewById(R.id.button_add_to_cart);
        buttonBuyNow=findViewById(R.id.button_buy_now);

        buttonAddToCart.setOnClickListener(this);

        product= (Product) getIntent().getSerializableExtra("PRODUCT");
        Glide.with(this).load(product.getImage()).into(imageView);
        textName.setText(product.getName());
        textUnit.setText(product.getUnit());
        textPrice.setText(String.valueOf(product.getPrice()));



    }
    @Override
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
    public void onClick(View v) {
        ArrayList<String> arrayListName=new ArrayList<>();
        ArrayList<Cart> arrayListCart = new ArrayList<>();
        for(int i=0;i<arrayListCart.size();i++){
            arrayListName.add(arrayListCart.get(i).getProductName());
        }


        switch (v.getId()){
            case R.id.button_add_to_cart:
                dbHelper.addToCart(product);
                break;
        }
    }
}
