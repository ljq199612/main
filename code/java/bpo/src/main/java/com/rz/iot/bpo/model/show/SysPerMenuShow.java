package com.rz.iot.bpo.model.show;

import lombok.Data;

import java.util.Date;

/**
 * 描述 : 展示菜单权限
 * 作者 : Rycony
 * 创建时间 : 2020/7/17 16:12
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class SysPerMenuShow {
    // 组织类型id
    public Integer orgId;
    // 组织名称
    public String orgName;
    // 角色id
    public Integer roleId;
    // 角色名称
    private String roleName;
    // 创建时间
    private Date createTime;
}
