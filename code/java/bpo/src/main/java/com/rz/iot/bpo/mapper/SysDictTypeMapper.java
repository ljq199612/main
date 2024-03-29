package com.rz.iot.bpo.mapper;


import com.rz.iot.bpo.model.SysDictData;
import com.rz.iot.bpo.model.SysDictType;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface SysDictTypeMapper {
    int deleteByPrimaryKey(Long dictId);

    int insert(SysDictType record);

    int insertSelective(SysDictType record);

    SysDictType selectByPrimaryKey(Long dictId);

    int updateByPrimaryKeySelective(SysDictType record);

    int updateByPrimaryKey(SysDictType record);

    List<SysDictType> selectBySysDictType(@Param("sysDictType") SysDictType sysDictType);

    List<SysDictType> findAllNoPage();

    SysDictType isExsitType(@Param("id") Long id,@Param("dictType") String dictType);

    List<SysDictData> findDataByType(String type);

    void updateStatus(@Param("dictId") Long dictId,@Param("status") Byte status);
}