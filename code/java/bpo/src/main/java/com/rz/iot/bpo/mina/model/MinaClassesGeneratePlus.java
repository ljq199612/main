package com.rz.iot.bpo.mina.model;

import lombok.Data;
import java.util.Map;

/**
 * @Description : 对排班实体类的属性补充
 */
@Data
public class MinaClassesGeneratePlus extends  MinaClassesGenerate{
    // 项目的名称
    private String projectName;

    // 项目编号
    private String projectSN;

    // 已排班人数
    private Integer scheduledPersonNum;

    // 工作小组 [Id,名称] 列表
    Map<Integer, String> workGroupIdAndNameMap;

}