package com.rz.iot.bpo.mina.model.param;


import com.rz.iot.bpo.mina.model.MinaScheduleRule;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 描述 : 排班规则参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MinaScheduleRuleParam extends MinaScheduleRule {
    //id
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    //状态
    @ApiModelProperty(value = "状态", hidden = true)
    private Integer status;

    //修改时间
    @ApiModelProperty(value = "修改时间", hidden = true)
    private Date updateTime;

    //创建时间
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;
}
