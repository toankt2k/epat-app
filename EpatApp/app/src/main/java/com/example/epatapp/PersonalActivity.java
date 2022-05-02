package com.example.epatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Window;


import com.example.epatapp.adapter.TabAdapter;
import com.google.android.material.tabs.TabLayout;

public class PersonalActivity extends AppCompatActivity{

    TabLayout tabLayout;
    ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_personal);
        setComponents();
    }
    private void setComponents(){
        setTabLayout();
    }

    private void setTabLayout(){
        tabLayout = findViewById(R.id.personal_tab);
        pager = findViewById(R.id.personal_viewPager);

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new MainPersonInfoFragment(),"Thông tin chính");
        adapter.addFragment(new DiffPersonInfoFragment(),"Thông tin khác");
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