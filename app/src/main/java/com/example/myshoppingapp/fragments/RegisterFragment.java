package com.example.myshoppingapp.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myshoppingapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    EditText editemail,editName,editPass;
    Button buttonRegister;
    FragmentManager fragmentManager;
    SharedPreferences sharedPreferences;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        editemail=view.findViewById(R.id.register_edit_text_email);
        editName=view.findViewById(R.id.register_edit_text_name);
        editPass=view.findViewById(R.id.register_edit_text_pass);
        buttonRegister=view.findViewById(R.id.register_fragment_button);
        fragmentManager=getActivity().getSupportFragmentManager();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 sharedPreferences=getActivity().getSharedPreferences("DATA", Context.MODE_PRIVATE);
                 SharedPreferences.Editor editor=sharedPreferences.edit();
                 editor.putString("EMAIL",editemail.getText().toString());
                 editor.putString("NAME",editName.getText().toString());
                 editor.putString("PASSWORD",editPass.getText().toString());
                 editor.commit();
                Toast.makeText(getContext(), "Registerd successfully", Toast.LENGTH_SHORT).show();
                fragmentManager.beginTransaction().replace(R.id.login_activity_fragment, new LoginFragment()).addToBackStack(null).commit();
            }
        });
    }

}
