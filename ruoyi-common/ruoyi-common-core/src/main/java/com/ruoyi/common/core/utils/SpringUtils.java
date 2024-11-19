package com.ruoyi.common.core.utils;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.boot.autoconfigure.thread.Threading;
import org.springframework.core.env.Environment;

/**
 * @author Link
 * @date 2024-11-19
 */
public class SpringUtils {
    
    public static boolean isVirtual() {
        return Threading.VIRTUAL.isActive(SpringUtil.getBean(Environment.class));
    }
}
