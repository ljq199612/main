package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.SysCompanyRoleUser;
import com.rz.iot.bpo.model.param.SysPerParam;
import com.rz.iot.bpo.model.show.SysPerShow;

import java.util.List;

public interface SysCompanyRoleUserMapper {
    Integer selectCompanyIdByUserId(Integer id);

    SysCompanyRoleUser selectByUserId(Integer id);

    int deleteByPrimaryKey(Integer id);

    int insert(SysCompanyRoleUser record);

    int insertSelective(SysCompanyRoleUser record);

    SysCompanyRoleUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysCompanyRoleUser record);

    int updateByPrimaryKey(SysCompanyRoleUser record);

    /**
     * 查询用户的角色类型和组织类型
     * @param sysPerParam 系统权限查询参数
     * @return
     */
    List<SysPerShow> findAllUserRolePer(SysPerParam sysPerParam);

    void update(SysCompanyRoleUser sysCompanyRoleUser);
}