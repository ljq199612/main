package com.rz.iot.bpo.mina.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class MinaClassesGroupRel {
    @ApiModelProperty(value = "ID", example = "12")
    private Integer id;

    @ApiModelProperty(value = "项目ID", example = "12")
    private Integer projectId;

    @ApiModelProperty(value = "班次编号", example = "SN_20200506_1600_2300")
    private String classSn;

    @ApiModelProperty(value = "工作小组ID", example = "13")
    private Integer workGroupId;

    // 状态  1: 正常  9: 删除
    @ApiModelProperty(value = "状态", example = "1")
    private Integer status;

    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间", example = "13:00:00")
    private Date createTime;

    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间", example = "13:00:00")
    private Date updateTime;

    @ApiModelProperty(value = "排班 ID", example = "10")
    private Integer classGenerateId;
}