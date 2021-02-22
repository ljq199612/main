package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.SysOrgRoleMenu;
import com.rz.iot.bpo.model.SysRole;
import com.rz.iot.bpo.model.param.SysRolePerParam;
import com.rz.iot.bpo.model.show.SysMenuShow;
import com.rz.iot.bpo.model.show.SysPerMenuShow;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Map;

public interface SysOrgRoleMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysOrgRoleMenu record);

    int insertSelective(SysOrgRoleMenu record);

    SysOrgRoleMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysOrgRoleMenu record);

    int updateByPrimaryKey(SysOrgRoleMenu record);

    List<SysMenuShow> findMenuPerByOrgRole(SysOrgRoleMenu sysOrgRoleMenu);

    void insertMenuPer(@Param("orgId") Integer orgId, @Param("roleId") Integer roleId, @Param("menuIds") List<Integer> menuIds);

    void updateMenuPer(@Param("orgId") Integer orgId, @Param("roleId") Integer roleId, @Param("menuIds") List<Integer> menuIds);

    List<SysMenuShow> findUnMenuPerByOrgRole(SysOrgRoleMenu sysOrgRoleMenu);

    List<Map<String,Object>> findUnRelRoleByOrgKey(String orgKey);

    // 查询所有角色菜单
    List<SysPerMenuShow> findMenuAll(SysPerMenuShow sysPerMenuShow);

    SysOrgRoleMenu findMenuRelByOrgRole(@Param("orgId") Integer orgType,@Param("roleId") Integer roleId);

    List<SysMenuShow> getAllMenuChildren();
}