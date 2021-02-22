package com.rz.iot.bpo.model.show;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 描述 : 计价规则模块
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoFeeRuleModelShow {
    @ApiModelProperty(value = "模块id", example = "2")
    private Integer modelId;
    @ApiModelProperty(value = "模块编码", example = "GGMK-ZZZY")
    private String moduleCode;
    @ApiModelProperty(value = "模块名称", example = "中转作业")
    private String modelName;
    private List<BpoFeeRuleGroupShow> feeRuleGroupShows;
}
