package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoContractPaymentInfo;

import java.util.List;

public interface BpoContractPaymentInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoContractPaymentInfo record);

    int insertSelective(BpoContractPaymentInfo record);

    BpoContractPaymentInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoContractPaymentInfo record);

    int updateByPrimaryKey(BpoContractPaymentInfo record);

    List<BpoContractPaymentInfo> selectByContractId(Integer id);
}