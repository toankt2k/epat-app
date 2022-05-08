package com.example.epatapp.apihelpers;

import com.example.epatapp.models.Department;
import com.example.epatapp.models.MedicalRecord;
import com.example.epatapp.models.Patient;
import com.example.epatapp.models.ResultLogin;
import com.example.epatapp.models.ResultPatient;
import com.example.epatapp.models.Account;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
//http://168.138.171.44:5000/api/v1/
public interface ApiService {

    @GET("Accounts")
    Call<List<Account>> getAccounts();

    @PUT("Accounts")
    Call<ResponseBody> updateAccount(@Body Account account);

    @POST("Accounts/login")
    Call<Account> login(@Body Account account);

    @GET("Departments/{id}")
    Call<Department> getDepartmentById(@Path("id") String id);

    @GET("Patients/filter")
    Call<ResultPatient> filterPatient(@Query("textFilter") String name);

    @GET("Patients/{id}")
    Call<Patient> getPatientById(@Path("id") String id);

    @PUT("Patients")
    Call<ResponseBody> updatePatient(@Body Patient patient);

    @GET("MedicalRecords/patient/{id}")
    Call<List<MedicalRecord>> getMedicalRecords(@Path("id") String id);

    @PUT("MedicalRecords")
    Call<ResponseBody> updateMedicalRecord(@Body MedicalRecord medicalRecord);

    @GET("MedicalRecords/{id}")
    Call<MedicalRecord> getMedicalRecordById(@Path("id") String id);

    @POST("MedicalRecords")
    Call<ResponseBody> addMedicalRecord(@Body MedicalRecord medicalRecord);

}