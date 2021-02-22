package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoContractSettlementInfo;

public interface BpoContractSettlementInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoContractSettlementInfo record);

    int insertSelective(BpoContractSettlementInfo record);

    BpoContractSettlementInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoContractSettlementInfo record);

    int updateByPrimaryKey(BpoContractSettlementInfo record);
}