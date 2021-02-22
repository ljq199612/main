package com.rz.iot.bpo.mina.model.show;

import com.rz.iot.bpo.mina.model.MinaClassesGeneratePlus;
import lombok.Data;

import java.util.List;
import java.util.Map;


/**
 * @description: 展示排班信息列表
 * @author: LiuJiaqi
 * @create: 2020-07-22 19:12
 **/
@Data
public class MinaClassGenerateShow extends MinaClassesGeneratePlus{
    //项目名
    private String projectName;

    // 工作小组 id, (单行分开)
    private Integer workGroupId;

    // 工作小组名称 (单行分开)
    private String workGroupName;

    // 工作小组 {id, name}
    private Map<Integer, String> workGroupIdAndName;

    // 排班信息
    private MinaClassesGeneratePlus minaClassesGeneratePlus;

    //排班和工作小组关联信息(包含工作小组 id 和 工作小组名称等, 非单行显示)
    private List<MinaClassesGroupRelShow> minaClassesGroupRelShows;
    // 排班信息
    private List<MinaClassesGeneratePlus> minaClassesGeneratePluses;

}
