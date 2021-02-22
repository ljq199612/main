package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.param.bpoBill.BillReconciliationSelectParam;
import com.rz.iot.bpo.model.param.bpoBill.BillSelectParam;
import com.rz.iot.bpo.service.BpoBillReconciliationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("reconciliation")
public class BpoBillReconciliationController {

    @Resource
    private BpoBillReconciliationService billReconciliationService;

    /**
     * 供应商对账列表
     */
    @GetMapping("supplierReconciliationList")
    public Result supplierReconciliationList(BillReconciliationSelectParam param){
        return Result.success();
    }

    /**
     * 月结员工对账列表
     */
    @GetMapping("monthPersonReconciliationList")
    public Result monthPersonReconciliationList(BillReconciliationSelectParam param){
        return Result.success();
    }

    /**
     * 日结员工对账列表
     */
    @GetMapping("dayPersonReconciliationList")
    public Result dayPersonReconciliationList(BillReconciliationSelectParam param){
        return Result.success();
    }


}
