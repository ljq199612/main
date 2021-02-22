package com.rz.iot.bpo.model.show.bpoBill;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: bpo-preview
 * @description: 用于展示供应商日账单
 * @author: YangShun
 * @create: 2020-07-29 15:30
 **/
@Data
public class BpoBillDayShow {
    //id
    private Integer id;
    //场地id
    private Integer transferId;
    //场地
    private String transferName;
    //项目id
    private Integer projectId;
    //项目
    private String projectName;
    //付款方公司id
    private Integer payerId;
    //付款方
    private String payer;
    //收款方公司id
    private Integer payeeId;
    //收款方
    private String payee;
    //账单日期
    private String billDate;
    //计价方式
    private Integer feeRuleType;
    //总件量
    private Integer totalOutput;
    //总工时
    private BigDecimal totalTime;
    //总人数
    private Integer totalPeaple;
    //结算标准
    private BigDecimal supplierSalary;
    //扣款
    private BigDecimal totalDeduction;
    //账单金额
    private BigDecimal realBillAmount;
    //账单状态
    private Integer reconciliationStatus;

    //按时 详情数据
    private List<BpoBillTimeShow> lists;
}
