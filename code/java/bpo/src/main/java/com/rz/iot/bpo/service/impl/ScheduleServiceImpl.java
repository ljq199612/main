package com.rz.iot.bpo.service.impl;

import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.common.utils.SecurityUtils;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.*;
import com.rz.iot.bpo.model.*;
import com.rz.iot.bpo.model.param.*;
import com.rz.iot.bpo.model.show.*;
import com.rz.iot.bpo.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import static com.rz.iot.bpo.common.constant.DictDataConstants.DELETE_STATUS;
import static com.rz.iot.bpo.common.constant.DictDataConstants.NORMAL_STATUS;

@Slf4j
@Service("ScheduleService")
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    BpoScheduleRuleMapper scheduleRuleMapper;

    @Resource
    BpoClassesFromExternalMapper classesFromExternalMapper;

    @Resource
    BpoClassesGenerateMapper classesGenerateMapper;

    @Resource
    BpoScheduleToEmployeeMapper scheduleToEmployeeMapper;

    @Resource
    BpoBatchMapper bpoBatchMapper;


    @Override
    public Result<BpoClassGenerateShow> getClassGenerate(Integer projectId) {
        BpoClassGenerateShow bpoClassGenerateShow = classesGenerateMapper.selectByProjectId(projectId);
        Result<BpoClassGenerateShow> result = new Result<>(ResultConstants.REQUEST_SUCCESS);
        result.setData(bpoClassGenerateShow);
        return result;
    }
    @Override
    public Result<BpoClassGenerateListShow> getClassGenerateList(BpoClassGenerateListShow info) {

        List<BpoClassGenerateListShow> list = classesGenerateMapper.getClassGenerateList(info);

        return Result.success(list);
    }
    @Override
    public Result<BpoClassesShow> getClassList(Integer projectId,Integer transferStationId) {
        BpoClassesShow bpoClassesShow = classesFromExternalMapper.selectByProjectId(projectId,transferStationId);
        Result<BpoClassesShow> result = new Result<>(ResultConstants.REQUEST_SUCCESS);
        result.setData(bpoClassesShow);
        return result;
    }
    @Override
    public Result<BpoScheduleRuleShow> getScheduleRule(Integer projectId) {
        BpoScheduleRuleShow scheduleRuleShow = scheduleRuleMapper.selectByProjectId(projectId);
        Result<BpoScheduleRuleShow> result = new Result<>(ResultConstants.REQUEST_SUCCESS);
        result.setData(scheduleRuleShow);
        return result;
    }
    public static void main(String[] args) {
        Class<FeeRuleParam> bpoProjectClass = FeeRuleParam.class;
        Field[] fields = bpoProjectClass.getDeclaredFields();
        String className = "updateFeeRuleParam";
        for (Field field : fields) {
            String name = field.getName();
            Class<?> type = field.getType();
            String s = name.substring(0, 1).toUpperCase() + name.substring(1);
            switch (type.toString()) {
                case "class java.lang.Integer":
                    System.out.println("Integer " + name + " = " + className + ".get" + s + "();\n" +
                            "        if (" + name + " == null){\n" +
                            "            return Result.requestParamError(\"" + name + " is null\");\n" +
                            "        }");
                    break;
                case "class java.lang.String":
                    System.out.println("String " + name + " = " + className + ".get" + s + "();\n" +
                            "        if (StringUtils.isEmpty(" + name + ")){\n" +
                            "            return Result.requestParamError(\"" + name + " is null\");\n" +
                            "        }");
                    break;
                case "class java.util.Date":
                    System.out.println("Date " + name + " = " + className + ".get" + s + "();\n" +
                            "        if (" + name + " == null){\n" +
                            "            return Result.requestParamError(\"" + name + " is null\");\n" +
                            "        }");

                    break;
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public Result addClassFromExternal(BpoClassesParam bpoClassesParam) {
        Result result = handleAddClassListParam(bpoClassesParam);
        if (result != null) {
            return result;
        }
        //添加批次信息
        Integer scheduleRuleId = bpoClassesParam.getScheduleRuleId();
        BpoBatch bpoBatch = new BpoBatch();
        bpoBatch.setStatus(NORMAL_STATUS);
        bpoBatch.setScheduleRuleId(scheduleRuleId);
        bpoBatchMapper.insertSelective(bpoBatch);
        Integer id = bpoBatch.getId();
        List<BpoClassesFromExternal> bpoClassesList = bpoClassesParam.getBpoClassesList();
        //添加班次信息
        for (BpoClassesFromExternal bpoClasses : bpoClassesList) {
            bpoClasses.setBatchId(id);
            bpoClasses.setStatus(NORMAL_STATUS);
            classesFromExternalMapper.insertSelective(bpoClasses);
        }

        return Result.success();
    }

    private Result handleAddClassListParam(BpoClassesParam bpoClassesParam) {
        Integer scheduleRuleId = bpoClassesParam.getScheduleRuleId();
        if (scheduleRuleId == null) {
            return Result.requestParamError("scheduleRuleId is null");
        }
        Integer projectId = bpoClassesParam.getProjectId();
        if (projectId == null) {
            return Result.requestParamError("projectId is null");
        }
        List<BpoClassesFromExternal> bpoClassesList = bpoClassesParam.getBpoClassesList();
        for (BpoClassesFromExternal bpoClasses : bpoClassesList) {
            bpoClasses.setProjectId(projectId);
            bpoClasses.setId(null);
            String classSn = bpoClasses.getClassSn();
            if (StringUtils.isEmpty(classSn)) {
                return Result.requestParamError("classSn is null");
            }
            String className = bpoClasses.getClassName();
            if (StringUtils.isEmpty(className)) {
                return Result.requestParamError("className is null");
            }
            Date startDate = bpoClasses.getStartDate();
            if (startDate == null) {
                return Result.requestParamError("startDate is null");
            }
            Date endDate = bpoClasses.getEndDate();
            if (endDate == null) {
                return Result.requestParamError("endDate is null");
            }
            Date startTime = bpoClasses.getStartTime();
            if (startTime == null) {
                return Result.requestParamError("startTime is null");
            }
            Date endTime = bpoClasses.getEndTime();
            if (endTime == null) {
                return Result.requestParamError("endTime is null");
            }
            Date arriveTime = bpoClasses.getArriveTime();
            if (arriveTime == null) {
                return Result.requestParamError("arriveTime is null");
            }
            Integer peopleNumber = bpoClasses.getPeopleNumber();
            if (peopleNumber == null) {
                return Result.requestParamError("peopleNumber is null");
            }
            Integer source = bpoClasses.getSource();
            if (source == null) {
                return Result.requestParamError("source is null");
            }
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public Result addScheduleRule(BpoScheduleRuleParam scheduleRuleParam) {
        Result result = handelScheduleRuleParam(scheduleRuleParam);
        if (result != null) {
            return result;
        }
        scheduleRuleMapper.insertSelective(scheduleRuleParam);
        BpoScheduleRule bpoScheduleRule = new BpoScheduleRule();
        bpoScheduleRule.setId(scheduleRuleParam.getId());
        return Result.success(bpoScheduleRule);
    }

    private Result handelScheduleRuleParam(BpoScheduleRule scheduleRuleParam) {
        scheduleRuleParam.setId(null);
        Integer maxWorkHour = scheduleRuleParam.getMaxWorkHour();
        if (maxWorkHour == null) {
            return Result.requestParamError("maxWorkHour is null");
        }
        Integer minWorkHour = scheduleRuleParam.getMinWorkHour();
        if (minWorkHour == null) {
            return Result.requestParamError("minWorkHour is null");
        }
        Integer scheduleNumber = scheduleRuleParam.getScheduleNumber();
        if (scheduleNumber == null) {
            return Result.requestParamError("scheduleNumber is null");
        }
        Integer priorityWorkers = scheduleRuleParam.getPriorityWorkers();
        if (priorityWorkers == null) {
            return Result.requestParamError("priorityWorkers is null");
        }
        String deviationRate = scheduleRuleParam.getDeviationRate();
        if (StringUtils.isEmpty(deviationRate)) {
            return Result.requestParamError("deviationRate is null");
        }
        return null;
    }

    @Override
    public Result<BpoProjectEmployeeShow> getEmployeeData(Integer projectId) {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public Result addClassGenerate(BpoClassesGenerateParam bpoClassesGenerateParam) {
        Result result = handelClassGenerateParam(bpoClassesGenerateParam);
        if (result != null) {
            return result;
        }
        classesGenerateMapper.insertSelective(bpoClassesGenerateParam);
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public Result updateClassFromExternal(BpoUpdateClassesParam bpoClassesParam) {
        Result result = handelUpdateClassFromExternalParam(bpoClassesParam);
        Integer id = bpoClassesParam.getId();
        if (id == null || id < 0) {
            return Result.requestParamError();
        }
        if (result != null) {
            return result;
        }
        classesFromExternalMapper.updateByPrimaryKeySelective(bpoClassesParam);
        return Result.success();
    }

    private Result handelUpdateClassFromExternalParam(BpoUpdateClassesParam bpoClassesParam) {
        String classSn = bpoClassesParam.getClassSn();
        if (StringUtils.isEmpty(classSn)) {
            return Result.requestParamError("classSn is null");
        }
        String className = bpoClassesParam.getClassName();
        if (StringUtils.isEmpty(className)) {
            return Result.requestParamError("className is null");
        }
        Date startDate = bpoClassesParam.getStartDate();
        if (startDate == null) {
            return Result.requestParamError("startDate is null");
        }
        Date endDate = bpoClassesParam.getEndDate();
        if (endDate == null) {
            return Result.requestParamError("endDate is null");
        }
        Date startTime = bpoClassesParam.getStartTime();
        if (startTime == null) {
            return Result.requestParamError("startTime is null");
        }
        Date endTime = bpoClassesParam.getEndTime();
        if (endTime == null) {
            return Result.requestParamError("endTime is null");
        }
        Date arriveTime = bpoClassesParam.getArriveTime();
        if (arriveTime == null) {
            return Result.requestParamError("arriveTime is null");
        }
        Integer peopleNumber = bpoClassesParam.getPeopleNumber();
        if (peopleNumber == null) {
            return Result.requestParamError("peopleNumber is null");
        }
        Integer source = bpoClassesParam.getSource();
        if (source == null) {
            return Result.requestParamError("source is null");
        }
        Integer projectId = bpoClassesParam.getProjectId();
        if (projectId == null) {
            return Result.requestParamError("projectId is null");
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public Result updateClassGenerate(BpoClassesGenerateParam bpoClassesGenerateParam) {
        Result result = handelClassGenerateParam(bpoClassesGenerateParam);
        Integer id = bpoClassesGenerateParam.getId();
        if (id == null || id < 0) {
            return Result.requestParamError();
        }
        if (result != null) {
            return result;
        }
        bpoClassesGenerateParam.setUpdateUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        classesGenerateMapper.insertSelective(bpoClassesGenerateParam);
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public Result updateScheduleRule(BpoScheduleRuleParam scheduleRuleParam) {
        Result result = handelScheduleRuleParam(scheduleRuleParam);
        Integer id = scheduleRuleParam.getId();
        if (id == null || id < 0) {
            return Result.requestParamError();
        }
        if (result != null) {
            return result;
        }
        scheduleRuleMapper.insertSelective(scheduleRuleParam);
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public Result deleteClassFromExternal(Integer classId) {
        if (classId == null || classId < 0) {
            return Result.requestParamError("classId is invalid");
        }
        BpoClassesFromExternal bpoClassesFromExternal = new BpoClassesFromExternal();
        bpoClassesFromExternal.setStatus(DELETE_STATUS);
        bpoClassesFromExternal.setId(classId);
        classesFromExternalMapper.updateByPrimaryKeySelective(bpoClassesFromExternal);
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public Result deleteClassGenerate(Integer scheduleId) {
        if (scheduleId == null || scheduleId < 0) {
            return Result.requestParamError("scheduleId is invalid");
        }
        BpoClassesGenerate bpoClassesGenerate = new BpoClassesGenerate();
        bpoClassesGenerate.setStatus(DELETE_STATUS);
        bpoClassesGenerate.setId(scheduleId);
        classesGenerateMapper.updateByPrimaryKeySelective(bpoClassesGenerate);
        scheduleToEmployeeMapper.deleteStatusByScheduleId(scheduleId);
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public Result deleteScheduleRule(Integer scheduleRuleId) {
        if (scheduleRuleId == null || scheduleRuleId < 0) {
            return Result.requestParamError("scheduleRuleId is invalid");
        }
        BpoScheduleRule bpoScheduleRule = new BpoScheduleRule();
        bpoScheduleRule.setStatus(DELETE_STATUS);
        bpoScheduleRule.setId(scheduleRuleId);
        scheduleRuleMapper.updateByPrimaryKeySelective(bpoScheduleRule);
        return Result.success();
    }

    private Result handelClassGenerateParam(BpoClassesGenerate bpoClassesGenerateParam) {
        bpoClassesGenerateParam.setId(null);
        String classSn = bpoClassesGenerateParam.getClassSn();
        if (StringUtils.isEmpty(classSn)) {
            return Result.requestParamError("classSn is null");
        }
        String className = bpoClassesGenerateParam.getClassName();
        if (StringUtils.isEmpty(className)) {
            return Result.requestParamError("className is null");
        }
        Date startDate = bpoClassesGenerateParam.getStartDate();
        if (startDate == null) {
            return Result.requestParamError("startDate is null");
        }
        Date endDate = bpoClassesGenerateParam.getEndDate();
        if (endDate == null) {
            return Result.requestParamError("endDate is null");
        }
        Date startTime = bpoClassesGenerateParam.getStartTime();
        if (startTime == null) {
            return Result.requestParamError("startTime is null");
        }
        Date endTime = bpoClassesGenerateParam.getEndTime();
        if (endTime == null) {
            return Result.requestParamError("endTime is null");
        }
        Date arriveTime = bpoClassesGenerateParam.getArriveTime();
        if (arriveTime == null) {
            return Result.requestParamError("arriveTime is null");
        }
        Integer peopleNumber = bpoClassesGenerateParam.getPeopleNumber();
        if (peopleNumber == null) {
            return Result.requestParamError("peopleNumber is null");
        }
        Integer projectId = bpoClassesGenerateParam.getProjectId();
        if (projectId == null) {
            return Result.requestParamError("projectId is null");
        }
        Integer source = bpoClassesGenerateParam.getSource();
        if (source == null) {
            return Result.requestParamError("source is null");
        }
        Integer isSpecial = bpoClassesGenerateParam.getIsSpecial();
        if (isSpecial == null) {
            return Result.requestParamError("isSpecial is null");
        }
        String specialAllowance = bpoClassesGenerateParam.getSpecialAllowance();
        if (StringUtils.isEmpty(specialAllowance)) {
            return Result.requestParamError("specialAllowance is null");
        }
        String mealAllowance = bpoClassesGenerateParam.getMealAllowance();
        if (StringUtils.isEmpty(mealAllowance)) {
            return Result.requestParamError("mealAllowance is null");
        }
        String nightAllowance = bpoClassesGenerateParam.getNightAllowance();
        if (StringUtils.isEmpty(nightAllowance)) {
            return Result.requestParamError("nightAllowance is null");
        }
        Integer isAllowance = bpoClassesGenerateParam.getIsAllowance();
        if (isAllowance == null) {
            return Result.requestParamError("isAllowance is null");
        }
        return null;
    }
}
