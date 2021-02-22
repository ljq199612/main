package com.rz.iot.bpo.service.impl;

import com.rz.iot.bpo.common.constant.DictDataConstants;
import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.common.utils.SecurityUtils;
import com.rz.iot.bpo.controller.BpoEvaluationResultParam;
import com.rz.iot.bpo.framework.aspectj.DataScopeAspect;
import com.rz.iot.bpo.framework.aspectj.lang.annotation.DataScope;
import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.*;
import com.rz.iot.bpo.model.*;
import com.rz.iot.bpo.model.param.bpoBill.BillSelectParam;
import com.rz.iot.bpo.model.show.BpoPerformanceEvaluationShow;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillDayShow;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillMonthShow;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillReconciliationShow;
import com.rz.iot.bpo.model.show.bpoBill.BpoBillSupplierShow;
import com.rz.iot.bpo.service.BpoBillSupplierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BpoBillSupplierServiceImpl implements BpoBillSupplierService {

    @Resource
    private BpoBillSupplierDayMapper supplierDayMapper;

    @Resource
    private BpoPerformanceEvaluationMapper performanceEvaluationMapper;

    @Resource
    private BpoBillSupplierMonthMapper supplierMonthMapper;

    @Resource
    private BpoEvaluationResultMapper bpoEvaluationResultMapper;

    @Resource
    private BpoBillSupplierMonthDetailMapper bpoBillSupplierMonthDetailMapper;

    @Resource
    private BpoBillTimeMapper billTimeMapper;

    @Resource
    private BpoBillReconciliationMapper reconciliationMapper;

    @Resource
    private BpoBillReconciliationTimeDetailedMapper bpoBillReconciliationTimeDetailedMapper;

    private ThreadLocal<BpoBillReconciliationShow> localshow = new ThreadLocal<> ();


    @Override
    public List<BpoBillMonthShow> selectAllMonth(BaseEntity entity, BillSelectParam params) {
        List<BpoBillMonthShow> bpoBillMonthShows = supplierMonthMapper.selectAllMonthAccount(entity,params);
        return bpoBillMonthShows;
    }

    /**
     * 查询供应商所有日账单
     * @param entity
     * @param param
     * @return
     */
    @Override
    public Result finaAllDay(BaseEntity entity, BillSelectParam param) {
        List<BpoBillDayShow> bpoBillDayShows = supplierDayMapper.selectAllDayAccount (entity,param);
        Result<List<BpoBillDayShow>> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (bpoBillDayShows);
        return result;
    }

    /**
     * 根据供应商月账单表id，获取此供应商下的员工账单记录
     * @param id
     * @return
     */
    @Override
    public Result findDetailDays(Integer id) {
        BpoBillDayShow bpoBillDayShow = supplierDayMapper.selectDetailById (id);
        Result<BpoBillDayShow> result = new Result<> (ResultConstants.REQUEST_SUCCESS);
        result.setData (bpoBillDayShow);
        return result;
    }

    /**
     * 对账 应收
     * 插入信息到对账供应商详情表
     * 插入信息到对账表
     * 插入信息到对账详情表
     * @param monthId
     * @param type 1 为应收 2为应付
     * @return
     */
    public Result createCheck(Integer monthId,Integer type) {
        //获取供应商月账单信息
        BpoBillSupplierMonth bpoBillSupplierMonth = supplierMonthMapper.selectByPrimaryKey (monthId);
        if(bpoBillSupplierMonth == null){
            return Result.requestParamError ("供应商账单信息获取失败");
        }
        //获取收款方ifno_id->ID和付款方id->ParentID和收款方id->orgId
        BpoBillSupplierShow bpoSupplierInfo = supplierMonthMapper.selectSupplierIdByCompanyId (bpoBillSupplierMonth.getPayeeId (),bpoBillSupplierMonth.getProjectId ());
        if(bpoSupplierInfo == null ){
            return Result.requestParamError ("供应商信息获取失败");
        }
        //获取供应商当月的人员账单表信息
        List<BpoBillTime> bpoBillTimes = billTimeMapper.findAllByCompanyAndTime (bpoBillSupplierMonth.getPayeeId (), bpoBillSupplierMonth.getProjectId (), bpoBillSupplierMonth.getBillCycle ());
        if(bpoBillTimes == null || bpoBillTimes.size () == 0){
            return Result.requestParamError ("没有查询到人员账单信息");
        }
        //获取供应商月账单详情表信息(用于最后判断是做插入还是修改操作)
        BpoBillSupplierMonthDetail bpoBillSupplierMonthDetail = bpoBillSupplierMonthDetailMapper.selectByMonthKey (monthId);

        //修改账单状态信息(供应商日和月账单)
        String billCycle = bpoBillSupplierMonth.getBillCycle ().substring (0,7);//获取格式为yyyy-MM
        supplierDayMapper.updateReconciliationStatus (DictDataConstants.INRECONCILIATION,billCycle);
        supplierMonthMapper.updateReconciliationStatus (DictDataConstants.INRECONCILIATION,billCycle);
        //做插入数据操作
        addCheckMessage (bpoBillSupplierMonth,type,bpoSupplierInfo,bpoBillTimes);

        //获取供应商考核标准
        BpoPerformanceEvaluationShow info = performanceEvaluationMapper.selectBysupplierInfoId(bpoSupplierInfo.getPayeeInfoId ());
        //获取供应商考核信息
        BpoEvaluationResultParam param = new BpoEvaluationResultParam ();
        param.setSupplierInfoId (bpoSupplierInfo.getPayeeInfoId ());
        param.setEvaluationTime (bpoBillSupplierMonth.getBillCycle ());
        BpoEvaluationResult result = bpoEvaluationResultMapper.getResult (param);

        //插入信息到对账供应商详情表
        BpoBillSupplierMonthDetail detail = calculation (bpoBillSupplierMonth,result,info);

        if(bpoBillSupplierMonthDetail!=null){
            detail.setId (bpoBillSupplierMonthDetail.getId ());
            bpoBillSupplierMonthDetailMapper.updateByPrimaryKeySelective (detail);
        }else{
            bpoBillSupplierMonthDetailMapper.insertSelective (detail);
        }

        return Result.success ();
    }

    /**
     * 查看详情
     * 获取信息
     * @param monthId
     * @param type 1：应收 2：应付
     * @return
     */
    public Result selectDetail(Integer monthId,Integer type){
        //获取供应商月账单信息
        BpoBillSupplierMonth bpoBillSupplierMonth = supplierMonthMapper.selectByPrimaryKey (monthId);
        if(bpoBillSupplierMonth == null){
            return Result.requestParamError ("供应商账单信息获取失败");
        }
        //获取收款方info id和
        BpoBillSupplierShow bpoSupplierInfo = supplierMonthMapper.selectSupplierIdByCompanyId (bpoBillSupplierMonth.getPayeeId (),bpoBillSupplierMonth.getProjectId ());
        if(bpoSupplierInfo == null ){
            return Result.requestParamError ("供应商信息获取失败");
        }
        //获取供应商当月的人员账单表信息
        List<BpoBillTime> bpoBillTimes = billTimeMapper.findAllByCompanyAndTime (bpoBillSupplierMonth.getPayeeId (), bpoBillSupplierMonth.getProjectId (), bpoBillSupplierMonth.getBillCycle ());
        if(bpoBillTimes == null || bpoBillTimes.size () == 0){
            return Result.requestParamError ("没有查询到人员账单信息");
        }
        //获取供应商考核信息(通过收款方供应商infoId查询)
        BpoEvaluationResultParam param = new BpoEvaluationResultParam ();
        param.setSupplierInfoId (bpoSupplierInfo.getPayeeInfoId ());
        param.setEvaluationTime (bpoBillSupplierMonth.getBillCycle ());
        BpoEvaluationResult result = bpoEvaluationResultMapper.getResult (param);
        //获取供应商月账单详情表信息(用于最后判断是做插入还是修改操作)
        BpoBillSupplierMonthDetail bpoBillSupplierMonthDetail = bpoBillSupplierMonthDetailMapper.selectByMonthKey (monthId);
        //插入信息到对账供应商详情表
        BpoBillSupplierMonthDetail detail = new BpoBillSupplierMonthDetail ();
        //获取供应商考核标准(通过收款方供应商infoId查询)
        BpoPerformanceEvaluationShow info = performanceEvaluationMapper.selectBysupplierInfoId(bpoSupplierInfo.getPayeeInfoId ());
        BigDecimal billAmount = bpoBillSupplierMonth.getBillAmount ();//未扣款账单金额
        if(result!=null) {
            //创建信息并插入到账单表和账单明细表
            addCheckMessage(bpoBillSupplierMonth,type,bpoSupplierInfo,bpoBillTimes);
            detail = calculation (bpoBillSupplierMonth,result,info);
        }else{
            detail.setMonthBillId (bpoBillSupplierMonth.getId ());//月账单ID
            detail.setSupplierSalary (bpoBillSupplierMonth.getSupplierSalary ());//薪资标准
            detail.setDeduction (bpoBillSupplierMonth.getTotalDeduction ());//扣款
            detail.setBillAmount (bpoBillSupplierMonth.getRealBillAmount ());//账单金额
        }
        if(bpoBillSupplierMonthDetail!=null){
            detail.setId (bpoBillSupplierMonthDetail.getId ());
            bpoBillSupplierMonthDetailMapper.updateByPrimaryKeySelective (detail);
        }else{
            bpoBillSupplierMonthDetailMapper.insertSelective (detail);
        }

        return Result.success ();
    }

    /**
     * 获取账单信息
     * @param monthId
     * @param type 类型，1：应收 2：应付
     * @param selectType 类型 1为点击发起对账的信息  2为查看详情的信息
     * @return
     */
    @Override
    @Transactional
    public BpoBillReconciliationShow getCheckMessage(Integer monthId,Integer type,Integer selectType) {
        //账单表
        BpoBillReconciliation reconciliation = reconciliationMapper.selectByMonthBillKey (monthId);
        //供应商月账单
        BpoBillReconciliationShow bpoBillMonthShow = supplierMonthMapper.selectReconciliationByKey (monthId);
        if(reconciliation == null){
            if(selectType == 1){
                createCheck(monthId,type);
                //生成账单后才会有数据
                reconciliation = reconciliationMapper.selectByMonthBillKey (monthId);
            }else {
                selectDetail (monthId,type);
            }
        }
        //如果未发起对账且是查看
        if(selectType == 2 && reconciliation==null) {
            List<BpoBillReconciliationTimeDetailed> getdetail = getdetail (new BaseEntity (), reconciliation.getBillingCycle (), type);
            bpoBillMonthShow.setDetaileds (getdetail);
        }else{
            //账单对账明细 人员计时
            List<BpoBillReconciliationTimeDetailed> detaileds = bpoBillReconciliationTimeDetailedMapper.selectByParam
                    (reconciliation.getPayeeId (), reconciliation.getProjectId (), reconciliation.getBillingCycle ().substring (0, 7));
            bpoBillMonthShow.setDetaileds (detaileds);

            bpoBillMonthShow.setBillReconciliationId (reconciliation.getId ());
        }
        //供应商月账单明细
        BpoBillSupplierMonthDetail detail = bpoBillSupplierMonthDetailMapper.selectByMonthKey (monthId);
        if (detail.getOtherDeduction () != null) {
            bpoBillMonthShow.setOtherDeduction (detail.getOtherDeduction ().doubleValue ());
        }
        if (detail.getOtherDeduction () != null) {
            bpoBillMonthShow.setOtherIncome (detail.getOtherIncome ().doubleValue ());
        }
        bpoBillMonthShow.setPerformanceScore(detail.getPerformanceScore ());
        bpoBillMonthShow.setPerformanceScoreRate(detail.getPerformanceScoreRate ());
        bpoBillMonthShow.setPerformanceQuota(detail.getPerformanceQuota ());
        bpoBillMonthShow.setPerformanceScoreQuota(detail.getPerformanceScoreQuota ());
        bpoBillMonthShow.setFeeRuleType (bpoBillMonthShow.getFeeRuleType ());
        if(localshow.get ()!=null){
            localshow.remove ();
        }
        localshow.set (bpoBillMonthShow);
        return bpoBillMonthShow;
    }

    @Override
    @Transactional
    public Result startCheck(BpoBillReconciliationShow bpoBillMonthShow,Integer type) {
        BpoBillReconciliationShow checkMessage = null;
        if(localshow.get ()==null){
            checkMessage = getCheckMessage (bpoBillMonthShow.getId (),1,type);
        }
        checkMessage = localshow.get ();
        BpoBillReconciliation reconciliation = reconciliationMapper.selectByMonthBillKey (bpoBillMonthShow.getId ());
        if(reconciliation.getStatus ()!=DictDataConstants.NoRECONCILIATION){
            return Result.requestParamError ("账单已经开始对账，无法申请");
        }
        //供应商月账单参数设置
        BpoBillSupplierMonth bpoBillSupplierMonth = new BpoBillSupplierMonth ();
        bpoBillSupplierMonth.setId (bpoBillMonthShow.getId ());
        bpoBillMonthShow.setTotalDeduction (bpoBillMonthShow.getTotalDeduction ());
        bpoBillMonthShow.setBillAmount (bpoBillMonthShow.getBillAmount ());
        supplierMonthMapper.updateByPrimaryKey (bpoBillSupplierMonth);
        //供应商月账单详情参数设置
        BpoBillSupplierMonthDetail bpoBillSupplierMonthDetail = new BpoBillSupplierMonthDetail ();
        bpoBillSupplierMonthDetail.setOtherDeduction (new BigDecimal (bpoBillMonthShow.getOtherDeduction ()));
        bpoBillSupplierMonthDetail.setOtherIncome (new BigDecimal (bpoBillMonthShow.getOtherIncome ()));
        bpoBillSupplierMonthDetail.setRealBillAmount (bpoBillMonthShow.getRelBillMoney ());
        bpoBillSupplierMonthDetail.setOtherDeductionExplain (bpoBillMonthShow.getOtherDeductionMessage ());
        bpoBillSupplierMonthDetail.setOtherIncomeExplain (bpoBillMonthShow.getOtherIncomeMessage ());
        bpoBillSupplierMonthDetail.setMonthBillId (bpoBillMonthShow.getId ());
        bpoBillSupplierMonthDetailMapper.updateByPrimaryKeySelectiveByMonthId (bpoBillSupplierMonthDetail);
        //修改账单表状态
        BpoBillReconciliation bpoBillReconciliation = new BpoBillReconciliation ();
        bpoBillReconciliation.setStatus (DictDataConstants.INRECONCILIATION);
        bpoBillReconciliation.setTotalHours (bpoBillMonthShow.getTotalHours ().floatValue ());
        bpoBillReconciliation.setId (localshow.get ().getBillReconciliationId ());
        reconciliationMapper.updateByPrimaryKeySelective (bpoBillReconciliation);
//        System.out.println (1/0);
        return Result.success ();
    }

    @Override
    public Result saveMessage(BpoBillReconciliationTimeDetailed detailed) {
        bpoBillReconciliationTimeDetailedMapper.updateByPrimaryKeySelective (detailed);
        return Result.success ();
    }

    /**
     * 获取到按时账单信息并转换
     * @param entity
     * @param billCycle
     * @return
     */
    @DataScope(level = DataScopeAspect.DATA_SCOPE_DEPT)
    private List<BpoBillReconciliationTimeDetailed> getdetail(BaseEntity entity,String billCycle,Integer type){
        List<BpoBillTime> allByBillCycle = billTimeMapper.findAllByBillCycle (entity, billCycle);
        List<BpoBillReconciliationTimeDetailed> detaileds = new ArrayList<> ();
        for (BpoBillTime bpoBillTime : allByBillCycle) {
            if(bpoBillTime.getStatus () == DictDataConstants.DELETE_STATUS){
                continue;
            }
            BpoBillReconciliationTimeDetailed detailed = new BpoBillReconciliationTimeDetailed ();
            detailed.setProjectId (bpoBillTime.getProjectId ());//项目id
            detailed.setBillingCycle (bpoBillTime.getBillingCycle ().substring (0,7));//周期
            detailed.setPersonId (bpoBillTime.getPersonId ());//人员id
            detailed.setFeeRuleType (bpoBillTime.getFeeRuleType ());//计价模式
            detailed.setAttendanceStartTime (bpoBillTime.getAttendanceStartTime ());//上班卡
            detailed.setAttendanceEndTime (bpoBillTime.getAttendanceEndTime ());//下班卡
            detailed.setWageHours (bpoBillTime.getWageHours ().floatValue ());//计薪工时
            detailed.setIncome (bpoBillTime.getSalaryStandard ());//收入
            detailed.setRealIncome (bpoBillTime.getRealIncome ());//实际收入
            detailed.setStatus (bpoBillTime.getStatus ());//状态
            detailed.setUpdateUserId (SecurityUtils.getLoginUser ().getUser ().getUserId ());//操作者
            detaileds.add (detailed);
        }
        return detaileds;
    }

    /**
     * 当有考核信息时第一次获取对账信息
     * 新增对账信息
     * 插入信息到对账表和账单对账明细表
     * @param bpoBillSupplierMonth 月账单信息
     * @param type 1：应收 2：应付
     * @param bpoSupplierInfo 供应商信息
     * @param BpoBillTimes 个人账单表信息
     */
    private void addCheckMessage( BpoBillSupplierMonth bpoBillSupplierMonth,
                                  Integer type,BpoBillSupplierShow bpoSupplierInfo,List<BpoBillTime> BpoBillTimes){
        //新增对账表记录
        BpoBillReconciliation reconciliation = new BpoBillReconciliation ();
        reconciliation.setBillingCycle (bpoBillSupplierMonth.getBillCycle ().substring (0,7));//周期
        reconciliation.setBillType (DictDataConstants.MONTHEND);//账单类型
        reconciliation.setPayeeId (bpoBillSupplierMonth.getPayeeId ());//收款方id
        reconciliation.setPayerId (bpoBillSupplierMonth.getPayerId ());//付款方id
        reconciliation.setProjectId (bpoBillSupplierMonth.getProjectId ());//项目ID
        reconciliation.setMonthBillId (bpoBillSupplierMonth.getId ());//对应月账单ID

        /**
         * 应收应付 按照type
         */
        if(type == 1){
            reconciliation.setReconciliationWay (DictDataConstants.RECIVEABLE);//对账方式  此设置为应收
        }else if(type ==2){
            reconciliation.setReconciliationWay (DictDataConstants.SHOULDPAY);//对账方式  此设置为应付
        }
        if(bpoSupplierInfo.getPayeeParentId ()==-1){
            reconciliation.setReconciliationType (DictDataConstants.CUSTOMERTOMAINSUPPLIER);//对账类型为 客户-主体供应商
        }else{
            reconciliation.setReconciliationType (DictDataConstants.MAINSUPPLIERTOSUPPLIER);//对账类型为 主体供应商-供应商
        }


        reconciliation.setFeeRuleType (DictDataConstants.BY_TIME);//计价模式
        reconciliation.setTotalHours (bpoBillSupplierMonth.getTotalHours ().floatValue ());//总时长
//        reconciliation.setTotalOutput ();//总数量
        reconciliation.setReconciliationStatus (DictDataConstants.NoRECONCILIATION);//对账状态 未对账
        reconciliation.setStatus (DictDataConstants.NORMAL_STATUS);
        reconciliationMapper.insertSelective (reconciliation);
        //复制一份信息进入对账明细表
        List<BpoBillReconciliationTimeDetailed> detaileds = new ArrayList<> ();
        for (BpoBillTime bpoBillTime : BpoBillTimes) {
            if(bpoBillTime.getStatus () == DictDataConstants.DELETE_STATUS){
                continue;
            }
            BpoBillReconciliationTimeDetailed detailed = new BpoBillReconciliationTimeDetailed ();
            detailed.setBillReconciliationId (reconciliation.getId ());//id
            detailed.setProjectId (reconciliation.getProjectId ());//项目id
            detailed.setBillingCycle (bpoBillTime.getBillingCycle ().substring (0,10));//周期
            detailed.setPersonId (bpoBillTime.getPersonId ());//人员id
            detailed.setFeeRuleType (bpoBillTime.getFeeRuleType ());//计价模式
            detailed.setAttendanceStartTime (bpoBillTime.getAttendanceStartTime ());//上班卡
            detailed.setAttendanceEndTime (bpoBillTime.getAttendanceEndTime ());//下班卡
            detailed.setWageHours (bpoBillTime.getWageHours ().floatValue ());//计薪工时
            detailed.setIncome (bpoBillTime.getSalaryStandard ());//收入
            detailed.setRealIncome (bpoBillTime.getRealIncome ());//实际收入
            detailed.setDeduction (bpoBillTime.getDeduction ());//扣款
            detailed.setStatus (bpoBillTime.getStatus ());//状态
            detailed.setAppealStatus (DictDataConstants.ORIGINALGENERATION);//申诉状态
            detailed.setUpdateUserId (SecurityUtils.getLoginUser ().getUser ().getUserId ());//操作者
            detaileds.add (detailed);
        }
        bpoBillReconciliationTimeDetailedMapper.insertSelectiveAll (detaileds);
    }

    /**
     * 用于计算考核后金额 并返回信息
     * @param bpoBillSupplierMonth 月账单信息
     * @param result 考核结果信息
     * @param info 考核信息
     * @return
     */
    private BpoBillSupplierMonthDetail calculation(BpoBillSupplierMonth bpoBillSupplierMonth,BpoEvaluationResult result,
                                                   BpoPerformanceEvaluationShow info){
        //插入信息到对账供应商详情表
        BpoBillSupplierMonthDetail detail = new BpoBillSupplierMonthDetail ();
        detail.setMonthBillId (bpoBillSupplierMonth.getId ());//月账单ID
        detail.setSupplierSalary (bpoBillSupplierMonth.getSupplierSalary ());//薪资标准
        detail.setDeduction (bpoBillSupplierMonth.getTotalDeduction ());//扣款
        detail.setBillAmount (bpoBillSupplierMonth.getRealBillAmount ());//账单金额
        detail.setPerformanceScore (result.getGrade ());//绩效得分
        detail.setPerformanceScoreRate (info.getLinkRate ());//绩效考核挂钩比
        detail.setPerformanceQuota (result.getAssessmentQuota ());//考核额度
        BigDecimal billAmount = bpoBillSupplierMonth.getBillAmount ();//未扣款账单金额
        //支付比例  1.获取绩效得分 2.获得支付比例
        Double paymentRatio = new Double (0.00);
        if(result.getGrade ()!=null) {
            Double grade = Double.parseDouble (result.getGrade ());
            if (grade>90){
                paymentRatio = 1.00;
            }else if(grade>50 && grade<=90){
                paymentRatio = grade*0.01;
            }else {
                paymentRatio = 0.50;
            }
        }
        //挂钩比例
        BigDecimal hookRatio = new BigDecimal ("1").subtract (new BigDecimal (paymentRatio));
        //考核后额度
        Double param1 =  Double.parseDouble (result.getResultMoney ());
        Double param2 = Double.parseDouble(billAmount.toString ())*paymentRatio;
        BigDecimal afterAssessment = new BigDecimal (param1.toString ()).subtract (new BigDecimal (param2.toString ()));

        detail.setPerformanceScoreQuota (afterAssessment.toString ());//考核后额度
        //实际账单金额
        Double actualBill = 0.00;
        double agencySalary = bpoBillSupplierMonth.getAgencySalary ().doubleValue ();//代发薪资
        actualBill = Double.parseDouble (result.getResultMoney ()) + agencySalary;
        detail.setRealBillAmount (new BigDecimal (actualBill));//加上代发薪资的最终实际账单金额
        return detail;
    }

}
