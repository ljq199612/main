package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoPersonContractInfo;
import com.rz.iot.bpo.model.show.BpoPersonContractInfoShow;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Map;

public interface BpoPersonContractInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoPersonContractInfo record);

    int insertSelective(BpoPersonContractInfo record);

    BpoPersonContractInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoPersonContractInfo record);

    int updateByPrimaryKey(BpoPersonContractInfo record);

    BpoPersonContractInfoShow selectDetail(@Param("jiaId") Integer jiaId,@Param("yiId") Integer yiId);

    void updateUrlByContractId(@Param("url") String url,@Param("id") String contractId);

    // 通过urls查询文件名
    List<Map<String, String>> findFileNameByUrls(List<String> splitList);
}