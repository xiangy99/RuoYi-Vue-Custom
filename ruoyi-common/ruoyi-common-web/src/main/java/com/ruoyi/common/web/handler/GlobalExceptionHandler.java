package com.ruoyi.common.web.handler;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.core.exception.BusinessException;
import com.ruoyi.common.core.exception.ValidationException;
import com.ruoyi.common.core.result.ResultCode;
import com.ruoyi.common.core.result.ResultData;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * 全局异常捕获处理
 *
 * @author coriander
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * 系统异常
     *
     * @param exception the exception
     * @return the result data
     */
    @ExceptionHandler(value = Exception.class)
    public ResultData handlerException(Exception exception, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        log.error("Exception 请求方法:'{}',请求地址:'{}',发生系统异常.", method, requestURI, exception);
        return ResultData.fail(ResultCode.UNKNOWN);
    }
    
    /**
     * 资源未找到异常
     *
     * @param exception the exception
     * @return the result data
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResultData handlerNoHandlerFoundException(Exception exception, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        log.error("NoHandlerFoundException 请求方法:'{}',请求地址:'{}',请求路径不存在.", method, requestURI, exception);
        String message = StrUtil.format(ResultCode.Client.URL_NOT_FOUND.message(), requestURI);
        return ResultData.fail(ResultCode.Client.URL_NOT_FOUND.code(), message);
    }
    
    /**
     * 请求的内容格式不正确
     *
     * @param exception the exception
     * @return the result data
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResultData handlerHttpMessageNotReadableException(Exception exception, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        log.error("HttpMessageNotReadableException 请求方法:'{}',请求地址:'{}',请求的内容格式不正确.", method,
                requestURI, exception);
        String message = StrUtil.format(ResultCode.Client.REQUEST_INCORRECT.message(), requestURI);
        return ResultData.fail(ResultCode.Client.REQUEST_INCORRECT.code(), message);
    }
    
    /**
     * 拦截未知的运行时异常
     *
     * @param exception the exception
     * @return the result data
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResultData handlerResultException(RuntimeException exception, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        log.error("RuntimeException 请求方法:'{}',请求地址:'{}',发生系统异常.", method, requestURI, exception);
        return ResultData.fail(ResultCode.UNKNOWN);
    }
    
    /**
     * 拦截空指针异常
     *
     * @param exception the exception
     * @return the result data
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ResultData handlerNullPointerException(NullPointerException exception, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        log.error("NullPointerException 请求方法:'{}',请求地址:'{}',空指针异常.", method, requestURI, exception);
        return ResultData.fail(ResultCode.UNKNOWN);
    }
    
    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultData handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception,
            HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = exception.getMethod();
        log.error("HttpRequestMethodNotSupportedException 请求地址'{}',不支持'{}'请求", requestURI, method, exception);
        String message = StrUtil.format(ResultCode.Client.COMMON_REQUEST_METHOD_INVALID.message(), requestURI, method);
        return ResultData.fail(ResultCode.Client.COMMON_REQUEST_METHOD_INVALID.code(), message);
    }
    
    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResultData handleServiceException(BusinessException exception, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        
        log.error("BusinessException 请求方法:'{}',请求地址:'{}',发生业务异常.", method, requestURI, exception);
        return ResultData.fail(exception.getCode(), exception.getMessage());
    }
    
    /**
     * 参数错误异常
     */
    @ExceptionHandler(ValidationException.class)
    public ResultData handleValidationException(ValidationException exception, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        log.error("ValidationException 请求方法:'{}',请求地址:'{}',参数错误.", method, requestURI, exception);
        return ResultData.fail(exception.getCode(), exception.getMessage());
    }
}
