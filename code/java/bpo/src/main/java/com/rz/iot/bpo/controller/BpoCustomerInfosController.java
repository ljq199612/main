package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.aspectj.lang.annotation.Log;
import com.rz.iot.bpo.framework.aspectj.lang.enums.BusinessType;
import com.rz.iot.bpo.framework.aspectj.lang.enums.OperatorType;
import com.rz.iot.bpo.framework.web.domain.BaseEntity;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.*;
import com.rz.iot.bpo.model.BpoCustomerInfos;
import com.rz.iot.bpo.service.BpoCustomerInfosService;
import com.rz.iot.bpo.service.ContractInfoService;
import com.rz.iot.bpo.service.SysDeptService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 客户管理
 */
@RestController
@RequestMapping("customer")
public class BpoCustomerInfosController {

    @Autowired
    private BpoCustomerInfosService customerInfosService;

    @Resource
    private ContractInfoService contractInfoService;

    @Autowired
    private SysDeptService sysDeptService;


    /**
     * 获取单条客户信息
     * @param id 公司id
     * @param projectId 项目id
     * @return
     */
    @GetMapping("getCustomerById")
    public Result<BpoCustomerInfos> getCustomerById(Integer projectId,Integer id){
        BpoCustomerInfos infos = new BpoCustomerInfos ();
        infos.setOrgId (id);
        infos.setProject (new BpoProject ());
        infos.getProject ().setId (projectId);
        return customerInfosService.getDetail (infos);
    }

    /**
     * 新增客户信息
     * @param bpoCustomerInfos
     * @return
     */
    @PostMapping("addCustomer")
//    @PreAuthorize("@ss.hasPermi('Customer:addCustomer')")
    @Log(title = "新增客户信息", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result addCustomer(@RequestBody BpoCustomerInfos bpoCustomerInfos){
        return customerInfosService.insertCustomer (bpoCustomerInfos);
    }

    /**
     * 更新客户信息
     * @param BpoCustomerInfos
     * @return
     */
    @PostMapping("updateCustomer")
    @ApiOperation("更新客户信息")
    @Log(title = "更新客户信息", businessType = BusinessType.UPDATE, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result updateCustomer(@RequestBody BpoCustomerInfos BpoCustomerInfos){
        return customerInfosService.updateCustomer (BpoCustomerInfos);
    }

    /**
     * 删除客户信息
     * @param id
     * @return
     */
    @ApiOperation("删除客户信息")
    @GetMapping("deleteCustomer")
    @Log(title = "删除客户信息", businessType = BusinessType.DELETE, operatorType = OperatorType.WEB, isSaveRequestData = true)
    public Result deleteCustomer(Integer id){
        return customerInfosService.deleteCustomer (id);
    }

    /**
     * 条件查询
     * @param infos
     * @param page
     * @return
     */
    @ApiOperation("条件查询")
    @GetMapping("selectCustomerByCondition")
    public Result selectCustomerByCondition(BpoCustomerInfos infos,Page<BpoCustomerInfos> page){
        return customerInfosService.selectCustomergByCondition (new BaseEntity (),infos,page);
    }

    /**
     * 通过查询到的url，以及合同名下载对应合同文件
     * @param id
     * @param request
     * @param response
     */
    @GetMapping("download")
    public void download(Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        BpoContractInfo contractInfo = contractInfoService.selectByContractId(id);
        if (contractInfo == null || contractInfo.getFilePath() == null) {
            Result.error(null,"该合同下不存在附件");
        }
        String path = contractInfo.getFilePath();
        contractInfoService.download(path,contractInfo.getName(),request,response);
    }

    /**
     * 查询当前所属的组织架构以及下级
     * @return
     */
    @GetMapping("findCanControl")
    public Result<List<SysDept>> findCanControl(){
        return sysDeptService.findCanControl (new BaseEntity ());
    }

    /**
     * 获取所有客户类型
     * @return
     */
    @GetMapping("findType")
    public Result<List<SysDictData>> findType(){return sysDeptService.findType ();}

    /**
     * 获取当前登录用户所属部门及以下 所有的客户信息
     */
    @GetMapping("/findAllByLoginUserId")
    public Result findAllByLoginUserId(BaseEntity param){
        return customerInfosService.findAllByLoginUserId(param);
    }

    /**
     * 获取当前登录用户所属部门 的客户信息
     */
    @GetMapping("/findAllInDepByLoginUserId")
    public Result findAllInDepByLoginUserId(BaseEntity param){
        return customerInfosService.findAllInDepByLoginUserId(param);
    }
}
