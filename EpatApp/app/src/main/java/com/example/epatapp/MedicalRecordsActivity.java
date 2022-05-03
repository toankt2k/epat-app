package com.example.epatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.epatapp.adapter.MediaRecordAdapter;
import com.example.epatapp.apihelpers.ApiService;
import com.example.epatapp.models.MedicalRecord;
import com.example.epatapp.models.Patient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicalRecordsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MediaRecordAdapter adapter;
    private List<MedicalRecord> list;
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_records);
        recyclerView = findViewById(R.id.medicalRecords);
        list = new ArrayList<>();
        patient = (Patient) getIntent().getSerializableExtra("patient");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MediaRecordAdapter(this, list);
        recyclerView.setAdapter(adapter);
        ApiService.apiService.getMediaRecords(patient.getPatient_id()).enqueue(new Callback<List<MedicalRecord>>() {
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
}