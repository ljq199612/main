package com.rz.iot.bpo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BpoEvaluationResultRecord {
    private Integer id;
    //项目id
    private Integer projectId;
    //供应商信息表id
    private Integer supplierInfoId;
    //分数结果id
    private Integer resultId;
    //考核项目id
    private Integer evaluationItemId;
    //考核项目名称
    private String evaluationItemName;
    //考核项目权重
    private Integer weight;
    //考核内容id
    private Integer evaluationContentId;
    //考核内容名称
    private String evaluationContentName;
    //考核标准id
    private Integer evaluationStandardId;
    //单项权重
    private Integer singleWeight;
    //考核标准名称
    private String evaluationStandardName;
    //考核标准说明
    private String evaluationStandardDesc;
    //计分标准单位
    @ApiModelProperty(value = "计分标准单位",example = "人")
    private String scoreStandardUnit;
    //计分标准每单位
    @ApiModelProperty(value = "计分标准每单位",example = "-2")
    private Integer scoreStandardPoints;
    //满分(上限)
    private Integer upperLimit;
    //单项扣/加分
    private Integer singleScore;
    //分数制度(加分制/减分制)
    private Integer scoreType;
    //分数
    private Integer score;
    //加权分数
    private String weightedScore;
    //考核人
    private Integer userId;
    //考核时间
    private String evaluationTime;

    private Integer status;

    private Date createTime;

    private Date updateTime;

}