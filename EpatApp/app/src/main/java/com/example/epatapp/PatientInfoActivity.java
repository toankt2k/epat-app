package com.example.epatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Window;

import com.example.epatapp.adapter.TabAdapter;
import com.example.epatapp.models.Patient;
import com.google.android.material.tabs.TabLayout;

public class PatientInfoActivity extends AppCompatActivity {

    ViewPager pager;
    TabLayout tab;
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_patient_info);
        setComponents();
    }

    private void setComponents() {
        setTab();
    }

    private void setTab() {
        tab = findViewById(R.id.patient_tab);
        pager = findViewById(R.id.patient_viewPager);
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new PatientInfoInfoFragment(), "Thông tin");
        adapter.addFragment(new PatientInfoStatusFragment(), "Trạng thái");
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        tab.setupWithViewPager(pager);

    }
}