package com.example.epatapp.models;

import java.io.Serializable;
import java.util.Date;

//bệnh án
public class MedicalRecord extends BaseEntity implements Serializable {
    /// <summary>
    /// Khóa chính
    /// </summary>
    private String medical_record_id ;

    /// <summary>
    /// Id của bệnh nhân
    /// </summary>
           private String patient_id ;

    /// <summary>
    /// ngày nhập viện
    /// </summary>
           private Date hospitalized_day ;

    /// <summary>
    /// ngày ra viện
    /// </summary>
           private Date discharged_day ;

    /// <summary>
    /// chẩn đoán/ lý do vào viện(nhập freetext)
    /// </summary>
           private String diagnose ;

    /// <summary>
    /// triệu chứng(nhập freetext)
    /// </summary>
           private String symptom ;

    /// <summary>
    /// lịch sử đo trạng thái(nhiệt độ, huyết áp, nhịp tim, SPO2, ngày đo) => lưu JSON
    /// </summary>
           private String status ;

    /// <summary>
    /// tiền sử bệnh(freetext)
    /// </summary>
           private String diseases ;


    /// <summary>
    /// lịch sử điều trị(ngày giờ, diễn biến)=> Lưu JSON
    /// </summary>
           private String treatment ;

    public MedicalRecord(String medical_record_id, String patient_id, Date hospitalized_day, Date discharged_day, String diagnose, String symptom, String status, String diseases, String treatment) {
        this.medical_record_id = medical_record_id;
        this.patient_id = patient_id;
        this.hospitalized_day = hospitalized_day;
        this.discharged_day = discharged_day;
        this.diagnose = diagnose;
        this.symptom = symptom;
        this.status = status;
        this.diseases = diseases;
        this.treatment = treatment;
    }

    public MedicalRecord() {
    }

    public String getMedical_record_id() {
        return medical_record_id;
    }

    public void setMedical_record_id(String medical_record_id) {
        this.medical_record_id = medical_record_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public Date getHospitalized_day() {
        return hospitalized_day;
    }

    public void setHospitalized_day(Date hospitalized_day) {
        this.hospitalized_day = hospitalized_day;
    }

    public Date getDischarged_day() {
        return discharged_day;
    }

    public void setDischarged_day(Date discharged_day) {
        this.discharged_day = discharged_day;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiseases() {
        return diseases;
    }

    public void setDiseases(String diseases) {
        this.diseases = diseases;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}
