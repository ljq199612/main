package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.controller.BpoEvaluationResultParam;
import com.rz.iot.bpo.model.BpoEvaluationResult;
import com.rz.iot.bpo.model.show.BpoEvaluationResultShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BpoEvaluationResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoEvaluationResult record);

    int insertSelective(BpoEvaluationResult record);

    BpoEvaluationResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoEvaluationResult record);

    int updateByPrimaryKey(BpoEvaluationResult record);

    BpoEvaluationResultShow selectByProjectId(int projectId);

    List<BpoEvaluationResultShow> getProjectResultList(@Param("info")BpoEvaluationResultShow info);

    List<BpoEvaluationResultShow> getSupplierResultList(@Param("info")BpoEvaluationResultShow info);

    BpoEvaluationResult getResult(@Param("info")BpoEvaluationResultParam info);


}