package com.rz.iot.bpo.model;

import lombok.Data;

import java.util.Date;

@Data
public class WorkflowLog {

    private Integer id;

    private Integer instanceId;

    private Integer nodeInstanceId;

    private String assigneeSuggestion;

    private Byte assigneeResult;

    private Date createTime;

    private Date updateTime;

}