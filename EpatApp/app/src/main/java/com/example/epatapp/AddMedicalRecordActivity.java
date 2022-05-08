package com.example.epatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.epatapp.apihelpers.ApiHelper;
import com.example.epatapp.models.Account;
import com.example.epatapp.models.MedicalRecord;
import com.example.epatapp.models.Patient;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMedicalRecordActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private ConstraintLayout picker;
    private ImageView back;
    private TextView tvName;
    private EditText dateIn, diagnose, diseases, sympton;
    private Button btnUpdate;
    private Patient patient;
    private SharedPreferences sharedPreferences;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_medical_record);
        sharedPreferences = getSharedPreferences("authenticated", MODE_PRIVATE);
        account = new Gson().fromJson(sharedPreferences.getString("account", null), Account.class);
        patient = (Patient) getIntent().getSerializableExtra("patient");
        setComponents();
    }
    private void setComponents(){
        setPicker();
        back = findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvName = findViewById(R.id.patientName);
        tvName.setText(patient.getFullname());

        dateIn = findViewById(R.id.dateIn);
        diagnose = findViewById(R.id.diagnose);
        diseases = findViewById(R.id.diseases);
        sympton = findViewById(R.id.symptom);
        btnUpdate = findViewById(R.id.addBtn);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = dateIn.getText().toString();
                String dia = diagnose.getText().toString();
                String dis = diseases.getText().toString();
                String sym = sympton.getText().toString();
                if(!date.isEmpty() && !dis.isEmpty() && !dia.isEmpty() && ! sym.isEmpty()){
                    try {
                        MedicalRecord medicalRecord = new MedicalRecord();
                        medicalRecord.setPatient_id(patient.getPatient_id());
                        medicalRecord.setHospitalized_day(new SimpleDateFormat("dd/MM/yyyy").parse(date));
                        medicalRecord.setDiseases(dis);
                        medicalRecord.setDiagnose(dia);
                        medicalRecord.setSymptom(sym);
                        ApiHelper.getInstance().getApiService().addMedicalRecord(medicalRecord).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(AddMedicalRecordActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else{
                                    Toast.makeText(AddMedicalRecordActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(AddMedicalRecordActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
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
        picker = findViewById(R.id.calendar_picker);
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
        String birthString = day + "/" + month + "/" + year;
        this.dateIn.setText(birthString);
    }
}