package com.ruoyi.system.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * $SysLoginUserInfoVo
 *
 * @author coriander
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysLoginUserInfoVo {
    
    private Long userId;
    
    private String username;
}
