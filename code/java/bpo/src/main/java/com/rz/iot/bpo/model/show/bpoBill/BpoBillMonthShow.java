package com.rz.iot.bpo.model.show.bpoBill;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: bpo-preview
 * @description: 用于展示月账单 应收
 * @author: YangShun
 * @create: 2020-07-28 16:54
 **/
@Data
public class BpoBillMonthShow {

    private Integer id;

    //场地
    private String transferStationName;

    //项目
    private String projectName;

    //客户 付款方名称
    private String payerName;

    //供应商 收款方名称
    private String payeeName;

    //账单周期
    private String billCycle;

    //计价方式
    private Integer feeRuleType;

    //是否代发薪
    private Boolean isAgencySalary;

    //代发薪金额
    private BigDecimal agencySalary;

    //总件数
    private Integer totalOutput;

    //总工时
    private Double totalHours;

    //结算标准(元/小时)
    private BigDecimal supplierSalary;

    //总人数
    private Integer totalPeople;

    //总扣款
    private BigDecimal totalDeduction;

    //账单金额
    private BigDecimal billAmount;

    //实际账单金额
    private BigDecimal relBillMoney;

    //账单状态
    private Integer reconciliationStatus;


}
