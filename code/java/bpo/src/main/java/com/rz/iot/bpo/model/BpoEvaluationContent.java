package com.rz.iot.bpo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BpoEvaluationContent {
    private Integer id;
    //考核项目id
    @ApiModelProperty(value = "考核项目id",example = "3")
    private Integer evaluationItemId;
    //考核内容名称
    @ApiModelProperty(value = "考核内容名称",example = "拣货类")
    private String evaluationContentName;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private List<BpoEvaluationStandard> bpoEvaluationStandardList;

}