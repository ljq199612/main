package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.model.BpoClassesGenerate;
import com.rz.iot.bpo.model.show.BpoClassGenerateShow;
import com.rz.iot.bpo.model.show.personClass.BpoGroupShow;
import com.rz.iot.bpo.model.show.personClass.BpoPersonArrangeShow;
import com.rz.iot.bpo.model.show.personClass.BpoPersonShow;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import com.rz.iot.bpo.model.show.BpoClassGenerateListShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BpoClassesGenerateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoClassesGenerate record);

    int insertSelective(BpoClassesGenerate record);

    BpoClassesGenerate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoClassesGenerate record);

    int updateByPrimaryKey(BpoClassesGenerate record);

    BpoClassGenerateShow selectByProjectId(Integer projectId);

    List<BpoClassGenerateListShow> getClassGenerateList(@Param("info")BpoClassGenerateListShow info);

    List<BpoPersonArrangeShow> findAll(@Param("base") BaseEntity baseEntity,@Param("stationId") Integer stationId,@Param("projectId") Integer projectId,@Param("userId") Integer userId);

    List<BpoGroupShow> getGroupsByClassId(Integer classId);

    List<BpoPersonShow> getPersonNoGroup(Integer classId);

    List<BpoPersonShow> getPersonNoClass();

    // 新增排班人员
    void insertClassPerson(@Param("classId") Integer classId, Date cycle, @Param("param") List<BpoGroupShow> bpoGroupShows);

    // 删除对应排班人员
    void updateStatusByPersonId(@Param("list") List<BpoPersonShow> unPerson);
}