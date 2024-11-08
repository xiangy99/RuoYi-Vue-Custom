package com.ruoyi.system.domain.pojo;

import com.ruoyi.common.mybatis.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * ${DESCRIPTION}
 *
 * @author Link 
 * @date 2024-11-08 
 * 
 */

/**
 * 角色和菜单关联表
 */
@Schema(description = "角色和菜单关联表")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleMenu extends BaseEntity implements Serializable {
    
    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    @NotNull(message = "角色ID不能为null")
    private Long roleId;
    
    /**
     * 菜单ID
     */
    @Schema(description = "菜单ID")
    @NotNull(message = "菜单ID不能为null")
    private Long menuId;
}