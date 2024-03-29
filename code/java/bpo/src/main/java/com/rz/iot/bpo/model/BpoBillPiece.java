package com.rz.iot.bpo.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BpoBillPiece {

    private Integer id;

    private Integer projectId;

    private Integer supplierInfoId;

    private String billingCycle;

    private Integer personId;

    private Integer feeRuleType;

    private BigDecimal income;

    private BigDecimal deduction;

    private BigDecimal realIncome;

    private Integer workModelId;

    private Integer workGroupId;

    private Integer processId;

    private Integer productId;

    private Integer pricingIntervalId;

    private Integer upperLimit;

    private Integer lowerLimit;

    private BigDecimal price;

    private Integer status;

    private Date createTime;

    private Date updateTime;

}