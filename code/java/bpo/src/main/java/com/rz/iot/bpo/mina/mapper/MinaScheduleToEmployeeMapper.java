package com.rz.iot.bpo.mina.mapper;

import com.rz.iot.bpo.mina.model.MinaClassesGroupRel;
import com.rz.iot.bpo.mina.model.MinaClassesUserRel;
import com.rz.iot.bpo.mina.model.MinaPerson;
import com.rz.iot.bpo.mina.model.MinaScheduleToEmployee;
import com.rz.iot.bpo.mina.model.show.MinaClassGenerateShow;
import com.rz.iot.bpo.mina.model.show.MinaScheduleToEmployeeShow;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MinaScheduleToEmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MinaScheduleToEmployee record);

    int insertSelective(MinaScheduleToEmployee record);

    MinaScheduleToEmployee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MinaScheduleToEmployee record);

    int updateByPrimaryKey(MinaScheduleToEmployee record);

    int deleteStatusByScheduleId(Integer scheduleId);

    int ScheduleEmployeeNum(@Param("classGenerateId")Integer classGenerateId);

    List<MinaScheduleToEmployeeShow> findAll();

    List<MinaScheduleToEmployeeShow> selectByProjectIds(List<Integer> pIds);


/*Person*/
    int updatePersonByPrimaryKey(MinaPerson person);

    int updatePersonByPrimaryKeyList(List<MinaPerson> persons);

    List<MinaPerson> selectPersonByGroupAndDateInterval(Integer groupId, Date startDate, Date endDate);

    List<MinaPerson> selectPersonByGroupAndDate(Integer groupId, Date date);

    List<MinaPerson> selectPersonByProjectId(Integer projectId);

    List<MinaPerson> selectPersonByProjectIdAndDateInterval(Integer projectId, Date startDate, Date endDate);

    List<MinaPerson> selectPersonByProjectIdAndDate(Integer projectId, Date someDay);

    int deleteScheduledPerson(@Param("id") Integer classesUserRelId);

    int insertClassesUserRel(MinaClassesUserRel minaClassesUserRel);

    int insertClassesUserRelBatch(List<MinaClassesUserRel> minaClassesUserRels);



}