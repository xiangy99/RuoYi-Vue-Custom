package com.ruoyi.system.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * $SysDictTypeBO
 *
 * @author Link
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysDictTypeSaveBo implements Serializable {
    
    /**
     * 字典主键
     */
    @Schema(description = "字典主键")
    private Long dictId;
    
    /**
     * 字典名称
     */
    @Schema(description = "字典名称")
    @Size(max = 100, message = "字典名称最大长度要小于 100")
    private String dictName;
    
    /**
     * 字典类型
     */
    @Schema(description = "字典类型")
    @Size(max = 100, message = "字典类型最大长度要小于 100")
    private String dictType;
    
    /**
     * 状态（0正常 1停用）
     */
    @Schema(description = "状态（0正常 1停用）")
    @Size(max = 1, message = "状态（0正常 1停用）最大长度要小于 1")
    private String status;
    
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;
}
