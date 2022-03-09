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

public class PatientInfoInfoFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    ConstraintLayout picker;
    EditText birth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patien_info_info, container, false);
        setComponents(view) ;
        return view;
    }

    private void setComponents(View view) {
        setPicker(view);
        birth = view.findViewById(R.id.patient_birth);
    }

    private void setPicker(View view) {
        picker = view.findViewById(R.id.patient_calendar_picker);
        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPicker();
            }
        });
    }

    private void showPicker() {
        DatePickerDialog dialog = new DatePickerDialog(getContext(),this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String birthString = day + "/" + month + "/" + year;
        this.birth.setText(birthString);
    }
}