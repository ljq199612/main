package com.rz.iot.bpo.model.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 描述 : 更新计价参数
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class FeeRuleParam {
    //计价规则id
    @ApiModelProperty(value = "计价规则id", example = "1")
    private Integer ruleId;
    //计价类型:1按工件,2按时间
    @ApiModelProperty(value = "计价类型:1按工件,2按时间", example = "1")
    private Integer computationalType;
    //单价(元/小时)
    @ApiModelProperty(value = "单价(元/小时)", example = "26.00")
    private String unitPrice;
    //规则名称
    @ApiModelProperty(value = "规则名称", example = "618计价规则")
    private String ruleName;
    //优先级越大越高
    @ApiModelProperty(value = "优先级越大越高", example = "1")
    private Integer priorityLevel;
    //生效时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "生效时间", example = "2020-05-06")
    private Date effectiveTime;
    //失效时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "失效时间", example = "2020-06-06")
    private Date finishTime;
    //1:甲方给乙方2:乙方给供应商3:乙方给个人4:供应商给个人5:供应商给供应商
    @ApiModelProperty(value = "1:甲方给乙方2:乙方给供应商3:乙方给个人4:供应商给个人5:供应商给供应商", example = "1")
    private Integer type;
}
