package com.example.epatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.epatapp.adapter.TabAdapter;
import com.example.epatapp.fragment.PatientInfoInfoFragment;
import com.example.epatapp.fragment.PatientInfoStatusFragment;
import com.example.epatapp.models.Patient;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PatientInfoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private ConstraintLayout picker;
    private EditText birth, name, idNum;
    private Patient patient;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_patient_info);
        setComponents();

    }

    private void setComponents() {
        setPicker();
        birth = findViewById(R.id.patient_birth);
        name = findViewById(R.id.patient_name);
        idNum = findViewById(R.id.patient_cmnd);
        patient = (Patient) getIntent().getSerializableExtra("patient");
        name.setText(patient.getFullname());
        birth.setText(new SimpleDateFormat("dd/MM/yyyy").format(patient.getDate_of_birth()));
        idNum.setText(patient.getIdentity_number());
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
        String birthString = day + "/" + month + "/" + year;
        this.birth.setText(birthString);
    }

//    private void setTab() {
//        tab = findViewById(R.id.patient_tab);
//        pager = findViewById(R.id.patient_viewPager);
//        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        adapter.addFragment(new PatientInfoInfoFragment(), "Thông tin");
//        adapter.addFragment(new PatientInfoStatusFragment(), "Trạng thái");
//        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//        pager.setAdapter(adapter);
//        tab.setupWithViewPager(pager);
//
//    }
}