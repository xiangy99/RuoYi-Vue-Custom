package com.ruoyi.system.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * $SysPostModifyBO
 *
 * @author Link
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysPostModifyBo implements Serializable {
    
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
     * 状态（0正常 1停用）
     */
    @Schema(description = "状态（0正常 1停用）")
    @Size(max = 1, message = "状态（0正常 1停用）最大长度要小于 1")
    private String status;
    
    /**
     * 备注
     */
    private String remark;
}
