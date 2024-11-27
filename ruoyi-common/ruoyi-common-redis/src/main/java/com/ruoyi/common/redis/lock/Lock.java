package com.ruoyi.common.redis.lock;


import cn.hutool.extra.spring.SpringUtil;

import java.util.function.Supplier;

public class Lock {
    
    /**
     * 分布式加锁执行
     *
     * @param waitSecond 等待秒数
     * @param lockSecond 加锁后锁定秒数，防止死锁
     * @param lockKey    锁键
     * @param runnable   执行方法
     */
    public static void distributedLockRun(String lockKey, int waitSecond, int lockSecond, Runnable runnable) {
        DistributedLockService lockService = SpringUtil.getBean(DistributedLockService.class);
        lockService.lockRun(lockKey, waitSecond, lockSecond, runnable);
    }
    
    /**
     * 分布式加锁执行
     *
     * @param waitSecond 等待秒数
     * @param lockSecond 加锁后锁定秒数，防止死锁
     * @param lockKey    锁键
     * @param runnable   执行方法
     */
    public static <T> T distributedLockRun(String lockKey, int waitSecond, int lockSecond, Supplier<T> runnable) {
        DistributedLockService lockService = SpringUtil.getBean(DistributedLockService.class);
        return lockService.lockRun(lockKey, waitSecond, lockSecond, runnable);
    }
}
