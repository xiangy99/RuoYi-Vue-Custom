package com.ruoyi.demo.controller;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.redis.lock.Lock;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Link
 * @date 2024-11-27
 */
@Slf4j
@Tag(name = "Lock分布式锁测试", description = "Lock分布式锁测试")
@RestController
@RequestMapping("/lock")
public class LockController {
    
    // 线程安全的 List 用于存储数据
    private final List<String> dataList = new CopyOnWriteArrayList<>();
    
    @Operation(summary = "重复请求测试", description = "重复请求测试")
    @GetMapping
    public Object get() {
        int id = 1;
        return Lock.distributedLockRun("get:" + id, 30, 30, () -> {
            log.info("进入锁");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 向列表中添加当前时间戳数据
            dataList.add(DateUtil.format(new Date(), "HH:mm:ss.SSS"));
            
            // 返回当前列表
            return dataList;
        });
        
        
    }
}
