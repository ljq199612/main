package com.rz.iot.bpo.controller;

import com.github.pagehelper.PageInfo;
import com.rz.iot.bpo.framework.aspectj.lang.annotation.Log;
import com.rz.iot.bpo.framework.aspectj.lang.enums.BusinessType;
import com.rz.iot.bpo.framework.aspectj.lang.enums.OperatorType;
import com.rz.iot.bpo.framework.web.controller.BaseController;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.*;
import com.rz.iot.bpo.model.param.BpoContractInfoParam;
import com.rz.iot.bpo.model.param.ContractInfoParam;
import com.rz.iot.bpo.model.show.BpoContractDetailShow;
import com.rz.iot.bpo.model.show.BpoContractQueryShow;
import com.rz.iot.bpo.service.ContractInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contractInfo")
public class ContractInfoController extends BaseController {

    @Resource
    private ContractInfoService contractInfoService;

    /**
     * 查询合同-分页
     * @param contractInfoParam 合同
     * @return
     */
    @GetMapping("/findAll")
    public Result findAll(ContractInfoParam contractInfoParam) {
        contractInfoService.buildQueryParams(contractInfoParam);
        startPage();
        List<BpoContractQueryShow> list = contractInfoService.findAll(contractInfoParam);
        Result result = getDataTable(list);
        PageInfo pageInfo = (PageInfo)result.getData();
        List<BpoContractQueryShow> totalList = contractInfoService.findAll(contractInfoParam);
        pageInfo.setTotal(totalList.size());
        return result;
    }

    /**
     * 新增合同
     * @param bpoContractInfoParam 合同
     * @return
     */
    @PostMapping("/insert")
    @Log(title = "新增合同",businessType = BusinessType.INSERT)
    public Result insert(@RequestBody BpoContractInfoParam bpoContractInfoParam){
        return contractInfoService.insert(bpoContractInfoParam);
    }

    /**
     * 合同详情
     * @param id 合同id
     * @return
     */
    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id){
        BpoContractDetailShow contractDetailShow = contractInfoService.selectDetailById(id);
        return Result.success(contractDetailShow);
    }

    /**
     * 修改合同
     *
     * @return
     */
    @ApiOperation("修改合同")
    @PostMapping("/update")
    @Log(title = "修改合同", businessType = BusinessType.UPDATE)
    public Result update(@RequestBody BpoContractDetailShow contractDetailShow) {
        contractInfoService.update(contractDetailShow);
        return Result.success();
    }

    /**
     * 删除合同付款信息
     */
    @ApiOperation("删除合同付款信息")
    @PostMapping("/deletePaymentInfo")
    @Log(title = "删除合同付款信息", businessType = BusinessType.DELETE)
    public Result deletePaymentInfo(Integer id) {
        contractInfoService.deletePaymentInfo(id);
        return Result.success();
    }

    /**
     * 删除合同
     */
    @ApiOperation("删除合同")
    @PostMapping("/delete")
    @Log(title = "删除合同", businessType = BusinessType.DELETE)
    public Result delete(Integer id) {
        contractInfoService.delete(id);
        return Result.success();
    }

    /**
     * 根据供应商获取所有企业合同
     */
    @ApiOperation("供应商合同")
    @GetMapping("/getBySupplierOrgId")
    public Result getBySupplierId(Integer supplierOrgId) {
        List<Map<String,Object>> contractInfoList = contractInfoService.getBySupplierOrgId(supplierOrgId);
        return Result.success(contractInfoList);
    }

    /**
     * 上传合同文件
     * 需求:
     *      1.将文件存储至指定文件服务器
     *      2.修改文件名称保证文件的唯一性
     *      3.返回文件的url值
     * @param file 文件
     * @return
     */
    @PostMapping("/upload")
    @Log(title = "上传合同附件", businessType = BusinessType.INSERT)
    public Result upload(MultipartFile file) {
        return contractInfoService.upload(file);
    }

    /**
     * 下载合同附件
     * @param id 合同id
     *
     * @return
     */
    @RequestMapping("/downloadFiles")
    @Log(title = "下载合同附件", businessType = BusinessType.EXPORT)
    public void downloadFile(Integer id, HttpServletRequest request,HttpServletResponse response) throws IOException {
        BpoContractInfo contractInfo = contractInfoService.selectByContractId(id);
        if (contractInfo == null || contractInfo.getFilePath() == null) {
            Result.error(null,"该合同下不存在附件");
        }
        String path = contractInfo.getFilePath();
        contractInfoService.download(path,contractInfo.getName(),request,response);
    }

    /**
     * 根据供应商信息表Id 获取供应商与项目关联的合同(精准)
     * @return
     */
    @RequestMapping("/getbySupplierInfoId")
    public Result<BpoContractInfo> getbySupplierInfoId(Integer supplierInfoId){
        return contractInfoService.getbySupplierInfoId(supplierInfoId);
    }
}
