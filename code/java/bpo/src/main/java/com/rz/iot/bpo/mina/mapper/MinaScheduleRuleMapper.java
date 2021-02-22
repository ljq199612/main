package com.rz.iot.bpo.mina.mapper;


import com.rz.iot.bpo.mina.model.MinaSchedule;
import com.rz.iot.bpo.mina.model.MinaScheduleRule;
import com.rz.iot.bpo.model.show.BpoScheduleRuleShow;

public interface MinaScheduleRuleMapper {

    int insertSelective(MinaScheduleRule record);

    int deleteByPrimaryKey(Integer id);

    int insert(MinaScheduleRule record);

    MinaScheduleRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MinaScheduleRule record);

    int updateByPrimaryKey(MinaScheduleRule record);

    BpoScheduleRuleShow selectByProjectId(Integer projectId);
}