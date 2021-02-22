package com.rz.iot.bpo.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
public class BpoAttendanceDevice {
    private Integer id;

    private String deviceName;

    private String deviceSn;

    private String deviceNo;

    private Byte status;

    private Byte networkStatus;

    private Integer transferStationId;

    private String manufactor;

    private String version;

    private String ip;

    private String linkMan;

    private String phone;

    private String gateType;

    private String remark;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Integer companyId;

    private Integer deptId;

    //设备归属
    private String attendenceBelong;

    //场地归属
    private String transBelong;

    //储存中转场信息
    private BpoTransferStation bpoTransferStation;

    //储存场地归属部门信息
    private List<SysDept> dept;

    private Integer userId;

}