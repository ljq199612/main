package com.rz.iot.bpo.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述 : 项目产品价格参数
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class ProductPriceParam {
    //工序id
    @ApiModelProperty(value = "工序id",example = "34")
    private Integer processId;
    //计价规则id
    @ApiModelProperty(value = "计价规则id",example = "12")
    private Integer feeRuleId;
    //产品名称
    @ApiModelProperty(value = "产品id", example = "1", allowEmptyValue = true)
    private Integer productId;
    //产品名称
    @ApiModelProperty(value = "产品名称", example = "搬运", allowEmptyValue = true)
    private String productName;
    @ApiModelProperty(value = "产品价格id", example = "1")
    private Integer priceId;
    //计价件数上限
    @ApiModelProperty(value = "计价件数上限", example = "20")
    private Integer upperLimit;
    //计价件数下限
    @ApiModelProperty(value = "计价件数下限", example = "10")
    private Integer lowerLimit;
    //单价
    @ApiModelProperty(value = "单价", example = "10.00")
    private String unitPrice;
}
