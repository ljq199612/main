package com.rz.iot.bpo.model.show.workflow;

import lombok.Data;

import java.util.Date;

@Data
public class WorkflowNodeShow {

    private Integer id;

    private Integer configId;

    private String nodeCode;

    private String nodeName;

    private Integer userId;

    private String realName;

    private Integer nextId;

    private Integer preId;

}
