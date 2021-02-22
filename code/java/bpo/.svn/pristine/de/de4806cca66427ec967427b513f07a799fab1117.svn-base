package com.rz.iot.bpo.controller;

import com.rz.iot.bpo.common.constant.HttpStatus;
import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.common.utils.StringUtils;
import com.rz.iot.bpo.framework.web.entity.Result;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Author by xuxiake, Date on 2020/6/9 17:48.
 * PS: Not easy to write code, please indicate.
 * Description：
 */
@RequestMapping("/")
@RestController
public class CustomErrorController extends AbstractErrorController {

    public CustomErrorController(ErrorAttributes errorAttributes, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers);
    }

    @RequestMapping("/error")
    public Result handleError(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, ErrorAttributeOptions.defaults());
        if(statusCode == HttpStatus.NOT_FOUND) {
            String msg = StringUtils.format("请求访问：{}, 不存在", errorAttributes.get("path"));
            return Result.error(ResultConstants.RESOURCES_NOT_FOUND.getCode(), errorAttributes, msg);
        } else{
            return Result.error();
        }

    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
