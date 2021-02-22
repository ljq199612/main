package com.rz.iot.bpo.framework.web.entity;

import com.rz.iot.bpo.common.constant.ResultConstants;
import com.rz.iot.bpo.model.BpoSupplierInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@SuppressWarnings("unchecked")
public class Result<T> implements Serializable {

    @ApiModelProperty(value = "错误代码", example = "20000")
    private Integer code;
    @ApiModelProperty(value = "数据", example = "{}")
    private T data;
    @ApiModelProperty(value = "错误信息", example = "id为空")
    private String message;

    public Result() {
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public Result(ResultConstants requestSuccess) {
        this.code = requestSuccess.getCode();
        this.message = requestSuccess.getMessage();
    }

    public static Result buildResult(Integer code, String message) {
        return new Result<Map>(code, message);
    }

    public Result useMapParam() {
        this.data = (T) new HashMap<String, Object>();
        return this;
    }

    public Result addMapParam(String key, String value) {

        Map<String, Object> data = (Map) this.data;
        data.put(key, value);
        return this;
    }

    public static <U> Result success(U data, String message) {
        return new Result<>(ResultConstants.REQUEST_SUCCESS.getCode(), data, message);
    }

    public static <U> Result success(U data) {
        return Result.success(data, ResultConstants.REQUEST_SUCCESS.getMessage());
    }

    public static Result success() {
        return Result.success(null);
    }

    public static <U> Result error(Integer code, U data, String message) {
        return new Result<>(code, data, message);
    }

    public static Result error(Integer code, String message) {
        return Result.error(code, null, message);
    }

    public static Result error(String message) {
        return Result.error(ResultConstants.PROGRAM_ERROR.getCode(), message);
    }

    public static Result error() {
        return Result.error(ResultConstants.PROGRAM_ERROR.getMessage());
    }

    public static Result error(ResultConstants resultConstants) {
        return Result.error(resultConstants.getCode(), resultConstants.getMessage());
    }

    public static Result requestParamError(String message) {
        return new Result<>(ResultConstants.REQUEST_PARAM_ERROR.getCode(), null, message);
    }

    public static Result requestParamError() {
        return Result.requestParamError(ResultConstants.REQUEST_PARAM_ERROR.getMessage());
    }
}
