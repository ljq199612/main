package com.rz.iot.bpo.model;

import lombok.Data;

import java.util.Date;

@Data
public class BpoAttendanceAuditRecord {
    private Integer id;

    private Integer attendanceInfoId;

    private Integer personId;

    private String personName;

    private Date auditTime;

    private Byte auditType;

    private Byte auditResult;

    private Double approvalWageHours;

    private String remark;

    private Byte status;

    private Date createTime;

    private Date updateTime;

}