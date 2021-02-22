package com.rz.iot.bpo.model.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rz.iot.bpo.model.BpoFeeRule;
import com.rz.iot.bpo.model.BpoFeeRuleByTime;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

import static com.rz.iot.bpo.common.constant.DictDataConstants.BY_NUMBER;
import static com.rz.iot.bpo.common.constant.DictDataConstants.BY_TIME;

/**
 * 描述 : 计价规则参数
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoFeeRuleParam {
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

    @ApiModelProperty(value = "项目Id", example = "1")
    private Integer projectId;
    @ApiModelProperty(value = "供应商信息表Id", example = "1")
    private Integer supplierInfoId;

    //工序
    private List<BpoProcessParam> bpoProcessParams;

    public BpoFeeRule toBpoFeeRule(){
        if (computationalType == BY_TIME){
            return null;
        }
        Date effectiveTime = this.getEffectiveTime();
        Date finishTime = this.getFinishTime();
        Integer priorityLevel = this.getPriorityLevel();
        Integer type = this.getType();
        String ruleName = this.getRuleName();
        Integer supplierInfoId = this.getSupplierInfoId();

        BpoFeeRule bpoFeeRule = new BpoFeeRule();
        bpoFeeRule.setEffectiveTime(effectiveTime);
        bpoFeeRule.setFinishTime(finishTime);
        bpoFeeRule.setRuleName(ruleName);
        bpoFeeRule.setType(type);
        bpoFeeRule.setPriorityLevel(priorityLevel);
        bpoFeeRule.setSupplierInfoId(supplierInfoId);
        return bpoFeeRule;
    }

    public BpoFeeRuleByTime toBpoFeeRuleByTime(){
        if (computationalType == BY_NUMBER){
            return null;
        }
        Date effectiveTime = this.getEffectiveTime();
        Date finishTime = this.getFinishTime();
        Integer priorityLevel = this.getPriorityLevel();
        Integer type = this.getType();
        String unitPrice = this.getUnitPrice();
        String ruleName = this.getRuleName();
        Integer projectId = this.getProjectId();
        Integer supplierInfoId = this.getSupplierInfoId();

        BpoFeeRuleByTime bpoFeeRule = new BpoFeeRuleByTime();
        bpoFeeRule.setEffectiveTime(effectiveTime);
        bpoFeeRule.setFinishTime(finishTime);
        bpoFeeRule.setRuleName(ruleName);
        bpoFeeRule.setType(type);
        bpoFeeRule.setPriorityLevel(priorityLevel);
        bpoFeeRule.setUnitPrice(unitPrice);
        bpoFeeRule.setProjectId(projectId);
        bpoFeeRule.setSupplierInfoId(supplierInfoId);
        return bpoFeeRule;
    }
}
