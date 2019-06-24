package com.example.myshoppingapp.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.myshoppingapp.R;
import com.example.myshoppingapp.adapters.FragmentAdapter;
import com.example.myshoppingapp.fragments.LoginFragment;

public class LoginActivity extends AppCompatActivity {
    TextView textView;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        setUpFragment(frameLayout);

    }

    private void init() {
        textView=findViewById(R.id.login_activity_text_view);
        frameLayout=findViewById(R.id.login_activity_fragment);
        fragmentManager=getSupportFragmentManager();
    }

    private void setUpFragment(FrameLayout fragment) {
        fragmentManager.beginTransaction().add(R.id.login_activity_fragment,new LoginFragment()).commit();
    }
}
