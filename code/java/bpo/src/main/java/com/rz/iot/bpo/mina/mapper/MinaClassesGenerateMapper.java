package com.rz.iot.bpo.mina.mapper;

import com.rz.iot.bpo.mina.model.*;
import com.rz.iot.bpo.mina.model.show.MinaClassGenerateShow;
import com.rz.iot.bpo.mina.model.show.MinaClassesGroupRelShow;
import com.rz.iot.bpo.mina.model.show.MinaWorkGroupShow;

import java.util.List;
import java.util.Map;

public interface MinaClassesGenerateMapper {

    int insert(MinaClassesGenerate record);

    int insertBatch(List<MinaClassesGenerate> items);

    int updateByPrimaryKey(MinaClassesGenerate record);

    MinaClassesGeneratePlus selectByPrimaryKey(Integer id);

    List<MinaClassesGeneratePlus> findAll();

    List<MinaClassesGroupRel> selectWorkGroupByClassIds(List<Integer> classIds);

    List<MinaClassesGeneratePlus> selectByProjectIds(List<Integer> pIds);

}