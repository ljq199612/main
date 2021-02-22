package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoPerformanceLevel;
import com.rz.iot.bpo.model.show.BpoPerformanceLevelShow;

import java.util.List;

public interface BpoPerformanceLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoPerformanceLevel record);

    int insertSelective(BpoPerformanceLevel record);

    BpoPerformanceLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoPerformanceLevel record);

    int updateByPrimaryKey(BpoPerformanceLevel record);

    BpoPerformanceLevelShow selectByProjectId(int projectId);

    BpoPerformanceLevelShow selectBySupplierInfoId(int supplierInfoId);
}