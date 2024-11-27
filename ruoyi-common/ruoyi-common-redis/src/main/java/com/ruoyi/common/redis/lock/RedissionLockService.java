package com.ruoyi.common.redis.lock;

import com.ruoyi.common.redis.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Slf4j
@Service
public class RedissionLockService implements DistributedLockService {
    
    @Override
    public void lockRun(String lockKey, int waitSecond, int lockSecond, Runnable runnable) {
        lockKey = warpKey(lockKey);
        
        RedissonClient redissonClient = RedisUtil.getClient();
        
        RLock lock = redissonClient.getLock(lockKey);
        
        try {
            if (lock.tryLock(waitSecond, lockSecond, TimeUnit.SECONDS)) {
                runnable.run();
            } else {
                throw new LockFailException(lockKey);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("加锁【" + lockKey + "】执行失败", e);
            throw new LockFailException(lockKey);
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    }
    
    @Override
    public <T> T lockRun(String lockKey, int waitSecond, int lockSecond, Supplier<T> supplier) {
        
        lockKey = warpKey(lockKey);
        
        RedissonClient redissonClient = RedisUtil.getClient();
        
        RLock lock = redissonClient.getLock(lockKey);
        
        try {
            if (lock.tryLock(waitSecond, lockSecond, TimeUnit.SECONDS)) {
                return supplier.get();
            } else {
                throw new LockFailException(lockKey);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new LockFailException(lockKey);
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    }
    
    
    private String warpKey(String key) {
        return "lock:" + key;
    }
}
