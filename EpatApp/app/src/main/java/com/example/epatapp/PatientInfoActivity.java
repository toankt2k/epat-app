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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.epatapp.adapter.TabAdapter;
import com.example.epatapp.apihelpers.ApiService;
import com.example.epatapp.fragment.PatientInfoInfoFragment;
import com.example.epatapp.fragment.PatientInfoStatusFragment;
import com.example.epatapp.models.Patient;
import com.google.android.material.tabs.TabLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientInfoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private ConstraintLayout picker;
    private ImageView back;
    private EditText birth, name, idNum, phone, address;
    private Patient patient;
    private Button btnUpdate;
    private TextView tvName;
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

        patient = (Patient) getIntent().getSerializableExtra("patient");

        tvName = findViewById(R.id.personName);
        tvName.setText(patient.getFullname());

        birth = findViewById(R.id.patient_birth);
        name = findViewById(R.id.patient_name);
        idNum = findViewById(R.id.patient_cmnd);
        phone = findViewById(R.id.patient_phone);
        address = findViewById(R.id.patient_address);
        btnUpdate = findViewById(R.id.btn_update_patient);

        name.setText(patient.getFullname());
        birth.setText(new SimpleDateFormat("dd/MM/yyyy").format(patient.getDate_of_birth()));
        idNum.setText(patient.getIdentity_number());
        phone.setText(patient.getPhone_number());
        address.setText(patient.getAddress());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = name.getText().toString();
                String date = birth.getText().toString();
                String id = idNum.getText().toString();
                String ph = phone.getText().toString();
                String add = address.getText().toString();
                if(!n.isEmpty() && !date.isEmpty() && !id.isEmpty() && !ph.isEmpty() && !add.isEmpty()){
                    try {
                        patient.setFullname(n);
                        patient.setDate_of_birth(new SimpleDateFormat("dd/MM/yyyy").parse(date));
                        patient.setIdentity_number(id);
                        patient.setPhone_number(ph);
                        patient.setAddress(add);
                        System.out.println("..............."+patient.toString());
                        ApiService.apiService.updatePatient(patient).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if(response.isSuccessful()) {
                                    Toast.makeText(PatientInfoActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else{
                                    Toast.makeText(PatientInfoActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(PatientInfoActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        back = findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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