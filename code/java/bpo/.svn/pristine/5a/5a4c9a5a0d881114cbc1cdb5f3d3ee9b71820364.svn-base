package com.rz.iot.bpo.model.show;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 描述 : 计价规则
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoFeeRuleShow {
    @ApiModelProperty(value = "计价类型:1按工件,2按时间", example = "1")
    private Integer computationalType;
    @ApiModelProperty(value = "计价规则id",example = "3")
    private Integer feeRuleId;
    @ApiModelProperty(value = "生效时间",example = "2020-05-06")
    private Date effectiveTime;
    @ApiModelProperty(value = "结束时间",example = "2020-06-06")
    private Date finishTime;
    @ApiModelProperty(value = "计价规则名称",example = "3")
    private String ruleName;
    @ApiModelProperty(value = "优先级",example = "3")
    private Integer priorityLevel;
    @ApiModelProperty(value = "计价类型1:甲方给乙方2:乙方给供应商3:乙方给个人4:供应商给个人5:供应商给供应商"
            ,example = "3")
    private Integer type;
    //单价(元/小时)
    @ApiModelProperty(value = "单价(元/小时)", example = "26.00")
    private String unitPrice;
    private List<BpoFeeRuleModelShow> feeRuleModelShowList;
}
