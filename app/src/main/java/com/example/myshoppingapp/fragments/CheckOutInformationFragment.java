package com.example.myshoppingapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myshoppingapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckOutInformationFragment extends Fragment {

    TextView textView;
    EditText editTextName, editTextCity, editTextPincode, editTextPhone;
    Button button;

    FragmentManager fragmentManager;

    public CheckOutInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_out_information, container, false);

        textView = view.findViewById(R.id.information_fragment_text_view);
        editTextName = view.findViewById(R.id.information_fragment_edit_name);
        editTextCity = view.findViewById(R.id.information_fragment_edit_city);
        editTextPincode = view.findViewById(R.id.information_fragment_edit_pincode);
        editTextPhone = view.findViewById(R.id.information_fragment_edit_phone);
        button = view.findViewById(R.id.information_fragment_next_button);

        setEditTextInhibitInputSpace(editTextName);
        setEditTextInhibitInputSpace(editTextCity);
        setEditTextInhibitInputSpace(editTextPincode);
        setEditTextInhibitInputSpace(editTextPhone);

        fragmentManager = getActivity().getSupportFragmentManager();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editTextName.getText())) {
                    Toast.makeText(getContext(), "Please Put you name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(editTextCity.getText())) {
                    Toast.makeText(getContext(), "Please Put the city", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(editTextPincode.getText())) {
                    Toast.makeText(getContext(), "Please Put the pincode", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(editTextPhone.getText())) {
                    Toast.makeText(getContext(), "Please Put your phone", Toast.LENGTH_SHORT).show();
                } else {
                    fragmentManager.beginTransaction().replace(R.id.check_out_fragment, new CheckOutPayFragment()).addToBackStack(null).commit();
                }
            }
        });


        return view;
    }

    public static void setEditTextInhibitInputSpace(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.equals(" ")){
                    return "";
                }else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }
}
