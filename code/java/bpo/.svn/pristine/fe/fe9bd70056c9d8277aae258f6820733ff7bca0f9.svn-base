package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoPerformanceEvaluation;
import com.rz.iot.bpo.model.show.BpoPerformanceEvaluationShow;

public interface BpoPerformanceEvaluationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoPerformanceEvaluation record);

    int insertSelective(BpoPerformanceEvaluation record);

    BpoPerformanceEvaluation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoPerformanceEvaluation record);

    int updateByPrimaryKey(BpoPerformanceEvaluation record);

    BpoPerformanceEvaluationShow selectByProjectId(int projectId);

    BpoPerformanceEvaluationShow selectBysupplierInfoId(int supplierInfoId);
}