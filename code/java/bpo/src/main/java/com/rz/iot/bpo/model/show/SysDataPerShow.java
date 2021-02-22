package com.rz.iot.bpo.model.show;

import lombok.Data;

/**
 * 描述 : 系统数据权限show
 * 作者 : Rycony
 * 创建时间 : 2020/6/23 21:02
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class SysDataPerShow {
    // id
    private Integer id;
    // 模块id
    private Integer moduleId;
    // 模块名称
    private String moduleName;
    // 用户id
    private Integer userId;
    // 模块类型
    private Integer moduleType;
}
