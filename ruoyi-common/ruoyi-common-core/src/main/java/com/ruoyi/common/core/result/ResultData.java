package com.ruoyi.common.core.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Path;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 返回值封装
 *
 * @author coriander
 */
@Data
public class ResultData implements Serializable {
    
    /**
     * 返回状态码
     */
    private int code;
    
    /**
     * 返回结果描述
     */
    private String message;
    
    /**
     * 是否成功
     */
    private Boolean success;
    
    /**
     * 返回时间
     */
    private long timestamp;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<Path, String> validationErrorList;
    
    
    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }
    
    
    public static ResultData success(String message) {
        ResultData Result = new ResultData();
        Result.setCode(ResultCode.SUCCESS.code());
        Result.setMessage(message);
        Result.setSuccess(Boolean.TRUE);
        return Result;
    }
    
    public static ResultData success() {
        ResultData Result = new ResultData();
        Result.setCode(ResultCode.SUCCESS.code());
        Result.setMessage(ResultCode.SUCCESS.message());
        Result.setSuccess(Boolean.TRUE);
        return Result;
    }
    
    public static <T> Result<T> success(T data) {
        Result<T> Result = new Result<>();
        Result.setCode(ResultCode.SUCCESS.code());
        Result.setMessage(ResultCode.SUCCESS.message());
        Result.setData(data);
        Result.setSuccess(Boolean.TRUE);
        return Result;
    }
    
    public static ResultData fail(String message) {
        ResultData Result = new ResultData();
        Result.setCode(ResultCode.FAIL.code());
        Result.setMessage(message);
        Result.setSuccess(Boolean.FALSE);
        return Result;
    }
    
    public static ResultData fail(int code, String message) {
        ResultData Result = new ResultData();
        Result.setCode(code);
        Result.setMessage(message);
        Result.setSuccess(Boolean.FALSE);
        return Result;
    }
    
    public static ResultData fail() {
        ResultData Result = new ResultData();
        Result.setCode(ResultCode.FAIL.code());
        Result.setMessage(ResultCode.FAIL.message());
        Result.setSuccess(Boolean.FALSE);
        return Result;
    }
    
    public static ResultData fail(ResultCode resultCode) {
        ResultData Result = new ResultData();
        Result.setCode(resultCode.code());
        Result.setMessage(resultCode.message());
        Result.setSuccess(Boolean.FALSE);
        return Result;
    }
}