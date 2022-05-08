package com.example.epatapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epatapp.R;
import com.example.epatapp.TreatmentAddingActivity;
import com.example.epatapp.adapter.TreamentAdapter;
import com.example.epatapp.apihelpers.ApiService;
import com.example.epatapp.models.MedicalRecord;
import com.example.epatapp.models.Patient;
import com.example.epatapp.models.Treament;
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

public class HistoryOfTreatmentFragment extends Fragment {
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private MedicalRecord medicalRecord;
    private TreamentAdapter adapter;
    private List<Treament> treamentList;
    private Patient patient;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_treatment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.treament_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        patient = (Patient) getActivity().getIntent().getSerializableExtra("patient");
        medicalRecord = (MedicalRecord) getActivity().getIntent().getSerializableExtra("medical_record");

        Type listType = new TypeToken<ArrayList<Treament>>(){}.getType();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        treamentList = gson.fromJson(medicalRecord.getTreatment(), listType);
        if(treamentList == null) treamentList = new ArrayList<>();
        adapter = new TreamentAdapter(getContext(), treamentList);
        recyclerView.setAdapter(adapter);

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TreatmentAddingActivity.class);
                intent.putExtra("patient", patient);
                intent.putExtra("medical_record", medicalRecord);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ApiService.apiService.getMedicalRecordById(medicalRecord.getMedical_record_id()).enqueue(new Callback<MedicalRecord>() {
            @Override
            public void onResponse(Call<MedicalRecord> call, Response<MedicalRecord> response) {
                medicalRecord = response.body();
                Type listType = new TypeToken<ArrayList<Treament>>(){}.getType();
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                treamentList = gson.fromJson(medicalRecord.getTreatment(), listType);
                if(treamentList == null) treamentList = new ArrayList<>();
                adapter.setList(treamentList);
            }

            @Override
            public void onFailure(Call<MedicalRecord> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
