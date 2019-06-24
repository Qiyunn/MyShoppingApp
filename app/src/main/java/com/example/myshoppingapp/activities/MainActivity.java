package com.example.myshoppingapp.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myshoppingapp.R;
import com.example.myshoppingapp.adapters.FragmentAdapter;
import com.example.myshoppingapp.fragments.ProductFragment;
import com.example.myshoppingapp.models.SampleData;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentAdapter fragmentAdapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolBar();
        init();
    }

    private void setUpToolBar() {
        toolbar=findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Shopping App");

    }

    private void init() {

        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void setUpViewPager(ViewPager viewPager){
        fragmentAdapter=new FragmentAdapter(getSupportFragmentManager());

        fragmentAdapter.add(SampleData.MOBILE);
        fragmentAdapter.add(SampleData.LAPTOP);
        fragmentAdapter.add(SampleData.DESKTOP);

        viewPager.setAdapter(fragmentAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
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
