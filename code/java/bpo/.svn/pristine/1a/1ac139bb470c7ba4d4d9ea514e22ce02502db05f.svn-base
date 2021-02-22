package com.rz.iot.bpo.controller;

import com.ha.facecamera.configserver.pojo.AppConfig;
import com.rz.iot.bpo.service.BpoAttendanceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 描述 : 考勤测试Controller
 * 作者 : qin xian
 * 创建时间 : 2020/6/26 0026 17:54
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@RestController
@RequestMapping("/attendance")
public class BpoAttendanceController {

    @Resource
    private BpoAttendanceService bpoAttendanceService;

    @GetMapping("/startDeviceListener")
    public boolean startDeviceListener(){
        return bpoAttendanceService.startDeviceListener();
    }

    @GetMapping("/stopDeviceListener")
    public void stopDeviceListener(){
        bpoAttendanceService.stopDeviceListener();
    }

    @GetMapping("/startDateService")
    public boolean startDateService(){
        return bpoAttendanceService.startDateService();
    }

    @GetMapping("/stopDateService")
    public void stopDateService(){
        bpoAttendanceService.stopDateService();
    }

    @GetMapping("/getAppConfigByBySn")
    public AppConfig getAppConfigByBySn(){
        return bpoAttendanceService.getAppConfigByBySn("012316-591CE1-7ECEEE");
    }
}