package com.ruoyi.common.mybatis.handler;

import com.ruoyi.common.core.result.ResultData;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Mybatis异常处理器
 *
 * @author xiangy
 */
@Slf4j
@RestControllerAdvice
public class MybatisExceptionHandler {
    
    /**
     * 主键或UNIQUE索引，数据重复异常
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ResultData handleDuplicateKeyException(DuplicateKeyException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',数据库中已存在记录'{}'", requestURI, e.getMessage());
        return ResultData.fail("数据库中已存在该记录，请联系管理员确认");
    }
    
    /**
     * Mybatis系统异常 通用处理
     */
    @ExceptionHandler(MyBatisSystemException.class)
    public ResultData handleCannotFindDataSourceException(MyBatisSystemException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String message = e.getMessage();
        if (StringUtils.contains("CannotFindDataSourceException", message)) {
            log.error("请求地址'{}', 未找到数据源", requestURI);
            return ResultData.fail("未找到数据源，请联系管理员确认");
        }
        log.error("请求地址'{}', Mybatis系统异常", requestURI, e);
        return ResultData.fail(message);
    }
    
}
