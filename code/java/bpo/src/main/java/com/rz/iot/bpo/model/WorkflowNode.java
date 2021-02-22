package com.rz.iot.bpo.model;

import lombok.Data;

import java.util.Date;

@Data
public class WorkflowNode {

    private Integer id;

    private Integer configId;

    private String nodeCode;

    private String nodeName;

    private Integer userId;

    private Boolean isFirst;

    private Boolean isLast;

    private Integer nextId;

    private Integer preId;

    private Date createTime;

    private Date updateTime;

}