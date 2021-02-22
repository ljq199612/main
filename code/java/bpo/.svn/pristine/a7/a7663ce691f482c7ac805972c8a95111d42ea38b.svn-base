package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.SysRole;
import com.rz.iot.bpo.model.param.SysPerParam;
import com.rz.iot.bpo.model.show.SysPerShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<String> getRolePerms(Integer userId);

    List<SysRole> findAllRole();

    List<SysRole> findAll(SysRole sysRole);

    SysRole isExistCode(SysRole sysRole);

    List<SysRole> findAllNoPage();

    SysRole detail(Integer id);

    SysRole findRoleByUserId(Integer userId);
}