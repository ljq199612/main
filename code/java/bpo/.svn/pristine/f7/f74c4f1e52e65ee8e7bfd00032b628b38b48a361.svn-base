package com.rz.iot.bpo.framework.web.exception;

import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.common.exception.BaseException;
import com.rz.iot.bpo.common.exception.CustomException;
import com.rz.iot.bpo.common.utils.StringUtils;
import com.rz.iot.bpo.framework.web.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理器
 * 
 * @author ruoyi
 */
@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 基础异常
     */
    @ExceptionHandler(BaseException.class)
    public Result baseException(BaseException e)
    {
        return Result.error(e.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(CustomException.class)
    public Result businessException(CustomException e)
    {
        if (StringUtils.isNull(e.getCode()))
        {
            return Result.error(e.getMessage());
        }
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 资源或路径不存在
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoFoundException(Exception e)
    {
        log.error(e.getMessage(), e);
        return Result.error(ResultConstants.RESOURCES_NOT_FOUND);
    }

    /**
     * 没有权限
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public Result handleAuthorizationException(AccessDeniedException e)
    {
        log.error(e.getMessage());
        return Result.error(ResultConstants.IS_NOT_AUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e)
    {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }
}
