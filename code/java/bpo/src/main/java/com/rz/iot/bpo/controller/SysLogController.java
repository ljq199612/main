package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.web.controller.BaseController;
import com.rz.iot.bpo.framework.web.entity.Page;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.SysLoginLog;
import com.rz.iot.bpo.model.SysOperLog;
import com.rz.iot.bpo.model.SysSimpleSysInfo;
import com.rz.iot.bpo.model.param.BpoOptLoginLogParam;
import com.rz.iot.bpo.model.show.SysLoginLogShow;
import com.rz.iot.bpo.service.SysLogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

/**
 * 描述 : 日志管理Controller
 * 作者 : Rycony
 * 创建时间 : 2020/6/19 16:13
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@RestController
@RequestMapping("/sysLog")
public class SysLogController extends BaseController {
    @Resource
    private SysLogService sysLogService;

    /**
     * 查看所有操作日志
     * @param sysOperLog
     * @return
     */
    @RequestMapping("/findAllOpt")
    @PreAuthorize("@ss.hasPermi('sysLog:findAllOpt')")
    public Result findAllOpt(BpoOptLoginLogParam sysOperLog){
        startPage();
        List<SysOperLog> list = sysLogService.findAllOpt(sysOperLog);
        return getDataTable(list);
    }

    /**
     * 获取系统用户角色
     * @return
     */
    @RequestMapping("/findUserRole")
    @PreAuthorize("@ss.hasPermi('sysLog:findUserRole')")
    public Result findUserRole(){
        return sysLogService.findUserRole();
    }

    /**
     * 导出excel
     * @return
     */
    @RequestMapping("/exportOpeLog")
    @PreAuthorize("@ss.hasPermi('sysLog:exportOpeLog')")
    public Result exportOpeLog(HttpServletResponse response){
        return sysLogService.exportOpeLog(response);
    }


    /**
     * 查询登录日志
     * @param param
     * @return
     */
    @RequestMapping("/findAllLogin")
    @PreAuthorize("@ss.hasPermi('sysLog:findAllLogin')")
    public Result findAllLogin(BpoOptLoginLogParam param){
        startPage();
        List<SysLoginLogShow> list = sysLogService.findAllLogin(param);;
        return getDataTable(list);
    }

    @RequestMapping("/exportLoginLog")
    @PreAuthorize("@ss.hasPermi('sysLog:exportLoginLog')")
    public Result exportLoginLog(HttpServletResponse response){
        return sysLogService.exportLoginLog(response);
    }

    /**
     * 获取操作日志详情
     * @param sysOperLog
     * @return
     */
    @RequestMapping("/findOptDetail")
    @PreAuthorize("@ss.hasPermi('sysLog:findOptDetail')")
    public Result findOptDetail(SysOperLog sysOperLog){
        return sysLogService.findOptDetail(sysOperLog);
    }


     /** 获取系统日志
     * @param param 开始时间结束时间
     * @return
     */
    @RequestMapping("/findSysInfo")
    @PreAuthorize("@ss.hasPermi('sysLog:findSysInfo')")
    public Result findSysInfo(BpoOptLoginLogParam param){

        /**
         * 为空时传一个当前时间
         */
        if(param == null || param.getStartDate() == null || param.getStartDate().equals("")){
            // 昨天
            LocalDate localDate = LocalDate.now().plusDays(-1);
            param.setStartDate(localDate.toString());
        }

        return sysLogService.findSysInfo(param);
    }
}
