package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.param.BillSelectPersonParam;
import com.rz.iot.bpo.service.BpoBillPayablePersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 账单 个人应付
 */
@RestController
@RequestMapping("/bpoBillHandle")
public class BpoBillPayablePersonController {

    @Resource
    BpoBillPayablePersonService bpoBillPayablePersonService;

    /**
     * 查询个人账单列表
     * @param select
     * @return
     */
    @GetMapping("/findAllBillPerson")
    public Result findAllBillPerson(BillSelectPersonParam select){
        return bpoBillPayablePersonService.findAllBillPerson(select);
    }

    /**
     * 查询个人账单详情
     * @param
     * @param date
     * @return
     */
    @GetMapping("/billPersonDayDetail")
    public Result billPersonDayDetail(Integer personId , Integer projectId, String date){
        return bpoBillPayablePersonService.findADayBillDetail(personId, projectId, date);
    }

    /**
     * 查询个人日账单汇总数据和详情
     * @param
     * @param date
     * @return
     */
    @GetMapping("getBillPersonTotal")
    public Result getBillPersonTotal(Integer personId, Integer projectId, String date){
        return bpoBillPayablePersonService.getBillPersonTotal(personId,projectId, date);
    }

    /**
     * 查询个人月账单
     * @param select
     * @return
     */
    @GetMapping("/findAllBillMonth")
    public Result findAllBillMonth(BillSelectPersonParam select){
        return bpoBillPayablePersonService.findAllBillMonth(select);
    }

    /**
     * 月账单详情
     * @param monthId
     * @return
     */

    @GetMapping("/getPersonMonthDetail")
    public Result getPersonMonthDetail(Integer monthId){
        return bpoBillPayablePersonService.getPersonMonthDetail(monthId);
    }
    /**
     * 通过月账单id查找个人月账单信息
     * @param
     * @return
     */
    @GetMapping("/findMonthById")
    public Result findMonthById(Integer monthId){
        return bpoBillPayablePersonService.findMonthById(monthId);
    }


    @GetMapping("/findBillTimeByTimeAndId")
    public Result findBillTimeByTimeAndId(Integer personId,Integer projectId, String date){
        return bpoBillPayablePersonService.findBillTimeByTimeAndId(personId,projectId, date);
    }

    /**
     * 获取月账单对账信息
     * @param monthId
     * @return
     */
    @GetMapping("/getBIllMessage")
    public Result getBIllMessage(Integer monthId){
        return bpoBillPayablePersonService.getBIllMessage(monthId);
    }

}
