package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.SysDeptUserRel;

public interface SysDeptUserRelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDeptUserRel record);

    int insertSelective(SysDeptUserRel record);

    SysDeptUserRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDeptUserRel record);

    int updateByPrimaryKey(SysDeptUserRel record);

    // 通过用户查询用户与部门关联关系
    SysDeptUserRel findByUserId(Integer userId);

}