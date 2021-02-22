package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.aspectj.lang.annotation.Log;
import com.rz.iot.bpo.framework.aspectj.lang.enums.BusinessType;
import com.rz.iot.bpo.framework.aspectj.lang.enums.OperatorType;
import com.rz.iot.bpo.framework.web.entity.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author by xuxiake, Date on 2020/6/17 16:16.
 * PS: Not easy to write code, please indicate.
 * Description：
 */
@RestController
@RequestMapping("/userManager")
public class UserManagerController {

    @PreAuthorize("@ss.hasPermi('userManager:addUser')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT, operatorType = OperatorType.WEB, isSaveRequestData = false)
    @RequestMapping("/addUser")
    public Result addUser() {
        return Result.success();
    }
}
