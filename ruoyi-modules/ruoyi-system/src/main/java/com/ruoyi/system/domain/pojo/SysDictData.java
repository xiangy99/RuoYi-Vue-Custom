package com.ruoyi.system.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.mybatis.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
 * 字典数据表
 */
@Schema(description = "字典数据表")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SysDictData extends BaseEntity implements Serializable {
    
    /**
     * 字典编码
     */
    @TableId
    @Schema(description = "字典编码")
    @NotNull(message = "字典编码不能为null")
    private Long dictCode;
    
    /**
     * 字典标签
     */
    @Schema(description = "字典标签")
    @Size(max = 100, message = "字典标签最大长度要小于 100")
    private String dictLabel;
    
    /**
     * 字典键值
     */
    @Schema(description = "字典键值")
    @Size(max = 100, message = "字典键值最大长度要小于 100")
    private String dictValue;
    
    /**
     * 字典类型
     */
    @Schema(description = "字典类型")
    @Size(max = 100, message = "字典类型最大长度要小于 100")
    private String dictType;
    
    /**
     * 样式属性（其他样式扩展）
     */
    @Schema(description = "样式属性（其他样式扩展）")
    @Size(max = 100, message = "样式属性（其他样式扩展）最大长度要小于 100")
    private String cssClass;
    
    /**
     * 表格回显样式
     */
    @Schema(description = "表格回显样式")
    @Size(max = 100, message = "表格回显样式最大长度要小于 100")
    private String listClass;
    
    /**
     * 是否默认
     */
    @Schema(description = "是否默认")
    private Boolean isDefault;
    
    /**
     * 字典排序
     */
    @Schema(description = "字典排序")
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
    @Schema(description = "备注")
    @Size(max = 500, message = "备注最大长度要小于 500")
    private String remark;
}