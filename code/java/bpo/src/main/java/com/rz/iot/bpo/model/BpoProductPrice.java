package com.rz.iot.bpo.model;

import lombok.Data;

import java.util.Date;

@Data
public class BpoProductPrice {
    //
    private Integer id;

    //
    private Byte productType;

    //费用id
    private Integer feeRuleId;

    //工序id
    private Integer processId;

    //工序id
    private Integer productId;

    //计价件数上限
    private Integer upperLimit;

    //计价件数下限
    private Integer lowerLimit;

    //计价金额
    private String price;

    //1:甲方给乙方2:乙方给供应商3:乙方给个人4:供应商给个人5:供应商给供应商
    private Integer type;

    //状态
    private Byte status;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;
}