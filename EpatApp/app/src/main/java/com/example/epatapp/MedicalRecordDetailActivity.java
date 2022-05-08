package com.example.epatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.epatapp.adapter.TabAdapter;
import com.example.epatapp.fragment.HistoryOfTreatmentFragment;
import com.example.epatapp.fragment.MedicalInforFragment;
import com.example.epatapp.fragment.PatientInfoStatusFragment;
import com.example.epatapp.models.Patient;
import com.google.android.material.tabs.TabLayout;

public class MedicalRecordDetailActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager pager;
    private TextView name, code;
    private ImageView back;
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_medical_record_detail);
        setComponents();
    }
    private void setComponents(){
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
        setTabLayout();
    }

    private void setTabLayout(){
        tabLayout = findViewById(R.id.medical_record_tab);
        pager = findViewById(R.id.medical_record_viewPager);

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new MedicalInforFragment(),"Thông tin chính");
        adapter.addFragment(new HistoryOfTreatmentFragment(),"Lịch sử điều trị");
        adapter.addFragment(new PatientInfoStatusFragment(), "Trạng thái");
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
    }

}