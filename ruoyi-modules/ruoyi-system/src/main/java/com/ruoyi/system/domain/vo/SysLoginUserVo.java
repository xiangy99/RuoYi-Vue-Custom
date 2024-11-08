package com.ruoyi.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * $SysLoginUserVo
 *
 * @author Link
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysLoginUserVo {
    
    private Long userId;
    
    private String username;
    
    private Set<String> roleList;
    
    private Set<String> permissionList;
    
}
