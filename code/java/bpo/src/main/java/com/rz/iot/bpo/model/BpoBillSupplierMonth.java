package com.rz.iot.bpo.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BpoBillSupplierMonth {

    private Integer id;

    private Integer projectId;

    private String billCycle;

    private Integer payerId;

    private Integer payeeId;

    private Boolean isAgencySalary;

    private BigDecimal agencySalary;

    private Integer feeRuleType;

    private Integer totalOutput;

    private Double totalHours;

    private Integer totalPeople;

    private BigDecimal supplierSalary;

    private BigDecimal billAmount;

    private BigDecimal totalDeduction;

    private BigDecimal realBillAmount;

    private Integer reconciliationStatus;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    //供应商月账单对账明细
    private BpoBillSupplierMonthDetail detail;

}