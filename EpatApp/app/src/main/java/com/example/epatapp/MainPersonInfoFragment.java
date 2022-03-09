package com.example.epatapp;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class MainPersonInfoFragment extends Fragment implements DatePickerDialog.OnDateSetListener{

    ConstraintLayout picker;
    EditText birth,name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_person_info, container, false);
        setComponents(view);

        return view;
    }

    private void setComponents(View view){
        setPicker(view);
        birth = view.findViewById(R.id.personal_birth);
        name = view.findViewById(R.id.personal_name);
    }

    private void setPicker(View view){
        picker = view.findViewById(R.id.personal_calendar_picker);
        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPicker();
            }
        });
    }
    private void showPicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String birthString = day + "/" + month + "/" + year;
        this.birth.setText(birthString);
    }
}