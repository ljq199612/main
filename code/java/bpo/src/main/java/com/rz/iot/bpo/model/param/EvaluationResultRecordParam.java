package com.rz.iot.bpo.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述 : 查询项目考核加扣分明细参数
 * 作者 : wangluyao
 * 创建时间 : 2020/6/27 10:04
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class EvaluationResultRecordParam {
    @ApiModelProperty(value = "项目id", example = "3")
    private Integer projectId;
    @ApiModelProperty(value = "项目考核时间 yyyy-MM", example = "2020-06")
    private String ResultRecordDate;


}
