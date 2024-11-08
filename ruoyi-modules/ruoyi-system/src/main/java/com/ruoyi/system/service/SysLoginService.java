package com.ruoyi.system.service;


import com.ruoyi.system.domain.vo.SysLoginUserVo;

/**
 * $PassportService
 *
 * @author Link
 */
public interface SysLoginService {
    
    Long login(String username, String password);
    
    SysLoginUserVo getUserInfo(Long userId);
    
}
