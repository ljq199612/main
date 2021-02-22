package com.rz.iot.bpo.mapper;

import com.rz.iot.bpo.model.BpoBillReconciliation;

public interface BpoBillReconciliationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BpoBillReconciliation record);

    int insertSelective(BpoBillReconciliation record);

    BpoBillReconciliation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BpoBillReconciliation record);

    int updateByPrimaryKey(BpoBillReconciliation record);

    BpoBillReconciliation selectByMonthBillKey(Integer monthId);
}