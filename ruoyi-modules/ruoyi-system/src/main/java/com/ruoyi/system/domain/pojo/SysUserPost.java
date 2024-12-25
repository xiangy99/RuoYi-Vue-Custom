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
 * 用户与岗位关联表
 */
@Schema(description = "用户与岗位关联表")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SysUserPost extends BaseEntity implements Serializable {
    
    /**
     * 用户ID
     */
    @TableId
    @Schema(description = "用户ID")
    @NotNull(message = "用户ID不能为null")
    private Long userId;
    
    /**
     * 岗位ID
     */
    @Schema(description = "岗位ID")
    @NotNull(message = "岗位ID不能为null")
    private Long postId;
}