package com.example.epatapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.epatapp.apihelpers.ApiService;
import com.example.epatapp.models.Account;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button loginBtn;
    private TextView text;
    private EditText eUsername, ePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        text = findViewById(R.id.loginLabel);
        eUsername = findViewById(R.id.editTextTextPersonName);
        ePassword = findViewById(R.id.editTextTextPassword);
    }
    public void login(){
        String username = eUsername.getText().toString();
        String password = ePassword.getText().toString();
        if(!username.isEmpty() && !password.isEmpty()){
            Account account = new Account(username, password);
            callApi(account);
        }
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
    private void callApi(Account account){
        ApiService.apiService.login(account).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}