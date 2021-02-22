package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoAttendanceAuditRecord;

import java.util.List;
import java.util.Map;

public interface BpoAttendanceAuditRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoAttendanceAuditRecord record);

    int insertSelective(BpoAttendanceAuditRecord record);

    BpoAttendanceAuditRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoAttendanceAuditRecord record);

    int updateByPrimaryKey(BpoAttendanceAuditRecord record);

    List<Map<String,Object>> auditDetail(Integer attendanceInfoId);

    List<BpoAttendanceAuditRecord> mpAuditDetail(Integer attendanceInfoId);
}