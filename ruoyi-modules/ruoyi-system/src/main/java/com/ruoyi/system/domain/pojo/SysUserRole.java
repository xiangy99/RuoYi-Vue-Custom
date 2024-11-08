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
 * 用户和角色关联表
 */
@Schema(description = "用户和角色关联表")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRole extends BaseEntity implements Serializable {
    
    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    @NotNull(message = "用户ID不能为null")
    private Long userId;
    
    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    @NotNull(message = "角色ID不能为null")
    private Long roleId;
}