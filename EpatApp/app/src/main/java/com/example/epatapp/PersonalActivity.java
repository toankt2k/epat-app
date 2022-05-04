package com.example.epatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;


import com.example.epatapp.adapter.TabAdapter;
import com.example.epatapp.fragment.DiffPersonInfoFragment;
import com.example.epatapp.fragment.MainPersonInfoFragment;
import com.google.android.material.tabs.TabLayout;

public class PersonalActivity extends AppCompatActivity{

    TabLayout tabLayout;
    ViewPager pager;
    ImageView backButton;
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
        //set các action button trong màn hình
        setActionButton();
    }

    /*
    * Hàm set sự kiện cho các button trong màn hình thông tin cá nhân
    * Author: quyetkaito (02/05/2022)
    * */
    private void setActionButton() {
        //nút quay lại màn hình trước.
        backButton = findViewById(R.id.back_change_personal_act_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent chuyển trở lại màn hình trang chủ.
                Intent intent = new Intent(PersonalActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
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