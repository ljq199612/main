package com.rz.iot.bpo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BpoEvaluationStandard {
    private Integer id;
    //考核结果记录id
    @ApiModelProperty(value = "考核内容id",example = "1")
    private Integer recordId;
    //考核内容id
    @ApiModelProperty(value = "考核内容id",example = "3")
    private Integer evaluationContentId;
    //单项扣/加分
    @ApiModelProperty(value = "单项扣/加分",example = "1")
    private Integer singleScore;
    //单项权重
    @ApiModelProperty(value = "单项权重",example = "4")
    private Integer singleWeight;
    //考核标准名称
    @ApiModelProperty(value = "考核标准名称",example = "单据错误次数")
    private String evaluationStandardName;
    //考核标准描述
    @ApiModelProperty(value = "考核标准描述",example = "单据错误次数")
    private String evaluationStandardDesc;
    //计分标准单位
    @ApiModelProperty(value = "计分标准单位",example = "人")
    private String scoreStandardUnit;
    //计分标准每单位
    @ApiModelProperty(value = "计分标准每单位",example = "-2")
    private Integer scoreStandardPoints;
    //满分(上限)
    @ApiModelProperty(value = "满分(上限)",example = "10")
    private Integer upperLimit;
    //分数制度(加分制/减分制)
    @ApiModelProperty(value = "分数制度(加分制/减分制) 1加分制 2减分制",example = "1")
    private Integer scoreType;
    @ApiModelProperty(value = "分数",example = "10")
    private Integer score;
    @ApiModelProperty(value = "加权分数",example = "0.04")
    private String weightedScore;
    @ApiModelProperty(value = "考核员id",example = "10")
    private Integer userId;
    @ApiModelProperty(value = "考核员名字",example = "我是考核员")
    private String userName;

    private Integer status;

    private Date createTime;

    private Date updateTime;

}