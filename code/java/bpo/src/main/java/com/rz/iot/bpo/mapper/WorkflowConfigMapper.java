package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.WorkflowConfig;
import com.rz.iot.bpo.model.show.workflow.WorkflowConfigShow;

import java.util.List;
import java.util.Map;

public interface WorkflowConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkflowConfig record);

    int insertSelective(WorkflowConfig record);

    WorkflowConfig selectByPrimaryKey(Integer id);

    List<WorkflowConfigShow> findAll(Map<String,Object> params);

    WorkflowConfigShow configDetail(Integer id);

    WorkflowConfig selectByProjectSupplierId(Integer projectId,Integer supplierId);

    int updateByPrimaryKeySelective(WorkflowConfig record);

    int updateByPrimaryKey(WorkflowConfig record);
}