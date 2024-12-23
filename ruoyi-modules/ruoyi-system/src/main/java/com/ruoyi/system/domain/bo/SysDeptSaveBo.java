package com.ruoyi.system.domain.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * $SysDeptSaveBO
 *
 * @author Link
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysDeptSaveBo implements Serializable {
    
    private static final long serialVersionUID = -969216333322608985L;
    
    /**
     * 部门id
     */
    private Long deptId;
    
    /**
     * 父部门id
     */
    private Long parentId;
    
    /**
     * 祖级列表
     */
    @Size(max = 50, message = "祖级列表最大长度要小于 50")
    private String ancestors;
    
    /**
     * 部门名称
     */
    @Size(max = 30, message = "部门名称最大长度要小于 30")
    @NotBlank(message = "部门名称不能为空")
    private String deptName;
    
    /**
     * 显示顺序
     */
    private Integer sort;
    
    /**
     * 负责人
     */
    @Size(max = 20, message = "负责人最大长度要小于 20")
    private String leader;
    
    /**
     * 联系电话
     */
    @Size(max = 11, message = "联系电话最大长度要小于 11")
    private String phone;
    
    /**
     * 邮箱
     */
    @Size(max = 50, message = "邮箱最大长度要小于 50")
    private String email;
    
    /**
     * 部门状态（0正常 1停用）
     */
    @Size(max = 1, message = "部门状态（0正常 1停用）最大长度要小于 1")
    private String status;
}
