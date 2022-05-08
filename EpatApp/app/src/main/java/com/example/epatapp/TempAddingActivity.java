package com.example.epatapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.epatapp.apihelpers.ApiHelper;
import com.example.epatapp.apihelpers.ApiService;
import com.example.epatapp.models.MedicalRecord;
import com.example.epatapp.models.Patient;
import com.example.epatapp.models.Status;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TempAddingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ConstraintLayout picker;
    private EditText dateIn, timeIn, tempValue, heal_atm_value, heart_value, spo2_value;
    private Button btnAdd;
    private MedicalRecord medicalRecord;
    private List<Status> statusList;
    private Gson gson;
    private TextView name;
    private ImageView back;
    private Patient patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_temp_adding);

        name = findViewById(R.id.patientName);
        patient = (Patient) getIntent().getSerializableExtra("patient");
        name.setText(patient.getFullname());
        back = findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        medicalRecord = (MedicalRecord) getIntent().getSerializableExtra("medical_record");
        Type listType = new TypeToken<ArrayList<Status>>(){}.getType();
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        statusList = gson.fromJson(medicalRecord.getStatus(), listType);
        if(statusList == null) statusList = new ArrayList<>();

        setComponents();
    }

    private void setComponents() {
        setPicker();
        dateIn = findViewById(R.id.date_in);
        tempValue = findViewById(R.id.temp_value);
        heal_atm_value = findViewById(R.id.heal_atm_value);
        heart_value =findViewById(R.id.heart_value);
        spo2_value = findViewById(R.id.spo2_value);
        btnAdd = findViewById(R.id.btn_add_status);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = dateIn.getText().toString();
                String time = timeIn.getText().toString();
                String temp = tempValue.getText().toString();
                String heal_atm = heal_atm_value.getText().toString();
                String heart = heart_value.getText().toString();
                String spo2 = spo2_value.getText().toString();
                if(!date.isEmpty() && !time.isEmpty() && !temp.isEmpty() && !heal_atm.isEmpty() && !heart.isEmpty() && !spo2.isEmpty()){
                    try {
                        Date dateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(date+" "+time);
                        Status status = new Status(dateTime, temp, heart, heal_atm, spo2);
                        statusList.add(status);
                        medicalRecord.setStatus(gson.toJson(statusList));
                        ApiHelper.getInstance().getApiService().updateMedicalRecord(medicalRecord).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(TempAddingActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else{
                                    Toast.makeText(TempAddingActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(TempAddingActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void setPicker() {
        timeIn = findViewById(R.id.temp_time);
        timeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int hh = c.get(Calendar.HOUR_OF_DAY);
                int mm = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(TempAddingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        timeIn.setText(i+":"+i1+":00");
                    }
                }, hh, mm, true);
                timePickerDialog.show();
            }
        });
        picker = findViewById(R.id.temp_calendar_picker);
        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPicker();
            }
        });
    }

    private void showPicker() {
        DatePickerDialog dialog = new DatePickerDialog(this,this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String dateString = day + "/" + month + "/" + year;
        this.dateIn.setText(dateString);
    }
}