package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.core.utils.IdUtil;
import com.ruoyi.common.log.event.OperateLogEvent;
import com.ruoyi.system.domain.pojo.SysOperateLog;
import com.ruoyi.system.mapper.SysOperateLogMapper;
import com.ruoyi.system.service.SysOperateLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * $SysOperateLogServiceImpl
 *
 * @author Link
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysOperateLogServiceImpl implements SysOperateLogService {
    
    private final SysOperateLogMapper sysOperateLogMapper;
    
    
    /**
     * 操作日志记录
     *
     * @param operateLogEvent 操作日志事件
     */
    @Async
    @EventListener
    public void recordOperateLog(OperateLogEvent operateLogEvent) {
        this.save(operateLogEvent);
    }
    
    @Override
    public void save(OperateLogEvent param) {
        SysOperateLog sysOperateLogRecord = BeanUtil.copyProperties(param, SysOperateLog.class);
        sysOperateLogRecord.setOperateId(IdUtil.getId());
        sysOperateLogMapper.insert(sysOperateLogRecord);
    }
}
