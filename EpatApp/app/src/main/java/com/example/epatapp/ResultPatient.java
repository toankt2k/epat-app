package com.example.epatapp;

import com.example.epatapp.models.Patient;

import java.util.List;

public class ResultPatient {
    private List<Patient> Data;
    private int TotalRecord;
    private int TotalPage;

    public ResultPatient() {
    }

    public ResultPatient(List<Patient> data, int totalRecord, int totalPage) {
        Data = data;
        TotalRecord = totalRecord;
        TotalPage = totalPage;
    }

    public List<Patient> getData() {
        return Data;
    }

    public void setData(List<Patient> data) {
        Data = data;
    }

    public int getTotalRecord() {
        return TotalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        TotalRecord = totalRecord;
    }

    public int getTotalPage() {
        return TotalPage;
    }

    public void setTotalPage(int totalPage) {
        TotalPage = totalPage;
    }
}
