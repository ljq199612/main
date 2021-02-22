package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.WorkflowInfo;
import com.rz.iot.bpo.model.WorkflowNodeInstance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkflowInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkflowInfo record);

    int insertSelective(WorkflowInfo record);

    WorkflowInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkflowInfo record);

    int updateByPrimaryKey(WorkflowInfo record);

    List<WorkflowNodeInstance> selectNodeInstanceList(@Param("infoId") Integer infoId,@Param("type") Byte type);
}