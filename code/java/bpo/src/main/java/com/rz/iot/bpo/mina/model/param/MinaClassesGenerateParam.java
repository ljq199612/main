package com.rz.iot.bpo.mina.model.param;

import com.rz.iot.bpo.mina.model.MinaClassesGenerate;
import com.rz.iot.bpo.mina.model.MinaClassesGeneratePlus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * @Description: 排班参数
 * @Author: LiuJiaqi
 * @Create: 2020-07-23
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class MinaClassesGenerateParam extends MinaClassesGeneratePlus {

    // 场地名称
    private String transferStationName;
    // 场地 ID
    private String transferStationId;
    // 场地 {Id, name}
    private Map<Integer, String> transferStationIdAndName;

    // 排班 属性添加
    private MinaClassesGeneratePlus minaClassesGeneratePlus;

    // 排班
    private MinaClassesGeneratePlus minaClassesGenerate;

    // 工作小组 id
    private List<Integer> workGroupIds;
    // 工作小组 名称
    private List<String> workGroupNames;





}
