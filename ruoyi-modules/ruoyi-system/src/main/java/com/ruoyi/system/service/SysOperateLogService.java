package com.ruoyi.system.service;

import com.ruoyi.common.log.event.OperateLogEvent;

/**
 * @author Link
 * @date 2024-11-21
 */
public interface SysOperateLogService {
    
    void save(OperateLogEvent param);
}
