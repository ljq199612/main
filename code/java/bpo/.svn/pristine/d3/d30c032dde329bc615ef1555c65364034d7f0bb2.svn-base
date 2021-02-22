package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoProcess;

import java.util.List;

public interface BpoProcessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoProcess record);

    int insertSelective(BpoProcess record);

    BpoProcess selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoProcess record);

    int updateByPrimaryKey(BpoProcess record);

    List<BpoProcess> findByGroupId(Integer groupId);
}