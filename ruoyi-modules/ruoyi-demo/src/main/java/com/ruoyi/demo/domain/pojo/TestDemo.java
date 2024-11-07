package com.ruoyi.demo.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.ruoyi.common.mybatis.domain.BaseEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * ${DESCRIPTION}
 *
 * @author Link
 * @date 2024-11-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestDemo extends BaseEntity {
    
    /**
     * 主键
     */
    @NotNull(message = "主键不能为null")
    private Long id;
    
    /**
     * 编码
     */
    private Long code;
    
    /**
     * 是否默认
     */
    private Boolean isDefault;
    
    /**
     * 描述
     */
    @Size(max = 255, message = "描述最大长度要小于 255")
    private String des;
    
    /**
     * 状态(0正常 1停用)
     */
    @Size(max = 1, message = "状态(0正常 1停用)最大长度要小于 1")
    private String status;
    
    @TableLogic
    private Boolean isDeleted;
    
    /**
     * 版本
     */
    @Version
    private Long version;
}