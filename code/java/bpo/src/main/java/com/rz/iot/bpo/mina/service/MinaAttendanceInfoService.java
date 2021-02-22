package com.rz.iot.bpo.mina.service;

import com.rz.iot.bpo.mina.model.show.MinaAttendanceDetailShow;
import com.rz.iot.bpo.mina.model.show.MinaAttendanceInfoShow;
import com.rz.iot.bpo.model.param.BpoAttendanceInfoParam;
import com.rz.iot.bpo.model.show.BpoAttendanceInfoShow;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MinaAttendanceInfoService {

    List<MinaAttendanceInfoShow> findAll(Map<String,Object> params);

    MinaAttendanceDetailShow getDetail(Integer id);

    List<BpoAttendanceInfoShow> getListByMonth(Date startTime, Date endTime);

    void complain(Map<String,Object> params);

    List<BpoAttendanceInfoShow> abnormalList();

    void confirm(BpoAttendanceInfoParam attendanceInfoParam);
}
