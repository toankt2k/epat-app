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
import com.example.epatapp.models.Treament;
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

public class TreatmentAddingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ConstraintLayout pickerDate;
    private EditText dateIn, pickerTime, progress;
    private Button btnAdd;
    private MedicalRecord medicalRecord;
    private List<Treament> treamentList;
    private TextView name;
    private ImageView back;
    private Gson gson;
    private Patient patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_treatment_adding);
        medicalRecord = (MedicalRecord) getIntent().getSerializableExtra("medical_record");
        Type listType = new TypeToken<ArrayList<Treament>>(){}.getType();
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        treamentList = gson.fromJson(medicalRecord.getTreatment(), listType);
        if (treamentList == null) treamentList = new ArrayList<>();
        setComponents();
    }

    private void setComponents() {
        setPicker();
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

        dateIn = findViewById(R.id.date_treament);
        progress = findViewById(R.id.progress);
        btnAdd = findViewById(R.id.treatment_submit_btn);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = dateIn.getText().toString();
                String time = pickerTime.getText().toString();
                String prog = progress.getText().toString();
                if(!date.isEmpty() && !time.isEmpty() && !prog.isEmpty()){
                    String dateTime = date+" " +time;
                    try {
                        Date dt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dateTime);
                        Treament treament = new Treament(dt, prog);
                        treamentList.add(treament);
                        medicalRecord.setTreatment(gson.toJson(treamentList));
                        ApiHelper.apiService.updateMedicalRecord(medicalRecord).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(TreatmentAddingActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Toast.makeText(TreatmentAddingActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(TreatmentAddingActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
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
        pickerDate = findViewById(R.id.treatment_calendar_picker);
        pickerTime = findViewById(R.id.time_treament);
        pickerTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int hh = c.get(Calendar.HOUR_OF_DAY);
                int mm = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(TreatmentAddingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        pickerTime.setText(i+":"+i1+":00");
                    }
                }, hh, mm, true);
                timePickerDialog.show();
            }
        });
        pickerDate.setOnClickListener(new View.OnClickListener() {
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
        month = month+1;
        String dateString = day + "/" + month + "/" + year;
        this.dateIn.setText(dateString);
    }
}