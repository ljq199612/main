package com.rz.iot.bpo.service;

import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.param.BpoClassesGenerateParam;
import com.rz.iot.bpo.model.param.BpoClassesParam;
import com.rz.iot.bpo.model.param.BpoScheduleRuleParam;
import com.rz.iot.bpo.model.param.BpoUpdateClassesParam;
import com.rz.iot.bpo.model.show.*;


public interface ScheduleService {
    Result<BpoClassGenerateShow> getClassGenerate(Integer projectId);

    Result getClassGenerateList(BpoClassGenerateListShow info);

    Result addClassFromExternal(BpoClassesParam bpoClassesParam);

    Result addScheduleRule(BpoScheduleRuleParam scheduleRuleParam);

    Result<BpoClassesShow> getClassList(Integer projectId,Integer transferStationId);

    Result<BpoScheduleRuleShow> getScheduleRule(Integer projectId);

    Result<BpoProjectEmployeeShow> getEmployeeData(Integer projectId);

    Result addClassGenerate(BpoClassesGenerateParam bpoClassesGenerateParam);

    Result updateClassFromExternal(BpoUpdateClassesParam bpoClassesParam);

    Result updateClassGenerate(BpoClassesGenerateParam bpoClassesGenerateParam);

    Result updateScheduleRule(BpoScheduleRuleParam scheduleRuleParam);

    Result deleteClassFromExternal(Integer classId);

    Result deleteClassGenerate(Integer scheduleId);

    Result deleteScheduleRule(Integer scheduleRuleId);


}
