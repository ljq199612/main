package com.rz.iot.bpo.model.param;

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
public class BpoProductFeeParam {
    //是否直属工序
    @ApiModelProperty(value = "是否直属工序", example = "false")
    private boolean isDirectly;
    //产品名称
    @ApiModelProperty(value = "产品名称", example = "搬运")
    private String productName;
    //产品所属工序
    @ApiModelProperty(value = "产品所属工序", example = "5")
    private Integer processId;
    //计价件数上限
    @ApiModelProperty(value = "计价件数上限", example = "20")
    private Integer upperLimit;
    //计价件数下限
    @ApiModelProperty(value = "计价件数下限", example = "10")
    private Integer lowerLimit;
    //所属项目
    @ApiModelProperty(value = "所属项目", example = "1")
    private Integer projectId;
    //单价
    @ApiModelProperty(value = "单价", example = "10.00")
    private String unitPrice;
}
