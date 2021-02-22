package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.aspectj.lang.annotation.Log;
import com.rz.iot.bpo.framework.aspectj.lang.enums.BusinessType;
import com.rz.iot.bpo.framework.web.controller.BaseController;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.SysOrgRoleMenu;
import com.rz.iot.bpo.model.param.SysPerParam;
import com.rz.iot.bpo.model.param.SysRolePerParam;
import com.rz.iot.bpo.model.show.SysDataPerShow;
import com.rz.iot.bpo.model.show.SysPerMenuShow;
import com.rz.iot.bpo.model.show.SysPerShow;
import com.rz.iot.bpo.service.SysPerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述 : 系统权限控制器
 * 作者 : Rycony
 * 创建时间 : 2020/6/22 11:21
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@RestController
@RequestMapping("/sysPer")
public class SysPerController extends BaseController {
    @Resource
    private SysPerService sysPerService;

    /**
     * 查询所有人员角色类型权限类型-分页
     * @param sysPerParam 查询 参数
     * @return
     */
    @PostMapping("/findAll")
    @PreAuthorize("@ss.hasPermi('sysPer:findAll')")
    public Result findAll(SysPerParam sysPerParam){
        startPage();
        List<SysPerShow> list = sysPerService.findAllUserRolePer(sysPerParam);
        return getDataTable(list);
    }

    @RequestMapping("/findMenuAll")
    public Result findMenuAll(SysPerMenuShow sysPerMenuShow){
        startPage();
        List<SysPerMenuShow> list = sysPerService.findMenuAll(sysPerMenuShow);
        return getDataTable(list);
    }

    /**
     * 查询菜单权限中,组织类型未关联的角色
     */
    @RequestMapping("/findUnRelRole")
    public Result findUnRelRoleByOrgKey(String orgKey){
        return sysPerService.findUnRelRoleByOrgKey(orgKey);
    }


    /**
     * 新增菜单权限
     * @return
     */
    @PostMapping("/insertMenuPer")
    @Log(title = "新增菜单权限",businessType = BusinessType.INSERT)
    @PreAuthorize("@ss.hasPermi('sysPer:insertMenuPer')")
    public Result insertMenuPer(@RequestBody SysRolePerParam sysRolePerParam){
        return sysPerService.insertMenuPer(sysRolePerParam);
    }

    /**
     * 删除菜单权限
     */
    @PostMapping("/deleteMenuPer")
    @Log(title = "删除菜单权限",businessType = BusinessType.DELETE)
    @PreAuthorize("@ss.hasPermi('sysPer:deleteMenuPer')")
    public Result deleteMenuPer(@RequestBody SysRolePerParam sysRolePerParam){
        return sysPerService.deleteMenuPer(sysRolePerParam);
    }

    /**
     * 查询菜单详情
     */
    @RequestMapping("/menuDetail")
    @PreAuthorize("@ss.hasPermi('sysPer:menuDetail')")
    public Result menuDetail(SysPerParam sysPerParam) {
        return sysPerService.menuDetail(sysPerParam);
    }


    /**
     * 新增数据权限
     * @param sysDataPerShow
     * @return
     */
    @RequestMapping("/insertDataPer")
    @Log(title = "新增数据权限",businessType = BusinessType.INSERT)
    @PreAuthorize("@ss.hasPermi('sysPer:insertDataPer')")
    public Result insertDataPer(@RequestBody List<SysDataPerShow> sysDataPerShow){
        return sysPerService.insertDataPer(sysDataPerShow);
    }

    /**
     * 删除数据权限
     * @param sysDataPerShow
     * @return
     */
    @RequestMapping("/deleteDataPer")
    @Log(title = "删除数据权限",businessType = BusinessType.DELETE)
    @PreAuthorize("@ss.hasPermi('sysPer:deleteDataPer')")
    public Result deleteDataPer(@RequestBody List<SysDataPerShow> sysDataPerShow){
        return sysPerService.deleteDataPer(sysDataPerShow);
    }

    /**
     * 通过获取用户id,用户名,组织类型key,角色id
     * @param sysPerParam
     * @return
     */
    @RequestMapping("/dataDetail")
    @PreAuthorize("@ss.hasPermi('sysPer:dataDetail')")
    public Result dataDetail(@RequestBody SysPerParam sysPerParam){
        return sysPerService.dataDetail(sysPerParam);
    }
}
