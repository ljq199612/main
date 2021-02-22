package com.rz.iot.bpo.model.show;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 描述 : 供应商计费规则列表Show
 * 作者 : qin xian
 * 创建时间 : 2020/6/23 0023 9:13
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */

@Data
public class BpoSupplierRuleListShow {
    @ApiModelProperty(value = "供应商Id(公司Id)", example = "3")
    private Integer supplierId;

    @ApiModelProperty(value = "供应商名称(公司名称)", example = "供应商A")
    private String supplierName;

    @ApiModelProperty(value = "项目Id", example = "1")
    private Integer projectId;

    @ApiModelProperty(value = "项目名称", example = "项目A")
    private String projectName;

    @ApiModelProperty(value = "计费规则Id", example = "1")
    private Integer freeRuleId;

    @ApiModelProperty(value = "规则名称", example = "1")
    private String freeRuleName;

    @ApiModelProperty(value = "计费方式/类型(1:按件计价,2:按小时)", example = "1")
    private Integer feeRuleType;

    @ApiModelProperty(value = "生效时间", example = "2020-6-23 8：00:00")
    private Date effectiveTime;

    @ApiModelProperty(value = "失效时间", example = "2020-6-23 18：00:00")
    private Date finishTime;

    @ApiModelProperty(value = "单价", example = "5")
    private String unitPrice;


}
