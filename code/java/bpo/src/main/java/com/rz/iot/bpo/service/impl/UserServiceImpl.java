package com.rz.iot.bpo.service.impl;

import com.rz.iot.bpo.common.constant.Constants;
import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.common.utils.CaptchaUtil;
import com.rz.iot.bpo.common.utils.IdUtils;
import com.rz.iot.bpo.common.utils.SecurityUtils;
import com.rz.iot.bpo.common.utils.ServletUtils;
import com.rz.iot.bpo.common.utils.ip.IpUtils;
import com.rz.iot.bpo.common.utils.sign.Base64;
import com.rz.iot.bpo.framework.manager.AsyncManager;
import com.rz.iot.bpo.framework.manager.factory.AsyncFactory;
import com.rz.iot.bpo.framework.redis.RedisCache;
import com.rz.iot.bpo.framework.security.LoginUser;
import com.rz.iot.bpo.framework.security.service.TokenService;
import com.rz.iot.bpo.framework.web.entity.Result;
import com.rz.iot.bpo.mapper.SysMenuMapper;
import com.rz.iot.bpo.mapper.SysUserMapper;
import com.rz.iot.bpo.model.SysMenu;
import com.rz.iot.bpo.model.SysUser;
import com.rz.iot.bpo.model.param.LoginParam;
import com.rz.iot.bpo.model.show.CreateCaptchaShow;
import com.rz.iot.bpo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Author by xuxiake, Date on 2020/6/8 11:49.
 * PS: Not easy to write code, please indicate.
 * Description：
 */
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private RedisCache redisCache;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private TokenService tokenService;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysMenuMapper sysMenuMapper;

    /**
     * 获取验证码
     * @return
     */
    @Override
    public Result createCaptcha() {

        // 生成随机字串
        Object[] arr = CaptchaUtil.createImage();
        BufferedImage image = (BufferedImage) arr[0];
        String code = (String) arr[1];
        // 唯一标识
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);

        try(ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", stream);

            CreateCaptchaShow createCaptchaShow = new CreateCaptchaShow();
            createCaptchaShow.setCaptcha("data:image/jpg;base64," + Base64.encode(stream.toByteArray()));
            createCaptchaShow.setUuid(uuid);
            return Result.success(createCaptchaShow);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return Result.error();
    }

    @Override
    public Result login(LoginParam param) {

        if (StringUtils.isAnyEmpty(
                param.getCaptcha(),
                param.getPassword(),
                param.getUsername(),
                param.getUuid()
        )) {
            return Result.requestParamError();
        }

        String verifyKey = Constants.CAPTCHA_CODE_KEY + param.getUuid();
        String captchaFromRedis = redisCache.getCacheObject(verifyKey);
        if (StringUtils.isAnyEmpty(captchaFromRedis)) {
            return Result.error(ResultConstants.LOGIN_CAPTCHA_ERROR);
        }
        redisCache.deleteObject(verifyKey);
        if (!param.getCaptcha().equalsIgnoreCase(captchaFromRedis)) {

            return Result.error(ResultConstants.LOGIN_CAPTCHA_ERROR);
        }
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(param.getUsername(), param.getPassword()));
        } catch (InternalAuthenticationServiceException | BadCredentialsException e) {
            log.debug(ResultConstants.USERNAME_OR_PASSWORD_ERROR.getMessage());
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(param.getUsername(), Constants.LOGIN_FAIL, e.getMessage()));
            return Result.error(ResultConstants.USERNAME_OR_PASSWORD_ERROR);
        } catch (Exception e) {
            log.debug("登录失败");
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(param.getUsername(), Constants.LOGIN_FAIL, e.getMessage()));
            return Result.error(ResultConstants.LOGIN_ERROR);
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(param.getUsername(), Constants.LOGIN_SUCCESS, "登录成功"));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        String token = tokenService.createToken(loginUser);

        String ipAddr = IpUtils.getIpAddr(ServletUtils.getRequest());
        loginUser.getUser().setLoginIp(ipAddr);
        loginUser.getUser().setLoginDate(new Date());
        sysUserMapper.updateByPrimaryKey(loginUser.getUser());

        return Result.buildResult(ResultConstants.REQUEST_SUCCESS.getCode(), "登录成功")
                .useMapParam()
                .addMapParam("token", token);
    }

    @Override
    public Result refreshToken() {

        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser sysUser = sysUserMapper.findByUserId(loginUser.getUser().getUserId());
        loginUser.setUser(sysUser);
        List<String> perms = sysMenuMapper.getMenuPerms(sysUser.getUserId());
        loginUser.setPermissions(new HashSet<>(perms));
        tokenService.refreshToken(loginUser);
        return Result.success();
    }

    @Override
    public Result info() {

        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();

        List<SysMenu> menus = null;
        if (loginUser.getUser().isAdmin()) {
            menus = sysMenuMapper.getAllMenu();
        } else {
            menus = sysMenuMapper.getMenuByUserId(user.getUserId());
        }
        user.setPermissions(this.makeMenus(menus));
        return Result.success(user);

    }

    private List<SysMenu> makeMenus(List<SysMenu> sources) {
        List<SysMenu> res = new ArrayList<>();
        for (SysMenu source1 : sources) {
            if (source1.getParentId() == 0) {
                for (SysMenu source2 : sources) {
                    if (source2.getParentId().equals(source1.getMenuId())) {
                        source1.getChildren().add(source2);
                    }
                }
                res.add(source1);
            }
        }
        return res;
    }
}
