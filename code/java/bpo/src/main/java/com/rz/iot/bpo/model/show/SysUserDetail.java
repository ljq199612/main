package com.rz.iot.bpo.model.show;

import com.rz.iot.bpo.model.show.bpoPerson.SysDeptShow;
import lombok.Data;

/**
 * 描述 : 展示前端用户详情
 * 作者 : Rycony
 * 创建时间 : 2020/6/25 13:01
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class SysUserDetail {
    // userId
    private Integer userId;
    // user头像
    private String avatar;
    // 用户名
    private String username;
    // 状态 1.启用 2.禁用
    private Integer status;
    // 真实姓名
    private String realName;
    // 电话
    private String phone;
    // 备用电话
    private String backupsPhone;
    // 邮件
    private String email;
    // 备注
    private String remark;
    // 组织类型key
    private Integer orgType;
    // 组织类型名称
    private String orgName;
    // 组织id
    private Integer companyId;
    // 组织名称
    private String companyName;
    // 角色id
    private Integer roleId;
    // 角色名称
    private String roleName;
    // 用户部门
    private Integer deptId;
    // 用户组织架构
    private SysDeptShow sysDeptShow;
}
