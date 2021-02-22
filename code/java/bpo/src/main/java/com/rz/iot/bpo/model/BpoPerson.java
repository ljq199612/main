package com.rz.iot.bpo.model;

import lombok.Data;

import java.util.Date;

@Data
public class BpoPerson {
    private Integer id;

    private String personName;

    private Integer sex;

    private Integer age;

    private String idCard;

    private String nation;

    private String birth;

    private String idCardAddress;

    private String workNo;

    private Integer userId;

    private Byte syncSf;

    private String sfWorkNo;

    private String phone;

    private String address;

    private String remark;

    private Integer projectId;

    private Integer accountId;

    private Byte personType;

    private Integer deptId;

    private Integer status;

    private String station;

    private Integer stationLevel;

    private Integer wageId;

    private Date dimissionTime;

    private Integer dimissionType;

    private String dimissionReason;

    private Integer contractId;

    private Integer companyId;

    private Date createTime;

    private Date updateTime;
}