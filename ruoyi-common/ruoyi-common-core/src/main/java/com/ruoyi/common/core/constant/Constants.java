package com.ruoyi.common.core.constant;

/**
 * $Constants
 *
 * @author Link
 */
public class Constants {
    /**
     * www主域
     */
    public static final String WWW = "www.";
    
    /**
     * http请求
     */
    public static final String HTTP = "http://";
    
    /**
     * https请求
     */
    public static final String HTTPS = "https://";
    
    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";
    
    /**
     * 自动识别json对象白名单配置（仅允许解析的包名，范围越小越安全）
     */
    public static final String[] JSON_WHITELIST_STR = { "org.springframework", "com.ruoyi" };

}
