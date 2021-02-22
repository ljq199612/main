package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.SysOperLog;
import com.rz.iot.bpo.model.SysSimpleSysInfo;
import com.rz.iot.bpo.model.param.BpoOptLoginLogParam;

import java.util.List;

public interface SysOperLogMapper {
    int deleteByPrimaryKey(Long operId);

    int insert(SysOperLog record);

    int insertSelective(SysOperLog record);

    SysOperLog selectByPrimaryKey(Long operId);

    int updateByPrimaryKeySelective(SysOperLog record);

    int updateByPrimaryKey(SysOperLog record);

    List<SysOperLog> findAll(BpoOptLoginLogParam sysOperLog);

    List<SysOperLog> findOpeLog();
}