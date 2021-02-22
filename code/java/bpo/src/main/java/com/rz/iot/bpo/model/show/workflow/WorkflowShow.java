package com.rz.iot.bpo.model.show.workflow;

import lombok.Data;

@Data
public class WorkflowShow {

    private Integer instanceId;

    private Integer nodeInstanceId;

    private Integer configId;

    private String instanceCode;

    private String instanceName;

    private Byte instanceStatus;

    private String owner;

    private Integer originatorUserId;

    private String nodeInstanceName;

    private Integer nodeId;

    private String assigneeRole;

    private String assigneeName;

    private Integer assigneeUserId;

    private Byte nodeInstanceStatus;
}
