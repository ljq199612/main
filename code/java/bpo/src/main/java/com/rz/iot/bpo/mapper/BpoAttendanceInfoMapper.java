package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.mina.model.show.MinaAttendanceDetailShow;
import com.rz.iot.bpo.mina.model.show.MinaAttendanceInfoShow;
import com.rz.iot.bpo.model.BpoAttendanceInfo;
import com.rz.iot.bpo.model.show.BpoAttendanceInfoShow;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BpoAttendanceInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoAttendanceInfo record);

    int insertSelective(BpoAttendanceInfo record);

    BpoAttendanceInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoAttendanceInfo record);

    int updateByPrimaryKey(BpoAttendanceInfo record);

    List<BpoAttendanceInfoShow> getAll(Map<String,Object> params);

    /** 根据日期获取当天排班考勤信息 */
    List<BpoAttendanceInfo> selectByDate(@Param("date") String date);

    /** 获取已确认的考勤信息 用于财务模块生成账单明细*/
    List<BpoAttendanceInfo> selectConfirmedByDate(@Param("date") String date);

    /** 获取人员下一场排班考勤信息 */
    BpoAttendanceInfo selectNextInfo(@Param("personId") Integer personId, @Param("lastEndTime") Date lastEndTime);




    List<MinaAttendanceInfoShow> mpGetAll(Map<String,Object> params);

    MinaAttendanceDetailShow mpGetDetail(Integer id);

    List<BpoAttendanceInfoShow> mpGetListByMonth(@Param("userId") Integer userId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<BpoAttendanceInfoShow> abnormalList(@Param("userId") Integer userId);

    BpoAttendanceInfo selectByIdAndUserId(@Param("id") Integer id,@Param("userId") Integer userId);


}