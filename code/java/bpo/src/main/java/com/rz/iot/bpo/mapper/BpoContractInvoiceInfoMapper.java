package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoContractInvoiceInfo;

public interface BpoContractInvoiceInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoContractInvoiceInfo record);

    int insertSelective(BpoContractInvoiceInfo record);

    BpoContractInvoiceInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoContractInvoiceInfo record);

    int updateByPrimaryKey(BpoContractInvoiceInfo record);
}