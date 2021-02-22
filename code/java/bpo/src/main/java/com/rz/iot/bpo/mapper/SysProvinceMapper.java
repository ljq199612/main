package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.SysProvince;

import java.util.List;

public interface SysProvinceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysProvince record);

    int insertSelective(SysProvince record);

    SysProvince selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysProvince record);

    int updateByPrimaryKey(SysProvince record);

    /**
     * 获取所有省
     * @return
     */
    List<SysProvince> getAllProvince();

    /**
     * 获取市
     * @param code
     * @return
     */
    List<SysProvince> getCity(String code);

    /**
     * 获取区
     * @param code
     * @return
     */
    List<SysProvince> getDistrict(String code);
}