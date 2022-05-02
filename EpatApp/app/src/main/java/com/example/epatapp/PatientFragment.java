package com.example.epatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epatapp.apihelpers.ApiService;
import com.example.epatapp.models.Patient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientFragment extends Fragment {
    private SearchView searchView;
    private List<Patient> list;
    private RecyclerView recyclerView;
    private PatientAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.patient_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView = view.findViewById(R.id.searchView);
        recyclerView = view.findViewById(R.id.patRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        adapter = new PatientAdapter(list);
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ApiService.apiService.filterPatient(s).enqueue(new Callback<List<Patient>>() {
                    @Override
                    public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
                        List<Patient> list1 = response.body();
                        Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
                        adapter.setList(list1);
                    }

                    @Override
                    public void onFailure(Call<List<Patient>> call, Throwable t) {
                        System.out.println(t.toString());
                    }
                });
                return true;
            }

        });
//        btnSearch = view.findViewById(R.id.search_btn);
//        patientName = view.findViewById(R.id.pat_code);
//        btnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Gọi api tìm kiếm
//                String name = patientName.getText().toString();
//                ApiService.apiService.filterPatient(name).enqueue(new Callback<Patient>() {
//                    @Override
//                    public void onResponse(Call<Patient> call, Response<Patient> response) {
//                        Patient patient = response.body();
//                        Toast.makeText(getContext(), patient.toString(), Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getContext(), PatientActivity.class);
//                        intent.putExtra("patient", patient);
//                        startActivity(intent);
//                    }
//
//                    @Override
//                    public void onFailure(Call<Patient> call, Throwable t) {
//
//                    }
//                });
//            }
//        });
    }
}
