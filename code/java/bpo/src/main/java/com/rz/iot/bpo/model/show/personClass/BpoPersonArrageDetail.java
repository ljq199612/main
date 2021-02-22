package com.rz.iot.bpo.model.show.personClass;

import lombok.Data;

import java.util.List;

/**
 * 描述 : 人员安排详情
 * 作者 : Rycony
 * 创建时间 : 2020/7/30 21:15
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 * 班次编号,班次分类,上班时间,下班时间,开始日期,结束日期,扣除时长,计薪时长,人员安排
 */
@Data
public class BpoPersonArrageDetail {
    // 班次id
    private Integer classId;
    // 班次编号
    private String classSn;
    // 班次分类
    private Integer classType;
    // 上班时间
    private String startTime;
    // 下班时间
    private String endTime;
    // 开始日期
    private String startDate;
    // 结束日期
    private String endDate;
    // 扣除时长
    private Integer excludeHour;
    // 计薪时长
    private Integer feeHour;
    // 安排日期
    private String cycle;
    // 班次-小组信息
    private List<BpoGroupShow> workGroupShowList;
    // 班次-无小组人员
    private List<BpoPersonShow> bpoPersonShow;
    // 班次-未分组人员
    private List<BpoPersonShow> unBpoPersonShow;
}
