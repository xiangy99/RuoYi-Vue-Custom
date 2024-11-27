package com.ruoyi.common.redis.lock;

public class LockFailException extends RuntimeException {
    
    /**
     * 加锁键
     */
    private String key;
    
    public LockFailException(String key) {
        this.key = key;
    }
    
    @Override
    public String getMessage() {
        return "加锁失败[" + this.key + "]";
    }
    
}

