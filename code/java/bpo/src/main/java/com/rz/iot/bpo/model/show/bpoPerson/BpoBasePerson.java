package com.rz.iot.bpo.model.show.bpoPerson;

import com.alibaba.excel.annotation.ExcelColumnNum;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * 描述 : 人员基本信息
 * 作者 : Rycony
 * 创建时间 : 2020/6/29 14:48
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BpoBasePerson extends BaseRowModel {
    // 人员id
    private Integer personId;
    // 名称
    @ExcelProperty(value = "人员名称", index = 0)
    private String personName;
    // 性别 0.其他 1.男 2.女
    @ExcelProperty(value = "性别(1.男 2.女 0.其他)", index = 1)
    private Integer sex;
    // 民族
    @ExcelProperty(value = "民族(需要输入全称)", index = 2)
    private String nation;
    // 身份证
    @ExcelProperty(value = "身份证", index = 3)
    private String idCard;
    // 年龄
    @ExcelProperty(value = "年龄", index = 4)
    private Integer age;
    // 出生年月
    @ExcelProperty(value = "出生年月(格式:yyyy-mm-dd)", index = 5)
    private String birth;
    // 籍贯
    @ExcelProperty(value = "籍贯", index = 6)
    private String idCardAddress;
    // 工号
    @ExcelProperty(value = "工号", index = 7)
    private String workNo;
    // 顺丰工号
    @ExcelProperty(value = "顺丰工号", index = 8)
    private String sfWorkNo;
    // 人员附件 url name
    private List<Map<String,String>> file;
    // 联系电话
    @ExcelProperty(value = "联系电话", index = 9)
    private String phone;
    // 住址
    @ExcelProperty(value = "住址", index = 10)
    private String address;
    // 备注
    private String remark;
}
