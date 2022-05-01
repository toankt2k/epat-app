package com.example.epatapp.models;

import java.util.Date;

//bệnh nhân
public class Patient extends BaseEntity {
    /// <summary>
    /// khóa chính
    /// </summary>
    private String patient_id;

    /// <summary>
    /// tên đầy đủ
    /// </summary>
    private String fullname;

    /// <summary>
    /// Ngày sinh
    /// </summary>        
    private Date date_of_birth;

    /// <summary>
    /// Giới tính - 0 - nữ, 1 nam, 2 khác
    /// </summary>
    private int gender;

    /// <summary>
    /// số chứng minh nhân dân
    /// </summary>
    private String identity_number;


    /// <summary>
    /// Số điện thoại
    /// </summary>
    private String phone_number;

    /// <summary>
    /// Địa chỉ
    /// </summary>
    private String address;

    /// <summary>
    /// mô tả
    /// </summary>
    private String description;

    public Patient(String patient_id, String fullname, Date date_of_birth, int gender, String identity_number, String phone_number, String address, String description) {
        this.patient_id = patient_id;
        this.fullname = fullname;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.identity_number = identity_number;
        this.phone_number = phone_number;
        this.address = address;
        this.description = description;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(String identity_number) {
        this.identity_number = identity_number;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
