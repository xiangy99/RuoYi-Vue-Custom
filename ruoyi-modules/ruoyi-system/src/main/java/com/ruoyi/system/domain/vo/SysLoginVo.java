package com.ruoyi.system.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * $SysLoginUserVo
 *
 * @author Link
 */
@Getter
@Setter
public class SysLoginVo {
    
    private SysLoginUserInfoVo user;
    
    private Set<String> roleList;
    
    private Set<String> permissionList;
    
}
