package com.rz.iot.bpo.mina.model;

import lombok.Data;

import java.util.Date;

@Data
public class MinaContractInfo {

    /**
     * 类型:1企业-企业,2员工-劳务,3员工-实习协议,4员工-灵活排遣
     */
    private Byte type;

    private Date signTime;

    private Date startTime;

    private Date endTime;

    private String filePath;

    private Byte status;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private String customerCode;

    //是否代发工资
    private Integer agencySalary;

    //项目名称
    private String projectName;

    private String tranStation;

    //计价方式
    private Integer ruleType;
}