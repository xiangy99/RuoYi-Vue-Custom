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
 * 部门表
 */
@Schema(description = "部门表")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SysDept extends BaseEntity implements Serializable {
    
    /**
     * 部门id
     */
    @Schema(description = "部门id")
    @NotNull(message = "部门id不能为null")
    private Long deptId;
    
    /**
     * 父部门id
     */
    @Schema(description = "父部门id")
    private Long parentId;
    
    /**
     * 祖级列表
     */
    @Schema(description = "祖级列表")
    @Size(max = 50, message = "祖级列表最大长度要小于 50")
    private String ancestors;
    
    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    @Size(max = 30, message = "部门名称最大长度要小于 30")
    @NotBlank(message = "部门名称不能为空")
    private String deptName;
    
    /**
     * 负责人
     */
    @Schema(description = "负责人")
    @Size(max = 20, message = "负责人最大长度要小于 20")
    private String leader;
    
    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    @Size(max = 15, message = "联系电话最大长度要小于 15")
    private String phone;
    
    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    @Size(max = 50, message = "邮箱最大长度要小于 50")
    private String email;
    
    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer sort;
    
    /**
     * 部门状态（0正常 1停用）
     */
    @Schema(description = "部门状态（0正常 1停用）")
    @Size(max = 1, message = "部门状态（0正常 1停用）最大长度要小于 1")
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