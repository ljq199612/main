package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoWorkModel;

public interface BpoWorkModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoWorkModel record);

    int insertSelective(BpoWorkModel record);

    BpoWorkModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoWorkModel record);

    int updateByPrimaryKey(BpoWorkModel record);
}