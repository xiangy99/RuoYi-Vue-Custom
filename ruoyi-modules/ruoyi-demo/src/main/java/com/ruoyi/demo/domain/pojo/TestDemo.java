package com.ruoyi.demo.domain.pojo;

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
@Schema(description = "测试类")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestDemo extends BaseEntity implements Serializable {
    
    /**
     * 主键
     */
    @TableId
    @Schema(description = "主键")
    @NotNull(message = "主键不能为null")
    private Long id;
    
    /**
     * 编码
     */
    @Schema(description = "编码")
    private Long code;
    
    /**
     * 是否默认
     */
    @Schema(description = "是否默认")
    private Boolean isDefault;
    
    /**
     * 描述
     */
    @Schema(description = "描述")
    @Size(max = 255, message = "描述最大长度要小于 255")
    private String des;
    
    /**
     * 状态(0正常 1停用)
     */
    @Schema(description = "状态(0正常 1停用)")
    @Size(max = 1, message = "状态(0正常 1停用)最大长度要小于 1")
    private String status;
    
    /**
     * 版本
     */
    @Schema(description = "版本")
    private Long version;
    
    /**
     * 是否删除
     */
    @Schema(description = "是否删除")
    private Boolean isDeleted;
}