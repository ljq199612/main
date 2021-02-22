package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.SysCompanyFile;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Map;

public interface SysCompanyFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysCompanyFile record);

    int insertSelective(SysCompanyFile record);

    SysCompanyFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysCompanyFile record);

    int updateByPrimaryKey(SysCompanyFile record);

    void insertFileList(@Param("list") List<SysCompanyFile> list);

    void updateStatusByUrl(@Param("status") int deleteStatus,@Param("fileUrl") String url);

    // 更新公司营业执照
    void updateRel(@Param("list") List<SysCompanyFile> list);

    List<Map<String, String>> getUrlByCompanyId(Integer companyId);
}