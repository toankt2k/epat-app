package com.example.epatapp.apihelpers;

import com.example.epatapp.models.MedicalRecord;
import com.example.epatapp.models.ResultPatient;
import com.example.epatapp.models.Account;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://168.138.171.44:5000/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET("Accounts")
    Call<List<Account>> getAccounts();
    @POST("Accounts/login")
    Call<Account> login(@Body Account account);
    @GET("Patients/filter")
    Call<ResultPatient> filterPatient(@Query("textFilter") String name);
    @GET("MedicalRecords/patient/{id}")
    Call<List<MedicalRecord>> getMedicalRecords(@Path("id") String id);
    @PUT("MedicalRecords")
    Call<MedicalRecord> updateMedicalRecord(@Body MedicalRecord medicalRecord);
    @GET("MedicalRecords/{id}")
    Call<MedicalRecord> getMedicalRecordById(@Path("id") String id);

}
