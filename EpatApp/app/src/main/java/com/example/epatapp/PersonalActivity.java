package com.example.epatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.epatapp.adapter.TabAdapter;
import com.example.epatapp.apihelpers.ApiHelper;
import com.example.epatapp.apihelpers.ApiService;
import com.example.epatapp.fragment.DiffPersonInfoFragment;
import com.example.epatapp.fragment.MainPersonInfoFragment;
import com.example.epatapp.models.Account;
import com.example.epatapp.models.Department;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalActivity extends AppCompatActivity{

    private ImageView backButton;
    private EditText name, birth, depart;
    private SharedPreferences sharedPreferences;
    private Account account;
    private Department department;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_personal);
        setComponents();
    }
    private void setComponents(){
        sharedPreferences = getSharedPreferences("authenticated", MODE_PRIVATE);
        account = new Gson().fromJson(sharedPreferences.getString("account", null), Account.class);
        name = findViewById(R.id.personal_name);
        birth = findViewById(R.id.personal_birth);
        depart = findViewById(R.id.personal_department);
        name.setEnabled(false);
        birth.setEnabled(false);
        depart.setEnabled(false);
        name.setText(account.getAccount_name());
        ApiHelper.getInstance().getApiService().getDepartmentById(account.getDepartment_id()).enqueue(new Callback<Department>() {
            @Override
            public void onResponse(Call<Department> call, Response<Department> response) {
                if(response.isSuccessful()){
                    department = response.body();
                    if(department!=null)
                        depart.setText(department.getDepartment_name());
                }
            }

            @Override
            public void onFailure(Call<Department> call, Throwable t) {
                Toast.makeText(PersonalActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });


        //setTabLayout();
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
//                Intent intent = new Intent(PersonalActivity.this,MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });
    }

//    private void setTabLayout(){
//        tabLayout = findViewById(R.id.personal_tab);
//        pager = findViewById(R.id.personal_viewPager);
//
//        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        adapter.addFragment(new MainPersonInfoFragment(),"Thông tin chính");
//        adapter.addFragment(new DiffPersonInfoFragment(),"Thông tin khác");
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//        pager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(pager);
//    }

}