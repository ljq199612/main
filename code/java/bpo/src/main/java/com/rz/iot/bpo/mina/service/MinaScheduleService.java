package com.rz.iot.bpo.mina.service;

import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mina.model.param.*;
import com.rz.iot.bpo.mina.model.show.MinaClassGenerateShow;
import com.rz.iot.bpo.mina.model.show.MinaClassesShow;
import com.rz.iot.bpo.mina.model.show.MinaEmployeeParam;


public interface MinaScheduleService {

    Result getClassFromExternal(MinaClassesParam minaClassesParam);

    Result<MinaClassesShow> getClassFromExternalList(MinaClassesParam minaClassesParam);

    Result addClassFromExternal(MinaClassesParam minaClassesParam);

    Result updateClassFromExternal(MinaUpdateClassesParam minaClassesParam);

    Result deleteClassFromExternal(MinaClassesParam minaClassesParam);

    Result getProjectNamesByTransferStationName(MinaClassesParam minaClassesParam);

    Result<MinaClassGenerateShow> getClassGenerateList(MinaClassesGenerateParam classesGenerateParam);

    Result getClassGenerateDetail(MinaClassesGenerateParam classesGenerateParam);

    Result addClassGenerate(MinaClassesGenerateParam classesGenerateParam);

    Result addClassGenerateList(MinaClassesParam classesParamList);

    Result updateClassGenerate(MinaClassesGenerateParam bpoClassesGenerateParam);

    Result addScheduleRule(MinaScheduleRuleParam scheduleRuleParam);

    Result deleteClassGenerate(Integer scheduleId);

    Result deleteScheduleRule(Integer scheduleRuleId);

    Result getScheduleEmployeeDetail(MinaScheduleToEmployeeParam minaScheduleToEmployeeParam);

    Result  getScheduleEmployeeList(MinaScheduleToEmployeeParam minaScheduleToEmployeeParam);

    Result getEmployeeList(MinaScheduleToEmployeeParam minaEmployeeParam);

    Result deleteEmployee(Integer employeeId);

    Result addEmployees(MinaScheduleToEmployeeParam minaEmployeeParam);

}
