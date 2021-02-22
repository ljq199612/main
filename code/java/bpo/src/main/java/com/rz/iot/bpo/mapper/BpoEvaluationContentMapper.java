package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoEvaluationContent;

import java.util.List;

public interface BpoEvaluationContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoEvaluationContent record);

    int insertSelective(BpoEvaluationContent record);

    BpoEvaluationContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoEvaluationContent record);

    int updateByPrimaryKey(BpoEvaluationContent record);

    List<BpoEvaluationContent> findByItemId(int id);
}