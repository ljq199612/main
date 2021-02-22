package com.rz.iot.bpo.service;

import com.rz.iot.bpo.model.BpoAttendanceAuditRecord;
import com.rz.iot.bpo.model.BpoAttendanceInfo;
import com.rz.iot.bpo.model.BpoContractInfo;
import com.rz.iot.bpo.model.param.BpoAttendanceInfoParam;
import com.rz.iot.bpo.model.show.BpoAttendanceInfoShow;

import java.util.List;
import java.util.Map;

public interface BpoAttendanceInfoService {

    List<BpoAttendanceInfoShow> findAll(Map<String,Object> params);

    void confirm(BpoAttendanceInfoParam attendanceInfoParam);

    void oneKeyConfirm(Integer[] ids,Integer type);

    List<Map<String,Object>> clockDetail(Integer attendanceInfoId);

    List<Map<String,Object>> auditDetail(Integer attendanceInfoId);

}
