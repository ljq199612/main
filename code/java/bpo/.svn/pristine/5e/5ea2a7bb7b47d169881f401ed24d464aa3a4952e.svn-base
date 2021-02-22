package com.rz.iot.bpo.mina.mapper;

import com.rz.iot.bpo.mina.model.MinaClassesGroupRel;
import com.rz.iot.bpo.mina.model.show.MinaClassesGroupRelShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MinaClassesGroupRelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MinaClassesGroupRel id);

    int insertBatch(List<MinaClassesGroupRel> minaClassesGroupRels);

    MinaClassesGroupRel selectByPrimaryKey(Integer id);

    List<MinaClassesGroupRelShow> selectWorkGroupByClassesGenerateId(@Param("classesId") Integer id);

    int updateByPrimaryKey(MinaClassesGroupRel id);
}