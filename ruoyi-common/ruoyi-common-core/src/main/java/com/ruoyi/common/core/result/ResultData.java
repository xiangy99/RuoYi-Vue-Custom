package com.ruoyi.common.core.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Path;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 返回值封装
 *
 * @author coriander
 */
@Schema(description = "统一响应结果封装类")
@Data
public class ResultData implements Serializable {
    
    /**
     * 返回状态码
     */
    @Schema(description = "返回状态码", example = "200")
    private int code;
    
    /**
     * 返回结果描述
     */
    @Schema(description = "返回消息", example = "操作成功")
    private String message;
    
    /**
     * 是否成功
     */
    @Schema(description = "操作是否成功", example = "true")
    private Boolean success;
    
    /**
     * 返回时间
     */
    @Schema(description = "响应时间戳(毫秒)", example = "1689402473000L")
    private Long timestamp;
    
    @Schema(description = "校验错误列表")
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