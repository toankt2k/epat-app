package com.example.epatapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.epatapp.apihelpers.ApiHelper;
import com.example.epatapp.apihelpers.ApiService;
import com.example.epatapp.apihelpers.ApiTokens;
import com.example.epatapp.models.Account;
import com.example.epatapp.models.ResultLogin;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button loginBtn;
    private TextView text;
    private EditText eUsername, ePassword;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("authenticated", MODE_PRIVATE);

        String token = sharedPreferences.getString("token", null);
        if(token!=null){
            ApiHelper.token = token;
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        text = findViewById(R.id.loginLabel);
        eUsername = findViewById(R.id.editTextTextPersonName);
        ePassword = findViewById(R.id.editTextTextPassword);
        setComponent();
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

    //gọi api login
    private void callApi(Account account){
        ApiTokens.apiService.loginToken(account).enqueue(new Callback<ResultLogin>() {
            @Override
            public void onResponse(Call<ResultLogin> call, Response<ResultLogin> response) {
                if(response.isSuccessful()){
                    ResultLogin resultLogin = response.body();
                    Account account1 = resultLogin.getAccount();
                    String accountJson = new Gson().toJson(account1);
                    String token = resultLogin.getToken();
                    if(token!=null){
                        if(!token.isEmpty()){
                            sharedPreferences.edit().putString("account", accountJson).commit();
                            sharedPreferences.edit().putString("token", token).commit();
                            ApiHelper.token = token;
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);

                        }
                    }

                }else{
                    Toast.makeText(LoginActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

}