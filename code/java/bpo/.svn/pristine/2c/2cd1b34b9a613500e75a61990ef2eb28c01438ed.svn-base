package com.rz.iot.bpo.model;

import lombok.Data;

import java.util.Date;

@Data
public class BpoFeeRuleByTime {
    //
    private Integer id;

    //所属项目
    private Integer projectId;

    //计价规则名称
    private String ruleName;

    //优先级
    private Integer priorityLevel;

    //单价
    private String unitPrice;

    //1:甲方给乙方2:乙方给供应商3:乙方给个人4:供应商给个人5:供应商给供应商
    private Integer type;

    private Integer status;

    //生效时间
    private Date effectiveTime;

    //失效时间
    private Date finishTime;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;

    //供应商信息表Id,当type为2时,标记是哪个供应商
    private Integer supplierInfoId;

}