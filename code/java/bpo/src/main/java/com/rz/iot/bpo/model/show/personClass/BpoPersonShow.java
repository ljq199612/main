package com.rz.iot.bpo.model.show.personClass;

import lombok.Data;

/**
 * 描述 : 人员安排人员信息
 * 作者 : Rycony
 * 创建时间 : 2020/7/30 22:09
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 * 人员:
 * 工号,姓名,归属供应商,时薪,(岗位,类型),工作小组
 */
@Data
public class BpoPersonShow {
    // 人员id
    private Integer personId;
    // 工号
    private String workNo;
    // 姓名
    private String personName;
    // 归属供应商
    private String belongCompany;
    // 时薪
    private Integer wageType;
    // 岗位
    private String station;
    // 人员类型
    private Integer personType;
}
