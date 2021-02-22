package com.rz.iot.bpo.service;

import com.rz.iot.bpo.controller.BpoEvaluationResultParam;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.param.BpoEvaluationParam;
import com.rz.iot.bpo.model.param.BpoEvalutionAddParam;
import com.rz.iot.bpo.model.param.BpoPerformanceEvaluationParam;
import com.rz.iot.bpo.model.param.BpoPerformanceLevelParam;
import com.rz.iot.bpo.model.show.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述 : 供应商考核Service
 * 作者 : qin xian
 * 创建时间 : 2020/7/3 0003 15:48
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Service
public interface SupplierPerformanceEvaluationService {

    Result<BpoPerformanceEvaluationShow> getDetail(Integer supplierInfoId);

    Result<BpoPerformanceLevelShow> getLevel(Integer supplierInfoId);

    Result addEvaluationStandards(BpoPerformanceEvaluationParam info);

    Result addLevel(List<BpoPerformanceLevelParam> list);

    Result deleteEvaluationStandards(Integer id);

    Result deleteLevel(Integer id);

    Result updateEvaluationStandards(BpoPerformanceEvaluationParam info);

    Result updateLevel(BpoPerformanceLevelParam info);

    Result getResult(BpoEvaluationResultParam info);

    Result addResult(BpoEvaluationResultParam info);

    Result deleteResult(Integer id);

    Result updateResult(BpoEvaluationResultParam info);

    Result addEvaluationDetail(BpoEvaluationParam info);

    Result updateEvaluationDetail(BpoEvaluationParam info);

    Result deleteEvaluationDetail(Integer id,Integer type);

    Result<BpoEvaluationShow> getEvaluationDetail(Integer supplierInfoId);

    Result addEvaluationScoringResult(BpoEvaluationParam info);

    Result addEvaluation(BpoEvalutionAddParam info);

    Result updateEvaluationScoringResult(BpoEvaluationParam info);

    Result getEvaluationScoringResult(Integer resultId);

    Result getResultList(BpoEvaluationResultShow info, Page<BpoEvaluationResultShow> page);

}