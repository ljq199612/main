package com.rz.iot.bpo.service.impl;

import com.ha.facecamera.configserver.pojo.CaptureCompareData;
import com.rz.iot.bpo.mapper.BpoAttendanceRecordMapper;
import com.rz.iot.bpo.model.BpoAttendanceRecord;
import com.rz.iot.bpo.service.BpoAttendanceRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BpoAttendanceRecordServiceImpl implements BpoAttendanceRecordService {

    @Resource
    private BpoAttendanceRecordMapper attendanceRecordMapper;


    @Override
    public void insertCaptureCompareData(CaptureCompareData data) {
        BpoAttendanceRecord attendanceRecord = new BpoAttendanceRecord();
        attendanceRecord.setPersonName(data.getPersonName());
        //TODO 目前取到的是personId
        attendanceRecord.setIdCard(data.getPersonID());
        attendanceRecord.setAttendanceTime(data.getCaptureTime());
        attendanceRecord.setDeviceNo(data.getCameraID());
        attendanceRecord.setDeviceSn(data.getSn());
        attendanceRecordMapper.insertSelective(attendanceRecord);
    }

    @Override
    public BpoAttendanceRecord getLatestData(String idCard) {
        return attendanceRecordMapper.getLatestDataByIdCard(idCard);
    }
}
