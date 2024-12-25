package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.pojo.SysRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Link
 * @date 2024-11-08
 */
@Schema(description = "角色信息表 Vo")
@Getter
@Setter
public class SysRoleVo extends SysRole {
    
    private Long roleId;
    
    /**
     * 用户是否存在此角色标识 默认不存在
     */
    private Boolean flag = false;
    
    public boolean isAdmin() {
        return isAdmin(roleId);
    }
    
    public static boolean isAdmin(Long roleId) {
        return roleId != null && 1L == roleId;
    }
    
}
