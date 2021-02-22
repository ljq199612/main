package com.rz.iot.bpo.model.param;

import lombok.Data;

import java.util.Date;

/**
 * 描述 : 系统角色参数
 * 作者 : Rycony
 * 创建时间 : 2020/6/27 17:34
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class SysRoleParam {
    private Integer id;

    private String roleName;

    private String roleKey;

    private Integer roleSort;

    private Integer status;

    private String remark;

    private String createTime;

    private String updateTime;
}
