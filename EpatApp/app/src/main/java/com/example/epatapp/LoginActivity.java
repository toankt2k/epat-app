package com.example.epatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.epatapp.apihelpers.ApiService;
import com.example.epatapp.models.Account;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button loginBtn;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        text = findViewById(R.id.loginLabel);
        callApi();
    }
    public void login(){
        Intent intent = new Intent(this,HomeActivity.class);
        intent.putExtra("userName","toankt2k");
        startActivity(intent);
    }
    private void setComponent(){
        this.loginBtn = findViewById(R.id.loginBtn);
        this.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    private void callApi(){
        ApiService.apiService.getAccounts().enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                List<Account> test = response.body();
                text.setText(test.get(0).getAccount_name());
                Toast.makeText(LoginActivity.this,"success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                Log.e("apitoan",t.toString());
                Toast.makeText(LoginActivity.this,"fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}