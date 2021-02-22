package com.rz.iot.bpo.model.show.bpoBill;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 个人月账单
 */
@Data
public class BpoBillPersonMonthShow {
    private Integer id;

    private Integer personId;

    private Integer companyId;

    //人员姓名
    private String personName;

    //人员岗位
    private String station;

    //场地
    private String transName;

    //项目名
    private String projectName;

    //公司名
    private String companyName;

    //项目Id
    private Integer projectId;

    private String billingCycle;

    private Boolean feeRuleType;

    private Integer totalOutput;

    private Double totalHours;

    private BigDecimal timePrice;

    private BigDecimal foodSubsidy;

    private BigDecimal nightSubsidy;

    private BigDecimal otherSubsidy;

    private BigDecimal deduction;

    private BigDecimal receivableSalary;

    private BigDecimal relSalary;

    private Integer reconciliationStatus;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}
