package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.SysAreaRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAreaRelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysAreaRel record);

    int insertSelective(SysAreaRel record);

    SysAreaRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAreaRel record);

    int updateByPrimaryKey(SysAreaRel record);

    Integer isExsitAreaRel(SysAreaRel sysAreaRel);
    /**
     * 获取省、市、区关系表省
     */
    List<SysAreaRel> getAllSysAreaRelP();
    /**
     * 获取省、市、区关系表市
     */
    List<SysAreaRel> getSysAreaRelC(String code);
    /**
     * 获取省、市、区关系表区
     */
    List<SysAreaRel> getSysAreaRelD(@Param("cityCode") String cityCode);


    Integer getSysAreaIdByDistrictId(@Param("districtId") String districtId);
}