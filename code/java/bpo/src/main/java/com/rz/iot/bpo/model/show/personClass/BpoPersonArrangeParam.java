package com.rz.iot.bpo.model.show.personClass;

import lombok.Data;

import java.util.List;

/**
 * 描述 : 人员安排请求参数
 * 作者 : Rycony
 * 创建时间 : 2020/8/1 17:46
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoPersonArrangeParam {
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
    // 新增-已排班人员
    private List<BpoGroupShow> classList;
    // 删除-已排班人员,删除只需传人员id即可
    private List<BpoPersonShow> delClassList;
}
