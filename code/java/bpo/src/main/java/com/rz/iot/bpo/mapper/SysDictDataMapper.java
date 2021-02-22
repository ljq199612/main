package com.rz.iot.bpo.mapper;


import com.rz.iot.bpo.model.SysDictData;
import com.rz.iot.bpo.model.SysDictType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDictDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDictData record);

    int insertSelective(SysDictData record);

    SysDictData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDictData record);

    int updateByPrimaryKey(SysDictData record);

    List<SysDictData> selectBySysDictData(@Param("sysDictData") SysDictData sysDictData);

    List<SysDictData> findAllByType(String type);

    SysDictData isExsitType(SysDictData sysDictData);

    List<SysDictData> findCustomType();

    String getValueByKey(String key);
}