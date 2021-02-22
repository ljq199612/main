package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.SysOrg;
import com.rz.iot.bpo.service.SysOrgService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 描述 : 系统组织管理Controller
 * 作者 : Rycony
 * 创建时间 : 2020/6/19 18:32
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@RestController
@RequestMapping("/sysOrg")
public class SysOrgController {
    @Resource
    private SysOrgService sysOrgService;
    @RequestMapping("/findAllType")
    public Result findAllType(){
        return sysOrgService.findAllType();
    }
}
