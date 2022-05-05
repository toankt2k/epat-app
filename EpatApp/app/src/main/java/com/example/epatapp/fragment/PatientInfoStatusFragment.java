package com.example.epatapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.epatapp.R;
import com.example.epatapp.TempAddingActivity;
import com.example.epatapp.apihelpers.ApiService;
import com.example.epatapp.models.MedicalRecord;
import com.example.epatapp.models.Status;
import com.example.epatapp.models.Treament;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientInfoStatusFragment extends Fragment {
    private Button updateStatusBtn;
    private TextView spo2, heal_atm, heart, temp;
    private MedicalRecord medicalRecord;
    private List<Status> statusList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patient_info_status, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateStatusBtn = view.findViewById(R.id.updateStatusBtn);
        spo2 = view.findViewById(R.id.sop2_tv);
        temp = view.findViewById(R.id.temp_tv);
        heart = view.findViewById(R.id.heart_tv);
        heal_atm = view.findViewById(R.id.heal_atm_tv);
        medicalRecord = (MedicalRecord) getActivity().getIntent().getSerializableExtra("medical_record");
        setStatus();

        updateStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TempAddingActivity.class);
                intent.putExtra("medical_record", medicalRecord);
                startActivity(intent);
            }
        });
    }

    private void setStatus() {
        Type listType = new TypeToken<ArrayList<Status>>(){}.getType();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        statusList = gson.fromJson(medicalRecord.getStatus(), listType);
        Collections.sort(statusList, new Comparator<Status>() {
            @Override
            public int compare(Status s1, Status s2) {
                return s1.getDate().compareTo(s2.getDate());
            }
        });
        temp.setText("Nhiệt độ\n"+statusList.get(0).getTemp());
        heal_atm.setText("Huyết áp\n"+statusList.get(0).getHeal_atm());
        heart.setText("Nhịp tim\n"+statusList.get(0).getHeart());
        spo2.setText("SPO2\n"+statusList.get(0).getSpo2());
    }

    @Override
    public void onResume() {
        super.onResume();
        ApiService.apiService.getMedicalRecordById(medicalRecord.getMedical_record_id()).enqueue(new Callback<MedicalRecord>() {
            @Override
            public void onResponse(Call<MedicalRecord> call, Response<MedicalRecord> response) {
                medicalRecord = response.body();
                setStatus();
            }

            @Override
            public void onFailure(Call<MedicalRecord> call, Throwable t) {

            }
        });
    }
}