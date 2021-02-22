package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.model.BpoEvaluationResult;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class BpoEvaluationResultParam extends BpoEvaluationResult {
    //
    @ApiModelProperty(value = "id", example = "1")
    private Integer id;
    //状态
    @ApiModelProperty(value = "状态", hidden = true)
    private Integer status;

    //创建时间
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

    //修改时间
    @ApiModelProperty(value = "修改时间", hidden = true)
    private Date updateTime;
}
