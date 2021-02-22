package com.rz.iot.bpo.mina.model;


import lombok.Data;

import java.util.Date;

@Data
public class MinaTransferStation {
    private Integer id;

    private String name;

    private String code;

    private Integer cityId;

    private String regionName;

    private String businessName;

    private String type;

    private String linkMan;

    private String phone;

    private String remark;

    private Byte status;

    private String detailAddress;

    private Date createTime;

    private Date updateTime;
}