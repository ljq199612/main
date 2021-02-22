package com.rz.iot.bpo.model;

import lombok.Data;

import java.util.Date;

@Data
public class WorkflowInstance {

    private Integer id;

    private Integer configId;

    private String code;

    private String name;

    private Byte status;

    private String owner;

    private Integer userId;

    private Date createTime;

    private Date updateTime;

}