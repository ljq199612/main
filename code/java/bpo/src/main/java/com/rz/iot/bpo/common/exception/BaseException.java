package com.rz.iot.bpo.common.exception;

import com.rz.iot.bpo.common.constant.ResultConstants;
import lombok.Data;

/**
 * 基础异常
 * 
 * @author ruoyi
 */
@Data
public class BaseException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String message;

    public BaseException(String module, ResultConstants resultConstants, Object[] args)
    {
        this.module = module;
        this.code = resultConstants.getCode();
        this.args = args;
        this.message = resultConstants.getMessage();
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
