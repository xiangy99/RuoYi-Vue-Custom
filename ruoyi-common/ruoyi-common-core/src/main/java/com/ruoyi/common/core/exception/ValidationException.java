package com.ruoyi.common.core.exception;


import com.ruoyi.common.core.result.ResultCode;

/**
 * $ValidationException
 *
 * @author Link
 */
public class ValidationException extends BusinessException{
    
    public ValidationException(String errorMessage) {
        super(ResultCode.Client.COMMON_REQUEST_PARAMETERS_INVALID.code(),errorMessage);
    }
}
