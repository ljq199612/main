package com.rz.iot.bpo.model.show.bpoBill;

import com.rz.iot.bpo.model.BpoBillReconciliationTimeDetailed;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 个人对账详细信息
 */
@Data
public class BpoBillPersonReconciliationShow {
    private Integer id;

    private Integer personId;

    private Integer companyId;

    private String personName;

    private String station;

    private String transName;

    private String projectName;

    private String companyName;

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

    //业务扣款
    private BigDecimal businessDeduction;

    //社保扣款
    private BigDecimal socialSecurity;

    //公积金扣款
    private BigDecimal accumulationFund;

    //个税扣款
    private BigDecimal personalIncomeTax;

    //其他应收
    private BigDecimal otherIncome;

    //其他扣款
    private BigDecimal otherDeduction;

    //应发薪资
    private BigDecimal receivableSalary;

    //实发薪资
    private BigDecimal relSalary;

    //对账状态
    private Integer reconciliationStatus;

    //状态
    private Integer status;

    //账单明细
    private List<BpoBillReconciliationTimeDetailed> timeDetaileds;

    //个人账单表
    private List<BpoBillPersonDayDetail> bpoBillPersonDayDetails;
}
