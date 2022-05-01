package com.example.epatapp.models;

public class Account {
    /// <summary>
    /// Khóa chính
    /// </summary>

    private String account_id ;

    /// <summary>
    /// tên
    /// </summary>
    private String account_name ;

    /// <summary>
    /// tài khoản đăng nhập
    /// </summary>
    private String username ;

    /// <summary>
    /// mật khẩu dăng nhập
    /// </summary>
    private String password ;

    /// <summary>
    /// vị trí làm việc
    /// </summary>
    private String department_id ;

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }
}
