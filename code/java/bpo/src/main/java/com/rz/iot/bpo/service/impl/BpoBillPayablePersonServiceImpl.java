package com.rz.iot.bpo.service.impl;

import com.rz.iot.bpo.common.constant.DictDataConstants;
import com.rz.iot.bpo.common.utils.SecurityUtils;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.*;
import com.rz.iot.bpo.model.BpoBillPersonMonthDetail;
import com.rz.iot.bpo.model.BpoBillReconciliation;
import com.rz.iot.bpo.model.BpoBillReconciliationTimeDetailed;
import com.rz.iot.bpo.model.param.BillSelectPersonParam;
import com.rz.iot.bpo.model.show.BpoBillPayablePersonShow;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillPersonDayTotal;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillPersonDayDetail;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillPersonMonthShow;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillPersonReconciliationShow;
import com.rz.iot.bpo.service.BpoBillPayablePersonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BpoBillPayablePersonServiceImpl implements BpoBillPayablePersonService {

    @Resource
    private BpoBillTimeMapper bpoBillTimeMapper;

    @Resource
    private BpoBillPersonMonthMapper bpoBillPersonMonthMapper;

    @Resource
    private BpoBillReconciliationMapper reconciliationMapper;

    @Resource
    private BpoBillPersonMonthDetailMapper bpoBillPersonMonthDetailMapper;

    @Resource
    private BpoBillReconciliationTimeDetailedMapper bpoBillReconciliationTimeDetailedMapper;


    @Override
    public Result findAllBillPerson(BillSelectPersonParam select) {

        List<BpoBillPayablePersonShow> bpoBillPayablePersonShow = bpoBillTimeMapper.findAllBillPerson(select);
        return Result.success(bpoBillPayablePersonShow);
    }

    @Override
    public Result findBillPersonDetail(Integer id) {
        return null;
    }

    @Override
    public Result findAllBillMonth(BillSelectPersonParam select) {

        List<BpoBillPersonMonthShow> list = bpoBillPersonMonthMapper.findAllBillMonth(select);
        return Result.success(list);
    }

    //通过
    @Override
    public Result findADayBillDetail(Integer id, Integer projectId,String date) {

        List<BpoBillPersonDayDetail> bpoBillPersonDayDetail = bpoBillTimeMapper.billPersonDayDetail(id, projectId, date);

        return Result.success(bpoBillPersonDayDetail);
    }

    @Override
    public Result getBillPersonTotal(Integer personId, Integer projectId,String date) {

        //获取日账单汇总
        BpoBillPersonDayTotal list = bpoBillTimeMapper.getBillPersonTotal(personId, projectId,date);

        //获取日账单详情
//        List<BpoBillPersonDayDetail> bpoBillPersonDayDetail = bpoBillTimeMapper.billPersonDayDetail(id, date);
        List<BpoBillPersonDayDetail> bpoBillPersonDayDetail = bpoBillTimeMapper.billPersonDayDetail(personId, projectId, date);

        //计算总补助
        list.setTotalSubsidy(list.getTotalFoodSubsidy()+list.getTotalNightSubsidy()+list.getTotalOtherSubsidy());

//        list.setBpoBillPersonDayDetails(bpoBillPersonDayDetail);

        return Result.success(list);
    }

    public Result createDayBill(){
        return null;
    }

    /**
     * 对账 应收
     * 插入信息到对账供应商详情表
     * 插入信息到对账表
     * 插入信息到对账详情表
     * @param monthId
     * @return
     */

    @Override
    public Result createBill(Integer monthId) {

        //获取月账单信息
        BpoBillPersonMonthShow list = bpoBillPersonMonthMapper.findMonthById(monthId);

        //通过用户Id和周期获取当月账单详情
        List<BpoBillPersonDayDetail> bpoBillPersonDayDetails = bpoBillTimeMapper.findBillTimeByTimeAndId(list.getPersonId(),list.getProjectId(),list.getBillingCycle());

        //插入对账表记录
        BpoBillReconciliation bpoBillReconciliation = new BpoBillReconciliation();
        bpoBillReconciliation.setBillingCycle(list.getBillingCycle());
        bpoBillReconciliation.setBillType(DictDataConstants.MONTHEND);
        bpoBillReconciliation.setPayerId(list.getCompanyId());
        bpoBillReconciliation.setPayeeId(list.getPersonId());
        bpoBillReconciliation.setProjectId(list.getProjectId());
        bpoBillReconciliation.setMonthBillId(list.getId());
        bpoBillReconciliation.setReconciliationWay(DictDataConstants.SHOULDPAY);//应付
        bpoBillReconciliation.setReconciliationType(DictDataConstants.SUPPLIERTOPERSON); //供应商-个人
        bpoBillReconciliation.setFeeRuleType(DictDataConstants.BY_TIME); //按时
        bpoBillReconciliation.setTotalOutput(list.getTotalOutput());
        bpoBillReconciliation.setTotalHours(list.getTotalHours().floatValue());
        bpoBillReconciliation.setReconciliationStatus(DictDataConstants.NoRECONCILIATION);
        bpoBillReconciliation.setStatus(DictDataConstants.NORMAL_STATUS);
        reconciliationMapper.insertSelective(bpoBillReconciliation);

        //插入账单详情信息
        BpoBillPersonMonthDetail bpoBillPersonMonthDetail = new BpoBillPersonMonthDetail();
        bpoBillPersonMonthDetail.setMonthBillId(list.getId()); //人员账单Id
        bpoBillPersonMonthDetail.setHourlyWage(list.getTimePrice()); //员工当前时薪
        bpoBillPersonMonthDetail.setFoodSubsidy(list.getFoodSubsidy()); //餐补
        bpoBillPersonMonthDetail.setNightSubsidy(list.getNightSubsidy()); //晚补
        bpoBillPersonMonthDetail.setOtherSubsidy(list.getOtherSubsidy()); //其他补
        bpoBillPersonMonthDetail.setDeduction(list.getDeduction()); //扣款
        bpoBillPersonMonthDetailMapper.insertSelective(bpoBillPersonMonthDetail);

        //账单明细表数据的插入
        List<BpoBillReconciliationTimeDetailed> detaileds = new ArrayList<>();
        for (BpoBillPersonDayDetail dayDetail: bpoBillPersonDayDetails){
            if (dayDetail.getStatus() == DictDataConstants.DELETE_STATUS){
                continue;
            }
            BpoBillReconciliationTimeDetailed timeDetailed = new BpoBillReconciliationTimeDetailed();
            timeDetailed.setBillReconciliationId(bpoBillReconciliation.getId()); //账单对账id
            timeDetailed.setProjectId (bpoBillReconciliation.getProjectId ()); //项目id
            timeDetailed.setSupplierInfoId(bpoBillReconciliation.getSupplierInfoId());
            timeDetailed.setBillingCycle (dayDetail.getBillingCycle ()); //周期
            timeDetailed.setPersonId (dayDetail.getPersonId ()); //人员id
            timeDetailed.setFeeRuleType (dayDetail.getFeeRuleType ()); //计价模式
            timeDetailed.setAttendanceStartTime (dayDetail.getAttendanceStartTime ()); //上班卡
            timeDetailed.setAttendanceEndTime (dayDetail.getAttendanceEndTime ()); //下班卡
            timeDetailed.setWageHours (dayDetail.getWageHours ().floatValue ()); //计薪工时
            timeDetailed.setIncome (dayDetail.getSalaryStandard ()); //收入
            timeDetailed.setRealIncome (dayDetail.getRealIncome ()); //实际收入
            timeDetailed.setStatus (dayDetail.getStatus ());//状态
            timeDetailed.setUpdateUserId (SecurityUtils.getLoginUser ().getUser ().getUserId ());//操作者

//            bpoBillReconciliationTimeDetailedMapper.insertSelective(timeDetailed);
            detaileds.add (timeDetailed);
        }
        //批量插入数据
        bpoBillReconciliationTimeDetailedMapper.insertSelectiveAll(detaileds);
        return Result.success();
    }

    @Override
    public Result findMonthById(Integer monthId) {

        //通过Id获取个人月账单
//        BpoBillPersonMonthShow bpoBillPersonMonthShow = bpoBillPersonMonthMapper.findMonthById(monthId);

        BpoBillPersonMonthDetail monthDetail = bpoBillPersonMonthDetailMapper.getMonthDetailByMonthId(monthId);
//        BpoBillPersonReconciliationShow bpoBillPersonMonthShow = bpoBillPersonMonthMapper.getMonthById(monthId);
        return Result.success(monthDetail);
    }

    @Override
    public Result findBillTimeByTimeAndId(Integer personId, Integer projectId,String date) {

        List<BpoBillPersonDayDetail> bpoBillPersonDayDetails = bpoBillTimeMapper.findBillTimeByTimeAndId(personId,projectId,date);
        return Result.success(bpoBillPersonDayDetails);
    }

    /**
     * 获取账单信息
     * @param monthId
     * @return
     */
    @Override
    public Result getBIllMessage(Integer monthId) {
        BpoBillReconciliation reconciliation = reconciliationMapper.selectByMonthBillKey (monthId);
        if(reconciliation == null){
            createBill(monthId);
            reconciliation = reconciliationMapper.selectByMonthBillKey (monthId);
        }

        //月账单
        BpoBillPersonReconciliationShow reconciliationShow = bpoBillPersonMonthMapper.getMonthById(monthId);
        //月账单详情
        BpoBillPersonMonthDetail monthDetail = bpoBillPersonMonthDetailMapper.getMonthDetailByMonthId(monthId);
        //对账明细表
        List<BpoBillReconciliationTimeDetailed> timeDetaileds = bpoBillReconciliationTimeDetailedMapper.
                selectByPersonIdAndTime(reconciliation.getPayeeId(), reconciliation.getBillingCycle());
        reconciliationShow.setBusinessDeduction(monthDetail.getBusinessDeduction()); //业务扣款
        reconciliationShow.setSocialSecurity(monthDetail.getSocialSecurity()); //社保扣款
        reconciliationShow.setAccumulationFund(monthDetail.getAccumulationFund()); //公积金扣款
        reconciliationShow.setPersonalIncomeTax(monthDetail.getPersonalIncomeTax()); //个税扣款
        reconciliationShow.setOtherDeduction(monthDetail.getOtherDeduction()); //其他扣款
        reconciliationShow.setOtherIncome(monthDetail.getOtherIncome()); //其他应收
        reconciliationShow.setTimeDetaileds(timeDetaileds);
        return Result.success(reconciliationShow);
    }

    /**
     * 查看月账单详情
     * @param monthId
     * @return
     */
    @Override
    public Result getPersonMonthDetail(Integer monthId) {

        //获取月账单信息
        BpoBillPersonReconciliationShow reconciliationShow = bpoBillPersonMonthMapper.getMonthById(monthId);

        //查询月账单详情
        BpoBillPersonMonthDetail monthDetail = bpoBillPersonMonthDetailMapper.getMonthDetailByMonthId(monthId);

        //获取当月日结数据
        List<BpoBillPersonDayDetail> bpoBillPersonDayDetails = bpoBillTimeMapper.
                findBillTimeByTimeAndId(reconciliationShow.getPersonId(),reconciliationShow.getProjectId(),reconciliationShow.getBillingCycle());

//        reconciliationShow.setBpoBillPersonDayDetails(bpoBillPersonDayDetails);

        return Result.success(reconciliationShow);
    }

    @Override
    public Result createDayBill(Integer personId, Integer projectId, String date) {

        return null;
    }
}
