package com.rz.iot.bpo.service;

import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.SysOrgRoleMenu;
import com.rz.iot.bpo.model.param.SysPerParam;
import com.rz.iot.bpo.model.param.SysRolePerParam;
import com.rz.iot.bpo.model.show.SysDataPerShow;
import com.rz.iot.bpo.model.show.SysPerMenuShow;
import com.rz.iot.bpo.model.show.SysPerShow;

import java.util.List;

public interface SysPerService {
    List<SysPerShow> findAllUserRolePer(SysPerParam sysPerParam);

    Result insertMenuPer(SysRolePerParam sysRolePerParam);

    Result deleteMenuPer(SysRolePerParam sysPerParam);

    Result insertDataPer(List<SysDataPerShow> sysDataPerShow);

    Result deleteDataPer(List<SysDataPerShow> sysDataPerShow);

    Result dataDetail(SysPerParam sysPerParam);

    Result menuDetail(SysPerParam sysPerParam);

    Result findUnRelRoleByOrgKey(String orgKey);

    List<SysPerMenuShow> findMenuAll(SysPerMenuShow sysPerMenuShow);
}
