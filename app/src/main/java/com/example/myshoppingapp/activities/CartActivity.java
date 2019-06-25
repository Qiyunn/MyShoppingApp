package com.example.myshoppingapp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myshoppingapp.R;
import com.example.myshoppingapp.adapters.MyCartRecyclerAdapter;
import com.example.myshoppingapp.adapters.MyRecyclerAdapter;
import com.example.myshoppingapp.databases.DBHelper;
import com.example.myshoppingapp.fragments.CartBarFragment;
import com.example.myshoppingapp.fragments.CartEmptyFragment;
import com.example.myshoppingapp.fragments.CartItemFragment;
import com.example.myshoppingapp.models.Cart;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity{

    Toolbar toolbar;
    FrameLayout frameLayout1,frameLayout2;
    FragmentManager fragmentManager;
    DBHelper dbHelper;
    ArrayList<Cart> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        init();
    }

    private void init(){
        toolbar=findViewById(R.id.tool_bar);
        toolbar.setTitle("Cart");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        frameLayout1=findViewById(R.id.cart_activity_fragmet1);
        frameLayout2=findViewById(R.id.cart_activity_fragment2);
        fragmentManager=getSupportFragmentManager();
        dbHelper=new DBHelper(this);

        setUpFragment();
    }

    private void setUpFragment() {
        arrayList=dbHelper.selectFromCart();
        if(arrayList.size()==0){
            fragmentManager.beginTransaction().add(R.id.cart_activity_fragmet1,new CartEmptyFragment()).commit();
        }else {
            fragmentManager.beginTransaction().add(R.id.cart_activity_fragmet1,new CartItemFragment()).commit();
            fragmentManager.beginTransaction().add(R.id.cart_activity_fragment2,new CartBarFragment()).commit();
        }

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
}
