package com.rz.iot.bpo.model.show.personClass;

import com.rz.iot.bpo.model.param.SysWorkGroup;
import lombok.Data;

import java.util.List;

/**
 * 描述 : 前端人员安排班次展示
 * 作者 : Rycony
 * 创建时间 : 2020/7/29 11:39
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoClass {
    // 班次id
    private Integer classId;
    // 班次sn
    private String classSn;
    // 班次分类
    private Integer classType;
    // 到达时间
    private String arriveTime;
    // 上班时间
    private String startTime;
    // 下班时间
    private String endTime;
    // 扣除时长
    private Integer excludeHour;
    // 计薪时长
    private Integer feeHour;
    // 开始日期
    private String startDate;
    // 开始星期
    private String startWeek;
    // 结束日期
    private String endDate;
    // 结束日期
    private String endWeek;
    // 工作小组
    private List<SysWorkGroup> workGroups;
    // 实际人数
    private Integer actualNum;
    // 排班人数
    private Integer classNum;
}
