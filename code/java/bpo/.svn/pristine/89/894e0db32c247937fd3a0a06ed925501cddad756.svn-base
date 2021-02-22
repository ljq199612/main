package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.model.BpoAttendanceDevice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BpoAttendanceDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoAttendanceDevice record);

    int insertSelective(BpoAttendanceDevice record);

    BpoAttendanceDevice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoAttendanceDevice record);

    int updateByPrimaryKey(BpoAttendanceDevice record);

    //通过场地、设备名称、状态去获取设备信息
    List<BpoAttendanceDevice> getFuzzyDetails(BaseEntity param, BpoAttendanceDevice device);

    //通过设备id获取它的离线信息
    BpoAttendanceDevice getOfflineInformation(Integer id);

    //更新刷新的信息
    int updatefresh(BpoAttendanceDevice device);

    //通过sn获取设备信息
    BpoAttendanceDevice getDetailBySn(String sn);
	
	List<String> getTransferStationDevSns(String sn);

	//用s设备序列号n更新离线时间
    int updateDisonConnect(BpoAttendanceDevice device);

    //在刷新，时间同步后返回刷新后的数据
    List<BpoAttendanceDevice> getAfterUpdate(@Param ("deviceSn") List<String> deviceSn);
}