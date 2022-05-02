package com.example.epatapp.apihelpers;

import com.example.epatapp.models.Account;
import com.example.epatapp.models.Patient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    Gson gson = new GsonBuilder().create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.3:5234/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET("Accounts")
    Call<List<Account>> getAccounts();
    @POST("Accounts/login")
    Call<ResponseBody> login(Account account);
    @GET("Patients/{id}")
    Call<Patient> getPatient(@Path("id") String id);

}
