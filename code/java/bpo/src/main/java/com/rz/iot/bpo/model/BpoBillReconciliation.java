package com.rz.iot.bpo.model;

import lombok.Data;

import java.util.Date;

@Data
public class BpoBillReconciliation {

    private Integer id;

    private Integer supplierInfoId;

    private String billingCycle;

    private Integer billType;

    private Integer payerId;

    private Integer payeeId;

    private Integer projectId;

    private Integer monthBillId;

    private Integer reconciliationWay;

    private Integer reconciliationType;

    private Integer feeRuleType;

    private Integer totalOutput;

    private Float totalHours;

    private Integer reconciliationStatus;

    private Integer status;

    private Integer userId;

    private Date reconciliationTime;

    private Date createTime;

    private Date updateTime;

}