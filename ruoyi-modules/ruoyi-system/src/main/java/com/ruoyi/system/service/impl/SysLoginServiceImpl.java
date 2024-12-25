package com.ruoyi.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.system.domain.vo.SysLoginUserInfoVo;
import com.ruoyi.system.domain.vo.SysLoginVo;
import com.ruoyi.system.service.SysLoginService;
import org.springframework.stereotype.Service;

/**
 * @author Link
 * @date 2024-11-08
 */
@Service
public class SysLoginServiceImpl implements SysLoginService {
    
    @Override
    public Long login(String username, String password) {
        return 1L;
    }
    
    @Override
    public SysLoginVo getUserInfo(Long userId) {
        SysLoginVo vo = new SysLoginVo();
        
        SysLoginUserInfoVo user = new SysLoginUserInfoVo();
        user.setUserId(userId);
        user.setUsername("super");
        
        vo.setUser(user);
        // TODO 根据loginId获取用户信息
        
        // TODO 查询角色信息
        vo.setRoleList(CollUtil.newHashSet("admin"));
        
        // TODO 查询权限信息
        vo.setPermissionList(CollUtil.newHashSet("*:*:*"));
        
        return vo;
    }
}
