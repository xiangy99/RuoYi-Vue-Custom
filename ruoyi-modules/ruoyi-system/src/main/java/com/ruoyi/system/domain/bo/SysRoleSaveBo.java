package com.ruoyi.system.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * $SysRoleSaveBO
 *
 * @author Link
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleSaveBo implements Serializable {
    
    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private Long roleId;
    
    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    @Size(max = 30, message = "角色名称最大长度要小于 30")
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
    
    /**
     * 角色权限字符串
     */
    @Schema(description = "角色权限字符串")
    @Size(max = 100, message = "角色权限字符串最大长度要小于 100")
    @NotBlank(message = "角色权限字符串不能为空")
    private String roleKey;
    
    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    @Schema(description = "数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）")
    @Size(max = 1, message = "数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）最大长度要小于 1")
    private String dataScope;
    
    /**
     * 菜单树选择项是否关联显示
     */
    @Schema(description = "菜单树选择项是否关联显示")
    private Boolean menuCheckStrictly;
    
    /**
     * 部门树选择项是否关联显示
     */
    @Schema(description = "部门树选择项是否关联显示")
    private Boolean deptCheckStrictly;
    
    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer sort;
    
    /**
     * 角色状态（0正常 1停用）
     */
    @Schema(description = "角色状态（0正常 1停用）")
    @Size(max = 1, message = "角色状态（0正常 1停用）最大长度要小于 1")
    @NotBlank(message = "角色状态（0正常 1停用）不能为空")
    private String status;
    
    /**
     * 备注
     */
    private String remark;
    
    
    /** 菜单组 */
    private List<Long> menuIdList;
    
    /** 部门组（数据权限） */
    private Long[] deptIdList;
    
    /** 角色菜单权限 */
    private Set<String> permissionList;
}
