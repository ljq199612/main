package com.rz.iot.bpo.model.param;

import lombok.Data;

import java.util.Date;

@Data
public class BpoAttendanceInfoParam {

    private Integer id;

    private Integer projectId;

    private String classSn;

    private String personInfo;

    private Date date;

    private Byte approvalStatus;

//    private Double attendanceHours;
//
//    private Double wageHours;
//
//    private Double overtimeHours;

    private Double approvalWageHours;

    private String remark;

    // 查询时 1全部 2正常考勤 3异常考勤
    // 审核时 1审核 2确认
    private Integer type;
}
