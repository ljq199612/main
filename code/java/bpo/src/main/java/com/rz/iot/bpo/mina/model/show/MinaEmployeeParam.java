package com.rz.iot.bpo.mina.model.show;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Description: 人员安排参数
 * @Author: Liu Jiaqi
 * @Create: 2020-07-30
 */
@Data
public class MinaEmployeeParam {
    // 项目 ID
    Integer projectId;

    // 班次 ID
    private Integer ClassesId;

    // 人员信息 Id 列表
    private List<Integer> personIdList;

    // 小组 id
    Integer groupId;

    // 小组 id 列表
    List<Integer> groupIdList;

    // 班次开始日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "开始日期", example = "2020-05-06 ")
    Date startDate;

    // 班次结束日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "开始日期", example = "2020-05-06 ")
    Date endDate;
}