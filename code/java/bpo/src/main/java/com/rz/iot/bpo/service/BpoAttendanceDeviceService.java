package com.rz.iot.bpo.service;

import com.ha.facecamera.configserver.pojo.AppConfig;
import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.BpoAttendanceDevice;
import com.rz.iot.bpo.model.SysDictData;

import java.util.List;

public interface BpoAttendanceDeviceService {

    Result<BpoAttendanceDevice> getDetail(Integer id);

//    Result addEquipment(Integer sn);

    Result updateEquipment(BpoAttendanceDevice equipment);

    Result deleteEquipment(Integer id);

    Result<List<BpoAttendanceDevice>> getFuzzyDetails(BaseEntity param, BpoAttendanceDevice device, Page<BpoAttendanceDevice> page);

    Result<String> getOfflineInformation(Integer id);

    Result<List<SysDictData>> getGateType();

    Result<List<SysDictData>> getUpLoad();

    Result<List<BpoAttendanceDevice>> getAfterUpdate(List<String> deviceSn);

}
