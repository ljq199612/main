package com.rz.iot.bpo.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BpoBillTime {

    private Integer id;

    private Integer projectId;

    private Integer supplierInfoId;

    private String billingCycle;

    private Integer personId;

    private Integer feeRuleType;

    private Date attendanceStartTime;

    private Date attendanceEndTime;

    private Double wageHours;

    private BigDecimal timePrice;

    private BigDecimal salaryStandard;

    private BigDecimal foodSubsidy;

    private BigDecimal nightSubsidy;

    private BigDecimal otherSubsidy;

    private BigDecimal deduction;

    private BigDecimal realIncome;

    private Integer status;

    private Date createTime;

    private Date updateTime;

}