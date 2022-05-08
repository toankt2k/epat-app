package com.example.epatapp.models;

import java.io.Serializable;

public class Department extends  BaseEntity implements Serializable {
    /// <summary>
    /// khóa chính
    /// </summary>

    private String department_id;

    /// <summary>
    /// mã vị trí làm việc
    private String department_code;

    /// <summary>
    /// tên vị trí làm việc
    /// </summary>

    private String department_name;

    public Department() {
    }

    public Department(String department_id, String department_code, String department_name) {
        this.department_id = department_id;
        this.department_code = department_code;
        this.department_name = department_name;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_code() {
        return department_code;
    }

    public void setDepartment_code(String department_code) {
        this.department_code = department_code;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }
}
