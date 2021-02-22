package com.rz.iot.bpo.model.param;

import com.rz.iot.bpo.model.BpoProductPrice;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述 : 项目生成产品参数
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoProductParam {
    //是否直属工序
    @ApiModelProperty(value = "是否直属工序", example = "false")
    private Boolean directly;
    //产品Id
    @ApiModelProperty(value = "产品Id", example = "1", allowEmptyValue = true)
    private Integer productId;
    //产品名称
    @ApiModelProperty(value = "产品名称", example = "搬运", allowEmptyValue = true)
    private String productName;
    //计价件数上限
    @ApiModelProperty(value = "计价件数上限", example = "20")
    private Integer upperLimit;
    //计价件数下限
    @ApiModelProperty(value = "计价件数下限", example = "10")
    private Integer lowerLimit;
    //单价
    @ApiModelProperty(value = "单价", example = "10.00")
    private String unitPrice;

    public BpoProductPrice toBpoProductPrice(){
        BpoProductPrice info = new BpoProductPrice();

        if(this.getDirectly() == null || this.getDirectly() == false)
            info.setProductType((byte)2);
        else
            info.setProductType((byte)1);
        info.setProductId(this.getProductId());
        info.setUpperLimit(this.getUpperLimit());
        info.setLowerLimit(this.getLowerLimit());
        info.setPrice(this.getUnitPrice());
        return info;
    }
}
