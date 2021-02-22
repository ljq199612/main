package com.rz.iot.bpo.model.show.personClass;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 描述 : 人员安排展示
 * 作者 : Rycony
 * 创建时间 : 2020/7/29 11:22
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 * 场地,项目,班次编号,班次分类,到场时间,上班时间,下班时间,扣除时长,计薪时长,开始日期/星期,结束日期/星期,工作小组,实际/排班人数
 * 场地-项目 1-*关系
 */
@Data
public class BpoPersonArrangeShow {
    // 场地id
    private Integer stationId;
    // 场地名称
    private String stationName;
    // 项目排班
    private List<BpoProjectClass> bpoProjectClasses;
}
