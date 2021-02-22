package com.rz.iot.bpo.service;

import com.rz.iot.bpo.model.WorkflowInstance;
import com.rz.iot.bpo.model.WorkflowNodeInstance;
import com.rz.iot.bpo.model.show.workflow.WorkflowInstanceShow;
import com.rz.iot.bpo.model.show.workflow.WorkflowShow;

import java.util.List;
import java.util.Map;

public interface WorkflowInstanceService {

    void approve(Map<String,Object> params);

    void generateInstance(Byte type, Integer workflowId, Integer infoId);

    List<WorkflowNodeInstance> processProgress(Integer infoId,Byte type);

    /**
     * 查看已办/未办流程
     * @param type
     * @return
     */
    List<WorkflowShow> getWorkflowNodeInstanceList(Byte type);

    /**
     *获取所有流程
     * @return
     */
    List<WorkflowInstance> getWorkflowInstanceList();

    /**
     * 获取流程详情
     * @param id
     * @return
     */
    WorkflowInstanceShow getWorkflowDetail(Integer id);
}
