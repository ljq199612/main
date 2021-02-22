package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoEvaluationStandard;

import java.util.List;

public interface BpoEvaluationStandardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoEvaluationStandard record);

    int insertSelective(BpoEvaluationStandard record);

    BpoEvaluationStandard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoEvaluationStandard record);

    int updateByPrimaryKey(BpoEvaluationStandard record);

    List<BpoEvaluationStandard> findByContentId(Integer contentId);
}