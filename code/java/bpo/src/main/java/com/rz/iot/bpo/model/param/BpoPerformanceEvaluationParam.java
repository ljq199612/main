package com.rz.iot.bpo.model.param;

import com.rz.iot.bpo.model.BpoPerformanceEvaluation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 描述 : 绩效考核参数
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BpoPerformanceEvaluationParam extends BpoPerformanceEvaluation {
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    @ApiModelProperty(value = "状态", hidden = true)
    private Integer status;
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;
    @ApiModelProperty(value = "修改时间", hidden = true)
    private Date updateTime;
}
