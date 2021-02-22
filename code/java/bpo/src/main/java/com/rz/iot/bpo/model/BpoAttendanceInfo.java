package com.rz.iot.bpo.model;

import lombok.Data;

import java.util.Date;

@Data
public class BpoAttendanceInfo {
    private Integer id;

    private Integer projectId;

    private String classSn;

    private Integer personId;

    private Date attendanceDate;

    private Date arrivalTime;

    private Date startTime;

    private Date endTime;

    private Double excludeHour;

    private Date clockInTime;

    private Date clockOutTime;

    private Integer devId;

    private Double workingHours;

    private Byte status;

    private Double attendanceHours;

    private Double wageHours;

    private Double overtimeHours;

    private Byte recordStatus;

    private Byte recordApprovalStatus;

    private Byte approvalStatus;

    private Integer approvalUserId;

    private Double approvalWageHours;

    private String remark;

    private String complainRemark;

    private Date approvalTime;

    private Date createTime;

    private Date updateTime;
}