package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.SysOrg;

import java.util.List;

public interface SysOrgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysOrg record);

    int insertSelective(SysOrg record);

    SysOrg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysOrg record);

    int updateByPrimaryKey(SysOrg record);

    List<SysOrg> findAllType();

    List<String> getOrgPerms(Integer userId);
}