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
import android.widget.TextView;
import android.widget.Toast;

import com.example.epatapp.HistoryOfStatusInfor;
import com.example.epatapp.R;
import com.example.epatapp.TempAddingActivity;
import com.example.epatapp.apihelpers.ApiHelper;
import com.example.epatapp.apihelpers.ApiService;
import com.example.epatapp.models.MedicalRecord;
import com.example.epatapp.models.Patient;
import com.example.epatapp.models.Status;
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
    private Button historyBtn;
    private TextView spo2, heal_atm, heart, temp;
    private MedicalRecord medicalRecord;
    private List<Status> statusList;
    private Patient patient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patient_info_status, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        historyBtn = view.findViewById(R.id.historyStatusBtn);
        spo2 = view.findViewById(R.id.sop2_tv);
        temp = view.findViewById(R.id.temp_tv);
        heart = view.findViewById(R.id.heart_tv);
        heal_atm = view.findViewById(R.id.heal_atm_tv);
        medicalRecord = (MedicalRecord) getActivity().getIntent().getSerializableExtra("medical_record");
        patient= (Patient) getActivity().getIntent().getSerializableExtra("patient");
        setStatus();

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HistoryOfStatusInfor.class);
                intent.putExtra("medical_record", medicalRecord);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });
    }

    private void setStatus() {
        Type listType = new TypeToken<ArrayList<Status>>(){}.getType();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        statusList = gson.fromJson(medicalRecord.getStatus(), listType);
        if(statusList!=null) {
            if(statusList.size()>0) {
                Collections.sort(statusList, new Comparator<Status>() {
                    @Override
                    public int compare(Status s1, Status s2) {
                        return s2.getDate().compareTo(s1.getDate());
                    }
                });
                temp.setText("Nhiệt độ\n" + statusList.get(0).getTemp() + "°C");
                heal_atm.setText("Huyết áp\n" + statusList.get(0).getHeal_atm() + "mmHg");
                heart.setText("Nhịp tim\n" + statusList.get(0).getHeart() + "bpm");
                spo2.setText("SPO2\n" + statusList.get(0).getSpo2() + "%");
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ApiHelper.getInstance().getApiService().getMedicalRecordById(medicalRecord.getMedical_record_id()).enqueue(new Callback<MedicalRecord>() {
            @Override
            public void onResponse(Call<MedicalRecord> call, Response<MedicalRecord> response) {
                if(response.isSuccessful()) {
                    medicalRecord = response.body();
                    setStatus();
                }
            }

            @Override
            public void onFailure(Call<MedicalRecord> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}