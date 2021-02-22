package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.BpoBillReconciliationTimeDetailed;
import com.rz.iot.bpo.model.param.bpoBill.BillSelectParam;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillMonthShow;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillReconciliationShow;
import com.rz.iot.bpo.service.BpoBillSupplierService;
import com.rz.iot.bpo.service.BpoPublicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("supplierBill")
public class BpoBillSupplierController {

    @Resource
    private BpoBillSupplierService payableReceivableService;

    @Resource
    private BpoPublicService bpoPublicService;


    /**
     * 获取供应商月账单信息
     * @param params
     * @return
     */
    @GetMapping("findAllMonth")
    public Result findAllMonth(BillSelectParam params){
        return  payableReceivableService.finaAllDay (new BaseEntity (), params);
    }

    /**
     * 获取供应商日账单信息
     * @param params
     * @return
     */
    @GetMapping("findAllDay")
    public Result findAllDay(BillSelectParam params){
        List<BpoBillMonthShow> list = payableReceivableService.selectAllMonth(new BaseEntity(),params);
        return Result.success(list);
    }

    /**
     * 查询供应商日账单详情信息
     * @param id
     * @return
     */
    @GetMapping("findDayDetail")
    public Result findDayDetail(Integer id){
        return payableReceivableService.findDetailDays(id);
    }

    /**
     * 对账信息中修改单条信息
     * @return
     */
    @PostMapping("saveMessage")
    public Result saveMessage(BpoBillReconciliationTimeDetailed detailed){
        return payableReceivableService.saveMessage (detailed);
    }

    /**
     * 获取对账信息
     * @param monthId 月账单id
     * @param type 1：应收 2:应付
     * @param selectType  1：点击发起对账的信息  2：查看详情的信息
     * @return
     */
    @GetMapping("getCheckMessage")
    public Result getCheckMessage(Integer monthId,Integer type,Integer selectType){
        BpoBillReconciliationShow checkMessage = payableReceivableService.getCheckMessage (monthId,type,selectType);
        return Result.success (checkMessage);
    }

    /**
     * 进行对账
     * @param bpoBillMonthShow
     * @param type 1：应收 2：应付
     * @return
     */
    @PostMapping("checkBill")
    public Result checkBill(BpoBillReconciliationShow bpoBillMonthShow,Integer type){
        Result result = payableReceivableService.startCheck (bpoBillMonthShow,type);
        return result;
    }

    /**
     * 获取当前用户下的项目
     * @return
     */
    @GetMapping("getProject")
    public Result getProject(){
        return bpoPublicService.getProject (new BaseEntity ());
    }

    /**
     * 获取当前用户下的场地
     * @return
     */
    @GetMapping("getTransferStation")
    public Result getTransferStation(){
        return bpoPublicService.getTransferStation (new BaseEntity ());
    }

    /**
     * 获取所有付款方
     * @return
     */
    @GetMapping("getPayer")
    public Result getPayer(){
        return bpoPublicService.getPayer (new BaseEntity ());
    }
}
