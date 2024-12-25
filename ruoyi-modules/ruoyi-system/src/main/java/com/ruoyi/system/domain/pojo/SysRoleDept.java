package com.ruoyi.system.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.mybatis.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * ${DESCRIPTION}
 *
 * @author Link
 * @date 2024-11-08
 */

/**
 * 角色和部门关联表
 */
@Schema(description = "角色和部门关联表")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleDept extends BaseEntity implements Serializable {
    
    /**
     * 角色ID
     */
    @TableId
    @Schema(description = "角色ID")
    @NotNull(message = "角色ID不能为null")
    private Long roleId;
    
    /**
     * 部门ID
     */
    @Schema(description = "部门ID")
    @NotNull(message = "部门ID不能为null")
    private Long deptId;
}