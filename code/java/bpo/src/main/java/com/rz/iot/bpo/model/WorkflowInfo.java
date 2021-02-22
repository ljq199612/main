package com.rz.iot.bpo.model;

import lombok.Data;

import java.util.Date;

@Data
public class WorkflowInfo {

    private Integer id;

    private Integer instanceId;

    private Date createTime;

    private Date updateTime;

    private Byte type;

    private Integer infoId;

}