package com.rz.iot.bpo.model.show;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BpoAttendanceInfoShow {
    private Integer id;

    private Integer projectId;

    private String projectName;

    private String classSn;

    private String className;

    private Integer personId;

    private String personName;

    private String workNo;

    private String station;

    private Date attendanceDate;

    private Date clockInTime;

    private Date clockOutTime;

    private Double excludeHour;

    private Date arrivalTime;

    private Date startTime;

    private Date endTime;

    private Integer devId;

    private Float workingHours;

    private Byte status;

    private Float attendanceHours;

    private Float wageHours;

    private Float overtimeHours;

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
