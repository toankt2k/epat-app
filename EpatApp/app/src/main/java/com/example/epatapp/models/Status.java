package com.example.epatapp.models;

import java.io.Serializable;
import java.util.Date;

public class Status implements Serializable {
    private Date date;
    private String temp;
    private String heart;
    private String heal_atm;
    private String spo2;

    public Status() {
    }

    public Status(Date date, String temp, String heart, String heal_atm, String spo2) {
        this.date = date;
        this.temp = temp;
        this.heart = heart;
        this.heal_atm = heal_atm;
        this.spo2 = spo2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHeart() {
        return heart;
    }

    public void setHeart(String heart) {
        this.heart = heart;
    }

    public String getHeal_atm() {
        return heal_atm;
    }

    public void setHeal_atm(String heal_atm) {
        this.heal_atm = heal_atm;
    }

    public String getSpo2() {
        return spo2;
    }

    public void setSpo2(String spo2) {
        this.spo2 = spo2;
    }
}
