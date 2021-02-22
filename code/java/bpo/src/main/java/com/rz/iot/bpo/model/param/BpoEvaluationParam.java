package com.rz.iot.bpo.model.param;

import com.rz.iot.bpo.model.BpoEvaluationItem;
import com.rz.iot.bpo.model.BpoEvaluationResult;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 描述 : 项目考核加扣分明细参数
 * 作者 : wangluyao
 * 创建时间 : 2020/6/26 14:39
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoEvaluationParam {
    @ApiModelProperty(value = "项目id", example = "3")
    private Integer projectId;
    @ApiModelProperty(value = "供应商信息id", example = "3")
    private Integer supplierInfoId;
    @ApiModelProperty(value = "项目考核时间 yyyy-MM", example = "2020-06")
    private String ResultRecordDate;

    private List<BpoEvaluationItem> bpoEvaluationItemList;
}
