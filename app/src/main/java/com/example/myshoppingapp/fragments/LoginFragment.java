package com.example.myshoppingapp.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myshoppingapp.R;
import com.example.myshoppingapp.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    EditText editEmail,editPass;
    TextView textNotRegister;
    Button buttonLogin;
    CheckBox checkBox;
    FragmentManager fragmentManager;
    SharedPreferences sharedPreferences;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        editEmail=view.findViewById(R.id.login_edit_text_email);
        editPass=view.findViewById(R.id.login_edit_text_pass);
        textNotRegister=view.findViewById(R.id.login_fragment_text_view);
        buttonLogin=view.findViewById(R.id.login_fragment_button);
        checkBox=view.findViewById(R.id.login_fragment_check_box);
        fragmentManager=getActivity().getSupportFragmentManager();

        buttonLogin.setOnClickListener(this);
        textNotRegister.setOnClickListener(this);

        if(checkBox.isChecked()){
            editEmail.setText(editEmail.getText().toString());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_fragment_text_view:
                fragmentManager.beginTransaction().replace(R.id.login_activity_fragment,new RegisterFragment()).addToBackStack(null).commit();
                break;
            case R.id.login_fragment_button:
                sharedPreferences= getActivity().getSharedPreferences("DATA", Context.MODE_PRIVATE);
                String name=sharedPreferences.getString("Name",null);
                String email=sharedPreferences.getString("EMAIL",null);
                String pass=sharedPreferences.getString("PASSWORD",null);
                if(email.equals(editEmail.getText().toString()) && pass.equals(editPass.getText().toString())){
                    Toast.makeText(getContext(), "login successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), MainActivity.class));
                }else{
                    Toast.makeText(getContext(), "email and password not match", Toast.LENGTH_SHORT).show();
                }

                break;

        }
    }
}
