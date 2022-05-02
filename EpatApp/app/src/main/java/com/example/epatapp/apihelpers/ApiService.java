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
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder().create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://168.138.171.44:5000/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET("Accounts")
    Call<List<Account>> getAccounts();
    @POST("Accounts/login")
    Call<ResponseBody> login(Account account);
    @GET("Patients/filter")
    Call<List<Patient>> filterPatient(@Query("textFilter") String name);

}
