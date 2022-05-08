package com.example.epatapp.apihelpers;

import com.example.epatapp.models.Account;
import com.example.epatapp.models.ResultLogin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiTokens {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
    ApiTokens apiService = new Retrofit.Builder()
            .baseUrl("http://168.138.171.44:5000/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiTokens.class);
    @POST("Tokens")
    Call<ResultLogin> loginToken(@Body Account account);
}
