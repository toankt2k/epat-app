package com.example.epatapp.models;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {
    /// <summary>
    /// Người tạo
    /// </summary>
     private String created_by;

    /// <summary>
    /// Ngày tạo
    /// </summary>
     private Date created_date;

    /// <summary>
    /// Người chỉnh sửa
    /// </summary>
     private String modified_by;

    /// <summary>
    /// Ngày chỉnh sửa
    /// </summary>
     private Date modified_date;
}
