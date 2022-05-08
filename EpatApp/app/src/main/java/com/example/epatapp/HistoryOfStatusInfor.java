package com.example.epatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.epatapp.adapter.StatusAdapter;
import com.example.epatapp.apihelpers.ApiHelper;
import com.example.epatapp.apihelpers.ApiService;
import com.example.epatapp.models.MedicalRecord;
import com.example.epatapp.models.Patient;
import com.example.epatapp.models.Status;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryOfStatusInfor extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private MedicalRecord medicalRecord;
    private StatusAdapter adapter;
    private List<Status> statusList;
    private TextView name, code;
    private ImageView back;
    private Patient patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_history_of_status_infor);

        name = findViewById(R.id.patientName);
        code = findViewById(R.id.patientCode);
        patient = (Patient) getIntent().getSerializableExtra("patient");
        name.setText(patient.getFullname());
        code.setText(patient.getPatient_code());
        back = findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        medicalRecord = (MedicalRecord) getIntent().getSerializableExtra("medical_record");
        Type listType = new TypeToken<ArrayList<Status>>(){}.getType();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        statusList = gson.fromJson(medicalRecord.getStatus(), listType);
        if(statusList == null) statusList = new ArrayList<>();

        adapter = new StatusAdapter(this, statusList);
        recyclerView.setAdapter(adapter);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryOfStatusInfor.this, TempAddingActivity.class);
                intent.putExtra("medical_record", medicalRecord);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ApiHelper.apiService.getMedicalRecordById(medicalRecord.getMedical_record_id()).enqueue(new Callback<MedicalRecord>() {
            @Override
            public void onResponse(Call<MedicalRecord> call, Response<MedicalRecord> response) {
                medicalRecord = response.body();
                Type listType = new TypeToken<ArrayList<Status>>(){}.getType();
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                statusList = gson.fromJson(medicalRecord.getStatus(), listType);
                if(statusList == null) statusList = new ArrayList<>();
                adapter.setList(statusList);

            }

            @Override
            public void onFailure(Call<MedicalRecord> call, Throwable t) {
                Toast.makeText(HistoryOfStatusInfor.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}