package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoBillPersonMonthDetail;
import org.apache.ibatis.annotations.Param;

public interface BpoBillPersonMonthDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoBillPersonMonthDetail record);

    int insertSelective(BpoBillPersonMonthDetail record);

    BpoBillPersonMonthDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoBillPersonMonthDetail record);

    int updateByPrimaryKey(BpoBillPersonMonthDetail record);

    BpoBillPersonMonthDetail getMonthDetailByMonthId(@Param("monthId") Integer monthId);
}