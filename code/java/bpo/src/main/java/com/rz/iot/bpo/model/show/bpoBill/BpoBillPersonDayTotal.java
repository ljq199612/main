package com.rz.iot.bpo.model.show.bpoBill;

import lombok.Data;

import java.util.List;

/**
 * 个人日账单数据统计和详情
 */
@Data
public class BpoBillPersonDayTotal {

    private Integer id;

    private String personName;

    private Integer personType;

    private Integer timePrice;

    private Integer payType;

    private String billingCycle;

    private Integer totalWageHours;

    private Integer totalSalaryStandard;

    private Integer totalNightSubsidy;

    private Integer totalFoodSubsidy;

    private Integer totalOtherSubsidy;

    private Integer totalSubsidy;

    private Integer totalDeduction;

    private Integer totalRealSalary;

    List<BpoBillPersonDayDetail> bpoBillPersonDayDetails;
}
