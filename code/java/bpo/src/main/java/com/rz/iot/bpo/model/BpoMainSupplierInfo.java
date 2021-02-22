package com.rz.iot.bpo.model;

import lombok.Data;

import java.util.Date;

@Data
public class BpoMainSupplierInfo {
    private Integer id;

    private Integer belongBusiness;

    private String legalMan;

    private String creditCode;

    private Integer companyId;

    private Date foundTime;

    private Integer scale;

    private String registerFund;

    private String manageScope;

    private String address;

    private String remark;
}