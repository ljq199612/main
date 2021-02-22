package com.rz.iot.bpo.mina.mapper;

import com.rz.iot.bpo.mina.model.MinaSysUser;

import java.util.List;

public interface MinaSysUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(MinaSysUser record);

    int insertSelective(MinaSysUser record);

    MinaSysUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(MinaSysUser record);

    int updateByPrimaryKey(MinaSysUser record);

    MinaSysUser selectByOpenid(String openId);

    List<MinaSysUser> selectByParams(String param);

    int insertRelation(Integer userId,String openId);

    Integer selectPersonId(Integer userId);
}