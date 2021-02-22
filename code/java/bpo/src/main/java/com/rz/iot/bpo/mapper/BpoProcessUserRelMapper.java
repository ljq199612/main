package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoProcessUserRel;

public interface BpoProcessUserRelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoProcessUserRel record);

    int insertSelective(BpoProcessUserRel record);

    BpoProcessUserRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoProcessUserRel record);

    int updateByPrimaryKey(BpoProcessUserRel record);
}