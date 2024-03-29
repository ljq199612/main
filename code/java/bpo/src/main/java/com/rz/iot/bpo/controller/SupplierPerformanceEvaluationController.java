package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.aspectj.lang.annotation.Log;
import com.rz.iot.bpo.framework.aspectj.lang.enums.BusinessType;
import com.rz.iot.bpo.framework.aspectj.lang.enums.OperatorType;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.param.*;
import com.rz.iot.bpo.model.show.*;
import com.rz.iot.bpo.service.SupplierPerformanceEvaluationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述 : 供应商考核Controller
 * 作者 : qin xian
 * 创建时间 : 2020/7/3 0003 9:18
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Api(tags = "供应商考核")
@RequestMapping("/supplierEvaluation")
@RestController
public class SupplierPerformanceEvaluationController {

    @Resource
    private SupplierPerformanceEvaluationService supplierEvaluationService;

    //---------考核规则

    /*操作步骤：
    1、选择场地
    2、选择供应商
    3、选择供应商
    4、通过供应商Id+供应商Id获取基本信息
    5、添加费用支付比例

    1、新增(修改/删除/详情)考核标准
    2、新增(修改/删除/详情)考核等级
     */

    /**
     * 获取供应商考核标准列表
     *
     * @return
     */
    //@ApiOperation("获取供应商考核标准列表")
    @GetMapping("/getDetailList")
    public Result getDetailList() {
        // TODO: 2020/7/4 0004 qinxian 
        return Result.success();
    }

    /**
     * 获取供应商考核标准
     *
     * @return
     */
    @ApiOperation("获取供应商考核标准")
    @GetMapping("/getDetail")
    public Result<BpoPerformanceEvaluationShow> getDetail(Integer supplierInfoId) {
        return supplierEvaluationService.getDetail(supplierInfoId);
    }

    /**
     * 获取供应商考核标准等级
     *
     * @return
     */
    @ApiOperation("获取供应商考核标准等级")
    @GetMapping("/getLevel")
    public Result<BpoPerformanceLevelShow> getLevel(Integer supplierInfoId) {
        return supplierEvaluationService.getLevel(supplierInfoId);
    }

    /**
     * 增加绩效考核标准
     *
     * @return
     */
    @ApiOperation("增加绩效考核标准")
    @PostMapping("/addEvaluationStandards")
    @Log(title = "增加绩效考核标准", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result addEvaluationStandards(@RequestBody BpoPerformanceEvaluationParam info) {
        return supplierEvaluationService.addEvaluationStandards(info);
    }

    /**
     * 增加绩效考核评定等级
     *
     * @return
     */
    @ApiOperation("增加绩效考核评定等级")
    @PostMapping("/addLevel")
    @Log(title = "增加绩效考核评定等级", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result addLevel(@RequestBody List<BpoPerformanceLevelParam> list) {
        return supplierEvaluationService.addLevel(list);
    }

    /**
     * 删除绩效考核标准
     *
     * @return
     */
    @ApiOperation("删除绩效考核标准")
    @GetMapping("/deleteEvaluationStandards")
    @Log(title = "删除绩效考核标准", businessType = BusinessType.DELETE, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result deleteEvaluationStandards(Integer supplierInfoId) {
        return supplierEvaluationService.deleteEvaluationStandards(supplierInfoId);
    }

    /**
     * 删除供应商考核标准等级
     *
     * @return
     */
    @ApiOperation("删除供应商考核标准等级")
    @GetMapping("/deleteLevel")
    @Log(title = "删除供应商考核标准等级", businessType = BusinessType.DELETE, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result deleteLevel(Integer supplierInfoId) {
        return supplierEvaluationService.deleteLevel(supplierInfoId);
    }

    /**
     * 修改绩效考核标准
     *
     * @return
     */
    @ApiOperation("修改绩效考核标准")
    @GetMapping("/updateEvaluationStandards")
    @Log(title = "修改绩效考核标准", businessType = BusinessType.UPDATE, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result updateEvaluationStandards(@RequestBody BpoPerformanceEvaluationParam info) {
        return supplierEvaluationService.updateEvaluationStandards(info);
    }

    /**
     * 修改供应商考核标准等级
     *
     * @return
     */
    @ApiOperation("修改供应商考核标准等级")
    @PostMapping("/updateLevel")
    @Log(title = "修改供应商考核标准等级", businessType = BusinessType.UPDATE, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result updateLevel(@RequestBody BpoPerformanceLevelParam info) {
        return supplierEvaluationService.updateLevel(info);
    }


    //---------考核结果

    /*
    具备接口与功能：
    1、考核结果列表(根据考核记录统计)
    参数条件：若无参数 显示所有的供应商考核结果;
            (供应商Id)某个供应商下所有供应商的考核结果;
            (供应商Id)某个供应商所有供应商的考核结果;
            (供应商Id+供应商Id)某个供应商的某个供应商的考核结果;

    2、获取考核模板
    3、新增考核记录
     */

    /**
     * 考核结果列表
     *
     * @return
     */
    @ApiOperation("考核结果列表")
    @GetMapping("/getResultList")
    public Result getResultList(BpoEvaluationResultShow info, Page<BpoEvaluationResultShow> page) {

        return supplierEvaluationService.getResultList(info, page);
    }

    /**
     * 获取考核结果
     *
     * @return
     */
    @ApiOperation("获取考核结果")
    @GetMapping("/getResult")
    public Result getResult(@RequestBody BpoEvaluationResultParam info) {
        return supplierEvaluationService.getResult(info);
    }

    /**
     * 增加考核结果
     *
     * @return
     */
    @ApiOperation("增加考核结果")
    @GetMapping("/addResult")
    @Log(title = "增加考核结果", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result addResult(@RequestBody BpoEvaluationResultParam info) {
        return supplierEvaluationService.addResult(info);
    }

    /**
     * 删除考核结果
     *
     * @return
     */
    @ApiOperation("删除考核结果")
    @GetMapping("/deleteResult")
    @Log(title = "删除考核结果", businessType = BusinessType.DELETE, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result deleteResult(Integer supplierInfoId) {
        return supplierEvaluationService.deleteResult(supplierInfoId);
    }

    /**
     * 修改考核结果
     *
     * @return
     */
    @ApiOperation("修改考核结果")
    @GetMapping("/updateResult")
    @Log(title = "修改考核结果", businessType = BusinessType.UPDATE, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result updateResult(@RequestBody BpoEvaluationResultParam info) {
        return supplierEvaluationService.updateResult(info);
    }

    //--------考核项
    /*
    1、考核规则列表：获取供应商考核规则列表
    2、新增(修改/删除)纬度
    3、新增(修改/删除)分纬度
    4、新增(修改/删除)考核指标
     */

    /**
     * 添加考核项
     *
     * @return
     */
    @ApiOperation("添加考核项")
    @GetMapping("/addEvaluationDetail")
    @Log(title = "添加考核项", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result addEvaluationDetail(@RequestBody BpoEvaluationParam info) {
        return supplierEvaluationService.addEvaluationDetail(info);
    }

    /**
     * 修改考核项
     *
     * @return
     */
    @ApiOperation("修改考核项")
    @GetMapping("/updateEvaluationDetail")
    @Log(title = "添加考核项", businessType = BusinessType.UPDATE, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result updateEvaluationDetail(@RequestBody BpoEvaluationParam info) {
        return supplierEvaluationService.updateEvaluationDetail(info);
    }

    /**
     * 删除考核项
     *
     * @return
     */
    @ApiOperation("删除考核项")
    @GetMapping("/deleteEvaluationDetail")
    @Log(title = "删除考核项", businessType = BusinessType.DELETE, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result deleteEvaluationDetail(Integer id, Integer type) {
        return supplierEvaluationService.deleteEvaluationDetail(id, type);
    }

    /**
     * 获取考核项
     *
     * @return
     */
    @ApiOperation("获取考核项")
    @GetMapping("/getEvaluationDetail")
    public Result<BpoEvaluationShow> getEvaluationDetail(int supplierInfoId) {
        return supplierEvaluationService.getEvaluationDetail(supplierInfoId);
    }

    //------------------考核记录
    /**
     * 添加考核打分记录
     *
     * @return
     */
    @ApiOperation("添加考核记录")
    @PostMapping("/addEvaluationScoringResult")
    @Log(title = "添加考核记录", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result addEvaluationScoringResult(@RequestBody BpoEvaluationParam info) {
        return supplierEvaluationService.addEvaluationScoringResult(info);
    }

    /**
     * 新增考核结果与考核记录（新增考核）
     *
     * @return
     */
    @ApiOperation("添加考核记录(新)")
    @PostMapping("/addEvaluation")
    @Log(title = "添加考核记录", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result addEvaluation(@RequestBody BpoEvalutionAddParam info) {
        return supplierEvaluationService.addEvaluation(info);
    }

    /**
     * 修改考核打分记录
     *
     * @return
     */
    @ApiOperation("添加考核记录")
    @PostMapping("/updateEvaluationScoringResult")
    @Log(title = "修改考核记录", businessType = BusinessType.UPDATE, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result updateEvaluationScoringResult(@RequestBody BpoEvaluationParam info) {
        return supplierEvaluationService.updateEvaluationScoringResult(info);
    }

    /**
     * 获取考核打分记录
     *
     * @return
     */
    @ApiOperation("获取考核记录")
    @GetMapping("/getEvaluationScoringResult")
    public Result getEvaluationScoringResult(Integer resultId) {
        return supplierEvaluationService.getEvaluationScoringResult(resultId);
    }
}
