package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoOutputDetail;
import com.rz.iot.bpo.model.show.bpoOutput.BpoOutputDetailShow;

import java.util.List;

public interface BpoOutputDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoOutputDetail record);

    int insertSelective(BpoOutputDetail record);

    BpoOutputDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoOutputDetail record);

    int updateByPrimaryKey(BpoOutputDetail record);

    List<BpoOutputDetailShow> selectDetailByOutputId(Integer id);
}