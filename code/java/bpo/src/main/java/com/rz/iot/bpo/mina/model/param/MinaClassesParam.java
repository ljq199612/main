package com.rz.iot.bpo.mina.model.param;

import com.rz.iot.bpo.mina.model.MinaClassesFromExternal;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * @Description: 班次参数
 * @Author: Liu Jiaqi
 * @Create: 2020-07-24
 */
@Data
public class MinaClassesParam {

    // 项目编号
    private Integer projectId;

    // 排班规则 ID
    private Integer scheduleRuleId;

    // 项目名称
    private String projectName;

    // 场地 ID
    private Integer transferStationId;

    // 场地名称
    private String transferStationName;

    // 班次ID
    private Integer classId;

    // 班次
    private MinaClassesFromExternal minaClasses;

    // 班次列表
    private List<MinaClassesFromExternal> minaClassesList;

}
