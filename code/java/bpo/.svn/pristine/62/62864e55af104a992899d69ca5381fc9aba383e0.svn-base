package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.SysWorkModuleRel;
import com.rz.iot.bpo.model.param.SysProOrGrpParam;
import com.rz.iot.bpo.model.param.SysWorkModuleRelParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysWorkModuleRelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysWorkModuleRel record);

    int insertSelective(SysWorkModuleRel record);

    SysWorkModuleRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysWorkModuleRel record);

    int updateByPrimaryKey(SysWorkModuleRel record);

    List<SysWorkModuleRel> isExsitCode(@Param("param") SysProOrGrpParam sysProOrGrpParam);

    void insertSysProOrGrp(@Param("param") SysProOrGrpParam sysProOrGrpParam);

    List<SysWorkModuleRelParam> findAll();

    SysWorkModuleRel isExistSameCode(String code);

    SysWorkModuleRel isExistSameName(String name);

    SysWorkModuleRel findByNameAndCode(@Param("name")String name, @Param("code")String code);
}