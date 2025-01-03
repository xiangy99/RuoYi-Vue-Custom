package com.ruoyi.common.core.constant;

/**
 * 缓存组名称常量
 * <p>
 * key 格式为 cacheNames#ttl#maxIdleTime#maxSize
 * <p>
 * ttl 过期时间 如果设置为0则不过期 默认为0 maxIdleTime 最大空闲时间 根据LRU算法清理空闲数据 如果设置为0则不检测 默认为0 maxSize 组最大长度 根据LRU算法清理溢出数据 如果设置为0则无限长
 * 默认为0
 * <p>
 * 例子: test#60s、test#0#60s、test#0#1m#1000、test#1h#0#500
 *
 * @author xiangy
 */
public interface CacheNames {
    
    /**
     * 数据字典
     */
    String SYS_DICT = "sys_dict";
    
    
    /**
     * 系统配置
     */
    String SYS_CONFIG = "sys_config";
}
