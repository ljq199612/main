package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.SysSimpleSysInfo;
import com.rz.iot.bpo.model.param.BpoOptLoginLogParam;
import com.rz.iot.bpo.model.show.SysLogShow;

import java.util.List;

public interface SysSimpleSysInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysSimpleSysInfo record);

    int insertSelective(SysSimpleSysInfo record);

    SysSimpleSysInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysSimpleSysInfo record);

    int updateByPrimaryKey(SysSimpleSysInfo record);

    List<SysLogShow>findSysInfo(BpoOptLoginLogParam param);
}