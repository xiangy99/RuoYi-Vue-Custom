package com.ruoyi.common.redis.lock;

import java.util.function.Supplier;

/**
 * 分布式锁
 */
public interface DistributedLockService {
    
    /**
     * 加锁执行
     *
     * @param lockKey    锁键
     * @param waitSecond 等待秒数
     * @param lockSecond 加锁后锁定秒数，防止死锁
     * @param runnable   执行方法
     */
    void lockRun(String lockKey, int waitSecond, int lockSecond, Runnable runnable);
    
    /**
     * 加锁执行
     *
     * @param lockKey    锁键
     * @param waitSecond 等待秒数
     * @param lockSecond 加锁后锁定秒数，防止死锁
     * @param runnable   执行方法
     */
    <T> T lockRun(String lockKey, int waitSecond, int lockSecond, Supplier<T> runnable);
}
