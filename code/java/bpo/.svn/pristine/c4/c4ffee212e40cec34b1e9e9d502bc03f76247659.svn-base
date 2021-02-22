package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.WorkflowNodeInstance;
import org.apache.ibatis.annotations.Param;

public interface WorkflowNodeInstanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkflowNodeInstance record);

    int insertSelective(WorkflowNodeInstance record);

    WorkflowNodeInstance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkflowNodeInstance record);

    int updateByPrimaryKey(WorkflowNodeInstance record);

    WorkflowNodeInstance selectByNodeId(@Param("nodeId") Integer nodeId,@Param("instanceId") Integer instanceId);

    WorkflowNodeInstance selectByInfoId(@Param("infoId") Integer infoId, @Param("type") Byte type);
}