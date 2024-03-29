package com.rz.iot.bpo.controller;


import com.rz.iot.bpo.framework.aspectj.lang.annotation.Log;
import com.rz.iot.bpo.framework.aspectj.lang.enums.BusinessType;
import com.rz.iot.bpo.framework.aspectj.lang.enums.OperatorType;
import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.BpoSupplierInfo;
import com.rz.iot.bpo.model.BpoTransferStation;
import com.rz.iot.bpo.model.SysCompany;
import com.rz.iot.bpo.model.param.*;
import com.rz.iot.bpo.model.show.*;
import com.rz.iot.bpo.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述 : 项目管理
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Api(tags = "项目管理")
@RequestMapping("/project")
@RestController
public class ProjectController {


    @Resource
    ProjectService projectService;

    /**
     * 获取当前用户所在组织
     *
     */
    @ApiOperation("获取当前用户所在组织")
    @GetMapping("/getUserCompany")
    @PreAuthorize("@ss.hasPermi('schedule:getUserCompany')")
//    @Log(title = "获取甲方列表", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result<SysCompany> getUserCompany() {
        return projectService.getUserCompany();
    }
    /**
     * 项目详情列表
     *
     */
    @ApiOperation("项目详情列表")
    @GetMapping("/getProjectDetailList")
    @PreAuthorize("@ss.hasPermi('schedule:getProjectDetailList')")
//    @Log(title = "获取甲方列表", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result<List<BpoProjectDetailListShow>> getProjectDetailList(Integer transferStationId) {
        return projectService.getProjectDetailList(transferStationId);
    }

    /**
     * 获取项目编号创建项目时
     *
     */
    @ApiOperation("获取项目编号创建项目时")
    @PostMapping("/getProjectSnToCreate")
    @PreAuthorize("@ss.hasPermi('schedule:getProjectSnToCreate')")
//    @Log(title = "获取甲方列表", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result getProjectSnToCreate(@RequestBody BpoProjectSnParam bpoProjectSnParam) {
        return projectService.getProjectSnToCreate(bpoProjectSnParam);
    }

    /**
     * 获取甲方列表
     *
     */
    @ApiOperation("获取甲方列表")
    @GetMapping("/getPartyAList")
    @PreAuthorize("@ss.hasPermi('schedule:getPartyAList')")
//    @Log(title = "获取甲方列表", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result<BpoPartyAListShow> getPartyAList() {
        return projectService.getPartyAList();
    }

    /**
     * 获取甲方列表
     *
     */
    @ApiOperation("获取工序工作模块小组名称编码是否存在")
    @GetMapping("/isNameOrCodeExists")
    @PreAuthorize("@ss.hasPermi('schedule:isNameOrCodeExists')")
//    @Log(title = "获取工序工作模块小组名称编码是否存在", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result<NameOrCodeExistsShow> isNameOrCodeExists(String name, String code) {
        return projectService.isNameOrCodeExists(name, code);
    }

    /**
     * 获取项目关联供应商列表
     *
     */
    @ApiOperation("获取项目关联供应商列表")
    @GetMapping("/getSupplierListRelatedByProjectId")
    @PreAuthorize("@ss.hasPermi('schedule:getSupplierListRelatedByProjectId')")
//    @Log(title = "获取项目关联供应商列表", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result<BpoSupplierListRelatedShow> getSupplierListRelatedByProjectId(int projectId) {
        return projectService.getSupplierListRelatedByProjectId(projectId);
    }

    /**
     * 获取项目未关联供应商列表
     *
     */
    @ApiOperation("获取项目未关联供应商列表")
    @GetMapping("/getSupplierListUnrelated")
    @PreAuthorize("@ss.hasPermi('schedule:getSupplierListUnrelated')")
//    @Log(title = "获取项目关联供应商列表", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result<List<SysCompany>> getSupplierListUnrelated(int projectId) {
        return projectService.getSupplierListUnrelated(projectId);
    }

    /**
     * 获取项目未关联供应商列表
     *
     */
    @ApiOperation("获取中转场通过区id")
    @GetMapping("/getTransferStationByDistrictId")
    @PreAuthorize("@ss.hasPermi('schedule:getTransferStationByDistrictId')")
//    @Log(title = "获取项目关联供应商列表", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result<List<BpoTransferStation>> getTransferStationByDistrictId(String districtId) {
        return projectService.getTransferStationByDistrictId(districtId);
    }

    /**
     * 获取供应商列表
     *
     */
    @ApiOperation("获取供应商列表")
    @GetMapping("/getSupplierList")
    @PreAuthorize("@ss.hasPermi('schedule:getSupplierList')")
//    @Log(title = "获取供应商列表", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result<BpoSupplierShow> getSupplierList() {
        return projectService.getSupplierList();
    }

    /**
     * 获取合同列表
     *
     */
    @ApiOperation("获取合同列表")
    @GetMapping("/getContractList")
    @PreAuthorize("@ss.hasPermi('schedule:getContractList')")
//    @Log(title = "获取合同列表", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result<BpoContractInfoShow> getContractList(int partyAId) {
        return projectService.getContractList(partyAId);
    }

    /**
     * 获取合同列表
     *
     */
    @ApiOperation("获取合同列表通过甲乙方")
    @GetMapping("/getContractListByPartyAAndPartyB")
    @PreAuthorize("@ss.hasPermi('schedule:getContractList')")
//    @Log(title = "获取合同列表", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result<BpoContractInfoShow> getContractListByPartyAAndPartyB(int partyAId, int partyBId) {
        return projectService.getContractListByPartyAAndPartyB(partyAId, partyBId);
    }

    /**
     * 获取缓存数据
     *
     */
    @ApiOperation("获取缓存数据")
    @GetMapping("/getCache")
    @PreAuthorize("@ss.hasPermi('project:getCache')")
//    @Log(title = "获取缓存数据", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result<BpoProjectCache> getCache() {
        return projectService.getCache();
    }

    /**
     * 获取项目详情
     *
     */
    @ApiOperation("获取项目详情")
    @GetMapping("/detail")
//    @PreAuthorize("@ss.hasPermi('project:detail')")
//    @Log(title = "获取项目详情", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result<BpoProjectDetailShow> detail(int id) {
        return projectService.detail(id);
    }

    /**
     * 获取项目详情
     * 项目详情
     * 项目工作模块工作小组工序
     * 项目计价规则
     * 项目供应商
     * 四合一超级接口
     *
     */
    @ApiOperation("获取项目详情全部")
    @GetMapping("/detailAll")
//    @PreAuthorize("@ss.hasPermi('project:detailAll')")
//    @Log(title = "获取项目详情", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result<BpoProjectDetailAllShow> detailAll(int id) {
        return projectService.detailAll(id);
    }

    /**
     * 获取项目配置信息
     *
     */
    @ApiOperation("获取项目配置信息")
    @GetMapping("/getProjectConfig")
    @PreAuthorize("@ss.hasPermi('project:getProjectConfig')")
//    @Log(title = "获取项目配置信息", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result<ProjectConfigShow> getProjectConfig(int id) {
        return projectService.getProjectConfig(id);
    }

    /**
     * 获取项目配置信息列表
     *
     */
    @ApiOperation("获取项目配置信息列表")
    @GetMapping("/getProjectConfigList")
//    @PreAuthorize("@ss.hasPermi('project:getProjectConfigList')")
    public Result getProjectConfigList(int id) {
        List<ProjectConfigListShow> list = projectService.getProjectConfigList(id);
        return Result.success(list);
    }

    /**
     * 获取计价规则
     *
     */
    @ApiOperation("获取计价规则")
    @GetMapping("/getComputationalFeeRule")
    @PreAuthorize("@ss.hasPermi('project:getComputationalFeeRule')")
//    @Log(title = "获取计价规则", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result<ProjectComputationalFeeRuleShow> getComputationalFeeRule(int id) {
        return projectService.getComputationalFeeRule(id);
    }

    /**
     * 录入项目配置信息(存入缓存）
     *
     */
    @ApiOperation("录入项目配置信息")
    @PostMapping("/addProjectConfig")
    @PreAuthorize("@ss.hasPermi('project:addProjectConfig')")
    @Log(title = "录入项目配置信息", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result<ProjectConfigParam> addProjectConfig(@RequestBody ProjectConfigParam configParam) {
        return projectService.addProjectConfig(configParam);
    }

    /**
     * 录入项目供应商(存入缓存）
     *
     */
    @ApiOperation("录入项目供应商")
    @PostMapping("/addProjectSupplier")
    @PreAuthorize("@ss.hasPermi('project:addProjectSupplier')")
    @Log(title = "录入项目供应商", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result addProjectSupplier(@RequestBody ProjectSupplierParam projectSupplierParam) {
        return projectService.addProjectSupplier(projectSupplierParam);
    }

    /**
     * 录入项目基础信息(存入缓存）
     *
     */
    @ApiOperation("录入项目基础信息")
    @PostMapping("/addProjectBasicInformation")
    @PreAuthorize("@ss.hasPermi('project:addProjectBasicInformation')")
    @Log(title = "录入项目基础信息", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result addProjectBasicInformation(@RequestBody ProjectBasicInformationParam projectBasicInformationParam) {
        return projectService.addProjectBasicInformation(projectBasicInformationParam);
    }

    /**
     * 设置计费信息甲方(存入缓存）
     *
     */
    @ApiOperation("设置计费信息甲方")
    @PostMapping("/addFeeRulePartyA")
    @PreAuthorize("@ss.hasPermi('project:addFeeRulePartyA')")
    @Log(title = "设置计费信息甲方", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result addFeeRulePartyA(@RequestBody List<BpoFeeRuleParam> bpoFeeRuleParamList) {
        return projectService.addFeeRulePartyA(bpoFeeRuleParamList);
    }

//    /**
//     * 设置计费信息乙方
//     *
//     * @return
//     */
//    @ApiOperation("设置计费信息乙方")
//    @PostMapping("/addFeeRulePartyB")
//    @PreAuthorize("@ss.hasPermi('project:addFeeRulePartyB')")
//    @Log(title = "设置计费信息乙方", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
//    public Result addFeeRulePartyB(@RequestBody List<BpoFeeRuleParam> bpoFeeRuleParamList) {
//        return projectService.addFeeRulePartyB(bpoFeeRuleParamList);
//    }

    /**
     * 创建项目
     *
     */
    @ApiOperation("创建项目")
    @PostMapping("/createProject")
    @PreAuthorize("@ss.hasPermi('project:createProject')")
    @Log(title = "创建项目", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result createProject() {
        return projectService.createProject();
    }

    /**
     * 修改计价计费信息
     *
     */
    @ApiOperation("修改计价计费信息")
    @PostMapping("/updateProductPrice")
    @PreAuthorize("@ss.hasPermi('project:updateProductPrice')")
    @Log(title = "修改计价计费信息", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result updateProductPrice(@RequestBody List<ProductPriceParam> productPriceParamList) {
        return projectService.updateProductPrice(productPriceParamList);
    }

    /**
     * 修改计价计费信息
     *
     */
    @ApiOperation("修改项目联系人")
    @PostMapping("/updateProjectLink")
    @PreAuthorize("@ss.hasPermi('project:updateProjectLink')")
    @Log(title = "修改计价计费信息", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result updateProjectLink(@RequestBody ProjectBasicInformationParam projectBasicInformationParam) {
        return projectService.updateProjectLink(projectBasicInformationParam);
    }

    /**
     * 修改计价计费信息
     *
     */
    @ApiOperation("修改计价规则")
    @PostMapping("/updateFeeRule")
    @PreAuthorize("@ss.hasPermi('project:updateFeeRule')")
    @Log(title = "修改计价规则", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result updateFeeRule(@RequestBody List<FeeRuleParam> updateProductPriceParamList) {
        return projectService.updateFeeRule(updateProductPriceParamList);
    }

    /**
     * 删除计价计费信息
     *
     */
    @ApiOperation("删除计价计费信息")
    @PostMapping("/deleteProductPrice")
    @PreAuthorize("@ss.hasPermi('project:deleteProductPrice')")
    @Log(title = "删除计价计费信息", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result deleteProductPrice(@RequestBody int[] ids) {
        return projectService.deleteProductPrice(ids);
    }

    /**
     * 删除计价计费信息
     *
     */
    @ApiOperation("删除计价规则")
    @GetMapping("/deleteFeeRule")
    @PreAuthorize("@ss.hasPermi('project:deleteFeeRule')")
    @Log(title = "删除计价规则", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result deleteFeeRule(int id, int projectId) {
        return projectService.deleteFeeRule(id, projectId);
    }

    /**
     * 添加计价计费信息
     *
     */
    @ApiOperation("添加计价计费信息")
    @PostMapping("/addProductPrice")
    @PreAuthorize("@ss.hasPermi('project:addProductPrice')")
    @Log(title = "添加计价计费信息", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result addProductPrice(@RequestBody List<ProductPriceParam> addProductPriceParamList) {
        return projectService.addProductPrice(addProductPriceParamList);
    }

    /**
     * 添加计价计费信息
     *
     */
    @ApiOperation("添加计价规则")
    @PostMapping("/addFeeRule")
    @PreAuthorize("@ss.hasPermi('project:addFeeRule')")
    @Log(title = "添加计价规则", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result addFeeRule(@RequestBody List<FeeRuleParam> addProductPriceParamList) {
        return projectService.addFeeRule(addProductPriceParamList);
    }

    /**
     * 添加供应商
     *
     */
    @ApiOperation("添加供应商")
    @PostMapping("/addSupplier")
    @PreAuthorize("@ss.hasPermi('project:addSupplier')")
    @Log(title = "添加计价规则", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result addSupplier(@RequestBody BpoSupplierInfo supplierInfo) {
        return projectService.addSupplier(supplierInfo);
    }

    /**
     * 修改计价计费信息
     *
     */
    @ApiOperation("修改供应商")
    @PostMapping("/updateSupplier")
    @PreAuthorize("@ss.hasPermi('project:updateSupplier')")
    @Log(title = "修改计价规则", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result updateSupplier(@RequestBody ProjectSupplierParam projectSupplierParam) {
        return projectService.updateSupplier(projectSupplierParam);
    }

    /**
     * 删除计价计费信息
     *
     */
    @ApiOperation("删除供应商")
    @PostMapping("/deleteSupplier")
    @PreAuthorize("@ss.hasPermi('project:deleteSupplier')")
    @Log(title = "删除计价规则", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result deleteSupplier(@RequestBody int[] ids) {
        return projectService.deleteSupplier(ids);
    }

    /**
     * 添加项目配置信息
     *
     */
    @ApiOperation("添加已创建项目配置信息")
    @PostMapping("/addCreatedProjectConfig")
    @PreAuthorize("@ss.hasPermi('project:addCreatedProjectConfig')")
    @Log(title = "添加已创建项目配置信息", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result addCreatedProjectConfig(@RequestBody CreatedProjectConfigParam configParam) {
        return projectService.addCreatedProjectConfig(configParam);
    }

    /**
     * 删除项目配置信息
     *
     */
    @ApiOperation("删除已创建项目配置信息")
    @PostMapping("/deleteCreatedProjectConfig")
    @PreAuthorize("@ss.hasPermi('project:deleteCreatedProjectConfig')")
    @Log(title = "删除已创建项目配置信息", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result deleteCreatedProjectConfig(@RequestBody CreatedProjectConfigParam configParam) {
        return projectService.deleteCreatedProjectConfig(configParam);
    }

    /**
     * 修改项目配置信息
     *
     */
    @ApiOperation("修改已创建项目配置信息")
    @PostMapping("/updateCreatedProjectConfig")
    @PreAuthorize("@ss.hasPermi('project:updateCreatedProjectConfig')")
    @Log(title = "修改已创建项目配置信息", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB)
    public Result updateCreatedProjectConfig(@RequestBody CreatedProjectConfigParam configParam) {
        return projectService.updateCreatedProjectConfig(configParam);
    }

    @ApiOperation("获取项目信息(当前登录用户)")
    @GetMapping("/getAllByLoginUserId")
    public Result getAllByLoginUserId(){
        return projectService.getAllByLoginUserId();
    }

    /**
     *
     * @param param
     * @return
     */
    @ApiOperation("获取项目列表")
    @GetMapping("/list")
    public Result list(BpoProjectListShow param,Page<BpoProjectListShow> page){
        return projectService.getList(param, page);
    }

    /**
     * 删除项目
     */
    @ApiOperation("删除项目")
    @GetMapping("/delProject")
    public Result delProject(Integer projectId){
        return projectService.delProject(projectId);
    }
}
