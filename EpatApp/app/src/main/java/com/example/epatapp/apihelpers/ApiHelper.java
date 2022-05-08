package com.example.epatapp.apihelpers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.epatapp.LoginActivity;
import com.example.epatapp.MainActivity;
import com.example.epatapp.models.Account;
import com.example.epatapp.models.ResultLogin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {
    private SharedPreferences sharedPreferences;
    private OkHttpClient client;
    private Gson gson;
    private ApiService apiService;

    public static final ApiHelper INSTANCE = new ApiHelper();

    private ApiHelper() {
        this.client = new OkHttpClient.Builder()
                .authenticator(new Authenticator() {
                    @Nullable
                    @Override
                    public Request authenticate(@Nullable Route route, Response response) throws IOException {
                        Account account = new Gson().fromJson(ApiHelper.this.sharedPreferences.getString("account", null), Account.class);
                        retrofit2.Response<ResultLogin> response1 = ApiTokens.apiService.loginToken(account).execute();
                        if(response1.isSuccessful()){
                            ResultLogin resultLogin = response1.body();
                            String token = resultLogin.getToken();
                            if(token!=null){
                                if(!token.isEmpty()){
                                    sharedPreferences.edit().putString("token", token).commit();
                                    return response.request().newBuilder()
                                            .addHeader("Authorization", "Bearer " + token)
                                            .build();
                                }
                            }
                        }
                        return null;
                    }
                })
                .addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + ApiHelper.this.sharedPreferences.getString("token", null))
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

    this.gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
    this.apiService = new Retrofit.Builder()
            .client(client)
            .baseUrl("http://168.138.171.44:5000/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    }

    public static ApiHelper getInstance(){
         return INSTANCE;
    }

    public ApiService getApiService() {
        return apiService;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }
}
