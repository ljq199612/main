package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoPersonWage;

public interface BpoPersonWageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoPersonWage record);

    int insertSelective(BpoPersonWage record);

    BpoPersonWage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoPersonWage record);

    int updateByPrimaryKey(BpoPersonWage record);
}