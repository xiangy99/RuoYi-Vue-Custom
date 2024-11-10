package com.ruoyi.system.service;


import com.ruoyi.system.domain.vo.SysLoginVo;

/**
 * $PassportService
 *
 * @author Link
 */
public interface SysLoginService {
    
    Long login(String username, String password);
    
    SysLoginVo getUserInfo(Long userId);
    
}
