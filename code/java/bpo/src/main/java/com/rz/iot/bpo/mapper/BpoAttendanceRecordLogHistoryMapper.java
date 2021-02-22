package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoAttendanceRecordLogHistory;

public interface BpoAttendanceRecordLogHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoAttendanceRecordLogHistory record);

    int insertSelective(BpoAttendanceRecordLogHistory record);

    BpoAttendanceRecordLogHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoAttendanceRecordLogHistory record);

    int updateByPrimaryKey(BpoAttendanceRecordLogHistory record);
}