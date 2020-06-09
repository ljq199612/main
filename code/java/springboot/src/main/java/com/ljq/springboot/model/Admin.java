package com.ljq.springboot.model;

import lombok.*;

import java.sql.Date;


//@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private int key;
    private String id;
    private String userName;
    private String password;
    private Date registDate;

    public Admin(String id, String userName, String password, Date registDate) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.registDate = registDate;
    }
}