package com.rz.iot.bpo.framework.security.handle;

import com.alibaba.fastjson.JSON;
import com.rz.iot.bpo.common.constant.HttpStatus;
import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.common.utils.ServletUtils;
import com.rz.iot.bpo.common.utils.StringUtils;
import com.rz.iot.bpo.framework.web.entity.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * 认证失败处理类 返回未授权
 * 
 * @author ruoyi
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable
{
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        int code = ResultConstants.TOKEN_TIME_OUT.getCode();
        String msg = StringUtils.format("请求访问：{}，认证失败，请登录", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(Result.error(code, msg)));
    }
}
