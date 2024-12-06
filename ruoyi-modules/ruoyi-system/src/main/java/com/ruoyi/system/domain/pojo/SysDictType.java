package com.ruoyi.system.domain.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.mybatis.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * ${DESCRIPTION}
 *
 * @author Link
 * @date 2024-11-08
 */

/**
 * 字典类型表
 */
@Schema(description = "字典类型表")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SysDictType extends BaseEntity implements Serializable {
    
    /**
     * 字典主键
     */
    @TableId
    @Schema(description = "字典主键")
    @NotNull(message = "字典主键不能为null")
    private Long dictId;
    
    /**
     * 字典名称
     */
    @ExcelProperty(value = "字典名称")
    @Schema(description = "字典名称")
    @Size(max = 100, message = "字典名称最大长度要小于 100")
    @NotBlank(message = "字典名称不能为空")
    private String dictName;
    
    /**
     * 字典类型
     */
    @Schema(description = "字典类型")
    @Size(max = 100, message = "字典类型最大长度要小于 100")
    @NotBlank(message = "字典类型不能为空")
    private String dictType;
    
    /**
     * 是否默认
     */
    @Schema(description = "是否默认")
    private Boolean isDefault;
    
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
    @Size(max = 500, message = "备注最大长度要小于 500")
    private String remark;
}