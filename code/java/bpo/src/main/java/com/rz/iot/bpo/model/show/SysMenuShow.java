package com.rz.iot.bpo.model.show;

import com.rz.iot.bpo.model.SysMenu;
import lombok.Data;

import java.util.List;

/**
 * 描述 : 系统菜单展示实体类
 * 作者 : Rycony
 * 创建时间 : 2020/6/23 15:56
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class SysMenuShow {
    private Integer menuId;
    private String menuName;
    // 前端路由
    private String routerName;
    // 权限标识
    private String perms;
    private String parentId;
    private List<SysMenuShow> children;
}
