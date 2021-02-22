package com.rz.iot.bpo.model.show;

import lombok.Data;

import java.util.List;

/**
 * 描述 :
 * 作者 : wangluyao
 * 创建时间 : 2020/6/27 18:48
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoEvaluationResultRecordShow {
    private Integer projectId;
    private String grade;
    private Integer calculated;
    List<EvaluationTimeShow> evaluationTimeList;
}
