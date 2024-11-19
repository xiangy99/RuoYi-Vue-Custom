package com.ruoyi.common.web.config;

import com.ruoyi.common.web.filter.RepeatableFilter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * Filter配置
 *
 * @author xiangy
 */
@AutoConfiguration
public class FilterConfig {
    
    /**
     * 过滤器会应用于所有请求（通过 /* URL 模式），并且其执行顺序被设置为最低优先级
     */
    @Bean
    public FilterRegistrationBean<RepeatableFilter> someFilterRegistration() {
        FilterRegistrationBean<RepeatableFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new RepeatableFilter());
        registration.addUrlPatterns("/*");
        registration.setName("repeatableFilter");
        registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return registration;
    }
    
}
