package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoOutput;
import com.rz.iot.bpo.model.show.bpoOutput.BpoOutputShow;

import java.util.List;
import java.util.Map;

public interface BpoOutputMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoOutput record);

    int insertSelective(BpoOutput record);

    BpoOutput selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoOutput record);

    int updateByPrimaryKey(BpoOutput record);

    List<BpoOutputShow> getOutputList(Map<String,Object> params);
}