package com.rz.iot.bpo.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BpoBillReconciliationTimeDetailedLog {

    private Integer id;

    private Integer billReconciliationId;

    private Integer timeLogId;

    private Integer projectId;

    private Integer supplierInfoId;

    private String billingCycle;

    private Integer personId;

    private Integer feeRuleType;

    private Date attendanceStartTime;

    private Date attendanceEndTime;

    private Float wageHours;

    private BigDecimal income;

    private BigDecimal deduction;

    private BigDecimal realIncome;

    private Integer status;

    private Integer appealStatus;

    private String appealReason;

    private Integer updateUserId;

    private Date createTime;

    private Date updateTime;

}