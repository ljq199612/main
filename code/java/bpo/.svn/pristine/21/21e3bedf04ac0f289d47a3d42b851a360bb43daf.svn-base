package com.rz.iot.bpo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.*;

@Data
public class SysDept implements Serializable {
    private Integer deptId;

    private String deptCode;

    private String deptName;

    private Integer isTop;

    private Integer parentId;

    private String descendants;

    private Integer orderNum;

    private String leader;

    private String phone;

    private String email;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer topId;

    private String createName;

    //用于存储下级的部门
    private List<SysDept> depts = new ArrayList ();

    //用于存储关联表信息
    private Integer subId; //下属id

    private Integer subType; // 下属类型

    private Integer userId;//用户id

    private Integer control; //用于判断是否可以有权限操作  1：true  null：false

}