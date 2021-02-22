package com.rz.iot.bpo.model.show;

import com.rz.iot.bpo.model.BpoPerformanceEvaluation;
import com.rz.iot.bpo.model.BpoPerformanceLevel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 描述 : 绩效考核评分等级
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoPerformanceLevelShow {
    @ApiModelProperty(value = "id", example = "3")
    private  Integer projectId;

    List<BpoPerformanceLevel> performanceLevelList;
}
