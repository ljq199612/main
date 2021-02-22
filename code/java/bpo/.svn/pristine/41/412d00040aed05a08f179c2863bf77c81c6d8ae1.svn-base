package com.rz.iot.bpo.model.show.bpoPerson;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 描述 : 显示人员实体类
 * 作者 : Rycony
 * 创建时间 : 2020/6/29 10:43
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BpoPersonShow extends BaseRowModel {
    private Integer id;
    // 名称
    @ExcelProperty(value = "人员名称", index = 0)
    private String personName;
    // 工号
    @ExcelProperty(value = "工号", index = 1)
    private String workNo;
    // 岗位
    @ExcelProperty(value = "岗位", index = 2)
    private String  station;
    // 身份证
    @ExcelProperty(value = "身份证", index = 3)
    private String idCard;
    // 民族
    @ExcelProperty(value = "民族", index = 4)
    private String  nation;
    // 籍贯
    @ExcelProperty(value = "籍贯", index = 5)
    private String idCardAddress;
    // 组织id
    private Integer companyId; // 保留
    // 组织名称
    private String companyName;
    // 人员类型
    @ExcelProperty(value = "人员类型 1：自有员工 2：外包员工 3：临时工", index = 6)
    private String personType;
    // 状态
    @ExcelProperty(value = "状态 1：在职 9：离职", index = 7)
    private Integer status;
    // 入职时间
    @ExcelProperty(value = "入职时间", index = 8)
    private Date createTime;
    // 项目id
    private Integer projectId; // 保留
    // 项目名称
    @ExcelProperty(value = "项目名称", index = 9)
    private String projectName;
    // 薪资类型
    @ExcelProperty(value = "薪资类型 1.时薪 2.日薪 3.月薪", index = 10)
    private String wageType;
    // 联系方式
    @ExcelProperty(value = "联系方式", index = 11)
    private String phone;
    // 顺丰工号
    @ExcelProperty(value = "顺丰工号", index = 12)
    private String sfWorkNo;
    // 离职时间
    @ExcelProperty(value = "离职时间", index = 13)
    private Date dimissionTime;
    // 场地名称
    @ExcelProperty(value = "场地名称", index = 14)
    private String transactStationName;
}
