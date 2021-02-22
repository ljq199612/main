package com.rz.iot.bpo.mina.model.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rz.iot.bpo.mina.model.MinaClassesGeneratePlus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 排班到个人 参数
 * @Author: Liu Jiaqi
 * @Create: 2020-07-29
 */

@Data
public class MinaScheduleToEmployeeParam extends MinaClassesGeneratePlus {


    // 场地名称
    private String transferStationName;
    // 场地 ID
    private String transferStationId;
    // 场地 {Id, name}
    private Map<Integer, String> transferStationIdAndName;

    // 工作小组 id
    private List<Integer> workGroupIds;
    // 工作小组 名称
    private List<String> workGroupNames;

    // 选择班次的某个日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date someDay;

    // 今天的日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date today;

/*给特定班次人员安排用*/

    // 某工作小组 id
    private Integer workGroupId;

    // 某工作小组名称
    private String workGroupName;

    // 人员 id 列表
    private List<Integer> personIdList;

    // 排班
    private MinaClassesGeneratePlus minaClassesGeneratePlus;

  }