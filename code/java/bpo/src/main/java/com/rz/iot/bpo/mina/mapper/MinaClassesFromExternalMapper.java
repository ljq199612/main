package com.rz.iot.bpo.mina.mapper;

import com.rz.iot.bpo.mina.model.MinaClassesFromExternal;
import com.rz.iot.bpo.mina.model.show.MinaClassesShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 班次相关 DAO 接口
 * @Author: Liu Jiuqi
 * @Create: 2020-07-28
 */
public interface MinaClassesFromExternalMapper {

    int insert(MinaClassesFromExternal record);

    int updateByPrimaryKey(MinaClassesFromExternal record);

    MinaClassesShow selectByProjectId(Integer projectId);

    MinaClassesShow selectByPrimaryKey(Integer id);

    List<MinaClassesShow> selectByProjectIds(List<Integer> pIds);

    List<MinaClassesShow> findAll();

    List<Integer> selectProjectIdByTransferStationName(@Param("transferName")String transferStationName);

    List<String> selectProjectNameByTransferStationName(@Param("transferName")String transferStationName);

}