package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.model.param.LoginParam;
import com.rz.iot.bpo.model.show.CreateCaptchaShow;
import com.rz.iot.bpo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Author by xuxiake, Date on 2020/6/8 11:12.
 * PS: Not easy to write code, please indicate.
 * Description：
 */
@Api(tags = "登录相关")
@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 获取验证码
     * @return
     */
    @ApiOperation("获取验证码")
    @GetMapping("/createCaptcha")
    public Result<CreateCaptchaShow> createCaptcha() {
        return userService.createCaptcha();
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<Map> login(@RequestBody LoginParam param) {
        return userService.login(param);
    }

    @ApiOperation("更新token")
    @GetMapping("/refreshToken")
    public Result refreshToken() {
        return userService.refreshToken();
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/info")
    public Result info() {
        return userService.info();
    }
}
