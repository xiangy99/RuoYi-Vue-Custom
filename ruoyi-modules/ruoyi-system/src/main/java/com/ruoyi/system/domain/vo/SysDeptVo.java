package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.pojo.SysDept;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Link
 * @date 2024-11-08
 */
@Schema(description = "部门表 Vo")
@Getter
@Setter
public class SysDeptVo extends SysDept {
    
    /**
     * 部门组级层级(最多支持3级部门组级层级)
     */
    private Integer ancestorsLevel;
    
}
