package com.example.epatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.epatapp.adapter.MedicalRecordAdapter;
import com.example.epatapp.apihelpers.ApiHelper;
import com.example.epatapp.apihelpers.ApiService;
import com.example.epatapp.models.MedicalRecord;
import com.example.epatapp.models.Patient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicalRecordsActivity extends AppCompatActivity {
    private ImageView back;
    private TextView name, code;
    private RecyclerView recyclerView;
    private MedicalRecordAdapter adapter;
    private List<MedicalRecord> list;
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_medical_records);

        patient = (Patient) getIntent().getSerializableExtra("patient");

        name = findViewById(R.id.patientName);
        code = findViewById(R.id.patientCode);
        name.setText(patient.getFullname());
        code.setText(patient.getPatient_code());

        back = findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.medicalRecords);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MedicalRecordAdapter(this, list);
        adapter.setPatient(patient);
        recyclerView.setAdapter(adapter);
        callApi();
    }

    private void callApi() {
        ApiHelper.apiService.getMedicalRecords(patient.getPatient_id()).enqueue(new Callback<List<MedicalRecord>>() {
            @Override
            public void onResponse(Call<List<MedicalRecord>> call, Response<List<MedicalRecord>> response) {
                List<MedicalRecord> medicalRecords = response.body();
                adapter.setList(medicalRecords);
            }

            @Override
            public void onFailure(Call<List<MedicalRecord>> call, Throwable t) {
                System.out.println("Lỗi: "+t.toString());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        callApi();
    }
}