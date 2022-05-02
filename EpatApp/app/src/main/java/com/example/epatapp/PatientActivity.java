package com.example.epatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PatientActivity extends AppCompatActivity {

    private ArrayList<String> funcName = new ArrayList<>();
    private ListView funcs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_patient);
        setCommponents();
    }

    private void setCommponents(){
        setListFunc();

    }

    private void setListFunc(){
        funcs = findViewById(R.id.list_func);
        funcName.add("Thông tin bệnh nhân");
        funcName.add("Thông tin bệnh án");
        funcName.add("Thông tin điều trị");
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_activated_1,funcName);

        funcs.setAdapter(adapter);
        funcs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        //chọn tt bệnh nhân
                        Intent intent = new Intent(PatientActivity.this, PatientInfoActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        //chọn tt bệnh án
                        Intent intent2 = new Intent(PatientActivity.this, PatOfPatientInfoActivity.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        //chọn tt điều trị
                        Intent intent3 = new Intent(PatientActivity.this, HistoryOfTreatmentInfor.class);
                        startActivity(intent3);
                        break;
                }
            }
        });
    }
}