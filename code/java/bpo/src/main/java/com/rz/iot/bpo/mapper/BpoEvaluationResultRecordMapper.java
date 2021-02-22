package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoEvaluationResultRecord;
import com.rz.iot.bpo.model.param.EvaluationResultRecordParam;
import com.rz.iot.bpo.model.show.BpoEvaluationResultRecordShow;
import com.rz.iot.bpo.model.show.BpoEvaluationShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BpoEvaluationResultRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoEvaluationResultRecord record);

    int insertSelective(BpoEvaluationResultRecord record);

    BpoEvaluationResultRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoEvaluationResultRecord record);

    int updateByPrimaryKey(BpoEvaluationResultRecord record);

    BpoEvaluationResultRecordShow findRecordByProjectIdAndTime(EvaluationResultRecordParam param);

    Integer findResultIdByRecordId(Integer recordId);

    List<BpoEvaluationResultRecord> findRecordByResultId(Integer resultId);

    Integer findResultIdBYProjectIdAndTime(@Param("projectId") Integer projectId,@Param("time") String s);

    Integer findResultIdBYSupplierInfoIdAndTime(@Param("supplierInfoId") Integer supplierInfoId,@Param("time") String s);

    BpoEvaluationResultRecord findRecord(@Param("evaluationItemId")Integer evaluationItemId,
                                         @Param("evaluationStandardId")Integer evaluationStandardId,
                                         @Param("evaluationContentId")Integer evaluationContentId,
                                         @Param("evaluationTime")String evaluationTime
    );

    BpoEvaluationShow getEvaluationScoringResult(@Param("resultId")Integer resultId);
}