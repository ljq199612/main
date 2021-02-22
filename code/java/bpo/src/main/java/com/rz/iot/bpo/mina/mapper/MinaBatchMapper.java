package com.rz.iot.bpo.mina.mapper;

import com.rz.iot.bpo.mina.model.MinaBatch;
import com.rz.iot.bpo.model.BpoBatch;

/**
 * @Description: 批次
 * @Author: Liu Jiaqi
 * @Create: 2020-07-23
 */
public interface MinaBatchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MinaBatch record);

    int insertSelective(MinaBatch record);

    MinaBatch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MinaBatch record);

    int updateByPrimaryKey(MinaBatch record);
}