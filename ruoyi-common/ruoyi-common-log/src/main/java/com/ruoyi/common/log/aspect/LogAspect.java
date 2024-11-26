package com.ruoyi.common.log.aspect;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.core.enums.ResultStatusEnum;
import com.ruoyi.common.core.result.ResultData;
import com.ruoyi.common.core.utils.ServletUtil;
import com.ruoyi.common.core.utils.ip.AddressUtils;
import com.ruoyi.common.core.utils.ip.IpUtil;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.event.OperateLogEvent;
import com.ruoyi.common.log.filter.PropertyPreExcludeFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.core.NamedThreadLocal;
import org.springframework.http.HttpMethod;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

/**
 * 操作日志记录处理
 *
 * @author coriander
 */
@Slf4j
@Aspect
@AutoConfiguration
public class LogAspect {
    
    /**
     * 排除敏感属性字段
     */
    public static final String[] EXCLUDE_PROPERTIES = {"password", "oldPassword", "newPassword", "confirmPassword"};
    
    /**
     * 计算操作消耗时间
     */
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new NamedThreadLocal<Long>("Cost Time");
    
    @Before(value = "@annotation(controllerLog)")
    public void boBefore(JoinPoint joinPoint, Log controllerLog) {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }
    
    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object jsonResult) {
        this.handleLog(joinPoint, controllerLog, null, jsonResult);
    }
    
    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log controllerLog, Exception e) {
        this.handleLog(joinPoint, controllerLog, e, null);
    }
    
    protected void handleLog(final JoinPoint joinPoint, Log controllerLog, final Exception e, Object jsonResult) {
        try {
            // *========数据库日志=========*//
            OperateLogEvent operateLogEvent = new OperateLogEvent();
            operateLogEvent.setOperateStatus(ResultStatusEnum.SUCCESS.getCode());
            // 请求的地址
            
            String ip = IpUtil.getIpAddress(ServletUtil.getRequest());
            operateLogEvent.setIp(ip);
            operateLogEvent.setLocation(AddressUtils.getRealAddressByIP(ip));
            operateLogEvent.setUrl(ServletUtil.getRequest().getRequestURI());
            operateLogEvent.setOperateTime(LocalDateTime.now());
            
            if (e != null) {
                operateLogEvent.setOperateStatus(ResultStatusEnum.FAIL.getCode());
                operateLogEvent.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operateLogEvent.setMethod(className + "." + methodName + "()");
            // 设置请求方式
            operateLogEvent.setRequestMethod(ServletUtil.getRequest().getMethod());
            // 设置消耗时间
            operateLogEvent.setCostTime(System.currentTimeMillis() - TIME_THREADLOCAL.get());
            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, controllerLog, operateLogEvent, jsonResult);
            // 发布事件保存数据库
            SpringUtil.getApplicationContext().publishEvent(operateLogEvent);
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        } finally {
            TIME_THREADLOCAL.remove();
        }
    }
    
    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log             日志
     * @param operateLogEvent 操作日志
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, Log log, OperateLogEvent operateLogEvent,
            Object jsonResult) {
        // 设置action动作
        operateLogEvent.setBusinessType(log.businessType().ordinal());
        // 设置标题
        operateLogEvent.setTitle(log.title());
        // 设置操作人类别
        operateLogEvent.setOperatorType(log.operatorType().ordinal());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData()) {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(joinPoint, operateLogEvent);
        }
        // 是否需要保存response，参数和值
        if (log.isSaveResponseData() && ObjectUtil.isNotNull(jsonResult)) {
            if (!(jsonResult instanceof ResultData)) {
                jsonResult = ResultData.success(jsonResult);
            }
            operateLogEvent.setJsonResult(StringUtils.substring(JSONUtil.toJsonStr(jsonResult), 0, 2000));
        }
    }
    
    /**
     * 获取请求的参数，放到log中
     *
     * @param operateLogEvent 操作日志
     */
    private void setRequestValue(JoinPoint joinPoint, OperateLogEvent operateLogEvent) {
        String requestMethod = operateLogEvent.getRequestMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            String params = argsArrayToString(joinPoint.getArgs());
            operateLogEvent.setParam(StringUtils.substring(params, 0, 2000));
        }
    }
    
    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null && paramsArray.length > 0) {
            for (Object o : paramsArray) {
                if (ObjectUtil.isNotNull(o) && !isFilterObject(o)) {
                    try {
                        String jsonObj = JSON.toJSONString(o, excludePropertyPreFilter());
                        params.append(jsonObj).append(" ");
                    } catch (Exception e) {
                        params.append("参数拼接错误");
                    }
                }
            }
        }
        return params.toString().trim();
    }
    
    /**
     * 忽略敏感属性
     */
    public PropertyPreExcludeFilter excludePropertyPreFilter() {
        return new PropertyPreExcludeFilter().addExcludes(EXCLUDE_PROPERTIES);
    }
    
    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o) {
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) o;
            for (Object value : collection) {
                return value instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) o;
            for (Object value : map.entrySet()) {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
                || o instanceof BindingResult;
    }
}
