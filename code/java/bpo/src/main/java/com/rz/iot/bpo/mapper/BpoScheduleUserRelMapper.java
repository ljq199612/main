package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoScheduleUserRel;

public interface BpoScheduleUserRelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoScheduleUserRel record);

    int insertSelective(BpoScheduleUserRel record);

    BpoScheduleUserRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoScheduleUserRel record);

    int updateByPrimaryKey(BpoScheduleUserRel record);
}