package com.rz.iot.bpo.model.param;

import com.rz.iot.bpo.model.WorkflowNode;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class WorkflowParam {

    private Integer id;

    private String wfCode;

    private String wfName;

    private Date createTime;

    private Date updateTime;

    private Byte type;

    private Integer projectId;

    private Integer supplierInfoId;

    private Byte category;

    private Integer payer;

    private Integer payee;

    private String createBy;

    private List<WorkflowNode> nodeList;
}
