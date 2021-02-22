package com.rz.iot.bpo.service;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.BpoBillReconciliationTimeDetailed;
import com.rz.iot.bpo.model.param.bpoBill.BillSelectParam;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillMonthShow;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillReconciliationShow;

import java.util.List;

public interface BpoBillSupplierService {

    /**
     * 查询供应商所有月账单
     * @param entity
     * @param params
     * @return
     */
    List<BpoBillMonthShow> selectAllMonth(BaseEntity entity, BillSelectParam params);
    /**
     * 查询供应商所有日账单
     * @param entity
     * @param param
     * @return
     */
    Result finaAllDay(BaseEntity entity, BillSelectParam param);

    /**
     * 根据供应商日账单表id，获取此账单下的员工明细账单
     * @param id
     * @return
     */
    Result findDetailDays(Integer id);

    BpoBillReconciliationShow getCheckMessage(Integer monthId, Integer type, Integer selectType);

    Result startCheck(BpoBillReconciliationShow bpoBillMonthShow,Integer type);

    Result saveMessage(BpoBillReconciliationTimeDetailed detailed);

}
