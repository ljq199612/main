package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.aspectj.lang.annotation.Log;
import com.rz.iot.bpo.framework.aspectj.lang.enums.BusinessType;
import com.rz.iot.bpo.framework.web.controller.BaseController;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.SysDictType;
import com.rz.iot.bpo.service.SysDictTypeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 系统字典Contoller
 * @Author Rycony
 * @Date 2020/6/15 19:48
 *
 */
@RestController
@RequestMapping("/sysDictType")
public class SysDictTypeController extends BaseController {
    @Resource
    private SysDictTypeService sysDictTypeService;


    @RequestMapping("/insert")
    @Log(title = "系统字典类型新增",businessType = BusinessType.INSERT)
    @PreAuthorize("@ss.hasPermi('sysDictType:insert')")
    public Result insertSysDictType(SysDictType sysDictType){
        return sysDictTypeService.insertSysDictType(sysDictType);
    }
    @RequestMapping("/findAll")
    public Result findAll(SysDictType sysDictType){
        startPage();
        List<SysDictType> sysDictTypePage = sysDictTypeService.findAll(sysDictType);
        return getDataTable(sysDictTypePage);
    }
    @RequestMapping("/findAllNoPage")
    public Result findAllNoPage(){
        List<SysDictType> list = sysDictTypeService.findAllNoPage();
        return Result.success(list);
    }

    @Log(title = "系统字典类型更新",businessType = BusinessType.UPDATE)
    @RequestMapping("/update")
    @PreAuthorize("@ss.hasPermi('sysDictType:update')")
    public Result update(SysDictType sysDictType){
        return sysDictTypeService.update(sysDictType);
    }

    /**
     * 更改系统状态
     * @param sysDictType
     * @return
     */
    @Log(title = "系统字典类型更新状态",businessType = BusinessType.UPDATE)
    @RequestMapping("/updateStatus")
    @PreAuthorize("@ss.hasPermi('sysDictType:updateStatus')")
    public Result updateStatus(SysDictType sysDictType){
        return sysDictTypeService.updateStatus(sysDictType);
    }

    /**
     * 通过字典类型查询所有数据
     * @param type 类型
     * @return
     */
    @RequestMapping("/findDataByType")
    @PreAuthorize("@ss.hasPermi('sysDictType:findDataByType')")
    public Result findDataByType(String type){
        return sysDictTypeService.findDataByType(type);
    }
}
