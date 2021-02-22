package com.rz.iot.bpo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BpoEvaluationResult {
    //
    @ApiModelProperty(value = "id", example = "1")
    private Integer id;

    //所属项目`
    @ApiModelProperty(value = "项目id", example = "3")
    private Integer projectId;

    //供应商信息表id
    @ApiModelProperty(value = "供应商信息表id",example = "1")
    private Integer supplierInfoId;

    //考核分数
    @ApiModelProperty(value = "考核分数", example = "89")
    private String grade;

    //考核方
    @ApiModelProperty(value = "考核方", example = "3")
    private Integer userId;

    //已被用于计算金额,关联数据不能再修改 1:已使用,0:未使用
    @ApiModelProperty(value = "已被用于计算金额,关联数据不能再修改 1:已使用,0:未使用", example = "0")
    private Integer calculated;

    //状态
    @ApiModelProperty(value = "状态", example = "1")
    private Integer status;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    //修改时间
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    //考核时间(年-月)
    @ApiModelProperty(value = "考核时间(年-月)", example = "2020-03")
    private String evaluationTime;

    //考核时间(年-月)
    @ApiModelProperty(value = "费用支付比例", example = "80%")
    private String feeExpendRate;

    //考核时间(年-月)
    @ApiModelProperty(value = "考核额度", example = "50000")
    private String assessmentQuota;

    //考核时间(年-月)
    @ApiModelProperty(value = "考核后应付款", example = "2020-03")
    private String resultMoney;

}