package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoOutputDetailEmployee;
import com.rz.iot.bpo.model.show.bpoOutput.BpoOutputDetailEmployeeShow;

import java.util.List;
import java.util.Map;

public interface BpoOutputDetailEmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoOutputDetailEmployee record);

    int insertSelective(BpoOutputDetailEmployee record);

    BpoOutputDetailEmployee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoOutputDetailEmployee record);

    int updateByPrimaryKey(BpoOutputDetailEmployee record);

    List<BpoOutputDetailEmployeeShow> getEmployeeDetailList(Map<String, Object> params);
}