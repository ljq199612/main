package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoAttendanceRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BpoAttendanceRecordMapper {
    List<Map<String,Object>> clockDetail(@Param("idCard") String idCard,@Param("startTime") Date startTime,@Param("endTime") Date endTime);

    int deleteByPrimaryKey(Integer id);

    int insert(BpoAttendanceRecord record);

    int insertSelective(BpoAttendanceRecord record);

    BpoAttendanceRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoAttendanceRecord record);

    int updateByPrimaryKey(BpoAttendanceRecord record);

    BpoAttendanceRecord getLatestDataByIdCard(String idCard);

    BpoAttendanceRecord selectBetweenInfo(@Param("idCard") String idCard, @Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("isAsc") Boolean isAsc);

}