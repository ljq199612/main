package com.rz.iot.bpo.mina.service.impl;

import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.common.utils.SecurityUtils;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mina.mapper.*;
import com.rz.iot.bpo.mina.model.*;
import com.rz.iot.bpo.mina.model.param.*;
import com.rz.iot.bpo.mina.model.show.*;
import com.rz.iot.bpo.mina.service.MinaScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static com.rz.iot.bpo.common.constant.DictDataConstants.*;

@Slf4j
@Service("minaScheduleService")
public class MinaScheduleServiceImpl implements MinaScheduleService {

    @Resource
    MinaScheduleRuleMapper minaScheduleRuleMapper;

    @Resource
    MinaClassesFromExternalMapper minaClassesFromExternalMapper;

    @Resource
    MinaClassesGenerateMapper minaClassesGenerateMapper;

    @Resource
    MinaClassesGroupRelMapper minaClassesGroupRelMapper;

    @Resource
    MinaScheduleToEmployeeMapper minaScheduleToEmployeeMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result addClassFromExternal(MinaClassesParam minaClassesParam) {

        MinaClassesFromExternal minaClassesFromExternal = minaClassesParam.getMinaClasses();
        minaClassesFromExternalMapper.insert(minaClassesFromExternal);
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result getClassFromExternal(MinaClassesParam minaClassesParam) {
        Integer id = minaClassesParam.getClassId();
        MinaClassesShow minaClassesShow = minaClassesFromExternalMapper.selectByPrimaryKey(id);
        Result result = new Result(ResultConstants.REQUEST_SUCCESS);
        result.setData(minaClassesShow);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result getClassFromExternalList(MinaClassesParam minaClassesParam) {
        String transferStationName = minaClassesParam.getTransferStationName();
        Integer projectId = minaClassesParam.getProjectId();
        List<MinaClassesShow> classList = null;
        if (StringUtils.isEmpty(transferStationName)) {
            classList = minaClassesFromExternalMapper.findAll();
        } else {
            List<Integer> projectIds = minaClassesFromExternalMapper.selectProjectIdByTransferStationName(transferStationName);
            if (projectIds.size() == 0) {
                return new Result(ResultConstants.REQUEST_SUCCESS);
            } else {
                classList = minaClassesFromExternalMapper.selectByProjectIds(projectIds);
            }
        }
        Result result = new Result(ResultConstants.REQUEST_SUCCESS);
        result.setData(classList);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result updateClassFromExternal(MinaUpdateClassesParam bpoClassesParam) {
        minaClassesFromExternalMapper.updateByPrimaryKey(bpoClassesParam);
        return Result.success();
    }

    //TODO
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result addScheduleRule(MinaScheduleRuleParam scheduleRuleParam) {
        minaScheduleRuleMapper.insertSelective(scheduleRuleParam);
        MinaScheduleRule scheduleRule = new MinaScheduleRule();
        scheduleRule.setId(scheduleRuleParam.getId());
        return Result.success(scheduleRule);
    }

    @Override
    public Result getClassGenerateList(MinaClassesGenerateParam param) {
        String transferStationName = param.getTransferStationName();
        MinaClassGenerateShow show = new MinaClassGenerateShow();
        List<MinaClassesGeneratePlus> plus = null;

        if (StringUtils.isEmpty(transferStationName)) {
            plus = minaClassesGenerateMapper.findAll();
        } else {
            List<Integer> projectIds = minaClassesFromExternalMapper.selectProjectIdByTransferStationName(transferStationName);
            if (projectIds.size() == 0) {
                return new Result(ResultConstants.REQUEST_SUCCESS);
            } else {
                plus = minaClassesGenerateMapper.selectByProjectIds(projectIds);
            }
        }

        List<Integer> ids = new ArrayList<>();
        for (MinaClassesGeneratePlus list: plus) {
            ids.add(list.getId());
        }
        List<MinaClassesGroupRelShow> groupList = minaClassesGenerateMapper.selectWorkGroupByClassIds(ids);

        // 添加 工作小组 id 和 名称
        for (MinaClassesGeneratePlus list: plus) {

            list.setWorkGroupIdAndNameMap(new HashMap<>());

            int classId = list.getId();
            int classGenerateId;
            Integer workgroupId;
            String workGroupName;
            for (MinaClassesGroupRelShow group: groupList) {
                classGenerateId = group.getClassGenerateId();
                workgroupId = group.getWorkGroupId();
                workGroupName = group.getWorkGroupName();
                if(classGenerateId==classId && workgroupId != null){
                    list.getWorkGroupIdAndNameMap().put(workgroupId, workGroupName);
                };
            }
        }

        Result result = new Result<>(ResultConstants.REQUEST_SUCCESS);

        show.setMinaClassesGeneratePluses(plus);
        result.setData(show);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result getClassGenerateDetail(MinaClassesGenerateParam param) {
        MinaClassesGeneratePlus minaClassesGeneratePlus = param.getMinaClassesGeneratePlus();

        Map<Integer, String> workgroupMap = param.getWorkGroupIdAndNameMap();

        MinaClassGenerateShow show =  new MinaClassGenerateShow();
        show.setMinaClassesGeneratePlus(minaClassesGeneratePlus);
        show.setWorkGroupIdAndNameMap(workgroupMap);

        Result result = new Result<>(ResultConstants.REQUEST_SUCCESS);
        result.setData(show);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result addClassGenerate(MinaClassesGenerateParam param) {

        MinaClassesGeneratePlus minaClassesGeneratePlus = param.getMinaClassesGeneratePlus();

        List<MinaClassesGroupRel> minaClassesGroupRels = new ArrayList<>();

        List<Integer> groupIds = param.getWorkGroupIds();

        if (groupIds != null && groupIds.size() != 0) {
            minaClassesGeneratePlus.setIsClassGroupRel(NORMAL_STATUS);
            minaClassesGenerateMapper.insert(minaClassesGeneratePlus);
            for (Integer id : groupIds) {
                MinaClassesGroupRel classesGroupRel = new MinaClassesGroupRel();
                classesGroupRel.setWorkGroupId(id);
                classesGroupRel.setClassGenerateId(param.getId());
                classesGroupRel.setClassSn(param.getClassSn());
                classesGroupRel.setProjectId(param.getProjectId());
                minaClassesGroupRels.add(classesGroupRel);
                classesGroupRel.setStatus(NORMAL_STATUS);
            }
            minaClassesGroupRelMapper.insertBatch(minaClassesGroupRels);
            param.setIsClassGroupRel(NORMAL_STATUS);
        } else {
            param.setIsClassGroupRel(NOT_VERIFY_STATUS);
            minaClassesGenerateMapper.insert(minaClassesGeneratePlus);
        }

        return Result.success();
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result addClassGenerateList(MinaClassesParam classesParam) {

        List<MinaClassesGenerate> classesGenerates = new ArrayList<>();
        for (MinaClassesFromExternal classesFromExternal : classesParam.getMinaClassesList()) {

            MinaClassesGenerate minaClassesGenerate = new MinaClassesGenerate();
            minaClassesGenerate.setClassName(classesFromExternal.getClassName());
            minaClassesGenerate.setClassSn(classesFromExternal.getClassSn());
            minaClassesGenerate.setBatchId(classesFromExternal.getBatchId());
            minaClassesGenerate.setClassType(classesFromExternal.getClassType());
            minaClassesGenerate.setStartDate(classesFromExternal.getStartDate());
            minaClassesGenerate.setEndDate(classesFromExternal.getEndDate());
            minaClassesGenerate.setStartTime(classesFromExternal.getStartTime());
            minaClassesGenerate.setEndTime(classesFromExternal.getEndTime());
            minaClassesGenerate.setArriveTime(classesFromExternal.getArriveTime());
            minaClassesGenerate.setContents(classesFromExternal.getContents());
            minaClassesGenerate.setPeopleNumber(classesFromExternal.getPeopleNumber());
            minaClassesGenerate.setProjectId(classesFromExternal.getProjectId());
            minaClassesGenerate.setExcludeHour(classesFromExternal.getExcludeHour());
            minaClassesGenerate.setSource(classesFromExternal.getSource());
            minaClassesGenerate.setStatus(classesFromExternal.getStatus());
            minaClassesGenerate.setScheduleStatus(classesFromExternal.getScheduleStatus());

            classesGenerates.add(minaClassesGenerate);
        }
        minaClassesGenerateMapper.insertBatch(classesGenerates);
        return Result.success();
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result updateClassGenerate(MinaClassesGenerateParam param) {

        MinaClassesGenerate classesGenerate = param.getMinaClassesGenerate();

        classesGenerate.setUpdateUserId(SecurityUtils.getLoginUser().getUser().getUserId());

        minaClassesGenerateMapper.updateByPrimaryKey(classesGenerate);
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result deleteClassFromExternal(MinaClassesParam minaClassesParam) {
        MinaClassesFromExternal minaClassesFromExternal = new MinaClassesFromExternal();
        minaClassesFromExternal.setStatus(DELETE_STATUS);
        minaClassesFromExternal.setId(minaClassesParam.getClassId());
        minaClassesFromExternalMapper.updateByPrimaryKey(minaClassesFromExternal);
        return Result.success();
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result getProjectNamesByTransferStationName(MinaClassesParam minaClassesParam) {
        String transferStationName = minaClassesParam.getTransferStationName();
        List<String> projectNames = minaClassesFromExternalMapper.selectProjectNameByTransferStationName(transferStationName);
        Result result = new Result<>(ResultConstants.REQUEST_SUCCESS);
        result.setData(projectNames);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result deleteClassGenerate(Integer scheduleId) {
        if (scheduleId == null || scheduleId < 0) {
            return Result.requestParamError("scheduleId is invalid");
        }
        MinaClassesGenerate classesGenerate = new MinaClassesGenerate();
        classesGenerate.setStatus(DELETE_STATUS);
        classesGenerate.setId(scheduleId);
        minaClassesGenerateMapper.updateByPrimaryKey(classesGenerate);
        minaScheduleToEmployeeMapper.deleteStatusByScheduleId(scheduleId);
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result deleteScheduleRule(Integer scheduleRuleId) {
        if (scheduleRuleId == null || scheduleRuleId < 0) {
            return Result.requestParamError("scheduleRuleId is invalid");
        }
        MinaScheduleRule minaScheduleRule = new MinaScheduleRule();
        minaScheduleRule.setStatus(DELETE_STATUS);
        minaScheduleRule.setId(scheduleRuleId);
        minaScheduleRuleMapper.updateByPrimaryKeySelective(minaScheduleRule);
        return Result.success();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result getScheduleEmployeeDetail(MinaScheduleToEmployeeParam param) {

        Integer classId = param.getId();
        MinaClassesGeneratePlus classesGenerate = minaClassesGenerateMapper.selectByPrimaryKey(classId);

        Integer projectId = classesGenerate.getProjectId();
        Date startDate = classesGenerate.getStartDate();
        Date endDate = classesGenerate.getEndDate();

        Map<Integer,String> workGroupIdAndName = param.getWorkGroupIdAndNameMap();

        Set<Integer> groupIds = new HashSet<>();
        boolean hasGroup = workGroupIdAndName != null && workGroupIdAndName.size() != 0;
        if(hasGroup){
            groupIds =  workGroupIdAndName.keySet();
        }

        List<MinaPerson> personList = new ArrayList<>();
        if (hasGroup) {
            for (Integer id : groupIds) {
                personList.addAll(
                        minaScheduleToEmployeeMapper.selectPersonByGroupAndDateInterval(id, startDate, endDate)
                );
            }
        } else {
            personList.addAll(
                    minaScheduleToEmployeeMapper.selectPersonByProjectIdAndDateInterval(projectId, startDate, endDate)
            );
        }

        MinaScheduleToEmployeeShow show = new MinaScheduleToEmployeeShow();
        show.setMinaPersonList(personList);
        show.setClassesGeneratePlus(classesGenerate);

        Result result = new Result<>(ResultConstants.REQUEST_SUCCESS);
        result.setData(show);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result getScheduleEmployeeList(MinaScheduleToEmployeeParam param) {

        String transferStationName = param.getTransferStationName();
        MinaScheduleToEmployeeShow show = new MinaScheduleToEmployeeShow();

        List<MinaClassesGeneratePlus> classGenerateList = null;

        if (StringUtils.isEmpty(transferStationName)) {
            classGenerateList = minaClassesGenerateMapper.findAll();
        } else {
            List<Integer> projectIds = minaClassesFromExternalMapper.selectProjectIdByTransferStationName(transferStationName);
            if (projectIds.size() == 0) {
                return new Result(ResultConstants.REQUEST_SUCCESS);
            } else {
                classGenerateList = minaClassesGenerateMapper.selectByProjectIds(projectIds);
            }
        }

        List<Integer> ids = new ArrayList<>();
        Date today = param.getToday();
        //Date endDate;
        Integer projectId;
        List<MinaPerson> personList;
        Integer secheduledPersonNum = 0;  // 今天已经排班的人数

        for (MinaClassesGeneratePlus list: classGenerateList) {
            ids.add(list.getId());

            // 今天已排班的人数计算
            //endDate = list.getEndDate();
            projectId = list.getProjectId();
            personList =
                    minaScheduleToEmployeeMapper.selectPersonByProjectIdAndDate(projectId,today);
            secheduledPersonNum = personList.size();
            list.setScheduledPersonNum(secheduledPersonNum);
        }

        List<MinaClassesGroupRelShow> groupList = minaClassesGenerateMapper.selectWorkGroupByClassIds(ids);

        // 添加 工作小组 id 和 名称
        for (MinaClassesGeneratePlus list: classGenerateList) {

            list.setWorkGroupIdAndNameMap(new HashMap<>());

            int classId = list.getId();
            int classGenerateId;
            Integer workgroupId;
            String workGroupName;
            for (MinaClassesGroupRelShow group: groupList) {
                classGenerateId = group.getClassGenerateId();
                workgroupId = group.getWorkGroupId();
                workGroupName = group.getWorkGroupName();
                if(classGenerateId==classId && workgroupId != null){
                    list.getWorkGroupIdAndNameMap().put(workgroupId, workGroupName);
                };
            }
        }

        Result result = new Result<>(ResultConstants.REQUEST_SUCCESS);

        show.setMinaClassesGeneratePluses(classGenerateList);
        result.setData(show);
        return result;
    }


    // 可安排的人员列表
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result getEmployeeList(MinaScheduleToEmployeeParam param) {

        MinaClassesGeneratePlus minaClassesGeneratePlus = param.getMinaClassesGeneratePlus();

        String workGroupName = param.getWorkGroupName();

        // 项目 ID
        Integer projectId = param.getProjectId();
        //TODO @ljq 暂定根据 projectId 筛选
        List<MinaPerson> persons = minaScheduleToEmployeeMapper.selectPersonByProjectId(projectId);

        MinaScheduleToEmployeeShow show = new MinaScheduleToEmployeeShow();
        show.setClassesGeneratePlus(minaClassesGeneratePlus);
        show.setMinaPersonList(persons);
        show.setWorkGroupName(workGroupName);

        Result result = new Result<>(ResultConstants.REQUEST_SUCCESS);
        result.setData(show);
        return result;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result addEmployees(MinaScheduleToEmployeeParam param) {

        MinaClassesGeneratePlus minaClassesGeneratePlus = param.getMinaClassesGeneratePlus();

        // 工作小组 id
        Integer workGroupId = param.getWorkGroupId();

        // 人员列表
        List<Integer> personIds = param.getPersonIdList();

        // 排班的日期区间
        Date startDate = param.getStartDate();
        Date endDate = param.getEndDate();

        // 要排班的具体某一天, 如果不指定就用排班区间的日期安排人员
        Date someDay = param.getSomeDay();

        // 排班的天数
        long scheduleDays = 1;
        if(someDay !=null){
            scheduleDays = daysBetween(startDate, endDate) + 1;
        }

        List<MinaClassesUserRel> userRelList = new ArrayList<>();

        for (Integer id : personIds) {
            for (int i = 0; i < scheduleDays; i++) {
                MinaClassesUserRel userRel = new MinaClassesUserRel();
                userRel.setPersonId(id);
                if (workGroupId != null) {
                    userRel.setClassGroupId(workGroupId);
                }
                userRel.setProjectId(minaClassesGeneratePlus.getProjectId());
                userRel.setClassGenerateId(minaClassesGeneratePlus.getId());
                if(someDay != null){
                    userRel.setCycle(nextSomeDay(someDay, i));
                }else{
                    userRel.setCycle(nextSomeDay(startDate, i));
                }
                userRelList.add(userRel);
            }
        }

        minaScheduleToEmployeeMapper.insertClassesUserRelBatch(userRelList);

        Result result = new Result<>(ResultConstants.REQUEST_SUCCESS);
        return result;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result deleteEmployee(Integer classUserRelId) {
        minaScheduleToEmployeeMapper.deleteScheduledPerson(classUserRelId);
        return Result.success();
    }

    /*[Start]*/
    /*日期计算和转换相关*/
    private long daysBetween(Date startDay, Date endDay) {
        long difference = (startDay.getTime() - endDay.getTime()) / 86400000; //24*3600*1000
        return Math.abs(difference);
    }

    private LocalDate dateToLocalDate(java.util.Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalDate();
    }

    private Date localDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    private Date nextSomeDay(Date date, int days) {
        LocalDate currentDay = dateToLocalDate(date);
        LocalDate nextDay = currentDay.plusDays(days);
        return localDateToDate(nextDay);
    }
    /*[END]*/

}
