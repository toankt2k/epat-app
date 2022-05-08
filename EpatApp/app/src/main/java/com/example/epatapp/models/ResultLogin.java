package com.example.epatapp.models;

import java.io.Serializable;

public class ResultLogin implements Serializable {
    private String token;
    private Account account;

    public ResultLogin(String token, Account account) {
        this.token = token;
        this.account = account;
    }

    public ResultLogin() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
