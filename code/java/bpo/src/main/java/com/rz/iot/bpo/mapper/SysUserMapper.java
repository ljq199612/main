package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.OrgComRoleUser;
import com.rz.iot.bpo.model.SysUser;
import com.rz.iot.bpo.model.param.SysUserParam;
import com.rz.iot.bpo.model.show.SysUserDetail;
import com.rz.iot.bpo.model.show.SysUserShow;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Map;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser findByUsername(String username);

    SysUser findByUserId(Integer userId);

    SysUser isExistUserUpdate(@Param("username") String username,@Param("userId") Integer userId);

    List<SysUserShow> findAll(SysUserParam sysUserParam);

    SysUserDetail detail(Integer userId);

    OrgComRoleUser findOrgComRoleByUserId(Integer userId);

    void updateUserStatus(@Param("userId") Integer userId,@Param("status") Integer status);

    List<Map<String,Object>> selectAuditorByProject(@Param("projectId") Integer projectId);
}