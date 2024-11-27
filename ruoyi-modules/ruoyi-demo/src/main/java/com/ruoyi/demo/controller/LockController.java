package com.ruoyi.demo.controller;

import com.ruoyi.common.redis.lock.Lock;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Link
 * @date 2024-11-27
 */
@Slf4j
@Tag(name = "Lock分布式锁测试", description = "Lock分布式锁测试")
@RestController
@RequestMapping("/lock")
public class LockController {
    
    @Operation(summary = "重复请求测试", description = "重复请求测试")
    @GetMapping
    public Object get() {
        Integer id = 1;
        return Lock.distributedLockRun("get:" + id, 30, 30, () -> {
            log.info("进入锁");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return id;
        });
        
        
    }
}
