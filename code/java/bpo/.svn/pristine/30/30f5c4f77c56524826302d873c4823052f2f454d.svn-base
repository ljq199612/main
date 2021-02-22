package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoWorkGroup;
import com.rz.iot.bpo.model.show.personClass.BpoGroupShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BpoWorkGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoWorkGroup record);

    int insertSelective(BpoWorkGroup record);

    BpoWorkGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoWorkGroup record);

    int updateByPrimaryKey(BpoWorkGroup record);

    List<BpoWorkGroup> findByModelId(Integer id);
}