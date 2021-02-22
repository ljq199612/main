package com.rz.iot.bpo.model.show.bpoBill;

import com.rz.iot.bpo.model.BpoBillReconciliationTimeDetailed;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: bpo-preview
 * @description: 账单对账信息展示
 * @author: YangShun
 * @create: 2020-07-31 10:35
 **/
@Data
public class BpoBillReconciliationShow {
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

    //其它扣款
    private Double otherIncome;

    //其它扣款说明
    private String otherIncomeMessage;

    //其他应收
    private Double otherDeduction;

    //其他应收说明
    private String otherDeductionMessage;

    //绩效得分
    private String performanceScore;

    //绩效考核挂钩比率
    private String performanceScoreRate;

    //考核额度
    private String performanceQuota;

    //考核后额度
    private String performanceScoreQuota;

    //对账表id
    private Integer billReconciliationId;

    //对账按时人员详细信息
    private List<BpoBillReconciliationTimeDetailed> detaileds;

}
