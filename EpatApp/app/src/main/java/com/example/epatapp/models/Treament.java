package com.example.epatapp.models;

import java.util.Date;

public class Treament {
    //ngày điều trị;
    private Date date;
    //diễn biến;
    private String progress;

    public Treament() {
    }

    public Treament(Date date, String progress) {
        this.date = date;
        this.progress = progress;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
