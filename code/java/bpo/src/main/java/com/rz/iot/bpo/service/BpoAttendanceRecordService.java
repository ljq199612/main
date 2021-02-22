package com.rz.iot.bpo.service;

import com.ha.facecamera.configserver.pojo.CaptureCompareData;
import com.rz.iot.bpo.mapper.BpoAttendanceRecordMapper;
import com.rz.iot.bpo.model.BpoAttendanceRecord;

import javax.annotation.Resource;

public interface BpoAttendanceRecordService {

    void insertCaptureCompareData(CaptureCompareData data);

    BpoAttendanceRecord getLatestData(String idCard);

}
