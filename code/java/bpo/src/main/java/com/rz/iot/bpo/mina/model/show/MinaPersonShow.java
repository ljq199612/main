package com.rz.iot.bpo.mina.model.show;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: bpo-preview
 * @description: 用于展示用户信息
 * @author: YangShun
 * @create: 2020-07-20 11:42
 **/
@Data
public class MinaPersonShow {
    private Integer id;
    // 名称
    private String personName;
    // 工号
    private String workNo;
    // 岗位
    private String station;
    //性别
    private Integer sex;
    //头像
    private String avatar;
    //发薪方式
    private Integer wageType;
    //薪资
    private String wage;
    //年龄
    private Integer age;
    // 身份证
    private String idCard;
    // 民族
    private String  nation;
    // 籍贯
    private String idCardAddress;
    // 组织名称
    private String companyName;
    // 状态 1：在职 9：离职
    private Integer status;
    // 项目名称
    private String projectName;
    // 联系方式
    private String phone;
    // 顺丰工号
    private String sfWorkNo;
    // 生日
    private String birthday;
    //现居住地址
    private String address;
    //部门名
    private String deptName;
    //中转场名
    private String transName;
    //人员类别
    private Integer personType;
    //创建时间
    private Timestamp createTime;
    //展示时间
    private String showTime;


}
