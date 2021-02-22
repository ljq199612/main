package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.aspectj.lang.annotation.Log;
import com.rz.iot.bpo.framework.aspectj.lang.enums.BusinessType;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.BpoPersonContractInfo;
import com.rz.iot.bpo.model.show.BpoPersonContractInfoShow;
import com.rz.iot.bpo.service.BpoPersonContractService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/personContract")
public class BpoPersonContractInfoController {

    @Resource
    private BpoPersonContractService personContractService;


    /**
     * 新增企业对人员合同
     * @param personContractInfo 合同
     * @return
     */
    @PostMapping("/insert")
    @Log(title = "新增企业对人员合同",businessType = BusinessType.INSERT)
    public Result insert(@RequestBody BpoPersonContractInfo personContractInfo){
        personContractService.insert(personContractInfo);
        return Result.success();
    }

    /**
     * 合同详情
     * @param jiaId 甲方id  company
     * @param yiId 乙方id   person
     * @return
     */
    @GetMapping("/detail")
    public Result detail(Integer jiaId,Integer yiId){
        BpoPersonContractInfoShow personContractInfoShow = personContractService.getDetail(jiaId, yiId);
        return Result.success(personContractInfoShow);
    }


    /**
     * 合同详情
     * @param personContractInfo 合同
     * @return
     */
    @GetMapping("/update")
    public Result update(@RequestBody BpoPersonContractInfo personContractInfo){
        personContractService.update(personContractInfo);
        return Result.success();
    }
}
