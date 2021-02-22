package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoScheduleRule;
import com.rz.iot.bpo.model.show.BpoScheduleRuleShow;

public interface BpoScheduleRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoScheduleRule record);

    int insertSelective(BpoScheduleRule record);

    BpoScheduleRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoScheduleRule record);

    int updateByPrimaryKey(BpoScheduleRule record);

    BpoScheduleRuleShow selectByProjectId(Integer projectId);
}