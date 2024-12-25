package com.ruoyi.common.satoken.utils;

import cn.dev33.satoken.stp.StpUtil;

/**
 * $SecurityUtils
 *
 * @author Link
 */
public class SecurityUtils {
    
    /**
     * 获取当前登录用户ID
     */
    public static Long getUserId() {
        return Long.parseLong(String.valueOf(StpUtil.getLoginId()));
    }
    
    /**
     * 是否为超级管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isSuper(Long userId) {
        return userId != null && 1L == userId;
    }
    
    
}
