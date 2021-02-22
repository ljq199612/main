package com.rz.iot.bpo.mina.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
public class MinaScheduleRule {
    //id
    @ApiModelProperty(value = "id", example = "1")
    private Integer id;

    //最大工时时长
    @ApiModelProperty(value = "最大工时时长", example = "14")
    private Integer maxWorkHour;

    //最小工时时长
    @ApiModelProperty(value = "最小工时时长", example = "8")
    private Integer minWorkHour;

    //重组后班次数量
    @ApiModelProperty(value = "重组后班次数量", example = "10")
    private Integer scheduleNumber;

    //优先人员类型,1:固定人员,2:固定班次,3:年龄从小到大
    @ApiModelProperty(value = "优先人员类型,1:固定人员,2:固定班次,3:年龄从小到大", example = "1")
    private Integer priorityWorkers;

    //偏差率
    @ApiModelProperty(value = "偏差率", example = "10%")
    private String deviationRate;
    //项目id
    @ApiModelProperty(value = "项目id", example = "3")
    private Integer projectId;

    @ApiModelProperty(value = "状态", example = "1")
    private Integer status;
    @ApiModelProperty(value = "创建时间", example = "2020-06-06 16:04:32")
    private Date createTime;
    @ApiModelProperty(value = "修改时间", example = "2020-06-06 16:04:32")
    private Date updateTime;

}


