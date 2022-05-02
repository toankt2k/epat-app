package com.example.epatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class PatOfPatientInfoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    ConstraintLayout picker;
    EditText dateIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pat_of_patient_info);
        setComponents();
    }

    private void setComponents() {
        setPicker();
        dateIn = findViewById(R.id.patient_date_in);
    }

    private void setPicker() {
        picker = findViewById(R.id.patient_calendar_picker);
        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPicker();
            }
        });
    }

    private void showPicker() {
        DatePickerDialog dialog = new DatePickerDialog(this,this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String dateString = day + "/" + month + "/" + year;
        this.dateIn.setText(dateString);
    }
}