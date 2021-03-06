package com.example.epatapp.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.epatapp.R;
import com.example.epatapp.apihelpers.ApiHelper;
import com.example.epatapp.apihelpers.ApiService;
import com.example.epatapp.models.MedicalRecord;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicalInforFragment extends Fragment implements DatePickerDialog.OnDateSetListener{
    ConstraintLayout picker;
    EditText dateIn, diagnose, diseases, sympton;
    Button btnUpdate;
    MedicalRecord medicalRecord;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medical_infor, container, false);
        setComponents(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        medicalRecord = (MedicalRecord) getActivity().getIntent().getSerializableExtra("medical_record");
        dateIn.setText(new SimpleDateFormat("dd/MM/yyyy").format(medicalRecord.getHospitalized_day()));
        diseases.setText(medicalRecord.getDiseases());
        diagnose.setText(medicalRecord.getDiagnose());
        sympton.setText(medicalRecord.getSymptom());
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = dateIn.getText().toString();
                String reason = diagnose.getText().toString();
                String old_info = diseases.getText().toString();
                String symp = sympton.getText().toString();
                if(!date.isEmpty() && !reason.isEmpty() && !old_info.isEmpty() && !symp.isEmpty()){
                    try {
                        medicalRecord.setHospitalized_day(new SimpleDateFormat("dd/MM/yyyy").parse(date));
                        medicalRecord.setDiagnose(reason);
                        medicalRecord.setDiseases(old_info);
                        medicalRecord.setSymptom(symp);
                        ApiHelper.getInstance().getApiService().updateMedicalRecord(medicalRecord).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if(response.isSuccessful()) {
                                    Toast.makeText(getContext(), "C???p nh???t th??nh c??ng", Toast.LENGTH_SHORT).show();
                                    getActivity().finish();
                                }
                                else{
                                    Toast.makeText(getContext(), "L???i", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(getContext(), "L???i", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void setComponents(View view){
        setPicker(view);
        dateIn = view.findViewById(R.id.patient_date_in);
        diagnose = view.findViewById(R.id.patient_reason);
        diseases = view.findViewById(R.id.old_info);
        btnUpdate = view.findViewById(R.id.medicalRecords_submit_btn);
        sympton = view.findViewById(R.id.symptom);
    }

    private void setPicker(View view){
        picker = view.findViewById(R.id.patient_calendar_picker);
        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPicker();
            }
        });
    }
    private void showPicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        month = month+1;
        String birthString = day + "/" + month + "/" + year;
        this.dateIn.setText(birthString);
    }

    @Override
    public void onResume() {
        super.onResume();
        ApiHelper.getInstance().getApiService().getMedicalRecordById(medicalRecord.getMedical_record_id()).enqueue(new Callback<MedicalRecord>() {
            @Override
            public void onResponse(Call<MedicalRecord> call, Response<MedicalRecord> response) {
                if(response.isSuccessful()) {
                    medicalRecord = response.body();
                    dateIn.setText(new SimpleDateFormat("dd/MM/yyyy").format(medicalRecord.getHospitalized_day()));
                    diseases.setText(medicalRecord.getDiseases());
                    diagnose.setText(medicalRecord.getDiagnose());
                    sympton.setText(medicalRecord.getSymptom());
                }
            }

            @Override
            public void onFailure(Call<MedicalRecord> call, Throwable t) {
                Toast.makeText(getContext(), "L???i", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
