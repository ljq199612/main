package com.rz.iot.bpo.service;

import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.SysLoginLog;
import com.rz.iot.bpo.model.SysOperLog;
import com.rz.iot.bpo.model.SysSimpleSysInfo;
import com.rz.iot.bpo.model.param.BpoOptLoginLogParam;
import com.rz.iot.bpo.model.show.SysLoginLogShow;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface SysLogService {
    List<SysOperLog> findAllOpt(BpoOptLoginLogParam sysOperLog);

    List<SysLoginLogShow> findAllLogin(BpoOptLoginLogParam sysLoginLog);

    Result findOptDetail(SysOperLog sysOperLog);

    Result findUserRole();

    Result exportOpeLog(HttpServletResponse response);

    Result exportLoginLog(HttpServletResponse response);

    Result findSysInfo(BpoOptLoginLogParam param);
}
