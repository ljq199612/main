package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.aspectj.lang.annotation.Log;
import com.rz.iot.bpo.framework.aspectj.lang.enums.BusinessType;
import com.rz.iot.bpo.framework.aspectj.lang.enums.OperatorType;
import com.rz.iot.bpo.framework.web.controller.BaseController;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.BpoEvaluationResultRecord;
import com.rz.iot.bpo.model.param.BpoEvaluationParam;
import com.rz.iot.bpo.model.param.BpoPerformanceEvaluationParam;
import com.rz.iot.bpo.model.param.BpoPerformanceLevelParam;
import com.rz.iot.bpo.model.param.EvaluationResultRecordParam;
import com.rz.iot.bpo.model.show.*;
import com.rz.iot.bpo.service.EvaluationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述 : 项目考核
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Api(tags = "项目考核")
@RequestMapping("/evaluation")
@RestController
public class PerformanceEvaluationController extends BaseController {
    @Resource
    EvaluationService evaluationService;

    /**
     * 获取项目考核标准
     * bpo_performance_evaluation
     *
     * @return
     */
    @ApiOperation("获取项目考核标准")
    @GetMapping("/getDetail")
    @PreAuthorize("@ss.hasPermi('evaluation:getDetail')")
//    @Log(title = "获取项目考核标准", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result<BpoPerformanceEvaluationShow> getDetail(int projectId) {
        return evaluationService.getDetail(projectId);
    }

    /**
     * 获取项目考核条目
     * bpo_evaluation_item A
     * bpo_evaluation_content B
     * bpo_evaluation_standard C
     * 1A -> NB
     * 1B -> NC
     * 1 -> N 一对多
     *
     * @return
     */
    @ApiOperation("获取项目考核条目")
    @GetMapping("/getEvaluationDetail")
    @PreAuthorize("@ss.hasPermi('evaluation:getEvaluationDetail')")
//    @Log(title = "获取项目考核标准", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result<BpoEvaluationShow> getEvaluationDetail(int projectId) {
        startPage();
        return evaluationService.getEvaluationDetail(projectId);
    }

    /**
     * 获取项目考核条目结果记录
     * bpo_evaluation_result_record
     *
     * @return
     */
    @ApiOperation("获取项目考核条目结果记录")
    @PostMapping("/getEvaluationDetailResultRecord")
    @PreAuthorize("@ss.hasPermi('evaluation:getEvaluationDetailResultRecord')")
//    @Log(title = "获取项目考核标准", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result<BpoEvaluationResultRecordShow> getEvaluationDetailResultRecord(@RequestBody EvaluationResultRecordParam param) {
        return evaluationService.getEvaluationDetailResultRecord(param);
    }

    /**
     * 获取当前用户所属乙方的所有项目
     *
     * @return
     */
    @ApiOperation("获取当前用户所属乙方的所有项目")
    @GetMapping("/getCurrentPartyBAllProject")
    @PreAuthorize("@ss.hasPermi('evaluation:getCurrentPartyBAllProject')")
//    @Log(title = "获取当前用户所属乙方的所有项目", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result<List<BpoProjectEvaluationShow>> getCurrentPartyBAllProject() {
        return evaluationService.getCurrentPartyBAllProject();
    }

    /**
     * 获取项目考核结果
     * bpo_evaluation_result
     *
     * @return
     */
    @ApiOperation("获取项目考核结果")
    @GetMapping("/getResult")
    @PreAuthorize("@ss.hasPermi('evaluation:getResult')")
//    @Log(title = "获取项目考核结果", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result<BpoEvaluationResultShow> getResult(int projectId) {
        return evaluationService.getResult(projectId);
    }

    /**
     * 获取项目考核结果getProjectResultList
     * bpo_evaluation_result
     *
     * @return
     */
    @ApiOperation("获取项目考核结果列表")
    @GetMapping("/getResultList")

    public Result<BpoEvaluationResultShow> getResultList(BpoEvaluationResultShow info, Page<BpoEvaluationResultShow>page) {
        return evaluationService.getResultList(info,page);
    }


    /**
     * 获取项目考核标准等级
     * bpo_performance_level
     * @return
     */
    @ApiOperation("获取项目考核标准等级")
    @GetMapping("/getLevel")
    @PreAuthorize("@ss.hasPermi('evaluation:getLevel')")
//    @Log(title = "获取项目考核标准", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result<BpoPerformanceLevelShow> getLevel(int projectId) {
        return evaluationService.getLevel(projectId);
    }

    /**
     * 增加项目考核标准等级
     * bpo_performance_level
     * @return
     */
    @ApiOperation("增加项目考核标准等级")
    @PostMapping("/addLevel")
    @PreAuthorize("@ss.hasPermi('evaluation:addLevel')")
    @Log(title = "获取项目考核标准", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result addLevel(@RequestBody List<BpoPerformanceLevelParam> bpoPerformanceLevelParamList) {
        return evaluationService.addLevel(bpoPerformanceLevelParamList);
    }

    /**
     * 添加项目考核条目
     * bpo_evaluation_item A
     * bpo_evaluation_content B
     * bpo_evaluation_standard C
     * 1A -> NB
     * 1B -> NC
     * 1 -> N 一对多
     * @return
     */
    @ApiOperation("添加项目考核条目")
    @PostMapping("/addEvaluationDetail")
    @PreAuthorize("@ss.hasPermi('evaluation:addEvaluationDetail')")
    @Log(title = "添加项目考核条目", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result addEvaluationDetail(@RequestBody BpoEvaluationParam bpoEvaluationParam) {
        return evaluationService.addEvaluationDetail(bpoEvaluationParam);
    }

    /**
     * 添加项目考核条目
     * bpo_evaluation_result_record
     * @return
     */
    @ApiOperation("添加项目考核条目评分结果")
    @PostMapping("/addEvaluationScoringResult")
    @PreAuthorize("@ss.hasPermi('evaluation:addEvaluationScoringResult')")
    @Log(title = "添加项目考核条目评分结果", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result addEvaluationScoringResult(@RequestBody BpoEvaluationParam bpoEvaluationParam) {
        return evaluationService.addEvaluationScoringResult(bpoEvaluationParam);
    }

    /**
     * 增加考核结果
     * bpo_evaluation_result
     * @return
     */
    @ApiOperation("增加考核结果")
    @PostMapping("/addResult")
    @PreAuthorize("@ss.hasPermi('evaluation:addResult')")
    @Log(title = "增加考核结果", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result addResult(@RequestBody BpoEvaluationResultParam bpoEvaluationResultParam) {
        return evaluationService.addResult(bpoEvaluationResultParam);
    }

    /**
     * 修改项目考核条目
     * bpo_evaluation_item A
     * bpo_evaluation_content B
     * bpo_evaluation_standard C
     * 1A -> NB
     * 1B -> NC
     * 1 -> N 一对多
     * @return
     */
    @ApiOperation("修改项目考核条目")
    @PostMapping("/updateEvaluationDetail")
    @PreAuthorize("@ss.hasPermi('evaluation:updateEvaluationDetail')")
    @Log(title = "修改项目考核条目", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result updateEvaluationDetail(@RequestBody BpoEvaluationParam bpoEvaluationParam) {
        return evaluationService.updateEvaluationDetail(bpoEvaluationParam);
    }

    /**
     * 修改项目考核条目评分结果
     * bpo_evaluation_result_record
     * @return
     */
    @ApiOperation("修改项目考核条目评分结果")
    @PostMapping("/updateEvaluationScoringResult")
    @PreAuthorize("@ss.hasPermi('evaluation:updateEvaluationScoringResult')")
    @Log(title = "修改项目考核条目评分结果", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result updateEvaluationScoringResult(@RequestBody BpoEvaluationParam bpoEvaluationParam) {
        return evaluationService.updateEvaluationScoringResult(bpoEvaluationParam);
    }

    /**
     * 修改项目考核条目结果记录分数
     * bpo_evaluation_result_record
     * 只修改分数
     * @return
     */
    @ApiOperation("修改项目考核条目结果记录分数")
    @PostMapping("/updateEvaluationResultRecordScore")
    @PreAuthorize("@ss.hasPermi('evaluation:updateEvaluationResultRecordScore')")
    @Log(title = "修改项目考核条目结果记录分数", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result updateEvaluationResultRecordScore(@RequestBody BpoEvaluationResultRecord bpoEvaluationResultRecord) {
        return evaluationService.updateEvaluationResultRecordScore(bpoEvaluationResultRecord);
    }

    /**
     * 增加绩效考核标准
     *
     * @return
     */
    @ApiOperation("增加绩效考核标准")
    @PostMapping("/addEvaluationStandards")
    @PreAuthorize("@ss.hasPermi('evaluation:addEvaluationStandards')")
    @Log(title = "增加绩效考核标准", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result addEvaluationStandards(@RequestBody BpoPerformanceEvaluationParam bpoPerformanceEvaluationParam) {
        return evaluationService.addEvaluationStandards(bpoPerformanceEvaluationParam);
    }

    /**
     * 修改项目考核标准等级
     *
     * @return
     */
    @ApiOperation("修改项目考核标准等级")
    @PostMapping("/updateLevel")
    @PreAuthorize("@ss.hasPermi('evaluation:updateLevel')")
    @Log(title = "获取项目考核标准", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result updateLevel(@RequestBody BpoPerformanceLevelParam bpoPerformanceLevelParam) {
        return evaluationService.updateLevel(bpoPerformanceLevelParam);
    }

    /**
     * 修改考核结果
     *
     * @return
     */
    @ApiOperation("修改考核结果")
    @PostMapping("/updateResult")
    @PreAuthorize("@ss.hasPermi('evaluation:updateResult')")
    @Log(title = "修改考核结果", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result updateResult(@RequestBody BpoEvaluationResultParam bpoEvaluationResultParam) {
        return evaluationService.updateResult(bpoEvaluationResultParam);
    }

    /**
     * 修改绩效考核标准
     *
     * @return
     */
    @ApiOperation("修改绩效考核标准")
    @PostMapping("/updateEvaluationStandards")
    @PreAuthorize("@ss.hasPermi('evaluation:updateEvaluationStandards')")
    @Log(title = "修改绩效考核标准", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result updateEvaluationStandards(@RequestBody BpoPerformanceEvaluationParam bpoPerformanceEvaluationParam) {
        return evaluationService.updateEvaluationStandards(bpoPerformanceEvaluationParam);
    }

    /**
     * 删除项目考核条目
     *
     * @return
     */
    @ApiOperation("删除项目考核条目")
    @GetMapping("/deleteEvaluationDetail")
    @PreAuthorize("@ss.hasPermi('evaluation:deleteEvaluationDetail')")
    @Log(title = "删除项目考核条目", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result deleteEvaluationDetail(int id, int type) {
        return evaluationService.deleteEvaluationDetail(id, type);
    }

    /**
     * 删除项目考核标准等级
     *
     * @return
     */
    @ApiOperation("删除项目考核标准等级")
    @GetMapping("/deleteLevel")
    @PreAuthorize("@ss.hasPermi('evaluation:deleteLevel')")
    @Log(title = "获取项目考核标准", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result deleteLevel(int id) {
        return evaluationService.deleteLevel(id);
    }

    /**
     * 删除考核结果
     *
     * @return
     */
    @ApiOperation("删除考核结果")
    @GetMapping("/deleteResult")
    @PreAuthorize("@ss.hasPermi('evaluation:deleteResult')")
    @Log(title = "删除考核结果", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result deleteResult(int id) {
        return evaluationService.deleteResult(id);
    }

    /**
     * 删除绩效考核标准
     *
     * @return
     */
    @ApiOperation("删除绩效考核标准")
    @GetMapping("/deleteEvaluationStandards")
    @PreAuthorize("@ss.hasPermi('evaluation:deleteEvaluationStandards')")
    @Log(title = "删除绩效考核标准", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result deleteEvaluationStandards(int id) {
        return evaluationService.deleteEvaluationStandards(id);
    }
}
