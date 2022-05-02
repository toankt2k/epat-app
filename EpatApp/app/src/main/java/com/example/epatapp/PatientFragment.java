package com.example.epatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.epatapp.apihelpers.ApiService;
import com.example.epatapp.models.Patient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientFragment extends Fragment {
    private Button btnSearch;
    private TextView idPatient;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.patient_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSearch = view.findViewById(R.id.search_btn);
        idPatient = view.findViewById(R.id.pat_code);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Gọi api tìm kiếm
                String id = idPatient.getText().toString();
                ApiService.apiService.getPatient(id).enqueue(new Callback<Patient>() {
                    @Override
                    public void onResponse(Call<Patient> call, Response<Patient> response) {
                        Patient patient = response.body();
                        Toast.makeText(getContext(), patient.toString(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), PatientActivity.class);
                        intent.putExtra("patient", patient);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Patient> call, Throwable t) {

                    }
                });
            }
        });
    }
}
