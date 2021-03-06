package com.example.epatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.epatapp.apihelpers.ApiHelper;
import com.example.epatapp.apihelpers.ApiService;
import com.example.epatapp.models.Patient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientActivity extends AppCompatActivity {

    private ImageView back;
    private ArrayList<String> funcName = new ArrayList<>();
    private ListView funcs;
    private Patient patient;
    private TextView name, code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_patient);
        patient = (Patient) getIntent().getSerializableExtra("patient");
        setCommponents();
    }

    private void setCommponents(){
        name = findViewById(R.id.personName);
        code = findViewById(R.id.personCode);
        name.setText(patient.getFullname());
        code.setText(patient.getPatient_code());
        back = findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setListFunc();

    }

    private void setListFunc(){
        funcs = findViewById(R.id.list_func);
        funcName.add("Thông tin bệnh nhân");
        funcName.add("Thông tin bệnh án");
        //        funcName.add("Thông tin điều trị");
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_activated_1,funcName);

        funcs.setAdapter(adapter);
        funcs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        //chọn tt bệnh nhân
                        Intent intent = new Intent(PatientActivity.this, PatientInfoActivity.class);
                        intent.putExtra("patient", patient);
                        startActivity(intent);
                        break;
                    case 1:
                        //chọn tt bệnh án
                        Intent intent2 = new Intent(PatientActivity.this, MedicalRecordsActivity.class);
                        intent2.putExtra("patient", patient);
                        startActivity(intent2);
                        break;
//                    case 2:
//                        //chọn tt điều trị
//                        Intent intent3 = new Intent(PatientActivity.this, HistoryOfTreatmentInfor.class);
//                        startActivity(intent3);
//                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ApiHelper.getInstance().getApiService().getPatientById(patient.getPatient_id()).enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                if(response.isSuccessful())
                    patient = response.body();
            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {
                Toast.makeText(PatientActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}