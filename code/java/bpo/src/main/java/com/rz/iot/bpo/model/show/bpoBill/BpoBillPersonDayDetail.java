package com.rz.iot.bpo.model.show.bpoBill;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 个人日账单详情
 */
@Data
public class BpoBillPersonDayDetail {

    private Integer id;

    private Integer projectId;

    private String personName;

    private Integer personType;

    private Integer payType;

    private String transName;

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
