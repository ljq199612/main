package com.rz.iot.bpo.model.param;

import com.rz.iot.bpo.model.BpoScheduleRule;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 描述 : 排班规则参数
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BpoScheduleRuleParam extends BpoScheduleRule {
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
