package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.WorkflowInstance;
import com.rz.iot.bpo.model.show.workflow.WorkflowInstanceShow;
import com.rz.iot.bpo.model.show.workflow.WorkflowShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkflowInstanceMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(WorkflowInstance record);

    int insertSelective(WorkflowInstance record);

    WorkflowInstance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkflowInstance record);

    int updateByPrimaryKey(WorkflowInstance record);

    List<WorkflowShow> getWorkflowNodeInstanceList(@Param("type") Byte type, @Param("userId") Integer userId);

    List<WorkflowInstance> getWorkflowInstanceList(@Param("userId") Integer userId);

    WorkflowInstanceShow getWorkflowDetail(@Param("id") Integer id);
}