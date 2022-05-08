package com.example.epatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.epatapp.apihelpers.ApiService;
import com.example.epatapp.models.Account;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {
    private EditText old_pass, new_pass, retype_new_pass;
    private SharedPreferences sharedPreferences;
    private Button btnUpdate;
    private Account account;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_change_password);
        setComponents();
    }

    private void setComponents() {
        sharedPreferences = getSharedPreferences("authenticated", MODE_PRIVATE);
        account = new Gson().fromJson(sharedPreferences.getString("account", null), Account.class);
        old_pass = findViewById(R.id.old_password);
        new_pass = findViewById(R.id.new_password);
        retype_new_pass = findViewById(R.id.retype_new_password);
        btnUpdate = findViewById(R.id.change_password_btn);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String old = old_pass.getText().toString();
                String new_p = new_pass.getText().toString();
                String re_new = retype_new_pass.getText().toString();
                if(account.getPassword().equals(old) && new_p.equals(re_new) && !new_p.isEmpty() && !re_new.isEmpty()){
                    account.setPassword(new_p);
                    ApiService.apiService.updateAccount(account).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.isSuccessful()){
                                sharedPreferences.edit().putString("account", null).commit();
                                Intent intent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                        Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(ChangePasswordActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();

                        }
                    });
                }else {
                    Toast.makeText(ChangePasswordActivity.this, "Nhập lại thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back = findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}