package com.ruoyi.common.core.exception;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.core.result.ResultCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 业务异常
 *
 * @author coriander
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {
    
    /**
     * 错误码
     */
    private Integer code;
    
    /**
     * 错误提示
     */
    private String message;
    
    public BusinessException(String message) {
        this.code = ResultCode.UNKNOWN.code();
        this.message = message;
    }
    
    public BusinessException(ResultCode.Business resultCode, Object... args) {
        this.code = resultCode.code();
        if (ArrayUtil.isNotEmpty(args)) {
            this.message = StrUtil.format(resultCode.message(), args);
        } else {
            this.message = resultCode.message();
        }
    }
    
    public BusinessException() {
        this.code = ResultCode.UNKNOWN.code();
        this.message = ResultCode.UNKNOWN.message();
    }
    
    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}