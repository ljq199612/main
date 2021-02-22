package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.aspectj.lang.annotation.Log;
import com.rz.iot.bpo.framework.aspectj.lang.enums.BusinessType;
import com.rz.iot.bpo.framework.web.controller.BaseController;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.SysWorkModuleRel;
import com.rz.iot.bpo.model.param.SysProOrGrpParam;
import com.rz.iot.bpo.model.param.SysWorkModuleRelParam;
import com.rz.iot.bpo.service.SysWorkModuleRelService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述 : 系统中工作模块/工序/工作小组配置Controller
 * 作者 : Rycony
 * 创建时间 : 2020/6/15 19:25
 *
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@RestController
@RequestMapping("/sysWorkModuleRel")
public class SysWorkModuleRelController extends BaseController {
    @Resource
    private SysWorkModuleRelService sysWorkModuleRelService;
    /**
     * 查询所有工作模块/工序/工作小组
     */
    @RequestMapping("/findAll")
    public Result findAll(){
        startPage();
        List<SysWorkModuleRelParam> list = sysWorkModuleRelService.findAll();
        return getDataTable(list);
    }

    /**
     * 新增工作模块
     * @param sysWorkModuleRel 工序/工作小组
     * @return
     */
    @RequestMapping("/insert")
    @PreAuthorize("@ss.hasPermi('sysWorkModuleRel:insert')")
    @Log(title = "系统新增工作模块",businessType = BusinessType.INSERT)
    public Result insertSysWorkModuleRel(SysWorkModuleRel sysWorkModuleRel){
        return sysWorkModuleRelService.insertSysWorkModuleRel(sysWorkModuleRel);
    }

    /**
     * 更新工作模块
     * @param sysWorkModuleRel 工序/工作小组
     * @return
     */
    @Log(title = "系统更新工作模块",businessType = BusinessType.UPDATE)
    @RequestMapping("/update")
    @PreAuthorize("@ss.hasPermi('sysWorkModuleRel:update')")
    public Result updateSysWorkModuleRel(SysWorkModuleRel sysWorkModuleRel){
        return sysWorkModuleRelService.updateSysWorkModuleRel(sysWorkModuleRel);
    }

    /**
     * 新增工序/工作小组
     * @param sysProOrGrpParam 工序/工作小组参数
     * @return
     */
    @RequestMapping("/insertProOrGrp")
    @Log(title = "系统新增工序/工作小组",businessType = BusinessType.INSERT)
    @PreAuthorize("@ss.hasPermi('sysWorkModuleRel:insertSysProOrGrp')")
    public Result insertSysProOrGrp(@RequestBody SysProOrGrpParam sysProOrGrpParam){
        return sysWorkModuleRelService.insertSysProOrGrp(sysProOrGrpParam);
    }

    /**
     * 更新工序/工作小组
     * @param sysProOrGrpParam 工序/工作小组参数
     * @return
     */
    @Log(title = "系统更新工序/工作小组",businessType = BusinessType.UPDATE)
    @RequestMapping("/updateProOrGrp")
    @PreAuthorize("@ss.hasPermi('sysWorkModuleRel:updateProOrGrp')")
    public Result updateProOrGrp(@RequestBody SysProOrGrpParam sysProOrGrpParam){
        return sysWorkModuleRelService.updateProOrGrp(sysProOrGrpParam);
    }

    @Log(title = "系统删除工序/工作小组",businessType = BusinessType.DELETE)
    @RequestMapping("/deleteProOrGrp")
    @PreAuthorize("@ss.hasPermi('sysWorkModuleRel:deleteProOrGrp')")
    public Result deleteProOrGrp(SysWorkModuleRel sysWorkModuleRel){
        return sysWorkModuleRelService.deleteProOrGrp(sysWorkModuleRel);
    }
}
