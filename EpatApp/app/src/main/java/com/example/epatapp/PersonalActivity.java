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

/**
 * Activity của thông tin cá nhân
 */
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

    /**
     * Set sự kiện cho các component trong màn hình
     */
    private void setComponents(){
        sharedPreferences = getSharedPreferences("authenticated", MODE_PRIVATE);
        account = new Gson().fromJson(sharedPreferences.getString("account", null), Account.class);
        name = findViewById(R.id.personal_name);
        birth = findViewById(R.id.personal_birth);
        depart = findViewById(R.id.personal_department);

        //cho phép chỉnh sửa
        name.setEnabled(true);
        birth.setEnabled(true);
        depart.setEnabled(false);
        if(account!=null){
            //bind tên người dùng
            name.setText(account.getAccount_name());
            //gọi API lấy tên vị trí làm việc
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
        }


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
                finish();
            }
        });
    }

}