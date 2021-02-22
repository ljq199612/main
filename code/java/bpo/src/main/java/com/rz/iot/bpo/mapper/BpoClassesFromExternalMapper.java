package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoClassesFromExternal;
import com.rz.iot.bpo.model.show.BpoClassesShow;
import org.apache.ibatis.annotations.Param;

public interface BpoClassesFromExternalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoClassesFromExternal record);

    int insertSelective(BpoClassesFromExternal record);

    BpoClassesFromExternal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoClassesFromExternal record);

    int updateByPrimaryKey(BpoClassesFromExternal record);

    BpoClassesShow selectByProjectId(@Param("projectId")Integer projectId, @Param("transferStationId")Integer transferStationId);
}