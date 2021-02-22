package com.rz.iot.bpo.service;

import com.rz.iot.bpo.controller.BpoEvaluationResultParam;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.BpoEvaluationResultRecord;
import com.rz.iot.bpo.model.param.BpoEvaluationParam;
import com.rz.iot.bpo.model.param.BpoPerformanceEvaluationParam;
import com.rz.iot.bpo.model.param.BpoPerformanceLevelParam;
import com.rz.iot.bpo.model.param.EvaluationResultRecordParam;
import com.rz.iot.bpo.model.show.*;

import java.util.List;

public interface EvaluationService {
    Result<BpoPerformanceEvaluationShow> getDetail(int id);

    Result<BpoEvaluationShow> getEvaluationDetail(int projectId);

    Result<BpoEvaluationResultRecordShow> getEvaluationDetailResultRecord(EvaluationResultRecordParam evaluationResultRecordParam);

    Result addEvaluationStandards(BpoPerformanceEvaluationParam bpoPerformanceEvaluationParam);

    Result addEvaluationDetail(BpoEvaluationParam bpoEvaluationParam);

    Result addEvaluationScoringResult(BpoEvaluationParam bpoEvaluationParam);

    Result<BpoPerformanceLevelShow> getLevel(int projectId);

    Result addLevel(List<BpoPerformanceLevelParam> bpoPerformanceLevelParam);

    Result<BpoEvaluationResultShow> getResult(int projectId);

    Result<BpoEvaluationResultShow> getResultList(BpoEvaluationResultShow info, Page<BpoEvaluationResultShow> page);

    Result addResult(BpoEvaluationResultParam bpoEvaluationResultParam);

    Result updateEvaluationDetail(BpoEvaluationParam bpoEvaluationParam);

    Result updateLevel(BpoPerformanceLevelParam bpoPerformanceLevelParam);

    Result updateResult(BpoEvaluationResultParam bpoEvaluationResultParam);

    Result updateEvaluationStandards(BpoPerformanceEvaluationParam bpoPerformanceEvaluationParam);

    Result updateEvaluationResultRecordScore(BpoEvaluationResultRecord bpoEvaluationResultRecord);

    Result updateEvaluationScoringResult(BpoEvaluationParam bpoEvaluationParam);

    Result deleteEvaluationStandards(int id);

    Result deleteResult(int id);

    Result deleteLevel(int id);

    Result deleteEvaluationDetail(int id, int type);

    Result<List<BpoProjectEvaluationShow>> getCurrentPartyBAllProject();
}
