package com.ruoyi.common.log.annotation;




import com.ruoyi.common.log.enums.LogBusinessTypeEnum;
import com.ruoyi.common.log.enums.LogOperatorTypeEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义操作日志记录注解
 *
 * @author coriander
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    LogBusinessTypeEnum businessType() default LogBusinessTypeEnum.OTHER;

    /**
     * 操作人类别
     */
    LogOperatorTypeEnum operatorType() default LogOperatorTypeEnum.MANAGE;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    boolean isSaveResponseData() default true;
}
