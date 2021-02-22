package com.rz.iot.bpo.model.show;

import com.rz.iot.bpo.model.BpoEvaluationItem;
import lombok.Data;

import java.util.List;

/**
 * 描述 : 项目考核加扣分明细参数
 * 作者 : wangluyao
 * 创建时间 : 2020/6/27 18:53
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class EvaluationTimeShow {
    private String evaluationTime;

    List<BpoEvaluationItem> bpoEvaluationItemList;
}
