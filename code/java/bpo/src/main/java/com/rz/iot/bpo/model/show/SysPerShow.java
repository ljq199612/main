package com.rz.iot.bpo.model.show;

import com.rz.iot.bpo.model.show.bpoPerson.SysDeptShow;
import lombok.Data;

import java.util.Date;

/**
 * 描述 : 权限展示
 * 作者 : Rycony
 * 创建时间 : 2020/6/22 11:36
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class SysPerShow {
    private Integer userId;
    private String username;
    // 角色id
    private Integer roleId;
    // 对应前端角色类型
    private String roleName;
    private String orgKey;
    // 组织id
    private Integer orgId;
    // 组织类型名称
    private String orgName;
    // 公司名称
    private String companyName;
    // 部门名称
    private Integer deptId;
    
    private SysDeptShow sysDeptShow;
    // 创建时间
    private Date createTime;
    // 状态:1.启用 2.禁用
    private String status;
}
