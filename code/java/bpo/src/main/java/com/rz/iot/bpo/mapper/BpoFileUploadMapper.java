package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoFileUpload;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BpoFileUploadMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoFileUpload record);

    int insertSelective(BpoFileUpload record);

    BpoFileUpload selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoFileUpload record);

    int updateByPrimaryKey(BpoFileUpload record);

    BpoFileUpload selectByUrl(String url);

    void insertFileUploads(@Param("list") List<BpoFileUpload> list);

    void deleteStatusByUrl(String deleteUrl);

    List<BpoFileUpload> selectListByFilePath(@Param("files") String[] files);
}