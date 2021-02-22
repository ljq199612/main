package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.SysLoginLog;
import com.rz.iot.bpo.model.SysOperLog;
import com.rz.iot.bpo.model.param.BpoOptLoginLogParam;
import com.rz.iot.bpo.model.show.SysLoginLogShow;

import java.util.List;

public interface SysLoginLogMapper {
    int deleteByPrimaryKey(Long infoId);

    int insert(SysLoginLog record);

    int insertSelective(SysLoginLog record);

    SysLoginLog selectByPrimaryKey(Long infoId);

    int updateByPrimaryKeySelective(SysLoginLog record);

    int updateByPrimaryKey(SysLoginLog record);

    List<SysLoginLogShow> findAll(BpoOptLoginLogParam sysLoginLog);

    List<SysLoginLogShow> findLoginLog();
}