package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.SysUserModuleRel;
import com.rz.iot.bpo.model.param.SysPerParam;
import com.rz.iot.bpo.model.show.SysDataPerShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserModuleRelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserModuleRel record);

    int insertSelective(SysUserModuleRel record);

    SysUserModuleRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserModuleRel record);

    int updateByPrimaryKey(SysUserModuleRel record);

    List<SysDataPerShow> findDataPerByOrgRoleUser(SysPerParam sysPerParam);

    List<SysDataPerShow> findUnDataPerByOrgRoleUser(SysPerParam sysPerParam);

    void delete(@Param("list")List<SysDataPerShow> sysUserModuleRel);

    void insertDataPer(@Param("list") List<SysDataPerShow> sysDataPerShow);

    List<Integer> selectProjectIdsByUserId(Integer userId);
}