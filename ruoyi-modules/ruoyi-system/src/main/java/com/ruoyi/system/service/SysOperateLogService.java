package com.ruoyi.system.service;

import com.ruoyi.common.log.event.OperateLogEvent;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.system.domain.query.SysOperateLogQuery;
import com.ruoyi.system.domain.vo.SysOperateLogVo;

/**
 * @author Link
 * @date 2024-11-21
 */
public interface SysOperateLogService {
    
    void save(OperateLogEvent param);
    
    
    PageLight<SysOperateLogVo> page(SysOperateLogQuery param);
    
    SysOperateLogVo get(Long operateId);
}
