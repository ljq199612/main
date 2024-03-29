package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoProductPrice;

public interface BpoProductPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoProductPrice record);

    int insertSelective(BpoProductPrice record);

    BpoProductPrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoProductPrice record);

    int updateByPrimaryKey(BpoProductPrice record);

    BpoProductPrice selectByProductId(Integer productId);
}