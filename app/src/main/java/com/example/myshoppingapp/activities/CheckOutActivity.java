package com.example.myshoppingapp.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.myshoppingapp.R;
import com.example.myshoppingapp.fragments.CheckOutInformationFragment;

public class CheckOutActivity extends AppCompatActivity {
    Toolbar toolbar;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        toolbar=findViewById(R.id.tool_bar);
        frameLayout=findViewById(R.id.check_out_fragment);
        fragmentManager=getSupportFragmentManager();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fragmentManager.beginTransaction().add(R.id.check_out_fragment,new CheckOutInformationFragment()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;

    }
}
