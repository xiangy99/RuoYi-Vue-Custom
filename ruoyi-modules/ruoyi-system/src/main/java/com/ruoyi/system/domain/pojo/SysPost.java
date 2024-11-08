package com.ruoyi.system.domain.pojo;

import com.ruoyi.common.mybatis.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
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
 * 岗位信息表
 */
@Schema(description = "岗位信息表")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SysPost extends BaseEntity implements Serializable {
    
    /**
     * 岗位ID
     */
    @Schema(description = "岗位ID")
    @NotNull(message = "岗位ID不能为null")
    private Long postId;
    
    /**
     * 岗位编码
     */
    @Schema(description = "岗位编码")
    @Size(max = 64, message = "岗位编码最大长度要小于 64")
    @NotBlank(message = "岗位编码不能为空")
    private String postCode;
    
    /**
     * 岗位名称
     */
    @Schema(description = "岗位名称")
    @Size(max = 50, message = "岗位名称最大长度要小于 50")
    @NotBlank(message = "岗位名称不能为空")
    private String postName;
    
    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer sort;
    
    /**
     * 岗位状态（0正常 1停用）
     */
    @Schema(description = "岗位状态（0正常 1停用）")
    @Size(max = 1, message = "岗位状态（0正常 1停用）最大长度要小于 1")
    private String status;
    
    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 500, message = "备注最大长度要小于 500")
    private String remark;
    
    /**
     * 是否删除
     */
    @Schema(description = "是否删除")
    private Boolean isDeleted;
}