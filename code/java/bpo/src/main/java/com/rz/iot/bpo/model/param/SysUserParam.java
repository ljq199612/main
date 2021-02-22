package com.rz.iot.bpo.model.param;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 描述 : 系统用户接收参数
 * 作者 : Rycony
 * 创建时间 : 2020/6/22 9:47
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class SysUserParam extends BaseEntity {
    // 用户id
    private Integer userId;
    // 头像路径
    private String avatar;
    // 状态:1.启用 2.禁用
    private Integer status;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 邮件
    private String email;
    // 真实姓名
    private String realName;
    // 电话号码
    private String phone;
    // 备用电话号码
    private String backupsPhone;
    // 组织类型Id
    private Integer orgType;
    // 组织Id
    private Integer companyId;
    // 角色Id
    private Integer roleId;
    // 部门
    private Integer deptId;
}
