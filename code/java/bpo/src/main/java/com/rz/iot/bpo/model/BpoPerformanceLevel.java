package com.rz.iot.bpo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BpoPerformanceLevel {
    //
    @ApiModelProperty(value = "id", example = "1")
    private Integer id;

    //考核评定等级
    @ApiModelProperty(value = "考核评定等级", example = "A")
    private String performanceLevel;

    //考核评定上限
    @ApiModelProperty(value = "考核评定上限", example = "90")
    private Integer upperLimit;

    //考核评定下限
    @ApiModelProperty(value = "考核评定下限", example = "75")
    private Integer lowerLimit;

    //费用支付比例保底
    @ApiModelProperty(value = "费用支付比例保底", example = "85%")
    private String feeExpendRateMinimum;

    //评分高出下限增长
    @ApiModelProperty(value = "评分高出下限增长", example = "1")
    private Integer increase;

    //所属项目
    @ApiModelProperty(value = "所属项目", example = "3")
    private Integer projectId;

    //供应商信息表id
    @ApiModelProperty(value = "供应商信息表id",example = "1")
    private Integer supplierInfoId;

    @ApiModelProperty(value = "状态", example = "1")
    private Integer status;
    @ApiModelProperty(value = "创建时间", example = "2020-06-06 16:04:32")
    private Date createTime;
    @ApiModelProperty(value = "修改时间", example = "2020-06-06 16:04:32")
    private Date updateTime;

}