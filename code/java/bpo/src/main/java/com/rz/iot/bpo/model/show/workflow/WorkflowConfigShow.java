package com.rz.iot.bpo.model.show.workflow;

import com.rz.iot.bpo.model.WorkflowNode;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class WorkflowConfigShow {

    private Integer id;

    private String wfCode;

    private String wfName;

    private String projectName;

    private String supplierName;

    private Date createTime;

    private Date updateTime;

    private Byte type;

    private Integer projectId;

    private Integer supplierInfoId;

    private Byte category;

    private String payer;

    private String payee;

    private String payerName;

    private String payeeName;

    private String createBy;

    private String reserve2;

    private String reserve3;

    private List<WorkflowNodeShow> nodeList;
}
