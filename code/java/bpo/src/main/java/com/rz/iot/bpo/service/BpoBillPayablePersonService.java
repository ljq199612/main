package com.rz.iot.bpo.service;

import com.alibaba.druid.pool.ha.selector.StickyRandomDataSourceSelector;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.param.BillSelectPersonParam;

public interface BpoBillPayablePersonService {

    Result findAllBillPerson(BillSelectPersonParam select);

    Result findBillPersonDetail(Integer id);

    Result findAllBillMonth(BillSelectPersonParam select);

    Result findADayBillDetail(Integer id,Integer projectId, String date);

    Result getBillPersonTotal(Integer personId, Integer projectId,String date);

    Result createBill(Integer id);

    Result findMonthById(Integer monthId);

    Result findBillTimeByTimeAndId(Integer personId,Integer projectId, String date);

    Result getBIllMessage(Integer monthId);

    //个人月账单详情
    Result getPersonMonthDetail(Integer monthId);

    Result createDayBill(Integer personId, Integer projectId, String date);

}
