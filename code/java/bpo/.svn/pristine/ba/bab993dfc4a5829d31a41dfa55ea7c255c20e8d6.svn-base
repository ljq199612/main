package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoPersonIdFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BpoPersonIdFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoPersonIdFile record);

    int insertSelective(BpoPersonIdFile record);

    BpoPersonIdFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoPersonIdFile record);

    int updateByPrimaryKey(BpoPersonIdFile record);

    void insertList(@Param("list") List<BpoPersonIdFile> list);

    void updatePerFiles(@Param("list") List<BpoPersonIdFile> list);

    void updateByUrl(String url);
}