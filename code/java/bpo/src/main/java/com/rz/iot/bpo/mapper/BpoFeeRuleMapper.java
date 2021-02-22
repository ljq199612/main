package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoFeeRule;

public interface BpoFeeRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoFeeRule record);

    int insertSelective(BpoFeeRule record);

    BpoFeeRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoFeeRule record);

    int updateByPrimaryKey(BpoFeeRule record);
}